package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Tool;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetTools {
    Tool getTool(User user, String nameCompany, String value);
}
