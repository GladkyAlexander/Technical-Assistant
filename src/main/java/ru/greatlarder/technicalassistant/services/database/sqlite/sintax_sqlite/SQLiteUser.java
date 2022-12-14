package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteUser {
    String CREATE_TABLE_USER = "CREATE TABLE if not exists 'user' (" +
            "    'id'                                INTEGER  PRIMARY KEY AUTOINCREMENT, " +
            "    'lastName'                   TEXT," +
            "    'firstName'                  TEXT," +
            "    'mailAddress'                TEXT," +
            "    'phone'                      TEXT," +
            "    'post'                       TEXT," +
            "    'companyAffiliation'                       TEXT," +
            "    'language'                   TEXT," +
            "    'login'                      TEXT  NOT NULL," +
            "    'password'                   TEXT  NOT NULL," +
            "    'server'                     TEXT," +
            "    'port'                       TEXT," +
            "    'nameDB'                     TEXT," +
            "    'userDB'                     TEXT," +
            "    'passwordDB'                 TEXT);";

    String INSERT_TABLE_USER = "INSERT INTO user ( " +
            "    lastName," +
            "    firstName," +
            "    mailAddress," +
            "    phone," +
            "    post," +
            "    companyAffiliation," +
            "    language," +
            "    login," +
            "    password," +
            "    server," +
            "    port," +
            "    nameDB," +
            "    userDB," +
            "    passwordDB) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String READ_TABLE_USER = "SELECT * FROM user";
}
