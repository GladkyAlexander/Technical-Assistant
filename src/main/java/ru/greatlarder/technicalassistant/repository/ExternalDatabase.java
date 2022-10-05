package ru.greatlarder.technicalassistant.repository;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;

import java.util.List;

public interface ExternalDatabase {
    void sendCompanyDetailis(Company company);
     List<Company> acceptDataFromAllCompanies();
    void updateCompanyExternal(Company company);
    void updateEquipmentExternal(Equipment equipment);

    Company getCompanyForNameCompany(String nameCompany);

    void changeDataEquipment(Equipment equipment);
}
