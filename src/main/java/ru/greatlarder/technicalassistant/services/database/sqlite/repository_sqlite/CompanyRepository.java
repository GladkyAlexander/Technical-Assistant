package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.Company;

import java.util.List;

public interface CompanyRepository {

  List<Company> getAllCompany();
  List<String> getNameAllCompany();
  List<Company> getListCompanyByUserId(Integer idUser);

  Company getCompanyName(String nameCompany);
  Company getCompanyById(Integer idCompany);

  Integer setCompany(Integer idUser, Company company);

  Company change(Company company, String valueWhatToChange, String valueOnWhat);

    void changeCompany(Company company1);
}
