package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetTools {
    Integer setTool(User user, String nameCompany, Tool tool);
}
