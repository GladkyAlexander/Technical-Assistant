package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetRoomServiceImpl implements GetRoomService {
    @Override
    public Room getRoom(ResultSet resultSet) throws SQLException {
        Room room = new Room();

        room.setId(resultSet.getInt("id"));
        room.setNameRoom(resultSet.getString("nameRoom"));
        room.setNameCompanyForRoom(resultSet.getString("nameCompany"));

        return room;
    }
}
