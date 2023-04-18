package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListAffairs {
    List<Affairs> getListAffairs(User user, String nameCompany, String value);
}
