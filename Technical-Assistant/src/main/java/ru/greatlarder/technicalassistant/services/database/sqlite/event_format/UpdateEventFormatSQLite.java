package ru.greatlarder.technicalassistant.services.database.sqlite.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateEventFormat;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEvents;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdateEventFormatSQLite implements UpdateEventFormat {
    @Override
    public void updateEvent(User user, EventFormat events) {
        createEventTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteEvents.UPDATE_EVENTS);

            cf.setString(1, events.getNameEventFormat());
            cf.setString(2, events.getUrlImageEvent());
            cf.setString(3, events.getNameCompany());

            cf.setInt(4, events.getId());

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
