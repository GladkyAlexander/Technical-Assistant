package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.Equipment;

import java.util.List;

public interface GetListViewEquipmentByListEquipment {
    ListView<Equipment> getListView(List<Equipment> equipments);
}
