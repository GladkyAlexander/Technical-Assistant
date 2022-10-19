package ru.greatlarder.technicalassistant.controller.reception;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemRoomMin;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeReceptionController implements ObserverLang, ObserverUser, ObserverCompany {
    @FXML public BorderPane borderPaneHomePage;
    @FXML public Label labelRoomsHRC;
    @FXML public VBox vbtop;
    private User user;
    private String lang;
    Language language = new LanguageImpl();
    private Company company;
    HandlerLang handlerLang = new HandlerLang();
    FileManager fileManager = new FileManagerImpl();

    public void loadFragment() {
        loadRooms();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang1) {
        labelRoomsHRC.setText(language.ROOMS(lang1));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        loadFragment();
    }
    private void loadRooms(){
        if(company != null){
            vbtop.getChildren().add(getList());
        }
    }
    private List<Room> getRoomList(){
        List<String> list = new ArrayList<>();
        List<Room> roomList1 = new ArrayList<>();
        if(company != null){
            if(company.getEquipmentList() != null){
                for (Equipment equipment : company.getEquipmentList()) {
                    if (equipment.getRoom() != null && list.stream().noneMatch(s -> s.equals(equipment.getRoom()))) {
                        list.add(equipment.getRoom());
                    }
                }

                for (String s : list){
                    List<Equipment> equipmentList = new ArrayList<>();
                    for (Equipment equipment2 : company.getEquipmentList()){
                        if(equipment2.getRoom() != null){
                            if (equipment2.getRoom().equals(s)) {
                                equipmentList.add(equipment2);
                            }
                        }
                    }
                    roomList1.add(new Room(s, equipmentList));
                }
            }
        }
        return roomList1;
    }

    private ListView<Room> getList(){
        ObservableList<Room> observableList = FXCollections.observableArrayList(getRoomList());

        ListView<Room> listView = new ListView<>(observableList);
        listView.setCellFactory(param -> new ListCell<>(){

            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_room_min.fxml"));
            Node node;
            ItemRoomMin controller;

            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                    setPrefHeight(0);
                    setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
                    setStyle(StyleSRC.STYLE_ORDINARY);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Room room, boolean b) {
                super.updateItem(room, b);
                if(b){
                    setGraphic(null);
                } else{
                    controller.labelNameRoomIRM.setText(room.getNameRoom());
                    setGraphic(node);
                }
            }
        });
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Room>() {
            @Override
            public void changed(ObservableValue<? extends Room> observableValue, Room room, Room t1) {
                borderPaneHomePage.setRight(createTableEquipment(t1.getEquipmentList()));
            }
        });
        listView.setOrientation(Orientation.HORIZONTAL);
        return listView;
    }

    private ListView<Equipment> createTableEquipment(List<Equipment> equipmentList){

        ObservableList<Equipment> list = FXCollections.observableArrayList(equipmentList);
        ListView<Equipment> equipmentListView = new ListView<>(list);
        equipmentListView.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_equipment.fxml"));
            Node node;
            ItemEquipmentController controller;
            {
                try {
                    node = loader.load();
                    handlerLang.registerObserverLang(loader.getController());
                    handlerLang.onNewDataLang(new DataLang(lang));
                    controller = loader.getController();
                    setPrefWidth(0);
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

                    if(item.getImage() != null){
                        if(fileManager.getUrlFileImage(item.getImage()) != null){
                            controller.setIvPhoto(new Image(fileManager.getUrlFileImage(item.getImage())));
                        } else {
                            controller.setIvPhoto(new Image(Objects.requireNonNull(getClass()
                                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/" + item.getImage()))));
                        }
                    }
                    controller.setModel(item.getModel());
                    controller.setSerialNumber(item.getSerialNumber());
                    controller.setDate(item.getDateWork());
                    controller.setEquipment(item);
                    setGraphic(node);
                }
            }
        });
        return equipmentListView;
    }
}
