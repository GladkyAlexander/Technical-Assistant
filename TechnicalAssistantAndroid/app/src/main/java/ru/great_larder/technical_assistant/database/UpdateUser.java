package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateUser {
    User changeUser(User user);
}