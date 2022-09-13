package ru.greatlarder.technicalassistant.services.db.sqlite;

public interface SQLiteCompany {
    String CREATE_COMPANY = "CREATE TABLE if not exists 'company' (" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'nameCompany' text," +
            " 'address' text," +
            " 'curatorLastName' text," +
            " 'curatorFirstName' text," +
            " 'phoneCurator' text," +
            " 'mailCurator' text," +
            " 'websiteCompany' text," +
            " 'logoCompany' text," +
            " 'managerLastName' text," +
            " 'managerFirstName' text," +
            " 'phoneManager' text," +
            " 'mailManager' text," +
            " 'engineerLastName' text," +
            " 'engineerFirstName' text," +
            " 'phoneEngineer' text," +
            " 'mailEngineer' text);";

    String INSERT_COMPANY = "INSERT INTO company (" +
            " nameCompany," +
            " address," +
            " curatorLastName," +
            " curatorFirstName," +
            " phoneCurator," +
            " mailCurator," +
            " websiteCompany," +
            " logoCompany," +
            " managerLastName," +
            " managerFirstName," +
            " phoneManager," +
            " mailManager," +
            " engineerLastName," +
            " engineerFirstName," +
            " phoneEngineer," +
            " mailEngineer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String READE_COMPANY = "SELECT * FROM company";
}
