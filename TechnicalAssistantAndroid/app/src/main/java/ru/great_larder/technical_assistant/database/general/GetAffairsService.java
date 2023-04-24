package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Affairs;

public interface GetAffairsService {
    Affairs getAffairs(ResultSet resultSet) throws SQLException;
}
