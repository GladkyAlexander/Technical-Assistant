package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.SeatingArrangements;

public interface GetSeatingsArrangementsService {
    SeatingArrangements getSeating(ResultSet resultSet) throws SQLException;
}
