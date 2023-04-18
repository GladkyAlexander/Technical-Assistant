package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Names;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetNamesService {
    Names getNames(ResultSet resultSet) throws SQLException;
}
