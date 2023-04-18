package ru.greatlarder.technicalassistant.services.check.check_equipment;

import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckMac3Address {
    boolean checkingEquipmentMac3Address(User user, String macAddress, String nameCompany);
}
