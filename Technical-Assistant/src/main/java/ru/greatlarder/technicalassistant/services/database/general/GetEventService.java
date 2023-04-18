package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.EventFormat;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetEventService {
    EventFormat getEvent(ResultSet resultSetMySQL) throws SQLException;
}
