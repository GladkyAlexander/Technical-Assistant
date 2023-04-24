package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.EventFormat;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetEventFormat {
    Integer setEventFormat(User user, EventFormat events);
}
