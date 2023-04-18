package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateUser {
    User changeUser(User user);
}
