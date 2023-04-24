package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateEquipment {
    void updateEquipment(User user, Company company, Equipment equipment);
}
