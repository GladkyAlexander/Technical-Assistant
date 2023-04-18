package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesEvent;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEventFormat;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.GetListEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentEventController implements Initializable {
    @FXML
    public SplitPane splitPaneFragmentEvent;
    @FXML public TabPane tabPane1fragmentEvent;
    @FXML public TabPane tabPane2fragmentEvent;

    Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    String lang;
    User user;

    private void loadAllEvent(){

        GetListEventFormat getListEventFormat = new GetListEventFormatSQLite();
        ObservableList<EventFormat> eventsObservableList = FXCollections.observableArrayList(getListEventFormat.getListEventFormat(
                user, company.getNameCompany(), null));
        ListView<EventFormat> listView = new ListView<>(eventsObservableList);
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
            protected void updateItem(EventFormat events, boolean b) {
                super.updateItem(events, b);
                if(b){
                    setGraphic(null);
                } else {
                    handlerLang.registerObserverLang(controller);
                    controller.updateLang(new DataLang(lang));
                    controller.setEvents(events);
                    controller.setLabelEvent(events.getNameEventFormat());
                    controller.setImg_event(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/" + events.getUrlImageEvent()))));
                    setGraphic(node);
                }
            }
        });

        tabPane1fragmentEvent.getTabs().add(new Tab(company.getNameCompany(), listView));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadAllEvent();
    }
}
