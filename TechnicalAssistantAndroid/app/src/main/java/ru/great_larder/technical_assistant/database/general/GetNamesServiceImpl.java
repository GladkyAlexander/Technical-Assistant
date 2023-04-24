package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Names;

public class GetNamesServiceImpl implements GetNamesService{
    @Override
    public Names getNames(ResultSet resultSet) throws SQLException {
        Names names = new Names();

        names.setId(resultSet.getInt("id"));
        names.setNames(resultSet.getString("names"));
        names.setNameCompany(resultSet.getString("nameCompany"));
        names.setUrl(resultSet.getString("url"));
        names.setDomain(resultSet.getString("domain"));

        return names;
    }
}
