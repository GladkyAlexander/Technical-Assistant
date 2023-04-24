package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetEquipment {
    Integer setEquipment(User user, String nameCompany, Equipment equipment);
}
