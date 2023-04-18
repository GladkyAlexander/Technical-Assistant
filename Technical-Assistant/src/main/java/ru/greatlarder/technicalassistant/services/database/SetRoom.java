package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetRoom {
    Integer setRoom(User user, String nameCompany, Room room);
}
