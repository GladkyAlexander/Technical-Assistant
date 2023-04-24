package ru.great_larder.technical_assistant.database.general;


import android.os.Build;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import ru.great_larder.technical_assistant.domain.Tool;

public class GetToolServiceImpl implements GetToolService{
    @Override
    public Tool getTool(ResultSet resultSet) throws SQLException {
        Tool tool = new Tool();

            tool.setId(resultSet.getInt("id"));
            tool.setToolName(resultSet.getString("toolName"));
            tool.setToolBrand(resultSet.getString("toolBrand"));
            tool.setToolSerialNumber(resultSet.getString("toolSerialNumber"));
            tool.setNameCompanyToolLocation(resultSet.getString("nameCompanyToolLocation"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tool.setStartOfOperation(resultSet.getDate("startOfOperation").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }
        tool.setToolCondition(resultSet.getString("toolCondition"));

        return tool;
    }
}
