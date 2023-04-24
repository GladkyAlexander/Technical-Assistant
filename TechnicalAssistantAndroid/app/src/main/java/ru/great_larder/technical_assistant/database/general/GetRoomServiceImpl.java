package ru.great_larder.technical_assistant.database.general;

import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Room;

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
