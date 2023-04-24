package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetNames {
    Names getNames(User user, String nameCompany, String value);
}
