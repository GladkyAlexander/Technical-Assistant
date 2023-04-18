package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetEventFormat {
    Integer setEventFormat(User user, EventFormat events);
}
