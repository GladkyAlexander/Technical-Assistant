package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkFragmentEquipmentController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.*;

public class FragmentEquipmentController implements ObserverLang, ObserverCompany, ObserverUser {

    public SplitPane splitPaneEquipment;
    public TabPane tabPaneEquipment1;
    public TabPane tabPaneEquipment2;
    private String lang;
    private Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    private User user;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if (dataCompany == null){
            this.company = null;
        } else {
            this.company = dataCompany.getCompany();
            loadFragment();
        }
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
                    handlerLang.registerObserverLang(loader.getController());
                    controller.updateLang(new DataLang(lang));
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

        equipmentListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Equipment>() {
            @Override
            public void changed(ObservableValue<? extends Equipment> observableValue, Equipment equipment, Equipment t1) {
                openEquipment(t1);
            }
        });

        return equipmentListView;
    }

    public void loadFragment(){
        GlobalLinkFragmentEquipmentController.setFragmentEquipmentController(this);
        tabPaneEquipment1.getTabs().clear();
        List<Equipment> equipmentList = company.getEquipmentList();
        
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
            tabPaneEquipment1.getTabs().add(new Tab(name, createTableEquipment(equipmentList1)));
        }
    }

    private void openEquipment(Equipment equipment) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));
        try {
           tabPaneEquipment2.getTabs().add(
                    new Tab(equipment.getName() + " : " + equipment.getSerialNumber(), loader.load()));
            handlerLang.registerObserverLang(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentEquipmentOneController fragmentEquipmentOneItemController = loader.getController();
            fragmentEquipmentOneItemController.setEquip(equipment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
