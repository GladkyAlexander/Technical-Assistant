package ru.great_larder.technical_assistant.database.mysql.event_format;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.great_larder.technical_assistant.database.GetListEventFormat;
import ru.great_larder.technical_assistant.database.general.GetEventService;
import ru.great_larder.technical_assistant.database.general.GetEventServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.EventTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.EventFormatTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.EventFormat;
import ru.great_larder.technical_assistant.domain.user.User;

public class ListEventFormatByCompanyNameMySQL implements GetListEventFormat {
    @Override
    public List<EventFormat> getListEventFormat(User user, String nameCompany, String value) {
        List<EventFormat> eventFormats = new ArrayList<>();
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventFormatTableMySQLImpl();
        GetEventService getEventService = new GetEventServiceImpl();
        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(eventTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("nameCompany").equals(nameCompany)){
                    eventFormats.add(getEventService.getEvent(connection.resultSetMySQL));
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return eventFormats;
    }
}
