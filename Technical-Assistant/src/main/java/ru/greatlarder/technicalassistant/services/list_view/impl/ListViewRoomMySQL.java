package ru.greatlarder.technicalassistant.services.list_view.impl;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesRoom;
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
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_room.fxml"));
            Node node;
            ItemTypesRoom controller;

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
                    controller.setRoom(room);
                    controller.setLabelNameRoom(room.getNameRoom());
                    if(room.getUrlLogoRoom() != null){
                        controller.setImgLogoRoom(room.getUrlLogoRoom());
                    }
                    controller.setFragmentMySQL();
                    setGraphic(node);
                }
            }
        });
        return listView;
    }
}
