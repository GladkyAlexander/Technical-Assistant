package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateTools {
    void updateTool(User user, String nameCompany, Tool tool, Integer id);
}
