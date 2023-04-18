package ru.greatlarder.technicalassistant.services.list_view.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewAffairs;

import java.util.List;

public class ListViewAffairsCasesFromEmail implements GetListViewAffairs {
    @Override
    public ListView<Affairs> getListViewMail(List<Affairs> affairsList) {

        ObservableList<Affairs> observableList = FXCollections.observableArrayList(affairsList);
        ListView<Affairs> list = new ListView<>(observableList);
        list.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_mail.fxml"));
            Node node;

        });
        return list;
    }
}
