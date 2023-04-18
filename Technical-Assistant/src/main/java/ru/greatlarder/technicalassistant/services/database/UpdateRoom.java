package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateRoom {
    void updateRoom(User user, Room room, Integer Id);
}
