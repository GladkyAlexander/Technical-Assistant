package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemRoom;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.RoomsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.RoomsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;

public class FragmentRoom implements ObserverLang, ObserverCompany, ObserverUser {
    @FXML public SplitPane splitPaneRoomFragment;
    @FXML public TabPane tabPane1Room;
    @FXML public TabPane tabPane2Room;
    private Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    private String lang;
    private User user;

    public void loadFragment() {
        loadAllRoom();
    }
    private void loadAllRoom(){

        RoomsRepository repository = new RoomsRepositoryImpl();
        ObservableList<Room> roomsList = FXCollections.observableArrayList(repository.getListRoomForCompany(company.getNameCompany()));
        ListView<Room> listView = new ListView<>(roomsList);
        listView.setCellFactory(param-> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_room.fxml"));
            Node node;
            ItemRoom controller;
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
            protected void updateItem(Room room, boolean b) {
                super.updateItem(room, b);
                if(b){
                    setGraphic(null);
                } else {
                    handlerLang.registerObserverLang(controller);
                    controller.updateLang(new DataLang(lang));
                    controller.setRoom(room);
                    controller.setLabelNameRoom(room.getNameRoom());
                    setGraphic(node);
                }
            }
        });

        tabPane1Room.getTabs().add(new Tab(company.getNameCompany(), listView));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else {
            this.company = dataCompany.getCompany();
            loadFragment();
        }
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
