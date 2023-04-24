package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Day;

public interface GetDayService {
    Day getDay(ResultSet resultSet) throws SQLException;
}
