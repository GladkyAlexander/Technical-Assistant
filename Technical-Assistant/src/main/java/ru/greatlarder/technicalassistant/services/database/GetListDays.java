package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListDays {

    List<Day> getListDays(User user, Company company, String value);
}
