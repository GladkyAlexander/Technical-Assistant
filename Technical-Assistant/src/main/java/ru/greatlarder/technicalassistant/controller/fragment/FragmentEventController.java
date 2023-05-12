package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesArrangement;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesEvent;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.Reception;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEventFormat;
import ru.greatlarder.technicalassistant.services.database.GetListNames;
import ru.greatlarder.technicalassistant.services.database.GetListSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.mysql.names.ListNamesForEventFormatMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.ListNamesForSeatingArrangementMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.GetListEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetListSeatingArrangementsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class FragmentEventController implements Initializable {
    @FXML
    public SplitPane splitPaneFragmentEvent;
    @FXML public TabPane tabPane1fragmentEvent;
    @FXML public TabPane tabPane2fragmentEvent;

    Company company;
    String lang;
    User user;
    Language language = new LanguageImpl();

    private void loadAllEvent(){
        
        tabPane1fragmentEvent.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
        if (user instanceof Engineer){
            loadSQLite();
            loadMySQL();
        }
        if (user instanceof Reception){
            loadMySQL();
        }

        /*GetListEventFormat getListEventFormat = new GetListEventFormatSQLite();
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
                    controller.setEvents(events);
                    controller.setLabelEvent(events.getNameEventFormat());
                    controller.setImg_event(new Image(events.getUrlImageEvent()));
                    setGraphic(node);
                }
            }
        });

        tabPane1fragmentEvent.getTabs().add(new Tab(company.getNameCompany(), listView));*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadAllEvent();
    }
    private void loadSQLite(){
        Task<ListView<EventFormat>> task = new Task<>() {
            @Override
            protected ListView<EventFormat> call(){
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
                            controller.setEvents(events);
                            controller.setLabelEvent(events.getNameEventFormat());
                            controller.setImg_event(new Image(events.getUrlImageEvent()));
                            setGraphic(node);
                        }
                    }
                });
                return listView;
            }
        };
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        Tab tab = new Tab();
        tab.setText(company.getNameCompany() + " : " + "SQLite");
        tab.setContent(progressBar);
        tabPane1fragmentEvent.getTabs().add(tab);
        task.setOnSucceeded((t)->{
            ListView<EventFormat> listView = task.getValue();
            tab.setContent(listView);
            setSelectedSQLite(listView);
        });
        Platform.runLater(() -> {
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }
    private void loadMySQL(){
        Task<ListView<EventFormat>> task = new Task<>() {
            @Override
            protected ListView<EventFormat> call(){
                
                GetListNames getListNames = new ListNamesForEventFormatMySQL();
                List<Names> namesList = getListNames.getListNames(user, company.getNameCompany(), null);
                
                List<EventFormat> seatingArrangementsList = new ArrayList<>();
                
                for (Names name : namesList){
                    EventFormat s = new EventFormat();
                    s.setId(name.getId());
                    s.setNameEventFormat(name.getNames());
                    s.setNameCompany(name.getNameCompany());
                    s.setUrlImageEvent(name.getUrl().getAbsolutePath());
                    
                    seatingArrangementsList.add(s);
                }
                ObservableList<EventFormat> eventsObservableList = FXCollections.observableArrayList(seatingArrangementsList);
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
                            controller.setEvents(events);
                            controller.setLabelEvent(events.getNameEventFormat());
                            controller.setImg_event(new Image(events.getUrlImageEvent()));
                            controller.setFragmentMySQL();
                            setGraphic(node);
                        }
                    }
                });
                return listView;
            }
        };
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        Tab tab = new Tab();
        if(user instanceof Engineer){
            tab.setText(company.getNameCompany() + " : " + "MySQL");
        }
        if (user instanceof Reception){
            tab.setText(language.EVENT(lang) + "  " +company.getNameCompany());
        }
        tab.setContent(progressBar);
        tabPane1fragmentEvent.getTabs().add(tab);
        task.setOnSucceeded((t)->{
            ListView<EventFormat> listView = task.getValue();
            tab.setContent(listView);
            setSelectedMySQL(listView);
        });
        Platform.runLater(() -> {
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
        
    }
    
    private void setSelectedSQLite(ListView<EventFormat> listView){
       /* listView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            GetListViewEquipment listViewEquipment = new ListViewEquipmentByNameRoomSQLite();
            Task<ListView<Equipment>> task = new Task<>() {
                @Override
                protected ListView<Equipment> call() {
                    return listViewEquipment.getListViewEquipment(
                        user, company.getNameCompany(), newValue.getNameRoom());
                }
            };
            ProgressBar progressBar = new ProgressBar();
            progressBar.setMaxWidth(MAX_VALUE);
            Tab tab = new Tab();
            tab.setText(newValue.getNameRoom());
            tab.setContent(progressBar);
            task.setOnSucceeded((t)->{
                tab.setContent(task.getValue());
                tabPane2Room.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
                tabPane2Room.getTabs().add(0, tab);
                tabPane2Room.getSelectionModel().select(0);
            });
            Platform.runLater(() -> {
                progressBar.progressProperty().bind(task.progressProperty());
                progressBar.visibleProperty().bind(task.runningProperty());
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(task);
                executorService.shutdown();
            });
            
        }));*/
    }
    private void setSelectedMySQL(ListView<EventFormat> listView){
       /* listView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
            GetListViewEquipment listViewEquipment = new ListViewEquipmentPortableDatabaseMySQL();
            Task<ListView<Equipment>> task = new Task<>() {
                @Override
                protected ListView<Equipment> call() {
                    return listViewEquipment.getListViewEquipment(
                        user, company.getNameCompany(), newValue.getNameRoom());
                }
            };
            ProgressBar progressBar = new ProgressBar();
            progressBar.setMaxWidth(MAX_VALUE);
            Tab tab = new Tab();
            tab.setText(newValue.getNameRoom());
            tab.setContent(progressBar);
            task.setOnSucceeded((t)->{
                tab.setContent(task.getValue());
                tabPane2Room.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
                tabPane2Room.getTabs().add(0, tab);
                tabPane2Room.getSelectionModel().select(0);
            });
            Platform.runLater(() -> {
                progressBar.progressProperty().bind(task.progressProperty());
                progressBar.visibleProperty().bind(task.runningProperty());
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(task);
                executorService.shutdown();
            });
            
        }));*/
    }
}
