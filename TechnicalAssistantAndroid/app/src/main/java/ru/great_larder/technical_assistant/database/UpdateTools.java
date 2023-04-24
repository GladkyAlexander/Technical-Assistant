package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Tool;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateTools {
    void updateTool(User user, String nameCompany, Tool tool, Integer id);
}
