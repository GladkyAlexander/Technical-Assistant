package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Room;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetRoomService {
    Room getRoom(ResultSet resultSet) throws SQLException;
}
