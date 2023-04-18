package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListRoom {
    List<Room> getListRoom(User user, String nameCompany, String value);
}
