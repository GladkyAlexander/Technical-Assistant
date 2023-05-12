package ru.greatlarder.technicalassistant.services.database.sqlite.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetEventFormat;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEvents;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetEventFormatSQLite implements SetEventFormat {
    @Override
    public Integer setEventFormat(User user, EventFormat events) {
        Integer idEventFormat = null;
        createEventTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteEvents.INSERT_EVENTS, Statement.RETURN_GENERATED_KEYS);

            cf.setString(1, events.getNameEventFormat());
            cf.setString(2, events.getUrlImageEvent());
            cf.setString(10, events.getNameCompany());
            
            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idEventFormat = rs.getInt(1);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return idEventFormat;
    }
}
