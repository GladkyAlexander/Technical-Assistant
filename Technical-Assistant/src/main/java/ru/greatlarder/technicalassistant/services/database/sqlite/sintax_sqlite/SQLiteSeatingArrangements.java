package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteSeatingArrangements {

    String CREATE_SEATING_ARRANGEMENTS = "CREATE TABLE if not exists 'seating_arrangements'(" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'nameSeatingArrangements' text," +
            " 'nameCompany' text," +
            " 'urlImageSeatingArrangements' text);";

    String INSERT_SEATING_ARRANGEMENTS = "INSERT INTO seating_arrangements (" +
            " nameSeatingArrangements," +
            " nameCompany," +
            " urlImageSeatingArrangements)" +
            " VALUES (?,?,?)";

    String UPDATE_SEATING_ARRANGEMENTS = "UPDATE 'seating_arrangements' SET " +
            "            nameSeatingArrangements = ?," +
            "            nameCompany = ?," +
            "            urlImageSeatingArrangements = ? WHERE id = ?;";
    String READ_SEATING_ARRANGEMENTS = "SELECT * FROM seating_arrangements";

}
