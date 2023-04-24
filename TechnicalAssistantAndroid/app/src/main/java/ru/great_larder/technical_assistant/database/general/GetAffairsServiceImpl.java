package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import ru.great_larder.technical_assistant.domain.Affairs;

public class GetAffairsServiceImpl implements GetAffairsService{
    @Override
    public Affairs getAffairs(ResultSet resultSet) throws SQLException {
        Affairs task = new Affairs();
        task.setId(resultSet.getInt("id"));
        task.setDateOfCreation(resultSet.getDate("dateOfCreation").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        task.setTimeOfCreation(resultSet.getTime("timeOfCreation").toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
        if (resultSet.getDate("closingDate") != null) {
                task.setClosingDate(resultSet.getDate("closingDate").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        if(resultSet.getTime("closingTime") != null) {
                task.setClosingTime(resultSet.getTime("closingTime").toInstant().atZone(ZoneId.systemDefault()).toLocalTime());
        }
        task.setCreator(resultSet.getString("creator"));
        task.setRoom(resultSet.getString("room"));
        task.setExecutor(resultSet.getString("executor"));
        task.setTextTask(resultSet.getString("textTask"));
        task.setStatus(resultSet.getInt("status"));
        task.setNameCompany(resultSet.getString("nameCompany"));
        return task;
    }
}
