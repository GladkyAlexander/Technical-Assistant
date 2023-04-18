package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateNames {
    void updateNames(User user, Names updates);
}
