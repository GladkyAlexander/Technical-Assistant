package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteTask {
    String CREATE_TASK = "CREATE TABLE if not exists 'task'(" +
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

    String INSERT_TASK = "INSERT INTO task (" +
            "dateOfCreation," +
            " timeOfCreation," +
            " closingDate," +
            " closingTime," +
            " creator," +
            " room," +
            " executor," +
            " textTask," +
            " status," +
            " nameCompany) VALUES (?,?,?,?,?,?,?,?,?,?)";

    String READ_TASK = "SELECT * FROM task";
}
