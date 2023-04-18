package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetListViewEquipment {
    ListView<Equipment> getListViewEquipment(User user, String nameCompany, String value);

}
