package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface ListRoomNameRepositoryMySQL {
    void setRoomName(Room room);
    void setRoomNameList(User user, List<Room> room);
    List<Room> getRoomsNameList(User user, String companyName);
    Room getRoomsName(String companyName, String roomName);
    void updateRoomName(Room room);
    void updateRoomNameList(List<Room> rooms);
}
