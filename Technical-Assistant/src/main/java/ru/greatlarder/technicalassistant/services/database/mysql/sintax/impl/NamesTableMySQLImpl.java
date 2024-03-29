package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.NamesTableMySQL;

public class NamesTableMySQLImpl implements NamesTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`names` ( id int AUTO_INCREMENT primary key NOT NULL " +
                ", names text" +
                ", nameCompany text" +
                ", url mediumblob" +
                ", domain text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`names` (" +
                " names," +
                " nameCompany," +
                " url," +
                " domain) VALUES (?,?,?,?)";
    }

    @Override
    public String UPDATE_BY_NAME(String nameDB) {
        return "UPDATE `" + nameDB + "`.`names` SET " +
                " names = ?," +
                " nameCompany = ?," +
                " url = ?," +
                " domain = ? WHERE names = ? ";
    }
    
    @Override
    public String UPDATE_BY_ID(String nameDB) {
        return "UPDATE `" + nameDB + "`.`names` SET " +
            " names = ?," +
            " nameCompany = ?," +
            " url = ?," +
            " domain = ? WHERE id = ? ";
    }
    
    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`names`";
    }
}
