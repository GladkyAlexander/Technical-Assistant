package ru.greatlarder.technicalassistant.services.db.sqlite;

public interface SQLiteUser {
    String CREATE_TABLE_USER = "CREATE TABLE if not exists 'user' (" +
            "    'id'                                INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            "    'lastName'                   TEXT," +
            "    'firstName'                  TEXT," +
            "    'mailAddress'                TEXT," +
            "    'phone'                      TEXT," +
            "    'post'                       TEXT," +
            "    'language'                   TEXT);";

    String INSERT_TABLE_USER = "INSERT INTO user ( " +
            "    lastName," +
            "    firstName," +
            "    mailAddress," +
            "    phone," +
            "    post," +
            "    language) " +
            "VALUES (?,?,?,?,?,?)";

    String READ_TABLE_user = "SELECT * FROM user";
}
