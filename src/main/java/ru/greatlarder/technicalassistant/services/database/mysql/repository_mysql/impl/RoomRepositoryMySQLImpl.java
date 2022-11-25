package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.*;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.ListRoomNameTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.ListRoomNameTableMySQLImpl;

import java.sql.SQLException;
import java.util.List;

public class RoomRepositoryMySQLImpl implements RoomRepositoryMySQL {
    User user;
    @Override
    public List<Room> getListRoomForCompany(User user, String companyName) {
        this.user = user;
        ListRoomNameRepositoryMySQL listRoomNameRepositoryMySQL = new ListRoomNameRepositoryMySQLImpl();

        List<Room> nameRoom = listRoomNameRepositoryMySQL.getRoomsNameList(this.user, companyName);
        for (Room room : nameRoom){
            getLoadRoom(room);
        }

        return nameRoom;
    }

    @Override
    public Room getRoomByName(User user, String companyName, String roomName) {
        this.user = user;
        ConnectMySQL connectMySQL = new ConnectMySQL(this.user);
        connectMySQL.createListRoomNameTableMySQL();
        ListRoomNameTableMySQL listRoomNameTableMySQL = new ListRoomNameTableMySQLImpl();
        Room room = new Room();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(listRoomNameTableMySQL.SELECT(this.user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()) {
                if(connectMySQL.resultSetMySQL.getString("nameRoom").equals(roomName)){

                    room.setId(connectMySQL.resultSetMySQL.getInt("id"));
                    room.setNameRoom(connectMySQL.resultSetMySQL.getString("nameRoom"));
                    room.setNameCompanyForRoom(connectMySQL.resultSetMySQL.getString("nameCompany"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQL_DB();
        }

        return getLoadRoom(room);
    }

    @Override
    public void setRoom(Room room) {

    }

    @Override
    public void updateRoom(Room room) {

    }
    private Room getLoadRoom(Room room){
        EquipmentRepositoryMySQL equipmentRepositoryMySQL = new EquipmentRepositoryMySQLImpl();
        room.setEquipmentList(equipmentRepositoryMySQL.getEquipmentByRoomName(user, user.getCompanyAffiliation(), room.getNameRoom()));

        EventRepositoryMySQL eventRepositoryMySQL = new EventRepositoryMySQLImpl();
        room.setEventsList(eventRepositoryMySQL.getEventsForCompany(user, user.getCompanyAffiliation()));

        SeatingArrangementsRepositoryMySQL seatingArrangementsRepositoryMySQL = new SeatingArrangementsRepositoryMySQLImpl();
        room.setSeatingArrangementsList(seatingArrangementsRepositoryMySQL.getSeatingArrangementsList(user, user.getCompanyAffiliation()));

        DaysRepositoryMySQL daysRepositoryMySQL = new DaysRepositoryMySQLImpl();
        room.setDayList(daysRepositoryMySQL.getDaysForRoom(user, room.getNameRoom()));

        return room;
    }
}
