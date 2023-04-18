package ru.greatlarder.technicalassistant.services.tables.table_ip_address;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.ListOfIpAddressForTheCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.util.ArrayList;
import java.util.List;

public class TableViewIpAddress {

    Language language = new LanguageImpl();
    String lang = GlobalLinkMainController.getMainController().getLang();
    List<Equipment> equipments = new ArrayList<>();

    public TableView<IpAddressModel> getTableView(List<Equipment> list, List<Equipment> list2) {
        equipments = list2;
        TableView<IpAddressModel> tableView = new TableView<>();
        ObservableList<IpAddressModel> observableList = FXCollections.observableArrayList();

        List<String> pushIp = new ListOfIpAddressForTheCompany(list).receiveListAddress();

        for (String s : pushIp) {
            Equipment equipment2 = new Equipment();
            for (Equipment equipment1 : list) {
                if (s.equals(equipment1.getIpAddress())) {
                    equipment2 = equipment1;
                }
            }
            IpAddressModel tableIpAddress = new IpAddressModel(equipment2, s, "network");
            observableList.add(tableIpAddress);
        }
        TableColumn<IpAddressModel, String> equipmentName = new TableColumn<>(language.NAME(lang));
        equipmentName.setCellValueFactory(data -> data.getValue().nameProperty());

        TableColumn<IpAddressModel, String> equipmentManufacturer = new TableColumn<>(language.MANUFACTURER(lang));
        equipmentManufacturer.setCellValueFactory(data -> data.getValue().manufacturerProperty());

        TableColumn<IpAddressModel, String> equipmentSerialNumber = new TableColumn<>(language.SERIAL_NUMBER(lang));
        equipmentSerialNumber.setCellValueFactory(data -> data.getValue().serialNumberProperty());

        TableColumn<IpAddressModel, String> macAddress = new TableColumn<>(language.MAC_ADDRESS(lang));
        macAddress.setCellValueFactory(data -> data.getValue().macAddressProperty());

        TableColumn<IpAddressModel, String> ipAddress = new TableColumn<>(language.IP_ADDRESS(lang));
        ipAddress.setCellValueFactory(data -> data.getValue().ipAddressProperty());

        TableColumn<IpAddressModel, String> masc = new TableColumn<>("Masc");
        masc.setCellValueFactory(data -> data.getValue().mascProperty());

        TableColumn<IpAddressModel, String> gateway = new TableColumn<>("Gateway");
        gateway.setCellValueFactory(data -> data.getValue().gatewayProperty());

        TableColumn<IpAddressModel, String> equipmentRoom = new TableColumn<>(language.ROOM(lang));
        equipmentRoom.setCellValueFactory(data -> data.getValue().roomProperty());

        TableColumn<IpAddressModel, String> equipmentRoomLocation = new TableColumn<>(language.LOCATION(lang));
        equipmentRoomLocation.setCellValueFactory(data -> data.getValue().locationProperty());

        tableView.getColumns().setAll(equipmentName, equipmentManufacturer, equipmentSerialNumber, macAddress, ipAddress, masc, gateway
                , equipmentRoom, equipmentRoomLocation);

        tableView.setItems(observableList);
        return tableView;
    }
}
