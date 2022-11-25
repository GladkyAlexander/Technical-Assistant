package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface EquipmentRepositoryMySQL {
    Equipment getEquipmentById(User user, String companyName, Integer id);
    List<Equipment> getEquipmentByCompanyName(User user, String companyName);
    List<Equipment> getEquipmentByRoomName(User user, String companyName, String roomName);
    void setEquipment(User user, Equipment equipment);
    void setListEquipment(User user, String companyName, List<Equipment> equipmentList);
    void updateEquipment(User user, String companyName, Equipment equipment);
    void updateEquipmentList(User user, String companyName, List<Equipment> equipmentList);
}
