package ru.greatlarder.technicalassistant.services.list_view.impl;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemRoomMin;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;
import ru.greatlarder.technicalassistant.services.database.mysql.room.ListRoomByCompanyMySQL;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;

import java.io.IOException;

public class ListViewRoomMySQL implements GetListViewRoom {
    @Override
    public ListView<Room> getListView(User user, String nameCompany, String value) {
        GetListRoom getListRoom = new ListRoomByCompanyMySQL();
        ListView<Room> listView = new ListView<>(FXCollections.observableArrayList(getListRoom.getListRoom(user, nameCompany, null)));
        listView.setCellFactory(p -> new ListCell<>() {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_room_min.fxml"));
            Node node;
            ItemRoomMin controller;

            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Room room, boolean b) {
                super.updateItem(room, b);
                if (b) {
                    setGraphic(null);
                } else {
                    controller.setUser(user);
                    controller.labelNameRoomIRM.setText(room.getNameRoom());
                    setGraphic(node);
                }
            }
        });
        return listView;
    }
}
