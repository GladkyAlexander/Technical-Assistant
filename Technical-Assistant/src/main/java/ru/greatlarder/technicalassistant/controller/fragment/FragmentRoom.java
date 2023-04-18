package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipment;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomSQLite;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentByNameRoomSQLite;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentRoom implements Initializable {
    @FXML public SplitPane splitPaneRoomFragment;
    @FXML public TabPane tabPane1Room;
    @FXML public TabPane tabPane2Room;
    Company company;
    String lang;
    User user;

    public void loadFragment() {
        GetListViewRoom getListViewRoom = new ListViewRoomSQLite();
        tabPane1Room.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        ListView<Room> roomListView = getListViewRoom.getListView(user, company.getNameCompany(), null);

                roomListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                    GetListViewEquipment listViewEquipment = new ListViewEquipmentByNameRoomSQLite();
                    Task<ListView<Equipment>> task = new Task<>() {
                        @Override
                        protected ListView<Equipment> call() {
                            return listViewEquipment.getListViewEquipment(
                                    user, company.getNameCompany(), newValue.getNameRoom());
                        }
                    };
                    ProgressBar progressBar = new ProgressBar();
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
        tabPane1Room.getTabs().add(new Tab(company.getNameCompany(), roomListView));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadFragment();
    }
}
