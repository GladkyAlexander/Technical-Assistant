package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddEquipmentController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.tables.table_ip_address.IpAddressModel;
import ru.greatlarder.technicalassistant.services.tables.table_ip_address.TableViewIpAddress;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FragmentIpAddressController implements Initializable {
    @FXML public SplitPane splitPane;
    @FXML public TabPane tabPaneIp;
    @FXML public TabPane tabPaneDante;
    Company company;
    User user;
    String lang;
    List<Equipment> equipments = new ArrayList<>();

    public void loadFragment() {

        Set<String> listIp = new HashSet<>();
        Set<String> listDante = new HashSet<>();

        GetListEquipment equipmentSQLite = new ListEquipmentByNameCompanySQLite();
        equipments = equipmentSQLite.getListEquipment(user, company.getNameCompany(), null);

        TableViewIpAddress getTableViewIpAddress = new TableViewIpAddress();

        for (Equipment equipment : equipments){
            if(equipment.getIpAddress() != null && !equipment.getIpAddress().isEmpty()){
                listIp.add(tr(equipment.getIpAddress()));
            }
        }
        for (String f : listIp){
            List<Equipment> lj = new ArrayList<>();
            for (Equipment equipment : equipments){
                if(equipment.getIpAddress() != null && tr(equipment.getIpAddress()).equals(f)){
                        lj.add(equipment);
                }
            }

            TableView<IpAddressModel> tIp = getTableViewIpAddress.getTableView(lj, equipments);
            setListener(tIp);
            tabPaneIp.getTabs().add(new Tab(f + " - IPv4", tIp));
        }

        for (Equipment equipment : equipments){
                if (equipment.getDanteIpAddress() != null) {
                    listDante.add(tr(equipment.getDanteIpAddress()));
                }
        }

        for (String f : listDante){
            List<Equipment> lj = new ArrayList<>();
            for (Equipment equipment : equipments){
                if(equipment.getDanteIpAddress() != null && tr(equipment.getDanteIpAddress()).equals(f)){
                    lj.add(equipment);
                }
            }
            TableView<IpAddressModel> tDante = getTableViewIpAddress.getTableView(lj, equipments);
            setListenerDante(tDante);
            tabPaneDante.getTabs().add(new Tab(f + " - Dante", tDante));
        }

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

        return network1 + "." + network2 + "." + subnet;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadFragment();
    }

    private void setListener(TableView<IpAddressModel> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (tableView.getSelectionModel().getSelectedItem().getSerialNumber() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));

                try {

                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();

                    Equipment equipmentDate = new Equipment();
                    for (Equipment equipment : equipments) {
                        if (tableView.getSelectionModel().getSelectedItem().getSerialNumber().equals(equipment.getSerialNumber())) {
                            equipmentDate = equipment;
                        }
                    }
                    FragmentEquipmentOneController fragmentEquipmentOneItemController = loader.getController();
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
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_equipment.fxml"));
                try {
                    GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FragmentAddEquipmentController controller = loader.getController();
                controller.loadFragment();
                String sd = tableView.getSelectionModel().getSelectedItem().getIpAddress();
                String[] words = sd.split("\\.");
                List<String> s = new ArrayList<String>(Arrays.asList(words));

                    controller.network1.setText(s.get(0));
                    controller.network2.setText(s.get(1));
                    controller.subnet.setText(s.get(2));
                    controller.device.setText(s.get(3));
            }
        });
    }
    private void setListenerDante(TableView<IpAddressModel> tableView) {
        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (tableView.getSelectionModel().getSelectedItem().getSerialNumber() != null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));

                try {

                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();

                    Equipment equipmentDate = new Equipment();
                    for (Equipment equipment : equipments) {
                        if (tableView.getSelectionModel().getSelectedItem().getSerialNumber().equals(equipment.getSerialNumber())) {
                            equipmentDate = equipment;
                        }
                    }
                    FragmentEquipmentOneController fragmentEquipmentOneItemController = loader.getController();
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
            } else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_equipment.fxml"));
                try {
                    GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FragmentAddEquipmentController controller = loader.getController();
                controller.loadFragment();
                String sd = tableView.getSelectionModel().getSelectedItem().getIpAddress();
                String[] words = sd.split("\\.");
                List<String> s = new ArrayList<String>(Arrays.asList(words));

                    controller.network1Dante.setText(s.get(0));
                    controller.network2Dante.setText(s.get(1));
                    controller.subnetDante.setText(s.get(2));
                    controller.deviceDante.setText(s.get(3));
            }
        });
    }
}
