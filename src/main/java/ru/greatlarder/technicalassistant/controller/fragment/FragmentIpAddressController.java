package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.ListOfIpAddressForTheCompany;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.TableIpAddress;

import java.io.IOException;
import java.util.*;

public class FragmentIpAddressController implements ObserverCompany, ObserverLang {
    @FXML public SplitPane splitPane;
    @FXML public TabPane tabPaneIp;
    @FXML public TabPane tabPaneDante;
    private Company company;
    Language language = new LanguageImpl();
    String lang;
    HandlerLang handlerLang = new HandlerLang();

    public void loadFragment(Company company) {
        this.company = company;

        Set<String> listIp = new HashSet<>();
        Set<String> listDante = new HashSet<>();

        for (Equipment equipment : company.getEquipmentList()){
            if(equipment.getIpAddress() != null && !equipment.getIpAddress().isEmpty()){
                listIp.add(tr(equipment.getIpAddress()));
            }
        }
        for (String f : listIp){
            List<Equipment> lj = new ArrayList<>();
            for (Equipment equipment : company.getEquipmentList()){
                if(equipment.getIpAddress() != null && tr(equipment.getIpAddress()).equals(f)){
                        lj.add(equipment);
                }
            }
            tabPaneIp.getTabs().add(new Tab(f + " - IPv4", createTabIp(lj)));
        }

        for (Equipment equipment : company.getEquipmentList()){
            if(equipment.getDanteIpAddress() != null){
                listDante.add(tr(equipment.getDanteIpAddress()));
            }
        }

        for (String f : listDante){
            List<Equipment> lj = new ArrayList<>();
            for (Equipment equipment : company.getEquipmentList()){
                if(equipment.getDanteIpAddress() != null && tr(equipment.getDanteIpAddress()).equals(f)){
                    lj.add(equipment);
                }
            }
            tabPaneDante.getTabs().add(new Tab(f + " - Dante", createTabIpDante(lj)));
        }

    }

    public TableView<TableIpAddress> createTabIp(List<Equipment> equipmentLists){

        TableView<TableIpAddress> tableView = new TableView<>();
        ObservableList<TableIpAddress> observableList = FXCollections.observableArrayList();

        List<String> pushIp = new ListOfIpAddressForTheCompany(equipmentLists).receiveListAddress();

        for (String s : pushIp){
            Equipment equipment2 = new Equipment();
            for (Equipment equipment1 : equipmentLists){
                if (s.equals(equipment1.getIpAddress())){
                    equipment2 = equipment1;
                }
            }
            TableIpAddress tableIpAddress = new TableIpAddress(equipment2, s, "network");
            observableList.add(tableIpAddress);
        }


        TableColumn<TableIpAddress, String> equipmentName =new TableColumn<>(language.NAME(lang));
        equipmentName.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<TableIpAddress, String> equipmentManufacturer = new TableColumn<>(language.MANUFACTURER(lang));
        equipmentManufacturer.setCellValueFactory(data -> data.getValue().manufacturerProperty());

        TableColumn<TableIpAddress, String> equipmentSerialNumber = new TableColumn<>(language.SERIAL_NUMBER(lang));
        equipmentSerialNumber.setCellValueFactory(data -> data.getValue().serialNumberProperty());

        TableColumn<TableIpAddress, String> macAddress =new TableColumn<>(language.MAC_ADDRESS(lang));
        macAddress.setCellValueFactory(data -> data.getValue().macAddressProperty());

        TableColumn<TableIpAddress, String> ipAddress =new TableColumn<>(language.IP_ADDRESS(lang));
        ipAddress.setCellValueFactory(data -> data.getValue().ipAddressProperty());

        TableColumn<TableIpAddress, String> masc = new TableColumn<>("Masc");
        masc.setCellValueFactory(data -> data.getValue().mascProperty());

        TableColumn<TableIpAddress, String> gateway = new TableColumn<>("Gateway");
        gateway.setCellValueFactory(data -> data.getValue().gatewayProperty());

        TableColumn<TableIpAddress, String> equipmentRoom = new TableColumn<>(language.ROOM(lang));
        equipmentRoom.setCellValueFactory(data -> data.getValue().roomProperty());

        TableColumn<TableIpAddress, String> equipmentRoomLocation = new TableColumn<>(language.LOCATION(lang));
        equipmentRoomLocation.setCellValueFactory(data -> data.getValue().locationProperty());

        tableView.getColumns().setAll(equipmentName, equipmentManufacturer, equipmentSerialNumber, macAddress, ipAddress, masc, gateway
        , equipmentRoom, equipmentRoomLocation);

        tableView.setItems(observableList);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TableIpAddress>() {
            @Override
            public void changed(ObservableValue<? extends TableIpAddress> observableValue, TableIpAddress tableIpAddress, TableIpAddress t1) {
                if (tableView.getSelectionModel().getSelectedItem().getSerialNumber() != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOneItem.fxml"));

                    try {

                        Scene scene = new Scene(loader.load());
                        Stage stage = new Stage();

                        Equipment equipmentDate = new Equipment();
                        for (Equipment equipment : company.getEquipmentList()) {
                            if (tableView.getSelectionModel().getSelectedItem().getSerialNumber().equals(equipment.getSerialNumber())) {
                                equipmentDate = equipment;
                            }
                        }
                        handlerLang.registerObserverLang(loader.getController());
                        handlerLang.onNewDataLang(new DataLang(lang));
                        FragmentEquipmentOneItemController fragmentEquipmentOneItemController = loader.getController();
                        fragmentEquipmentOneItemController.setEquip(equipmentDate);

                        stage.setTitle((tableView.getSelectionModel().getSelectedItem().getModel() + " : "
                                + (tableView.getSelectionModel().getSelectedItem()).getSerialNumber()));
                        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/"
                                + (tableView.getSelectionModel().getSelectedItem()).getImage()))));

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        return tableView;
    }

