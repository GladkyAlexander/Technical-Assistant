package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetEquipment {
    Integer setEquipment(User user, String nameCompany, Equipment equipment);
}
