package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteRoom {
    String CREATE_ROOM = "CREATE TABLE if not exists 'room'(" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'nameRoom' text," +
            " 'nameCompanyForRoom');";

    String INSERT_ROOM = "INSERT INTO room (" +
            " nameRoom," +
            " nameCompanyForRoom)" +
            " VALUES (?,?)";

    String READ_ROOM = "SELECT * FROM room";
}
