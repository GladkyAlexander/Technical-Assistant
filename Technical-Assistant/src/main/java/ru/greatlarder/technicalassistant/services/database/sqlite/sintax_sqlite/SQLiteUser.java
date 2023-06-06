package ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite;

public interface SQLiteUser {
    String CREATE_TABLE_USER = "CREATE TABLE if not exists 'user' (" +
            "    'id'                         INTEGER  PRIMARY KEY AUTOINCREMENT primary key NOT NULL, " +
            "    'lastName'                   TEXT," +
            "    'firstName'                  TEXT," +
            "    'mailAddress'                TEXT," +
            "    'phone'                      TEXT," +
            "    'post'                       TEXT," +
            "    'companyAffiliation'         TEXT," +
            "    'language'                   TEXT," +
            "    'login'                      TEXT  NOT NULL," +
            "    'password'                   TEXT  NOT NULL," +
            "    'server'                     TEXT," +
            "    'port'                       TEXT," +
            "    'nameDB'                     TEXT," +
            "    'userDB'                     TEXT," +
            "    'passwordDB'                 TEXT," +
            "    'serverFTP'                  TEXT," +
            "    'portFTP'                    INTEGER," +
            "    'userFTP'                    TEXT," +
            "    'passwordFTP'                TEXT);";

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
            "    passwordDB," +
            "    serverFTP," +
            "    portFTP," +
            "    userFTP," +
            "    passwordFTP) " +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String UPDATE_USER = "UPDATE 'user' SET " +
            "    lastName = ?," +
            "    firstName = ?," +
            "    mailAddress = ?," +
            "    phone = ?," +
            "    post = ?," +
            "    companyAffiliation = ?," +
            "    language = ?," +
            "    login = ?," +
            "    password = ?," +
            "    server = ?," +
            "    port = ?," +
            "    nameDB = ?," +
            "    userDB = ?," +
            "    passwordDB = ?," +
            "    serverFTP = ?," +
            "    portFTP = ?," +
            "    userFTP = ?," +
            "    passwordFTP = ? WHERE id = ? ";

    String READ_TABLE_USER = "SELECT * FROM user";
}
