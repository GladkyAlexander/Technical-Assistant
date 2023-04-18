package ru.greatlarder.technicalassistant.services.database.mysql.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EventTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EventFormatTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.UpdateEventFormat;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
