package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckIpAddress;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIpAddressSqlite;

public class CheckingIpForEmploymentInTheDatabaseSQLite implements CheckIpAddress {
    @Override
    public boolean checkIpAddress(User user, Company company, String ipAddress) {
        GetEquipment getEquipmentForIpAddress = new EquipmentByIpAddressSqlite();
        if (getEquipmentForIpAddress.getEquipment(user, company.getNameCompany(), ipAddress) != null){
            return true;
        } else return false;
    }
}
