package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.Room;

import java.util.List;

public interface RoomsRepository {
    List<Room> getListRoomForCompany(String nameCompany);
    Room getRoomForName(String nameRoom);
    void setRoom(Room room);
    void changeRoom(Room room, String value, String column);


}
