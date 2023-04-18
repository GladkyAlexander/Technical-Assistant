package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListNames {
    List<Names> getListNames(User user, String nameCompany, String value);
}
