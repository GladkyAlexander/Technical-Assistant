package ru.greatlarder.technicalassistant.services.database.mysql.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EventTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EventFormatTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetEventService;
import ru.greatlarder.technicalassistant.services.database.general.GetEventServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetEventFormat;

import java.sql.SQLException;
import java.util.Objects;

public class GetEventFormatByIdMySQL implements GetEventFormat {
    @Override
    public EventFormat getEventFormat(User user, String nameCompany, String idEventFormat) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventFormatTableMySQLImpl();
        GetEventService getEventService = new GetEventServiceImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(eventTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(Objects.equals(connection.resultSetMySQL.getInt("id"), Integer.valueOf(idEventFormat))){
                    return getEventService.getEvent(connection.resultSetMySQL);
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
