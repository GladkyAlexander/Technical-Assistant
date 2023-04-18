package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetAffairs {
    Integer setAffairs(User user, String nameCompany, Affairs affairs);
}
