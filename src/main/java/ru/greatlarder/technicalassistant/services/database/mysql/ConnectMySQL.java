package ru.greatlarder.technicalassistant.services.database.mysql;

import ru.greatlarder.technicalassistant.domain.User;
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
    EventTableMySQL eventTableMySQL = new EventTableMySQLImpl();
    RoomTableMySQL roomTableMySQL = new RoomTableMySQLImpl();
    SeatingArrangementsTableMySQL seatingArrangementsTableMySQL = new SeatingArrangementsTableMySQLImpl();
    ListEventNameTableMySQL listEventNameTableMySQL = new ListEventNameTableMySQLImpl();
    ListRoomNameTableMySQL listRoomNameTableMySQL = new ListRoomNameTableMySQLImpl();
    ListSeatingArrangementsNameTableMySQL listSeatingArrangementsNameTableMySQL = new ListSeatingArrangementsNameTableMySQLImpl();
    DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();


    public ConnectMySQL(User user) {
        this.user = user;
    }

    public void connectionMySQL() {
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
    public void closeMySQL_DB(){
        try {
            if(resultSetMySQL != null) resultSetMySQL.close();
            if(statementMySQL != null) statementMySQL.close();
            if (connectionMySQL != null) connectionMySQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createCompanyTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(companyTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createEquipmentTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(equipmentTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createEventTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(eventTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createRoomTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(roomTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createSeatingArrangementsTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(seatingArrangementsTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createListEventNameTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(listEventNameTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createListRoomNameTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(listRoomNameTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createListSeatingArrangementsNameTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(listSeatingArrangementsNameTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createDaysTableMySQL(){
        connectionMySQL();
        try {
            statementMySQL = connectionMySQL.createStatement();
            statementMySQL.execute(daysTableMySQL.CREATE(user.getNameDB()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
