package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;

public class CheckingCompanySQLite implements CheckCompany{

    @Override
    public boolean checkCompany(User user, Company company) {
          return false;
    }
}
