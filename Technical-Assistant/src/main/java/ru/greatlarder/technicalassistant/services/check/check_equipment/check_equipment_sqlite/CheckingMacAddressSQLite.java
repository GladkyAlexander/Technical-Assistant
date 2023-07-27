package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckMacAddress;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByMacAddressSQLite;

public class CheckingMacAddressSQLite implements CheckMacAddress {

    @Override
    public boolean checkingEquipmentMacAddress(User user, String macAddress, String nameCompany) {
        if (macAddress != null) {
            GetEquipment getEquipmentByMacAddress = new EquipmentByMacAddressSQLite();
            return getEquipmentByMacAddress.getEquipment(user, nameCompany, macAddress) != null;
        }
        return false;
    }
}
