package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Affairs;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetAffairsService {
    Affairs getAffairs(ResultSet resultSet) throws SQLException;
}
