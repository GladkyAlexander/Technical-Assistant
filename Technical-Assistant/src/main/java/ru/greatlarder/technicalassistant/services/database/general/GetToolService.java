package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Tool;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetToolService {
    Tool getTool(ResultSet resultSet) throws SQLException;
}
