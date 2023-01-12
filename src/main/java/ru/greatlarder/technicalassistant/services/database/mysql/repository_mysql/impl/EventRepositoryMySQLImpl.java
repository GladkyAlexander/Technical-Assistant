package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.Events;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.EventRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EventTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EventTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EventRepositoryMySQLImpl implements EventRepositoryMySQL {

    User user;
    @Override
    public Events getEventsForId(User user, Integer id) {
        this.user = user;

        ConnectMySQL connection = new ConnectMySQL(this.user);
        connection.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventTableMySQLImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(eventTableMySQL.SELECT(this.user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getInt("id") == id){
                    return getEvent(connection.resultSetMySQL);
                }
            }
            connection.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQL_DB();
        }
        return null;
    }

    @Override
    public List<Events> getEventsForCompany(User user, String companyName) {
        return null;
    }

    @Override
    public Events getEventsForNameIdDayEventStart(User user, String nameEvent, Integer idDay, String eventStartTime) {
        this.user = user;

        ConnectMySQL connection = new ConnectMySQL(this.user);
        connection.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventTableMySQLImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(eventTableMySQL.SELECT(this.user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("nameEvent").equals(nameEvent)
                && connection.resultSetMySQL.getInt("idDay") == idDay
                && connection.resultSetMySQL.getString("eventStartTime").equals(eventStartTime)){
                    return getEvent(connection.resultSetMySQL);
                }
            }
            connection.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQL_DB();
        }
        return null;
    }

    @Override
    public Integer setEvent(User user, Events events) {
        this.user = user;
        ConnectMySQL connection = new ConnectMySQL(this.user);
        connection.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventTableMySQLImpl();

        try {
            PreparedStatement ps = connection.connectionMySQL.prepareStatement(eventTableMySQL.INSERT(this.user.getNameDB()),  Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, events.getNameEvent());
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
            
            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            connection.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQL_DB();
        }
return null;
    }

    @Override
    public void updateEvent(User user, Events events) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createEventTableMySQL();
        EventTableMySQL eventTableMySQL = new EventTableMySQLImpl();
    
        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(eventTableMySQL.UPDATE(this.user.getNameDB()));
            
            ps.setString(1, events.getNameEvent());
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
            ps.setInt(10, events.getId());
            ps.executeUpdate();
            connectMySQL.closeMySQL_DB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }
    }

    private Events getEvent(ResultSet resultSetMySQL) throws SQLException {
        Events events = new Events();

        events.setId(resultSetMySQL.getInt("id"));
        events.setNameEvent(resultSetMySQL.getString("nameEvent"));
        events.setUrlImageEvent(resultSetMySQL.getString("urlImageEvent"));
        events.setIdSeatingArrangements(resultSetMySQL.getInt("idSeatingArrangements"));
        events.setLastNameCustomer(resultSetMySQL.getString("lastNameCustomer"));
        events.setFirstNameCustomer(resultSetMySQL.getString("firstNameCustomer"));
        events.setIdDay(resultSetMySQL.getInt("idDay"));
        events.setEventStartTime(resultSetMySQL.getString("eventStartTime"));
        events.setEndTimeOfTheEvent(resultSetMySQL.getString("endTimeOfTheEvent"));
        events.setNote(resultSetMySQL.getString("note"));

        return events;
    }

}
