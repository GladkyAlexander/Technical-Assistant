package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateEventFormat {
    void updateEvent(User user, EventFormat events);
}
