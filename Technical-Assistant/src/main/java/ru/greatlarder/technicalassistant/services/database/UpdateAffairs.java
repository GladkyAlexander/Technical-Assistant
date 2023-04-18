package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateAffairs {
    void updateAffairs(User user, String nameCompany, Affairs affairs, Integer id);
}
