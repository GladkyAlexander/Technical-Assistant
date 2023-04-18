package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Day;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetDayService {
    Day getDay(ResultSet resultSet) throws SQLException;
}
