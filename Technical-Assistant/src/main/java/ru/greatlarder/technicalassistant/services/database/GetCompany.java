package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetCompany {
    Company getCompany(User user, String value);
}
