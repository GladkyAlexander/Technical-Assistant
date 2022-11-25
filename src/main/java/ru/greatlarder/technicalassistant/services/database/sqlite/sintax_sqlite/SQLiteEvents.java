package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteEvents {
    String CREATE_EVENTS = "CREATE TABLE if not exists 'events'(" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'nameEvent' text," +
            " 'urlImageEvent' text," +
            " 'nameCompany' text);";

    String INSERT_EVENTS = "INSERT INTO events (" +
            " nameEvent," +
            " urlImageEvent," +
            " nameCompany)" +
            " VALUES (?,?,?)";

    String READ_EVENTS = "SELECT * FROM events";
}
