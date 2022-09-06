package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkFragmentEquipmentController;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.IOException;
import java.util.*;

public class FragmentEquipmentController implements ObserverLang, ObserverCompany {

    public SplitPane splitPaneEquipment;
    public TabPane tabPaneEquipment1;
    public TabPane tabPaneEquipment2;
    private String lang;
    private Company company;
    HandlerLang handlerLang = new HandlerLang();
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        handlerLang.onNewDataLang(new DataLang(lang));
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        loadFragment(company);
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

    public void loadFragment(Company company){
        this.company = company;
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

}
