package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Affairs;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAffairsServiceImpl implements GetAffairsService{
    @Override
    public Affairs getAffairs(ResultSet resultSet) throws SQLException {
        Affairs task = new Affairs();
        task.setId(resultSet.getInt("id"));
        task.setDateOfCreation(resultSet.getDate("dateOfCreation").toLocalDate());
        task.setTimeOfCreation(resultSet.getTime("timeOfCreation").toLocalTime());
        if (resultSet.getDate("closingDate") != null) {
            task.setClosingDate(resultSet.getDate("closingDate").toLocalDate());
        }
        if(resultSet.getTime("closingTime") != null) {
            task.setClosingTime(resultSet.getTime("closingTime").toLocalTime());
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
