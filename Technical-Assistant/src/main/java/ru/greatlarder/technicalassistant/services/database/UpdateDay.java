package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateDay {
    void updateDay(User user, Day day);
}
