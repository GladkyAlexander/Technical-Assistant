package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Names;

import java.sql.ResultSet;
import java.sql.SQLException;

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
