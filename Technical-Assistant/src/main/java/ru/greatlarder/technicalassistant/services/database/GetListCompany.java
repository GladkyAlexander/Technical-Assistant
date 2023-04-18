package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListCompany {
    List<Company> getAllCompany(User user, String value);
}
