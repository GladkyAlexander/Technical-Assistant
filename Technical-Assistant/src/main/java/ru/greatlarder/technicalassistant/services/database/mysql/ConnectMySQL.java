package ru.greatlarder.technicalassistant.services.database.mysql;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.*;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

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
