package ru.greatlarder.technicalassistant.services.database.mysql.day;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.DaysTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.DaysTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.SetDay;

import java.sql.*;

public class SetDayMySQL implements SetDay {
    @Override
    public Integer setDay(User user, Company company, Day day) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();

        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(daysTableMySQL.INSERT(user.getNameDB()),  Statement.RETURN_GENERATED_KEYS);
            cf.setString(1, day.getRoom());
            cf.setDate(2, Date.valueOf(day.getDate()));
            if (day.getA800() != null) {
                cf.setInt(3, day.getA800());
            } else cf.setInt(3, 0);
            if (day.getA815() != null) {
                cf.setInt(4, day.getA815());
            } else cf.setInt(4, 0);
            if (day.getA830() != null) {
                cf.setInt(5, day.getA830());
            } else cf.setInt(5, 0);
            if (day.getA845() != null) {
                cf.setInt(6, day.getA845());
            } else cf.setInt(6, 0);
            if (day.getA900() != null) {
                cf.setInt(7, day.getA900());
            } else cf.setInt(7, 0);
            if (day.getA915() != null) {
                cf.setInt(8, day.getA915());
            }else cf.setInt(8, 0);
            if (day.getA930() != null) {
                cf.setInt(9, day.getA930());
            }else  cf.setInt(9, 0);
            if (day.getA945() != null) {
                cf.setInt(10, day.getA945());
            } else cf.setInt(10, 0);
            if (day.getA1000() != null) {
                cf.setInt(11, day.getA1000());
            }else cf.setInt(11, 0);
            if (day.getA1015() != null) {
                cf.setInt(12, day.getA1015());
            } else cf.setInt(12, 0);
            if (day.getA1030() != null) {
                cf.setInt(13, day.getA1030());
            } else cf.setInt(13, 0);
            if (day.getA1045() != null) {
                cf.setInt(14, day.getA1045());
            } else cf.setInt(14, 0);
            if (day.getA1100() != null) {
                cf.setInt(15, day.getA1100());
            }else cf.setInt(15, 0);
            if (day.getA1115() != null) {
                cf.setInt(16, day.getA1115());
            } else cf.setInt(16, 0);
            if (day.getA1130() != null) {
                cf.setInt(17, day.getA1130());
            } else cf.setInt(17, 0);
            if (day.getA1145() != null) {
                cf.setInt(18, day.getA1145());
            } else cf.setInt(18, 0);
            if (day.getA1200() != null) {
                cf.setInt(19, day.getA1200());
            } else cf.setInt(19, 0);
            if (day.getA1215() != null) {
                cf.setInt(20, day.getA1215());
            } else cf.setInt(20, 0);
            if (day.getA1230() != null) {
                cf.setInt(21, day.getA1230());
            } else cf.setInt(21, 0);
            if (day.getA1245() != null) {
                cf.setInt(22, day.getA1245());
            } else cf.setInt(22, 0);
            if (day.getA1300() != null) {
                cf.setInt(23, day.getA1300());
            } else cf.setInt(23, 0);
            if (day.getA1315() != null) {
                cf.setInt(24, day.getA1315());
            } else cf.setInt(24, 0);
            if (day.getA1330() != null) {
                cf.setInt(25, day.getA1330());
            } else cf.setInt(25, 0);
            if (day.getA1345() != null) {
                cf.setInt(26, day.getA1345());
            } else cf.setInt(26, 0);
            if (day.getA1400() != null) {
                cf.setInt(27, day.getA1400());
            } else cf.setInt(27, 0);
            if (day.getA1415() != null) {
                cf.setInt(28, day.getA1415());
            }else cf.setInt(28, 0);
            if (day.getA1430() != null) {
                cf.setInt(29, day.getA1430());
            } else cf.setInt(29, 0);
            if (day.getA1445() != null) {
                cf.setInt(30, day.getA1445());
            } else cf.setInt(30, 0);
            if (day.getA1500() != null) {
                cf.setInt(31, day.getA1500());
            } else cf.setInt(31, 0);
            if (day.getA1515() != null) {
                cf.setInt(32, day.getA1515());
            } else cf.setInt(32, 0);
            if (day.getA1530() != null) {
                cf.setInt(33, day.getA1530());
            } else cf.setInt(33, 0);
            if (day.getA1545() != null) {
                cf.setInt(34, day.getA1545());
            } else cf.setInt(34, 0);
            if (day.getA1600() != null) {
                cf.setInt(35, day.getA1600());
            } else cf.setInt(35, 0);
            if (day.getA1615() != null) {
                cf.setInt(36, day.getA1615());
            } else cf.setInt(36, 0);
            if (day.getA1630() != null) {
                cf.setInt(37, day.getA1630());
            } else cf.setInt(37, 0);
            if (day.getA1645() != null) {
                cf.setInt(38, day.getA1645());
            } else cf.setInt(38, 0);
            if (day.getA1700() != null) {
                cf.setInt(39, day.getA1700());
            } else cf.setInt(39, 0);
            if (day.getA1715() != null) {
                cf.setInt(40, day.getA1715());
            } else cf.setInt(40, 0);
            if (day.getA1730() != null) {
                cf.setInt(41, day.getA1730());
            } else cf.setInt(41, 0);
            if (day.getA1745() != null) {
                cf.setInt(42, day.getA1745());
            } else cf.setInt(42, 0);
            if (day.getA1800() != null) {
                cf.setInt(43, day.getA1800());
            } else cf.setInt(43, 0);
            if (day.getA1815() != null) {
                cf.setInt(44, day.getA1815());
            } else cf.setInt(44, 0);
            if (day.getA1830() != null) {
                cf.setInt(45, day.getA1830());
            } else cf.setInt(45, 0);
            if (day.getA1845() != null) {
                cf.setInt(46, day.getA1845());
            } else cf.setInt(46, 0);
            if (day.getA1900() != null) {
                cf.setInt(47, day.getA1900());
            } else cf.setInt(47, 0);
            if (day.getA1915() != null) {
                cf.setInt(48, day.getA1915());
            } else cf.setInt(48, 0);
            if (day.getA1930() != null) {
                cf.setInt(49, day.getA1930());
            } else cf.setInt(49, 0);
            if (day.getA1945() != null) {
                cf.setInt(50, day.getA1945());
            } else cf.setInt(50, 0);
            if (day.getA2000() != null) {
                cf.setInt(51, day.getA2000());
            } else cf.setInt(51, 0);

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }

            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectMySQL.closeMySQLDatabase();
        }
        return null;
    }
}
