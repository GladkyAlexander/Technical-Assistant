package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Room;

public interface GetRoomService {
    Room getRoom(ResultSet resultSet) throws SQLException;
}
