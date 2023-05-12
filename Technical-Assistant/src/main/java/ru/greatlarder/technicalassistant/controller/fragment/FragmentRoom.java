package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.Reception;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipment;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomMySQL;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomSQLite;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentByNameRoomSQLite;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentPortableDatabaseMySQL;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class FragmentRoom implements Initializable {
    @FXML public SplitPane splitPaneRoomFragment;
    @FXML public TabPane tabPane1Room;
    @FXML public TabPane tabPane2Room;
    Company company;
    String lang;
    User user;
    Language language = new LanguageImpl();

    public void loadFragment() {
        tabPane1Room.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
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
        loadFragment();
    }
    private void loadSQLite(){
        Task<ListView<Room>> task = new Task<>() {
            @Override
            protected ListView<Room> call(){
                GetListViewRoom getListViewRoom = new ListViewRoomSQLite();
                return getListViewRoom.getListView(user, company.getNameCompany(), null);
            }
        };
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        Tab tab = new Tab();
        tab.setText(company.getNameCompany() + " : " + "SQLite");
        tab.setContent(progressBar);
        tabPane1Room.getTabs().add(tab);
        task.setOnSucceeded((t)->{
            ListView<Room> listView = task.getValue();
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
        Task<ListView<Room>> task = new Task<>() {
            @Override
            protected ListView<Room> call(){
                GetListViewRoom getListViewRoom = new ListViewRoomMySQL();
                return getListViewRoom.getListView(user, company.getNameCompany(), null);
            }
        };
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        Tab tab = new Tab();
        if(user instanceof Engineer){
            tab.setText(company.getNameCompany() + " : " + "MySQL");
        }
        if (user instanceof Reception){
            tab.setText(language.ROOMS(lang) + "  " +company.getNameCompany());
        }
        tab.setContent(progressBar);
        tabPane1Room.getTabs().add(tab);
        task.setOnSucceeded((t)->{
            ListView<Room> listView = task.getValue();
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
    private void setSelectedSQLite(ListView<Room> listView){
        listView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
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
            
        }));
    }
    private void setSelectedMySQL(ListView<Room> listView){
        listView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
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
            
        }));
    }
}
