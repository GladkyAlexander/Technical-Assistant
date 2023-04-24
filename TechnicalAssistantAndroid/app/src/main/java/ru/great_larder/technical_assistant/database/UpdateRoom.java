package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateRoom {
    void updateRoom(User user, Room room, Integer Id);
}
