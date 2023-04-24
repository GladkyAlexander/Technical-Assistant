package ru.great_larder.technical_assistant.database.mysql.event_format;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.UpdateEventFormat;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.EventTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.EventFormatTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.EventFormat;
import ru.great_larder.technical_assistant.domain.user.User;

public class UpdateEventFormatMySQL implements UpdateEventFormat {
    @Override
    public void updateEvent(User user, EventFormat events) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventFormatTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(eventTableMySQL.UPDATE(user.getNameDB()));

            ps.setInt(1, events.getId());
            ps.setString(2, events.getNameEventFormat());
            ps.setString(3, events.getUrlImageEvent());
            if(events.getIdSeatingArrangements() != null) {
                ps.setInt(4, events.getIdSeatingArrangements());
            } else {ps.setInt(4, 0);}
            ps.setString(5, events.getLastNameCustomer());
            ps.setString(6, events.getFirstNameCustomer());
            ps.setInt(7, events.getIdDay());
            ps.setString(8, events.getEventStartTime());
            ps.setString(9, events.getEndTimeOfTheEvent());
            ps.setString(10, events.getNote());
            ps.setInt(11, events.getId());
            ps.executeUpdate();
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
}
