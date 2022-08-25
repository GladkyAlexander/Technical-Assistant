package ru.greatlarder.technicalassistant.services.db;

import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.sql.*;

public class DBconnect {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void connectionDB() {
        FileManager fileManager = new FileManagerImpl();
        String URL = "jdbc:sqlite:" + fileManager.folderDB() + "\\" + "company.sqlite";
        connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeDB() {
        try {
            if(resultSet != null) resultSet.close();
            if(statement != null) statement.close();
            if(connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createCompanyTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteCompany.CREATE_COMPANY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createEquipmentTable() {
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteEquipment.CREATE_TABLE_EQUIPMENT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createDefectTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteDefect.CREATE_DEFECT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createToolsTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteTools.CREATE_TOOLS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createTaskTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteTask.CREATE_TASK);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createUserTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteUser.CREATE_TABLE_USER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createMailSettingsTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLiteMailSettings.CREATE_TABLE_MAIL_SETTINGS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void createPortTable(){
        connectionDB();
        try {
            statement = connection.createStatement();
            statement.execute(SQLitePort.CREATE_TABLE_PORT);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
