package ru.greatlarder.technicalassistant.services.database.mysql.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetEventFormat;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EventTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EventFormatTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SetEventFormatMySQL implements SetEventFormat {
    @Override
    public Integer setEventFormat(User user, EventFormat events) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventFormatTableMySQLImpl();
        try {
            PreparedStatement ps = connection.connectionMySQL.prepareStatement(eventTableMySQL.INSERT(user.getNameDB()),  Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, events.getNameEventFormat());
            ps.setString(2, events.getUrlImageEvent());
            if(events.getIdSeatingArrangements() != null) {
                ps.setInt(3, events.getIdSeatingArrangements());
            } else {ps.setInt(3, 0);}
            ps.setString(4, events.getLastNameCustomer());
            ps.setString(5, events.getFirstNameCustomer());
            ps.setInt(6, events.getIdDay());
            ps.setString(7, events.getEventStartTime());
            ps.setString(8, events.getEndTimeOfTheEvent());
            ps.setString(9, events.getNote());
            ps.setString(10, events.getNameCompany());

            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return null;
    }
}
