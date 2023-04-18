package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteSettingsExternalDatabase {
    String CREATE_TABLE_SETTINGS_EXTERNAL_DATABASE = "CREATE TABLE if not exists 'settings_external_database' (" +
            "    'id'                                INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            "    'server'                   TEXT," +
            "    'port'                  TEXT," +
            "    'nameDB'                TEXT," +
            "    'user'                      TEXT," +
            "    'password'                       TEXT);";

    String INSERT_TABLE_SETTINGS_EXTERNAL_DATABASE = "INSERT INTO settings_external_database ( " +
            "    server," +
            "    port," +
            "    nameDB," +
            "    user," +
            "    password) " +
            "VALUES (?,?,?,?,?)";

    String READ_TABLE_SETTINGS_EXTERNAL_DATABASE = "SELECT * FROM settings_external_database";
}
