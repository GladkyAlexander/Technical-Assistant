package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteAffairs {
    String CREATE_AFFAIRS = "CREATE TABLE if not exists 'task'(" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'dateOfCreation' DATE," +
            " 'timeOfCreation' TIME," +
            " 'closingDate' DATE," +
            " 'closingTime' TIME," +
            " 'creator' TEXT," +
            " 'room' TEXT," +
            " 'executor' TEXT," +
            " 'textTask' TEXT," +
            " 'status' INTEGER," +
            " 'nameCompany' TEXT);";

    String INSERT_AFFAIRS = "INSERT INTO task (" +
            " dateOfCreation," +
            " timeOfCreation," +
            " closingDate," +
            " closingTime," +
            " creator," +
            " room," +
            " executor," +
            " textTask," +
            " status," +
            " nameCompany) VALUES (?,?,?,?,?,?,?,?,?,?)";

    String UPDATE_AFFAIRS = "UPDATE 'task' SET " +
            " dateOfCreation = ?," +
            " timeOfCreation = ?," +
            " closingDate = ?," +
            " closingTime = ?," +
            " creator = ?," +
            " room = ?," +
            " executor = ?," +
            " textTask = ?," +
            " status = ?," +
            " nameCompany = ?  WHERE id = ?";

    String READ_AFFAIRS = "SELECT * FROM task";
}
