package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetRoom {
    Room getRoom(User user, String nameCompany, String value);
}