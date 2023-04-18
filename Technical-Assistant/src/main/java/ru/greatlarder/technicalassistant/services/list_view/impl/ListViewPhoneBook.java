package ru.greatlarder.technicalassistant.services.list_view.impl;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemContactCart;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemPagePhoneBook;
import ru.greatlarder.technicalassistant.domain.PhoneBook;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewContactCard;

import java.io.IOException;
import java.util.List;

public class ListViewPhoneBook implements GetListViewContactCard {
    @Override
    public ListView<PhoneBook> getListViewPhoneBook(List<PhoneBook> contactCartList, ItemPagePhoneBook itemPagePhoneBook) {
        ListView<PhoneBook> listView = new ListView<>(FXCollections.observableArrayList(contactCartList));
        listView.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_contact_cart.fxml"));
            Node node;
            ItemContactCart controller;
            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected void updateItem(PhoneBook item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    controller.loadFragment(item, itemPagePhoneBook);
                    setGraphic(node);
                }
            }

        });
        return listView;
    }
}
