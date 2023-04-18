package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListDefect {
    List<Defect> getListDefect(User user, String nameCompany, String value);
}
