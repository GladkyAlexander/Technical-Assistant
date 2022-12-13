package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;

import java.util.List;

public interface ExternalDatabase {
    void sendCompanyDetailis(Company company);
     List<Company> acceptDataFromAllCompanies();
    void updateCompanyExternal(Company company);
    void updateEquipmentExternal(Equipment equipment);

    Company getCompanyForNameCompany(String nameCompany);

    List<Day> getListDayForRoom(Room room);
    void sendDay(Day day);
    void updateDay(Day day);

    void changeDataEquipment(Equipment equipment);
}
