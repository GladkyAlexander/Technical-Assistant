package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.DaysRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.DaysTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.DaysTableMySQLImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaysRepositoryMySQLImpl implements DaysRepositoryMySQL {
    private User user;
    @Override
    public List<Day> getDaysForRoom(User user, String roomName) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        List<Day> daysFromBD = new ArrayList<>();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(roomName)){
                    daysFromBD.add(getDays(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
        return daysFromBD;
    }

    @Override
    public Integer setDaysForRoom(User user, Day day) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();

        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(daysTableMySQL.INSERT(this.user.getNameDB()),  Statement.RETURN_GENERATED_KEYS);
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
            
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectMySQL.closeMySQL_DB();
        }
        return null;
    }

    @Override
    public Day getDayForName(User user, Day day) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(day.getRoom())
                && connectMySQL.resultSetMySQL.getString("date").equals(day.getDate().toString())){
                    return getDays(connectMySQL.resultSetMySQL);
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
        return null;
    }

    @Override
    public Day getDaysForRoomByDate(User user, LocalDate date, String nameRoom) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(nameRoom)
                && connectMySQL.resultSetMySQL.getDate("date").toLocalDate().equals(date)){
                    return getDays(connectMySQL.resultSetMySQL);
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
        return null;
    }

    @Override
    public boolean checkDayByName(User user, Day day) {

        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(day.getRoom())
                        && connectMySQL.resultSetMySQL.getString("date").equals(day.getDate().toString())){
                    return true;
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
        return false;
    }

    @Override
    public boolean checkDayById(User user, Integer idDay) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getInt("id") == idDay){
                    return true;
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
        return false;
    }

    @Override
    public void updateDay(User user, Day day) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();

        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(daysTableMySQL.UPDATE(this.user.getNameDB()));
            cf.setInt(1, day.getId());
            cf.setString(2, day.getRoom());
            cf.setDate(3, Date.valueOf(day.getDate()));
            if (day.getA800() != null) {
                cf.setInt(4, day.getA800());
            } else cf.setInt(4, 0);
            if (day.getA815() != null) {
                cf.setInt(5, day.getA815());
            } else cf.setInt(5, 0);
            if (day.getA830() != null) {
                cf.setInt(6, day.getA830());
            } else cf.setInt(6, 0);
            if (day.getA845() != null) {
                cf.setInt(7, day.getA845());
            } else cf.setInt(7, 0);
            if (day.getA900() != null) {
                cf.setInt(8, day.getA900());
            } else cf.setInt(8, 0);
            if (day.getA915() != null) {
                cf.setInt(9, day.getA915());
            }else cf.setInt(9, 0);
            if (day.getA930() != null) {
                cf.setInt(10, day.getA930());
            }else  cf.setInt(10, 0);
            if (day.getA945() != null) {
                cf.setInt(11, day.getA945());
            } else cf.setInt(11, 0);
            if (day.getA1000() != null) {
                cf.setInt(12, day.getA1000());
            }else cf.setInt(12, 0);
            if (day.getA1015() != null) {
                cf.setInt(13, day.getA1015());
            } else cf.setInt(13, 0);
            if (day.getA1030() != null) {
                cf.setInt(14, day.getA1030());
            } else cf.setInt(14, 0);
            if (day.getA1045() != null) {
                cf.setInt(15, day.getA1045());
            } else cf.setInt(15, 0);
            if (day.getA1100() != null) {
                cf.setInt(16, day.getA1100());
            }else cf.setInt(16, 0);
            if (day.getA1115() != null) {
                cf.setInt(17, day.getA1115());
            } else cf.setInt(17, 0);
            if (day.getA1130() != null) {
                cf.setInt(18, day.getA1130());
            } else cf.setInt(18, 0);
            if (day.getA1145() != null) {
                cf.setInt(19, day.getA1145());
            } else cf.setInt(19, 0);
            if (day.getA1200() != null) {
                cf.setInt(20, day.getA1200());
            } else cf.setInt(20, 0);
            if (day.getA1215() != null) {
                cf.setInt(21, day.getA1215());
            } else cf.setInt(21, 0);
            if (day.getA1230() != null) {
                cf.setInt(22, day.getA1230());
            } else cf.setInt(22, 0);
            if (day.getA1245() != null) {
                cf.setInt(23, day.getA1245());
            } else cf.setInt(23, 0);
            if (day.getA1300() != null) {
                cf.setInt(24, day.getA1300());
            } else cf.setInt(24, 0);
            if (day.getA1315() != null) {
                cf.setInt(25, day.getA1315());
            } else cf.setInt(25, 0);
            if (day.getA1330() != null) {
                cf.setInt(26, day.getA1330());
            } else cf.setInt(26, 0);
            if (day.getA1345() != null) {
                cf.setInt(27, day.getA1345());
            } else cf.setInt(27, 0);
            if (day.getA1400() != null) {
                cf.setInt(28, day.getA1400());
            } else cf.setInt(28, 0);
            if (day.getA1415() != null) {
                cf.setInt(29, day.getA1415());
            }else cf.setInt(29, 0);
            if (day.getA1430() != null) {
                cf.setInt(30, day.getA1430());
            } else cf.setInt(30, 0);
            if (day.getA1445() != null) {
                cf.setInt(31, day.getA1445());
            } else cf.setInt(31, 0);
            if (day.getA1500() != null) {
                cf.setInt(32, day.getA1500());
            } else cf.setInt(32, 0);
            if (day.getA1515() != null) {
                cf.setInt(33, day.getA1515());
            } else cf.setInt(33, 0);
            if (day.getA1530() != null) {
                cf.setInt(34, day.getA1530());
            } else cf.setInt(34, 0);
            if (day.getA1545() != null) {
                cf.setInt(35, day.getA1545());
            } else cf.setInt(35, 0);
            if (day.getA1600() != null) {
                cf.setInt(36, day.getA1600());
            } else cf.setInt(36, 0);
            if (day.getA1615() != null) {
                cf.setInt(37, day.getA1615());
            } else cf.setInt(37, 0);
            if (day.getA1630() != null) {
                cf.setInt(38, day.getA1630());
            } else cf.setInt(38, 0);
            if (day.getA1645() != null) {
                cf.setInt(39, day.getA1645());
            } else cf.setInt(39, 0);
            if (day.getA1700() != null) {
                cf.setInt(40, day.getA1700());
            } else cf.setInt(40, 0);
            if (day.getA1715() != null) {
                cf.setInt(41, day.getA1715());
            } else cf.setInt(41, 0);
            if (day.getA1730() != null) {
                cf.setInt(42, day.getA1730());
            } else cf.setInt(42, 0);
            if (day.getA1745() != null) {
                cf.setInt(43, day.getA1745());
            } else cf.setInt(43, 0);
            if (day.getA1800() != null) {
                cf.setInt(44, day.getA1800());
            } else cf.setInt(44, 0);
            if (day.getA1815() != null) {
                cf.setInt(45, day.getA1815());
            } else cf.setInt(45, 0);
            if (day.getA1830() != null) {
                cf.setInt(46, day.getA1830());
            } else cf.setInt(46, 0);
            if (day.getA1845() != null) {
                cf.setInt(47, day.getA1845());
            } else cf.setInt(47, 0);
            if (day.getA1900() != null) {
                cf.setInt(48, day.getA1900());
            } else cf.setInt(48, 0);
            if (day.getA1915() != null) {
                cf.setInt(49, day.getA1915());
            } else cf.setInt(49, 0);
            if (day.getA1930() != null) {
                cf.setInt(50, day.getA1930());
            } else cf.setInt(50, 0);
            if (day.getA1945() != null) {
                cf.setInt(51, day.getA1945());
            } else cf.setInt(51, 0);
            if (day.getA2000() != null) {
                cf.setInt(52, day.getA2000());
            } else cf.setInt(52, 0);

            cf.setInt(53, day.getId());

            cf.executeUpdate();
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }

    }

    private Day getDays(ResultSet resultSetMySQL) throws SQLException {
        Day day = new Day();
        day.setId(resultSetMySQL.getInt("id"));
        day.setDate(resultSetMySQL.getDate("date").toLocalDate());
        day.setRoom(resultSetMySQL.getString("room"));
        day.setA800(resultSetMySQL.getInt("time800"));
        day.setA815(resultSetMySQL.getInt("time815"));
        day.setA830(resultSetMySQL.getInt("time830"));
        day.setA845(resultSetMySQL.getInt("time845"));
        day.setA900(resultSetMySQL.getInt("time900"));
        day.setA915(resultSetMySQL.getInt("time915"));
        day.setA930(resultSetMySQL.getInt("time930"));
        day.setA945(resultSetMySQL.getInt("time945"));
        day.setA1000(resultSetMySQL.getInt("time1000"));
        day.setA1015(resultSetMySQL.getInt("time1015"));
        day.setA1030(resultSetMySQL.getInt("time1030"));
        day.setA1045(resultSetMySQL.getInt("time1045"));
        day.setA1100(resultSetMySQL.getInt("time1100"));
        day.setA1115(resultSetMySQL.getInt("time1115"));
        day.setA1130(resultSetMySQL.getInt("time1130"));
        day.setA1145(resultSetMySQL.getInt("time1145"));
        day.setA1200(resultSetMySQL.getInt("time1200"));
        day.setA1215(resultSetMySQL.getInt("time1215"));
        day.setA1230(resultSetMySQL.getInt("time1230"));
        day.setA1245(resultSetMySQL.getInt("time1245"));
        day.setA1300(resultSetMySQL.getInt("time1300"));
        day.setA1315(resultSetMySQL.getInt("time1315"));
        day.setA1330(resultSetMySQL.getInt("time1330"));
        day.setA1345(resultSetMySQL.getInt("time1345"));
        day.setA1400(resultSetMySQL.getInt("time1400"));
        day.setA1415(resultSetMySQL.getInt("time1415"));
        day.setA1430(resultSetMySQL.getInt("time1430"));
        day.setA1445(resultSetMySQL.getInt("time1445"));
        day.setA1500(resultSetMySQL.getInt("time1500"));
        day.setA1515(resultSetMySQL.getInt("time1515"));
        day.setA1530(resultSetMySQL.getInt("time1530"));
        day.setA1545(resultSetMySQL.getInt("time1545"));
        day.setA1600(resultSetMySQL.getInt("time1600"));
        day.setA1615(resultSetMySQL.getInt("time1615"));
        day.setA1630(resultSetMySQL.getInt("time1630"));
        day.setA1645(resultSetMySQL.getInt("time1645"));
        day.setA1700(resultSetMySQL.getInt("time1700"));
        day.setA1715(resultSetMySQL.getInt("time1715"));
        day.setA1730(resultSetMySQL.getInt("time1730"));
        day.setA1745(resultSetMySQL.getInt("time1745"));
        day.setA1800(resultSetMySQL.getInt("time1800"));
        day.setA1815(resultSetMySQL.getInt("time1815"));
        day.setA1830(resultSetMySQL.getInt("time1830"));
        day.setA1845(resultSetMySQL.getInt("time1845"));
        day.setA1900(resultSetMySQL.getInt("time1900"));
        day.setA1915(resultSetMySQL.getInt("time1915"));
        day.setA1930(resultSetMySQL.getInt("time1930"));
        day.setA1945(resultSetMySQL.getInt("time1945"));
        day.setA2000(resultSetMySQL.getInt("time2000"));

        return day;
    }

}
