package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.user.User;

public interface GetUser {
    User getUser(String value1, String value2);
}
