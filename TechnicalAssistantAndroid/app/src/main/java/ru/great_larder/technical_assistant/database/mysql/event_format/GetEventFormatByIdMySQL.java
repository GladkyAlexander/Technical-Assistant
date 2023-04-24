package ru.great_larder.technical_assistant.database.mysql.event_format;

import java.sql.SQLException;
import java.util.Objects;

import ru.great_larder.technical_assistant.database.GetEventFormat;
import ru.great_larder.technical_assistant.database.general.GetEventService;
import ru.great_larder.technical_assistant.database.general.GetEventServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.EventTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.EventFormatTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.EventFormat;
import ru.great_larder.technical_assistant.domain.user.User;

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
