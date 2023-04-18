package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckMac3Address;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByMac3AddressSQLite;

public class CheckingMac3AddressSQLite implements CheckMac3Address {
    @Override
    public boolean checkingEquipmentMac3Address(User user, String macAddress, String nameCompany) {
        if (macAddress != null) {
            GetEquipment getEquipmentByMac3Address = new EquipmentByMac3AddressSQLite();
            if(getEquipmentByMac3Address.getEquipment(user, nameCompany, macAddress) != null){
                return true;
            } else return false;
        }
        return false;
    }
}
