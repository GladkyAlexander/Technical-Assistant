package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Names;

public interface GetNamesService {
    Names getNames(ResultSet resultSet) throws SQLException;
}
