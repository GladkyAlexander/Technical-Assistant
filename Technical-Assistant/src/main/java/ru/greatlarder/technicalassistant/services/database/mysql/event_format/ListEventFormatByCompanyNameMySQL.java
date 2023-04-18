package ru.greatlarder.technicalassistant.services.database.mysql.event_format;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EventTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EventFormatTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetEventService;
import ru.greatlarder.technicalassistant.services.database.general.GetEventServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetListEventFormat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
