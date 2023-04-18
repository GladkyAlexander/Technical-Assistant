package ru.greatlarder.technicalassistant.services.list_view.impl.equipment;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.ListEquipmentByRoomVisibleTrueMySQL;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipment;

import java.io.IOException;

public class ListViewEquipmentVisibleTrueFromRoomDatabaseMySQL implements GetListViewEquipment {
    @Override
    public ListView<Equipment> getListViewEquipment(User user, String nameCompany, String nameRoom) {
        GetListEquipment getListEquipmentPortable = new ListEquipmentByRoomVisibleTrueMySQL();

        ListView<Equipment> listView = new ListView<>(FXCollections.observableArrayList(getListEquipmentPortable.getListEquipment(user, nameCompany, nameRoom)));
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
                    controller.setSt();
                    setGraphic(node);
                }
            }
        });

        return listView;
    }
}
