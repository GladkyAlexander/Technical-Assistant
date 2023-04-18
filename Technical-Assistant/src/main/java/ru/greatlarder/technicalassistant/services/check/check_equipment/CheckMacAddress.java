package ru.greatlarder.technicalassistant.services.check.check_equipment;

import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckMacAddress {
    boolean checkingEquipmentMacAddress(User user, String macAddress, String nameCompany);
}
