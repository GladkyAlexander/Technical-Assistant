package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckMac2Address;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByMac2AddressSQLite;

public class CheckingMac2AddressSQLite implements CheckMac2Address {
    @Override
    public boolean checkingEquipmentMac2Address(User user, String macAddress, String nameCompany) {
        if (macAddress != null) {
            GetEquipment getEquipmentByMac2Address = new EquipmentByMac2AddressSQLite();
            if(getEquipmentByMac2Address.getEquipment(user, nameCompany, macAddress) != null){
                return true;
            } else return false;
        }
        return false;
    }
}
