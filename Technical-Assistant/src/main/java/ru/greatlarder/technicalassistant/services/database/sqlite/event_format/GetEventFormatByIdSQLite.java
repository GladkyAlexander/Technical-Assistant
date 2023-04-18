package ru.greatlarder.technicalassistant.services.database.sqlite.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetEventFormat;
import ru.greatlarder.technicalassistant.services.database.general.GetEventService;
import ru.greatlarder.technicalassistant.services.database.general.GetEventServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEvents;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class GetEventFormatByIdSQLite implements GetEventFormat {
    @Override
    public EventFormat getEventFormat(User user, String nameCompany, String id) {
        createEventTable();
        try {
            resultSet = statement.executeQuery(SQLiteEvents.READ_EVENTS);
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)
                        && Objects.equals(resultSet.getInt("id"), Integer.valueOf(id))){
                    GetEventService getEventService = new GetEventServiceImpl();
                    return getEventService.getEvent(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
