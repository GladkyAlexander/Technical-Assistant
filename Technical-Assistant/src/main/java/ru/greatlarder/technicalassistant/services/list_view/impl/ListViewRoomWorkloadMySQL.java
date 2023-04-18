package ru.greatlarder.technicalassistant.services.list_view.impl;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemRoomWorkloadController;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;
import ru.greatlarder.technicalassistant.services.database.mysql.room.ListRoomByCompanyMySQL;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;

import java.io.IOException;
import java.time.LocalDate;

public class ListViewRoomWorkloadMySQL implements GetListViewRoom {
    @Override
    public ListView<Room> getListView(User user, String nameCompany, String localDate) {
        GetListRoom getListRoom = new ListRoomByCompanyMySQL();
        ListView<Room> listView = new ListView<>(FXCollections.observableArrayList(getListRoom.getListRoom(user, nameCompany, null)));
        listView.setCellFactory(p -> new ListCell<>() {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/itemRoomWorkload.fxml"));
            Node node;
            ItemRoomWorkloadController controller;
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
                    controller.loadFragment(room, LocalDate.parse(localDate));
                    setGraphic(node);
                }
            }
        });
        return listView;
    }
}
