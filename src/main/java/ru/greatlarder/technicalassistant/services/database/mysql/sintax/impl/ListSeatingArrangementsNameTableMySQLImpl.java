package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListSeatingArrangementsNameTableMySQL;

public class ListSeatingArrangementsNameTableMySQLImpl implements ListSeatingArrangementsNameTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`listSeatingArrangementsName` ( id int ," +
                " seatingArrangements text," +
                " nameCompany text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`listSeatingArrangementsName` (" +
                " id," +
                " seatingArrangements," +
                " nameCompany) VALUES (?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`listSeatingArrangementsName` SET " +
                " id = ?," +
                " seatingArrangements = ?," +
                " nameCompany = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`listSeatingArrangementsName`";
    }
}
