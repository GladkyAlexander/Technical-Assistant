package ru.greatlarder.technicalassistant.services.check.check_equipment;

import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckMac1Address {
    boolean checkingEquipmentMac1Address(User user, String macAddress, String nameCompany);
}
