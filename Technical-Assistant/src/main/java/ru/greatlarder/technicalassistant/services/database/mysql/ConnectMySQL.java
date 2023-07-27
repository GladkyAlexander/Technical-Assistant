package ru.greatlarder.technicalassistant.services.database.mysql;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.*;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.*;
import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageWarningsImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Objects;

public class ConnectMySQL {
    public Connection connectionMySQL;
    public Statement statementMySQL;
    public ResultSet resultSetMySQL;

    User user;
    CompanyTableMySQL companyTableMySQL = new CompanyTableMySQLImpl();
    EquipmentTableMySQL equipmentTableMySQL = new EquipmentTableMySQLImpl();
    EventTableMySQL eventTableMySQL = new EventFormatTableMySQLImpl();
    RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();
    SeatingArrangementsTableMySQL seatingArrangementsTableMySQL = new SeatingArrangementsTableMySQLImpl();
    DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
    BookingEquipmentTableMySQL bookingEquipmentTableMySQL = new BookingEquipmentTableMySQLImpl();
    NamesTableMySQL namesTableMySQL = new NamesTableMySQLImpl();

    public ConnectMySQL(User user) {
        this.user = user;
    }

    public void connectionDatabaseMySQL() {
        if(user.getServer() != null && user.getPort() != null && user.getPasswordDB() != null && user.getNameDB() != null){
            String server = user.getServer();
            String port = user.getPort();
            String password = user.getPasswordDB();
            String nameDB = user.getNameDB();
            connectionMySQL = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                connectionMySQL = DriverManager.getConnection("jdbc:mysql://" + server + ":" + port ,  nameDB , password);
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            LanguageWarnings languageWarnings = new LanguageWarningsImpl();
            alert.setTitle(languageWarnings.there_is_no_data_about_the_external_database(user.getLanguage()));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            alert.setContentText(languageWarnings.there_is_no_data_about_the_external_database(user.getLanguage()));
            alert.showAndWait();
        }
    }
    public void closeMySQLDatabase(){
        try {
            if(resultSetMySQL != null) resultSetMySQL.close();
            if(statementMySQL != null) statementMySQL.close();
            if (connectionMySQL != null) connectionMySQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCompanyTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(companyTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createEquipmentTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(equipmentTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createEventTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(eventTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createRoomTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(roomTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createSeatingArrangementsTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(seatingArrangementsTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDaysTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(daysTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createBookingEquipmentTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(bookingEquipmentTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createNamesTableMySQL(){
        connectionDatabaseMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(namesTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
