package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteMailSettings {
    String CREATE_TABLE_MAIL_SETTINGS = "CREATE TABLE if not exists 'mail_settings' (" +
            "    'id'                              INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            "    'mailMonitoring'                  TEXT," +
            "    'passwordMailMonitoring'          TEXT," +
            "    'hostMailMonitoring'              TEXT," +
            "    'subjectOfTheLetter'              TEXT," +
            "    'idUser'                   INTEGER);";

    String INSERT_TABLE_MAIL_SETTINGS = "INSERT INTO mail_settings ( " +
            "    mailMonitoring," +
            "    passwordMailMonitoring," +
            "    hostMailMonitoring," +
            "    subjectOfTheLetter," +
            "    idUser) " +
            "VALUES (?,?,?,?,?)";

    String READ_TABLE_MAIL_SETTINGS = "SELECT * FROM mail_settings";
}
