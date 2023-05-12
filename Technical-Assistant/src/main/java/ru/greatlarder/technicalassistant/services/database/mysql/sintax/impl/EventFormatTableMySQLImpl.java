package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EventTableMySQL;

public class EventFormatTableMySQLImpl implements EventTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`events` ( id int AUTO_INCREMENT primary key NOT NULL " +
                ", nameEvent text" +
                ", urlImageEvent mediumblob" +
                ", idSeatingArrangements int" +
                ", lastNameCustomer text" +
                ", firstNameCustomer text" +
                ", idDay int" +
                ", eventStartTime text" +
                ", endTimeOfTheEvent text" +
                ", note text" +
                ", nameCompany text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`events` (" +
                " nameEvent," +
                " urlImageEvent," +
                " idSeatingArrangements," +
                " lastNameCustomer," +
                " firstNameCustomer," +
                " idDay," +
                " eventStartTime," +
                " endTimeOfTheEvent," +
                " note," +
                " nameCompany) VALUES (?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`events` SET " +
                " id = ?," +
                " nameEvent = ?," +
                " urlImageEvent = ?," +
                " idSeatingArrangements = ?," +
                " lastNameCustomer = ?," +
                " firstNameCustomer = ?," +
                " idDay = ?," +
                " eventStartTime = ?," +
                " endTimeOfTheEvent = ?," +
                " note = ?," +
                " nameCompany WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`events`";
    }

}
