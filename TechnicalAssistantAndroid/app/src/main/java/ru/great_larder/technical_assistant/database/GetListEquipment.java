package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListEquipment {
    List<Equipment> getListEquipment(User user, String companyName, String value);
}
