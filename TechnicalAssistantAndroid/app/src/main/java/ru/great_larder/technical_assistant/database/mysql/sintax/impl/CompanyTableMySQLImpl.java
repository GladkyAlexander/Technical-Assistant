package ru.great_larder.technical_assistant.database.mysql.sintax.impl;


import ru.great_larder.technical_assistant.database.mysql.sintax.CompanyTableMySQL;

public class CompanyTableMySQLImpl implements CompanyTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`company` (id int" +
                ", nameCompany text" +
                ", address text" +
                ", curatorLastName text" +
                ", curatorFirstName text" +
                ", phoneCurator text" +
                ", mailCurator text" +
                ", websiteCompany text" +
                ", logoCompany text" +
                ", managerLastName text" +
                ", managerFirstName text" +
                ", phoneManager text" +
                ", mailManager text" +
                ", engineerLastName text" +
                ", engineerFirstName text" +
                ", phoneEngineer text" +
                ", mailEngineer text) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`company` (" +
                " id," +
                " nameCompany," +
                " address," +
                " curatorLastName," +
                " curatorFirstName," +
                " phoneCurator," +
                " mailCurator," +
                " websiteCompany," +
                " logoCompany," +
                " managerLastName," +
                " managerFirstName," +
                " phoneManager," +
                " mailManager," +
                " engineerLastName," +
                " engineerFirstName," +
                " phoneEngineer," +
                " mailEngineer) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`company` SET " +
                " id = ?," +
                " nameCompany = ?," +
                " address = ?," +
                " curatorLastName = ?," +
                " curatorFirstName = ?," +
                " phoneCurator = ?," +
                " mailCurator = ?," +
                " websiteCompany = ?," +
                " logoCompany = ?," +
                " managerLastName = ?," +
                " managerFirstName = ?," +
                " phoneManager = ?," +
                " mailManager = ?," +
                " engineerLastName = ?," +
                " engineerFirstName = ?," +
                " phoneEngineer = ?," +
                " mailEngineer = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`company`";
    }
}
