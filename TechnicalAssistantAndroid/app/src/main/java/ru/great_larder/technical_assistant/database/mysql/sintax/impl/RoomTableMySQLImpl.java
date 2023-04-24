package ru.great_larder.technical_assistant.database.mysql.sintax.impl;


import ru.great_larder.technical_assistant.database.mysql.sintax.RoomTableMySQL;

public class RoomTableMySQLImpl implements RoomTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`room` ( id int" +
                ", nameRoom text" +
                ", nameCompany text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`room` (" +
                " id," +
                " nameRoom," +
                " nameCompany) VALUES (?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`room` SET " +
                " nameRoom = ?," +
                " nameCompany = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`room`";
    }
}
