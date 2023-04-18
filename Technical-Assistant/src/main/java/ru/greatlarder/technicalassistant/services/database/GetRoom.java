package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetRoom {
    Room getRoom(User user, String nameCompany, String value);
}
