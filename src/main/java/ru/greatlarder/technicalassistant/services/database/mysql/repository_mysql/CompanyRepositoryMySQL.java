package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface CompanyRepositoryMySQL {
    List<Company> getListCompany(User user);
    Company getCompanyByName(User user,String nameCompany);
    void setCompany(User user, Company company);
    void updateCompany(User user, Company company);
}
