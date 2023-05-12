package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkFragmentEquipmentController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipmentByListEquipment;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentByListEquipment;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class FragmentEquipmentController implements Initializable {
    
    @FXML public BorderPane borderPane;
    @FXML
    SplitPane splitPaneEquipment;
    @FXML TabPane tabPaneEquipment1;
    @FXML TabPane tabPaneEquipment2;
    Company company;
    User user;

    public void loadFragment(){
        GlobalLinkFragmentEquipmentController.setFragmentEquipmentController(this);
        tabPaneEquipment1.getTabs().clear();
        
        Task<List<Equipment>> task = new Task<>() {
            @Override
            protected List<Equipment> call() {
                GetListEquipment equipments = new ListEquipmentByNameCompanySQLite();
                return equipments.getListEquipment(user, company.getNameCompany(), null);
            }
        };
        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        borderPane.setTop(progressBar);
        task.setOnSucceeded((succeededEvent)->{
            
            List<Equipment> equipmentList = task.getValue();
            
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(progressBar);
            GetListViewEquipmentByListEquipment getListViewEquipmentByName = new ListViewEquipmentByListEquipment();
            Set<String> nameEquipmentHashSet = new HashSet<>();
            
            for (Equipment equipment : equipmentList){
                nameEquipmentHashSet.add(equipment.getName());
            }
            for (String name : nameEquipmentHashSet){
                List<Equipment> equipmentList1 = new ArrayList<>();
                for(Equipment equipment : equipmentList){
                    if(name.equals(equipment.getName())){
                        equipmentList1.add(equipment);
                    }
                }
                ListView<Equipment> listView = getListViewEquipmentByName.getListView(equipmentList1);
                listView.getSelectionModel().selectedItemProperty().addListener((observableValue, equipment, t1) -> openEquipment(t1));
                tabPaneEquipment1.getTabs().add(new Tab(name, listView));
                borderPane.getChildren().remove(progressBar);
            }
        });
        Platform.runLater(()->{
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
        
    }

    private void openEquipment(Equipment equipment) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));
        try {
            tabPaneEquipment2.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            tabPaneEquipment2.getTabs().add(0,
                    new Tab(equipment.getName() + " : " + equipment.getSerialNumber(), loader.load()));
            tabPaneEquipment2.getSelectionModel().select(0);
            FragmentEquipmentOneController fragmentEquipmentOneItemController = loader.getController();
            GetEquipment getEquipment = new EquipmentByIdSQLite();
            fragmentEquipmentOneItemController.setEquip(getEquipment.getEquipment(user, equipment.getCompany(), String.valueOf(equipment.getId())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.user = GlobalLinkMainController.getMainController().getUser();
        loadFragment();
    }
}
