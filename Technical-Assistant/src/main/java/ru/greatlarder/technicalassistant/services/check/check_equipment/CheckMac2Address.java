package ru.greatlarder.technicalassistant.services.check.check_equipment;

import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckMac2Address {
    boolean checkingEquipmentMac2Address(User user, String macAddress, String nameCompany);
}
