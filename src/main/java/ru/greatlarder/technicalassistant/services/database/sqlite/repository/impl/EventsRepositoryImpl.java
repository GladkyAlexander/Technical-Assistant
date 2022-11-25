package ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl;

import ru.greatlarder.technicalassistant.domain.Events;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.EventsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEvents;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class EventsRepositoryImpl implements EventsRepository {

    @Override
    public List<Events> getListEventsForCompany(String nameCompany) {
        List<Events> eventsList = new ArrayList<>();
        createEventTable();

        try {
            resultSet = statement.executeQuery(SQLiteEvents.READ_EVENTS);

            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)){
                    Events events = new Events();
                    events.setId(resultSet.getInt("id"));
                    events.setNameEvent(resultSet.getString("nameEvent"));
                    events.setUrlImageEvent(resultSet.getString("urlImageEvent"));
                    events.setNameCompany(resultSet.getString("nameCompany"));
                    eventsList.add(events);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return eventsList;
    }

    @Override
    public Events setEvent(Events events) {
        createEventTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteEvents.INSERT_EVENTS);

            cf.setString(1, events.getNameEvent());
            cf.setString(2, events.getUrlImageEvent());
            cf.setString(3, events.getNameCompany());
            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }

    @Override
    public Events getEventForName(String nameCompany, String nameEvent) {
        createEventTable();
        try {
            resultSet = statement.executeQuery(SQLiteEvents.READ_EVENTS);
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany) && resultSet.getString("nameEvent").equals(nameEvent)){
                    Events events = new Events();
                    events.setId(resultSet.getInt("id"));
                    events.setNameEvent(resultSet.getString("nameEvent"));
                    events.setUrlImageEvent(resultSet.getString("urlImageEvent"));
                    return events;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }

    @Override
    public Events changeEventName(Events events, String value, String column) {
        createEventTable();
        String scl = "UPDATE events SET " + column + " = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(scl);
            ps.setString(1, value);
            ps.setInt(2, events.getId());

            ps.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
