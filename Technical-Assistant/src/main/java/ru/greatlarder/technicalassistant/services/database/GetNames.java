package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetNames {
    Names getNames(User user, String nameCompany, String value);
}
