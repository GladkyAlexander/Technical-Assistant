package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckCompany {
    boolean checkCompany(User user, Company company);
}
