package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckIpAddressDante;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIpAddressDanteSqlite;

public class CheckingIpDanteForEmploymentInTheDatabaseSQLite implements CheckIpAddressDante {
    @Override
    public boolean checkIpAddress(User user, Company company, String ipAddress) {
        GetEquipment getEquipmentForIpAddressDante = new EquipmentByIpAddressDanteSqlite();
        if (getEquipmentForIpAddressDante.getEquipment(user, company.getNameCompany(), ipAddress) != null){
            return true;
        } else return false;
    }
}
