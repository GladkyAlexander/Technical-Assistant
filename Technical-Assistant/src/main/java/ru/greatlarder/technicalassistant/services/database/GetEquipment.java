package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetEquipment {
    Equipment getEquipment(User user, String nameCompany, String value);
}
