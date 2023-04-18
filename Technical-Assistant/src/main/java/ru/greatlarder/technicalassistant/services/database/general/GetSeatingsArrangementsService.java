package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetSeatingsArrangementsService {
    SeatingArrangements getSeating(ResultSet resultSet) throws SQLException;
}
