package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Room;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListRoom {
    List<Room> getListRoom(User user, String nameCompany, String value);
}