    public TableView<TableIpAddress> createTabIpDante(List<Equipment> equipmentLists){

        TableView<TableIpAddress> tableView = new TableView<>();
        ObservableList<TableIpAddress> observableList = FXCollections.observableArrayList();

        List<String> pushIpDante = new ListOfIpAddressForTheCompany(equipmentLists).receiveListAddressDante();

        for (String s : pushIpDante){
            Equipment equipment2 = new Equipment();
            for (Equipment equipment1 : equipmentLists){
                if (s.equals(equipment1.getDanteIpAddress())){
                    equipment2 = equipment1;
                }
            }
            TableIpAddress tableIpAddress = new TableIpAddress(equipment2, s, "dante");
            observableList.add(tableIpAddress);
        }


        TableColumn<TableIpAddress, String> equipmentName =new TableColumn<>(language.NAME(lang));
        equipmentName.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<TableIpAddress, String> equipmentManufacturer = new TableColumn<>(language.MANUFACTURER(lang));
        equipmentManufacturer.setCellValueFactory(data -> data.getValue().manufacturerProperty());

        TableColumn<TableIpAddress, String> equipmentSerialNumber = new TableColumn<>(language.SERIAL_NUMBER(lang));
        equipmentSerialNumber.setCellValueFactory(data -> data.getValue().serialNumberProperty());

        TableColumn<TableIpAddress, String> macAddress =new TableColumn<>(language.MAC_ADDRESS(lang));
        macAddress.setCellValueFactory(data -> data.getValue().macAddressProperty());

        TableColumn<TableIpAddress, String> ipAddressDante =new TableColumn<>(language.IP_ADDRESS(lang));
        ipAddressDante.setCellValueFactory(data -> data.getValue().danteIpAddressProperty());

        TableColumn<TableIpAddress, String> mascDante = new TableColumn<>("Masc");
        mascDante.setCellValueFactory(data -> data.getValue().danteMascProperty());

        TableColumn<TableIpAddress, String> gatewayDante = new TableColumn<>("Gateway");
        gatewayDante.setCellValueFactory(data -> data.getValue().danteGatewayProperty());

        TableColumn<TableIpAddress, String> equipmentRoom = new TableColumn<>(language.ROOM(lang));
        equipmentRoom.setCellValueFactory(data -> data.getValue().roomProperty());

        TableColumn<TableIpAddress, String> equipmentRoomLocation = new TableColumn<>(language.LOCATION(lang));
        equipmentRoomLocation.setCellValueFactory(data -> data.getValue().locationProperty());

        tableView.getColumns().setAll(equipmentName, equipmentManufacturer, equipmentSerialNumber, macAddress, ipAddressDante, mascDante, gatewayDante
                , equipmentRoom, equipmentRoomLocation);

        tableView.setItems(observableList);

        tableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TableIpAddress>() {
            @Override
            public void changed(ObservableValue<? extends TableIpAddress> observableValue, TableIpAddress tableIpAddress, TableIpAddress t1) {
                if(tableView.getSelectionModel().getSelectedItem().getSerialNumber() != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOneItem.fxml"));

                    try {

                        Scene scene = new Scene(loader.load());
                        Stage stage = new Stage();

                        Equipment equipmentDate = new Equipment();
                        for (Equipment equipment : company.getEquipmentList()) {
                            if (tableView.getSelectionModel().getSelectedItem().getSerialNumber().equals(equipment.getSerialNumber())) {
                                equipmentDate = equipment;
                            }
                        }
                        handlerLang.registerObserverLang(loader.getController());
                        handlerLang.onNewDataLang(new DataLang(lang));
                        FragmentEquipmentOneItemController fragmentEquipmentOneItemController = loader.getController();
                        fragmentEquipmentOneItemController.setEquip(equipmentDate);

                        stage.setTitle((tableView.getSelectionModel().getSelectedItem().getModel() + " : "
                                + (tableView.getSelectionModel().getSelectedItem()).getSerialNumber()));
                        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/"
                                + (tableView.getSelectionModel().getSelectedItem()).getImage()))));

                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        return tableView;
    }

    private String tr(String s){

        String[] net = s.split("\\.");
        List<Integer> list = new ArrayList<>();
        for (String d : net) {
            list.add(Integer.valueOf(d));
        }
        String network1 = list.get(0).toString();
        String network2 = list.get(1).toString();
        String subnet = list.get(2).toString();
        String device = list.get(3).toString();

        String r = network1 + "." + network2 + "." + subnet;
        return r;
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        handlerLang.onNewDataLang(new DataLang(lang));
    }
}
