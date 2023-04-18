package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetNames {
    Integer setNames(User user, String nameCompany, Names names);
}
