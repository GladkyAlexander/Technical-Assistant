package ru.greatlarder.technicalassistant.services.db;

public interface SQLiteCompany {
    String CREATE_COMPANY = "CREATE TABLE if not exists 'company' (" +
            " 'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'nameCompany' text," +
            " 'address' text," +
            " 'curator' text," +
            " 'phoneCurator' text," +
            " 'mailCurator' text," +
            " 'websiteCompany' text," +
            " 'logoCompany' text," +
            " 'manager' text," +
            " 'phoneManager' text," +
            " 'mailManager' text," +
            " 'engineer' text," +
            " 'phoneEngineer' text," +
            " 'mailEngineer' text);";

    String INSERT_COMPANY = "INSERT INTO company (" +
            " nameCompany," +
            " address," +
            " curator," +
            " phoneCurator," +
            " mailCurator," +
            " websiteCompany," +
            " logoCompany," +
            " manager," +
            " phoneManager," +
            " mailManager," +
            " engineer," +
            " phoneEngineer," +
            " mailEngineer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    String READE_COMPANY = "SELECT * FROM company";
}
