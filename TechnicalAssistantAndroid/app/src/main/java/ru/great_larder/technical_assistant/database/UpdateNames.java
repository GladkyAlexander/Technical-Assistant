package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateNames {
    void updateNames(User user, Names updates);
}
