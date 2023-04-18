package ru.greatlarder.technicalassistant.services.database.sqlite.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEventFormat;
import ru.greatlarder.technicalassistant.services.database.general.GetEventService;
import ru.greatlarder.technicalassistant.services.database.general.GetEventServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEvents;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetListEventFormatSQLite implements GetListEventFormat {
    @Override
    public List<EventFormat> getListEventFormat(User user, String nameCompany, String value) {
        List<EventFormat> eventsList = new ArrayList<>();
        createEventTable();

        try {
            resultSet = statement.executeQuery(SQLiteEvents.READ_EVENTS);

            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)){
                    GetEventService getEventService = new GetEventServiceImpl();
                    eventsList.add(getEventService.getEvent(resultSet));
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
}
