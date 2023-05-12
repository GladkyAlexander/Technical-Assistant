package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteRoom {
    String CREATE_ROOM = "CREATE TABLE if not exists 'room'(" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'nameRoom' text," +
            " 'nameCompany' text," +
            " 'image' text);";

    String INSERT_ROOM = "INSERT INTO room (" +
            " nameRoom," +
            " nameCompany," +
            " image)" +
            " VALUES (?,?,?)";

    String UPDATE_ROOM = "UPDATE 'room' SET " +
            "   nameRoom = ?, " +
            "   nameCompany = ?, " +
            "   image = ? WHERE id = ? ";

    String READ_ROOM = "SELECT * FROM room";
}
