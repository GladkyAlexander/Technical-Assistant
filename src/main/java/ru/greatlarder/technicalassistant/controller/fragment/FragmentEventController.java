package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesEvent;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Events;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EventsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EventsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.Objects;

public class FragmentEventController implements ObserverLang, ObserverCompany, ObserverUser {
    @FXML
    public SplitPane splitPaneFragmentEvent;
    @FXML public TabPane tabPane1fragmentEvent;
    @FXML public TabPane tabPane2fragmentEvent;

    private Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    private String lang;
    private User user;

    public void loadFragment() {
        loadAllEvent();
    }
    private void loadAllEvent(){

        EventsRepository repository = new EventsRepositoryImpl();
        ObservableList<Events> eventsObservableList = FXCollections.observableArrayList(repository.getListEventsForCompany(company.getNameCompany()));
        ListView<Events> listView = new ListView<>(eventsObservableList);
        listView.setCellFactory(param-> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_events.fxml"));
            Node node;
            ItemTypesEvent controller;
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
            protected void updateItem(Events events, boolean b) {
                super.updateItem(events, b);
                if(b){
                    setGraphic(null);
                } else {
                    handlerLang.registerObserverLang(controller);
                    controller.updateLang(new DataLang(lang));
                    controller.setEvents(events);
                    controller.setLabelEvent(events.getNameEvent());
                    controller.setImg_event(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/" + events.getUrlImageEvent()))));
                    setGraphic(node);
                }
            }
        });

        tabPane1fragmentEvent.getTabs().add(new Tab(company.getNameCompany(), listView));
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
