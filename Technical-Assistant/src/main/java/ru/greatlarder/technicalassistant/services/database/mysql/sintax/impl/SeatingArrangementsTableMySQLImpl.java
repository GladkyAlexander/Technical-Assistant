package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.SeatingArrangementsTableMySQL;

public class SeatingArrangementsTableMySQLImpl implements SeatingArrangementsTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`seating_arrangements` ( id int AUTO_INCREMENT primary key NOT NULL " +
                ", nameSeatingArrangements text" +
                ", nameCompany text" +
                ", urlImageSeatingArrangements mediumblob" +
                ", numberOfParticipants int) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`seating_arrangements` (" +
                " nameSeatingArrangements," +
                " nameCompany," +
                " urlImageSeatingArrangements," +
                " numberOfParticipants) VALUES (?,?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`seating_arrangements` SET " +
                " id = ?," +
                " nameSeatingArrangements = ?," +
                " nameCompany = ?," +
                " urlImageSeatingArrangements = ?," +
                " numberOfParticipants = ? WHERE id = ? ";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`seating_arrangements`";
    }
}
