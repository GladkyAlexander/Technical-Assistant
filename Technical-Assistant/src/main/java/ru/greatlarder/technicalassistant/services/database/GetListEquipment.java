package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListEquipment {
    List<Equipment> getListEquipment(User user, String companyName, String value);
}
