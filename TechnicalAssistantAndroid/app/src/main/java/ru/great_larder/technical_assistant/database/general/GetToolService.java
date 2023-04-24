package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Tool;

public interface GetToolService {
    Tool getTool(ResultSet resultSet) throws SQLException;
}
