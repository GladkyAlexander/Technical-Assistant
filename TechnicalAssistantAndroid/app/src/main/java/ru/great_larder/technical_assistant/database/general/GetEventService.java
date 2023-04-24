package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.EventFormat;

public interface GetEventService {
    EventFormat getEvent(ResultSet resultSetMySQL) throws SQLException;
}
