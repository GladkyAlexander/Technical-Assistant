package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetToolServiceImpl implements GetToolService{
    @Override
    public Tool getTool(ResultSet resultSet) throws SQLException {
        Tool tool = new Tool();

            tool.setId(resultSet.getInt("id"));
            tool.setToolName(resultSet.getString("toolName"));
            tool.setToolBrand(resultSet.getString("toolBrand"));
            tool.setToolSerialNumber(resultSet.getString("toolSerialNumber"));
            tool.setNameCompanyToolLocation(resultSet.getString("nameCompanyToolLocation"));
            tool.setStartOfOperation(resultSet.getDate("startOfOperation").toLocalDate());
            tool.setToolCondition(resultSet.getString("toolCondition"));

        return tool;
    }
}
