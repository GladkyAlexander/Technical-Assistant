package ru.greatlarder.technicalassistant.services.db;

public interface SQLiteTools {
    String CREATE_TOOLS = "CREATE TABLE if not exists 'tools'( " +
            "'id' INTEGER PRIMARY KEY AUTOINCREMENT," +
            " 'toolName' text," +
            " 'toolBrand' text," +
            " 'toolSerialNumber' text," +
            " 'nameCompanyToolLocation' text," +
            " 'startOfOperation' date," +
            " 'toolCondition' text);";

    String INSERT_TOOLS = "INSERT INTO tools (" +
            "toolName," +
            " toolBrand," +
            " toolSerialNumber," +
            " nameCompanyToolLocation," +
            " startOfOperation," +
            " toolCondition) VALUES (?,?,?,?,?,?)";

    String READ_TOOLS = "SELECT * FROM tools";
}
