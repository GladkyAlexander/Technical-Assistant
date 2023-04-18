package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetTools {
    Tool getTool(User user, String nameCompany, String value);
}
