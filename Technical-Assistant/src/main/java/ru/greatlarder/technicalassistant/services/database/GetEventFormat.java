package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetEventFormat {
    EventFormat getEventFormat(User user, String nameCompany, String value);
}
