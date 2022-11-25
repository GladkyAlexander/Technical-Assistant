package ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.RoomsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteRoom;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class RoomsRepositoryImpl implements RoomsRepository {
    @Override
    public List<Room> getListRoomForCompany(String nameCompany) {
        List<Room> rooms = new ArrayList<Room>();
        createRoomTable();
        try {
            resultSet = statement.executeQuery(SQLiteRoom.READ_ROOM);
            while (resultSet.next()){
                if(resultSet.getString("nameCompanyForRoom").equals(nameCompany)){
                    Room room = new Room();
                    room.setId(resultSet.getInt("id"));
                    room.setNameRoom(resultSet.getString("nameRoom"));
                    room.setNameCompanyForRoom(resultSet.getString("nameCompanyForRoom"));
                    rooms.add(room);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return rooms;
    }
    @Override
    public Room getRoomForName(String nameRoom) {

        Room room = new Room();
        createRoomTable();

        try {
            resultSet = statement.executeQuery(SQLiteRoom.READ_ROOM);

            while (resultSet.next()){
                if(resultSet.getString("nameRoom").equals(nameRoom)){
                    room.setId(resultSet.getInt("id"));
                    room.setNameRoom(resultSet.getString("nameRoom"));
                    room.setNameCompanyForRoom(resultSet.getString("nameCompanyForRoom"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return room;
    }
    @Override
    public void setRoom(Room room) {
        createRoomTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteRoom.INSERT_ROOM);

            cf.setString(1, room.getNameRoom());
            cf.setString(2, room.getNameCompanyForRoom());

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
    @Override
    public void changeRoom(Room room, String value, String column) {
        createRoomTable();
        String scl = "UPDATE room SET " + column + " = ? WHERE id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(scl);
            ps.setString(1, value);
            ps.setInt(2, room.getId());

            ps.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

}
