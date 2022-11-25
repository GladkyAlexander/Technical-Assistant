package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.Events;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.ListEventNameRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListEventNameTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.ListEventNameTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListEventNameRepositoryMySQLImpl implements ListEventNameRepositoryMySQL {
    User user;
    ConnectMySQL connectMySQL;

    @Override
    public void setEventName(User user, Events events) {

    }

    @Override
    public void setEventNameList(User user, List<Events> events, String nameCompany) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createListEventNameTableMySQL();
        ListEventNameTableMySQL listEventNameTableMySQL = new ListEventNameTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(listEventNameTableMySQL.INSERT(this.user.getNameDB()));

            for (Events events1 : events ){
                ps.setInt(1, events1.getId());
                ps.setString(2, events1.getNameEvent());
                ps.setString(3, events1.getUrlImageEvent());
                ps.setString(4, nameCompany);
                ps.executeUpdate();
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
    }

    @Override
    public List<String> getEventsNameList(User user, String companyName) {
        this.user = user;
        List<Events> eventsList = new ArrayList<Events>();
        List<String> result = new ArrayList<String>();
        connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createListRoomNameTableMySQL();
        ListEventNameTableMySQL listEventNameTableMySQL = new ListEventNameTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(listEventNameTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if (companyName.equals(connectMySQL.resultSetMySQL.getString("nameCompany"))){
                    eventsList.add(getEvent(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }

        for(Events events : eventsList){
            result.add(events.getNameEvent());
        }
        return result;
    }

    @Override
    public void updateEventName(User user, Events events) {

    }

    @Override
    public void updateEventNameList(User user, List<Events> events) {

    }

    private Events getEvent(ResultSet resultSetMySQL) throws SQLException {
        Events events = new Events();
        events.setNameEvent(resultSetMySQL.getString("nameEvent"));
        events.setUrlImageEvent(resultSetMySQL.getString("urlImageEvent"));
        events.setNameRoom(resultSetMySQL.getString("nameCompany"));
        return events;
    }

}
