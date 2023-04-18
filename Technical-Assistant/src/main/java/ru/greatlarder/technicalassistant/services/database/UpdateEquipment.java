package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateEquipment {
    void updateEquipment(User user, Company company, Equipment equipment);
}
