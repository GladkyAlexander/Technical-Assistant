package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface RoomRepositoryMySQL {
    List<Room> getListRoomForCompany(User user, String companyName);
    Room getRoomByName(User user, String companyName, String roomName);
    void setRoom(Room room);
    void updateRoom(Room room);
}
