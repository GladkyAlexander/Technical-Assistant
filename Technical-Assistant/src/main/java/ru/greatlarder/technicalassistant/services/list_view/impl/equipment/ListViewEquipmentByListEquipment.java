package ru.greatlarder.technicalassistant.services.list_view.impl.equipment;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipmentByListEquipment;

import java.io.IOException;
import java.util.List;

public class ListViewEquipmentByListEquipment implements GetListViewEquipmentByListEquipment {
    @Override
    public ListView<Equipment> getListView(List<Equipment> equipments) {

        ListView<Equipment> listView = new ListView<>(FXCollections.observableArrayList(equipments));
        listView.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_equipment.fxml"));

            Node node;
            ItemEquipmentController controller;
            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Equipment item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    controller.setEquipment(item);
                    setGraphic(node);
                }
            }
        });

        return listView;
    }
}
