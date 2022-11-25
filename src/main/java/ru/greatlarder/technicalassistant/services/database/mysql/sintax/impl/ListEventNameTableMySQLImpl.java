package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListEventNameTableMySQL;

public class ListEventNameTableMySQLImpl implements ListEventNameTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`listEventName` ( id int ," +
                " nameEvent text," +
                " urlImageEvent text," +
                " nameCompany text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`listEventName` (" +
                " id," +
                " nameEvent," +
                " urlImageEvent," +
                " nameCompany) VALUES (?,?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`listEventName` SET " +
                " id = ?," +
                " nameEvent = ?," +
                " urlImageEvent = ?," +
                " nameCompany = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`listEventName`";
    }
}
