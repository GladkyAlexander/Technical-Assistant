package ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl;

import ru.greatlarder.technicalassistant.services.database.mysql.sintax.DaysTableMySQL;

public class DaysTableMySQLImpl implements DaysTableMySQL {
    @Override
    public String CREATE(String nameDB) {
        return "CREATE TABLE if not exists`" + nameDB + "`.`days` ( id int AUTO_INCREMENT primary key NOT NULL , " +
                "room text, " +
                "date date, " +
                "time800 int, " +
                "time815 int, " +
                "time830 int, " +
                "time845 int, " +
                "time900 int, " +
                "time915 int, " +
                "time930 int, " +
                "time945 int, " +
                "time1000 int, " +
                "time1015 int, " +
                "time1030 int, " +
                "time1045 int, " +
                "time1100 int, " +
                "time1115 int, " +
                "time1130 int, " +
                "time1145 int, " +
                "time1200 int, " +
                "time1215 int, " +
                "time1230 int, " +
                "time1245 int, " +
                "time1300 int, " +
                "time1315 int, " +
                "time1330 int, " +
                "time1345 int, " +
                "time1400 int, " +
                "time1415 int, " +
                "time1430 int, " +
                "time1445 int, " +
                "time1500 int, " +
                "time1515 int, " +
                "time1530 int, " +
                "time1545 int, " +
                "time1600 int, " +
                "time1615 int, " +
                "time1630 int, " +
                "time1645 int, " +
                "time1700 int, " +
                "time1715 int, " +
                "time1730 int, " +
                "time1745 int, " +
                "time1800 int, " +
                "time1815 int, " +
                "time1830 int, " +
                "time1845 int, " +
                "time1900 int, " +
                "time1915 int, " +
                "time1930 int, " +
                "time1945 int, " +
                "time2000 int) ENGINE = InnoDB";
    }

    @Override
    public String INSERT(String nameDB) {
        return "INSERT INTO `" + nameDB + "`.`days` (" +
                "room, " +
                "date, " +
                "time800, " +
                "time815, " +
                "time830, " +
                "time845, " +
                "time900, " +
                "time915, " +
                "time930, " +
                "time945, " +
                "time1000, " +
                "time1015, " +
                "time1030, " +
                "time1045, " +
                "time1100, " +
                "time1115, " +
                "time1130, " +
                "time1145, " +
                "time1200, " +
                "time1215, " +
                "time1230, " +
                "time1245, " +
                "time1300, " +
                "time1315, " +
                "time1330, " +
                "time1345, " +
                "time1400, " +
                "time1415, " +
                "time1430, " +
                "time1445, " +
                "time1500, " +
                "time1515, " +
                "time1530, " +
                "time1545, " +
                "time1600, " +
                "time1615, " +
                "time1630, " +
                "time1645, " +
                "time1700, " +
                "time1715, " +
                "time1730, " +
                "time1745, " +
                "time1800, " +
                "time1815, " +
                "time1830, " +
                "time1845, " +
                "time1900, " +
                "time1915, " +
                "time1930, " +
                "time1945, " +
                "time2000)" +
                "            VALUES (?,?,?,?,?,?,?,?,?,?" +
                "                   ,?,?,?,?,?,?,?,?,?,?" +
                "                   ,?,?,?,?,?,?,?,?,?,?" +
                "                   ,?,?,?,?,?,?,?,?,?,?" +
                "                   ,?,?,?,?,?,?,?,?,?,?" +
                "               ,?)";
    }

    @Override
    public String UPDATE(String nameDB) {
        return "UPDATE `" + nameDB + "`.`days` SET " +
                "id = ? ," +
                "room = ? , " +
                "date = ? , " +
                "time800 = ?, " +
                "time815 = ?, " +
                "time830 = ?, " +
                "time845 = ?, " +
                "time900 = ?, " +
                "time915 = ?, " +
                "time930 = ?, " +
                "time945 = ?, " +
                "time1000 = ?, " +
                "time1015 = ?, " +
                "time1030 = ?, " +
                "time1045 = ?, " +
                "time1100 = ?, " +
                "time1115 = ?, " +
                "time1130 = ?, " +
                "time1145 = ?, " +
                "time1200 = ?, " +
                "time1215 = ?, " +
                "time1230 = ?, " +
                "time1245 = ?, " +
                "time1300 = ?, " +
                "time1315 = ?, " +
                "time1330 = ?, " +
                "time1345 = ?, " +
                "time1400 = ?, " +
                "time1415 = ?, " +
                "time1430 = ?, " +
                "time1445 = ?, " +
                "time1500 = ?, " +
                "time1515 = ?, " +
                "time1530 = ?, " +
                "time1545 = ?, " +
                "time1600 = ?, " +
                "time1615 = ?, " +
                "time1630 = ?, " +
                "time1645 = ?, " +
                "time1700 = ?, " +
                "time1715 = ?, " +
                "time1730 = ?, " +
                "time1745 = ?, " +
                "time1800 = ?, " +
                "time1815 = ?, " +
                "time1830 = ?, " +
                "time1845 = ?, " +
                "time1900 = ?, " +
                "time1915 = ?, " +
                "time1930 = ?, " +
                "time1945 = ?, " +
                "time2000 = ? WHERE id = ?";
    }

    @Override
    public String SELECT(String nameDB) {
        return "SELECT * FROM `" + nameDB + "`.`days`";
    }

}
