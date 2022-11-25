package ru.greatlarder.technicalassistant.services.database.sqlite.repository;

import ru.greatlarder.technicalassistant.domain.Company;

import java.util.List;

public interface CompanyRepository {

  List<Company> getAllCompany();
  List<String> getNameAllCompany();

  Company getCompanyName(String nameCompany);

  Company setCompany(Company company);

  Company change(Company company, String valueWhatToChange, String valueOnWhat);

    void changeCompany(Company company1);
}
