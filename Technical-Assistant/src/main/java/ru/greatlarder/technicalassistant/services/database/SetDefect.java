package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetDefect {
    Integer setDefect(User user, String nameCompany, Defect defect);
}
