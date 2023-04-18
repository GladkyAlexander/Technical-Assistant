package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateDefect {
    void updateDefect(User user, String nameCompany, Defect defect, Integer idDefect);
}
