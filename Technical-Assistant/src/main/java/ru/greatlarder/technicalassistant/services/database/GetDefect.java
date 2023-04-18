package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetDefect {
    Defect getDefect(User user, String nameCompany, String value);
}
