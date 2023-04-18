package ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.Microphone;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.check_equipment.CheckFrequency;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.util.List;

public class CheckingFrequencyDatabaseSQLite implements CheckFrequency {

    User user = GlobalLinkMainController.getMainController().getUser();
    @Override
    public boolean checkFrequency(User user, String nameCompany, String frequency) {

        GetListEquipment getListEquipment = new ListEquipmentByNameCompanySQLite();

        List<Equipment> listMicrophone = getListEquipment.getListEquipment(user, nameCompany, getName());

        for (Equipment equipment : listMicrophone){
            if (frequency.equals(((Microphone) equipment).getFrequency())){
                return true;
            }
        }
        return false;
    }

    private String getName(){
        if(user.getLanguage().equals("English")){
            return "Microphone";
        } else return "Микрофон";
    }
}
