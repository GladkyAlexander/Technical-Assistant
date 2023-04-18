package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Day;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetDayServiceImpl implements GetDayService{
    @Override
    public Day getDay(ResultSet resultSet) throws SQLException {
        Day day = new Day();
        day.setId(resultSet.getInt("id"));
        day.setDate(resultSet.getDate("date").toLocalDate());
        day.setRoom(resultSet.getString("room"));
        day.setA800(resultSet.getInt("time800"));
        day.setA815(resultSet.getInt("time815"));
        day.setA830(resultSet.getInt("time830"));
        day.setA845(resultSet.getInt("time845"));
        day.setA900(resultSet.getInt("time900"));
        day.setA915(resultSet.getInt("time915"));
        day.setA930(resultSet.getInt("time930"));
        day.setA945(resultSet.getInt("time945"));
        day.setA1000(resultSet.getInt("time1000"));
        day.setA1015(resultSet.getInt("time1015"));
        day.setA1030(resultSet.getInt("time1030"));
        day.setA1045(resultSet.getInt("time1045"));
        day.setA1100(resultSet.getInt("time1100"));
        day.setA1115(resultSet.getInt("time1115"));
        day.setA1130(resultSet.getInt("time1130"));
        day.setA1145(resultSet.getInt("time1145"));
        day.setA1200(resultSet.getInt("time1200"));
        day.setA1215(resultSet.getInt("time1215"));
        day.setA1230(resultSet.getInt("time1230"));
        day.setA1245(resultSet.getInt("time1245"));
        day.setA1300(resultSet.getInt("time1300"));
        day.setA1315(resultSet.getInt("time1315"));
        day.setA1330(resultSet.getInt("time1330"));
        day.setA1345(resultSet.getInt("time1345"));
        day.setA1400(resultSet.getInt("time1400"));
        day.setA1415(resultSet.getInt("time1415"));
        day.setA1430(resultSet.getInt("time1430"));
        day.setA1445(resultSet.getInt("time1445"));
        day.setA1500(resultSet.getInt("time1500"));
        day.setA1515(resultSet.getInt("time1515"));
        day.setA1530(resultSet.getInt("time1530"));
        day.setA1545(resultSet.getInt("time1545"));
        day.setA1600(resultSet.getInt("time1600"));
        day.setA1615(resultSet.getInt("time1615"));
        day.setA1630(resultSet.getInt("time1630"));
        day.setA1645(resultSet.getInt("time1645"));
        day.setA1700(resultSet.getInt("time1700"));
        day.setA1715(resultSet.getInt("time1715"));
        day.setA1730(resultSet.getInt("time1730"));
        day.setA1745(resultSet.getInt("time1745"));
        day.setA1800(resultSet.getInt("time1800"));
        day.setA1815(resultSet.getInt("time1815"));
        day.setA1830(resultSet.getInt("time1830"));
        day.setA1845(resultSet.getInt("time1845"));
        day.setA1900(resultSet.getInt("time1900"));
        day.setA1915(resultSet.getInt("time1915"));
        day.setA1930(resultSet.getInt("time1930"));
        day.setA1945(resultSet.getInt("time1945"));
        day.setA2000(resultSet.getInt("time2000"));

        return day;
    }
}
