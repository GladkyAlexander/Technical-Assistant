package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetAffairs {
    Affairs getAffairs(User user, String nameCompany, String value);
}
