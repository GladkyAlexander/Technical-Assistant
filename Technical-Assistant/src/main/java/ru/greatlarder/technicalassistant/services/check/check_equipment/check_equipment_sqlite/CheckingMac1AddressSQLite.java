package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckMac1Address;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByMac1AddressSQLite;

public class CheckingMac1AddressSQLite implements CheckMac1Address {

    @Override
    public boolean checkingEquipmentMac1Address(User user, String macAddress, String nameCompany) {
        if (macAddress != null) {
            GetEquipment getEquipmentByMac1Address = new EquipmentByMac1AddressSQLite();
            if(getEquipmentByMac1Address.getEquipment(user, nameCompany, macAddress) != null){
                return true;
            } else return false;
        }
        return false;
    }
}
