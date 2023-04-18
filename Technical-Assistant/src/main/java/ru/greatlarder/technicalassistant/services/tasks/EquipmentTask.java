package ru.greatlarder.technicalassistant.services.tasks;

import javafx.concurrent.Task;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.util.List;

public class EquipmentTask extends Task<List<Equipment>> {
    User user = GlobalLinkMainController.getMainController().getUser();
    Company company = GlobalLinkMainController.getMainController().getCompany();
    @Override
    protected List<Equipment> call() throws Exception {
        GetListEquipment getListEquipment = new ListEquipmentByNameCompanySQLite();
        return getListEquipment.getListEquipment(user, company.getNameCompany(), null);
    }
}
