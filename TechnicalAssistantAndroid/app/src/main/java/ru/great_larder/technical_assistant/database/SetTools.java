package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Tool;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetTools {
    Integer setTool(User user, String nameCompany, Tool tool);
}
