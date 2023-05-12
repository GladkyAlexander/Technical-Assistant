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
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.Reception;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListNames;
import ru.greatlarder.technicalassistant.services.database.GetListSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.GetSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.mysql.names.ListNamesForSeatingArrangementMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetListSeatingArrangementsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetSeatingArrangementsByNameSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipment;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomMySQL;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomSQLite;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentByNameRoomSQLite;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentPortableDatabaseMySQL;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class FragmentSeatingArrangement implements Initializable {
    @FXML
    public TabPane tabPane1fragmentSeating;
    @FXML public TabPane tabPane2fragmentSeating;
    Company company;
    String lang;
    User user;
    Language language = new LanguageImpl();
    
    private void loadAllSeating(){
        
        tabPane1fragmentSeating.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        
        if (user instanceof Engineer){
            loadSQLite();
            loadMySQL();
        }
        if (user instanceof Reception){
            loadMySQL();
        }
        
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadAllSeating();
    }
    private void loadSQLite(){
        Task<ListView<SeatingArrangements>> task = new Task<>() {
            @Override
            protected ListView<SeatingArrangements> call(){
                GetListSeatingArrangements getListSeatingArrangements = new GetListSeatingArrangementsSQLite();
                ObservableList<SeatingArrangements> eventsObservableList = FXCollections.observableArrayList(getListSeatingArrangements.getListSeatingArrangements(
                    user, company.getNameCompany(), null));
                ListView<SeatingArrangements> listView = new ListView<>(eventsObservableList);
                listView.setCellFactory(param-> new ListCell<>(){
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_arrangement.fxml"));
                    Node node;
                    ItemTypesArrangement controller;
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
                    protected void updateItem(SeatingArrangements events, boolean b) {
                        super.updateItem(events, b);
                        if(b){
                            setGraphic(null);
                        } else {
                            controller.setSeatingArrangements(events);
                            controller.setLabelSeating(events.getNameSeatingArrangements());
                            controller.setImgLogo(new Image(events.getUrlImageSeatingArrangements()));
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
        tabPane1fragmentSeating.getTabs().add(tab);
        task.setOnSucceeded((t)->{
            ListView<SeatingArrangements> listView = task.getValue();
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
        Task<ListView<SeatingArrangements>> task = new Task<>() {
            @Override
            protected ListView<SeatingArrangements> call(){
                
                GetListNames getListNames = new ListNamesForSeatingArrangementMySQL();
                List<Names> namesList = getListNames.getListNames(user, company.getNameCompany(), null);
                
                List<SeatingArrangements> seatingArrangementsList = new ArrayList<>();
                
                for (Names name : namesList){
                        SeatingArrangements s = new SeatingArrangements();
                        s.setId(name.getId());
                        s.setNameSeatingArrangements(name.getNames());
                        s.setNameCompany(name.getNameCompany());
                        s.setUrlImageSeatingArrangements(name.getUrl().getAbsolutePath());
                        
                        seatingArrangementsList.add(s);
                }
                ObservableList<SeatingArrangements> eventsObservableList = FXCollections.observableArrayList(seatingArrangementsList);
                ListView<SeatingArrangements> listView = new ListView<>(eventsObservableList);
                listView.setCellFactory(param-> new ListCell<>(){
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_arrangement.fxml"));
                    Node node;
                    ItemTypesArrangement controller;
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
                    protected void updateItem(SeatingArrangements events, boolean b) {
                        super.updateItem(events, b);
                        if(b){
                            setGraphic(null);
                        } else {
                            controller.setSeatingArrangements(events);
                            controller.setLabelSeating(events.getNameSeatingArrangements());
                            controller.setImgLogo(new Image(events.getUrlImageSeatingArrangements()));
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
            tab.setText(language.SEATING_ARRANGEMENTS(lang) + "  " +company.getNameCompany());
        }
        tab.setContent(progressBar);
        tabPane1fragmentSeating.getTabs().add(tab);
        task.setOnSucceeded((t)->{
            ListView<SeatingArrangements> listView = task.getValue();
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
    private void setSelectedSQLite(ListView<SeatingArrangements> listView){
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
    private void setSelectedMySQL(ListView<SeatingArrangements> listView){
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
