package ru.great_larder.technical_assistant.database;


import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetEquipment {
    Equipment getEquipment(User user, String nameCompany, String value);
}
