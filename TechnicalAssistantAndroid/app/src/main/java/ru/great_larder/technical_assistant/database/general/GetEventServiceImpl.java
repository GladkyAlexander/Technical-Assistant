package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.EventFormat;

public class GetEventServiceImpl implements GetEventService{
    @Override
    public EventFormat getEvent(ResultSet resultSetMySQL) throws SQLException {
        EventFormat events = new EventFormat();

        events.setId(resultSetMySQL.getInt("id"));
        events.setNameEventFormat(resultSetMySQL.getString("nameEvent"));
        events.setUrlImageEvent(resultSetMySQL.getString("urlImageEvent"));
        events.setIdSeatingArrangements(resultSetMySQL.getInt("idSeatingArrangements"));
        events.setLastNameCustomer(resultSetMySQL.getString("lastNameCustomer"));
        events.setFirstNameCustomer(resultSetMySQL.getString("firstNameCustomer"));
        events.setIdDay(resultSetMySQL.getInt("idDay"));
        events.setEventStartTime(resultSetMySQL.getString("eventStartTime"));
        events.setEndTimeOfTheEvent(resultSetMySQL.getString("endTimeOfTheEvent"));
        events.setNote(resultSetMySQL.getString("note"));
        events.setNameCompany(resultSetMySQL.getString("nameCompany"));

        return events;
    }
}
