package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListRoomNameTableMySQL;

public class ListRoomNameTableMySQLImpl implements ListRoomNameTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`listRoomName` ( id int ," +
                " nameRoom text," +
                " nameCompany text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`listRoomName` (" +
                " id," +
                " nameRoom," +
                " nameCompany) VALUES (?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`listRoomName` SET " +
                " id = ?," +
                " nameRoom = ?," +
                " nameCompany = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`listRoomName`";
    }
}
