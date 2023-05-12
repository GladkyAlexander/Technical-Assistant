package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.RoomTableMySQL;

public class RoomTableMySQLImpl implements RoomTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`room` ( id int AUTO_INCREMENT primary key NOT NULL" +
                ", nameRoom text" +
                ", image mediumblob" +
                ", nameCompany text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`room` (" +
                " nameRoom," +
                " image," +
                " nameCompany) VALUES (?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`room` SET " +
                " nameRoom = ?," +
                " image = ?," +
                " nameCompany = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`room`";
    }
}
