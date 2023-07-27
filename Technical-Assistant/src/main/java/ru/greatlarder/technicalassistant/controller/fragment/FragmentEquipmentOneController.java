package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.CheckString;
import ru.greatlarder.technicalassistant.services.check.CheckingStringImpl;
import ru.greatlarder.technicalassistant.services.check.check_equipment.*;
import ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite.*;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.UpdateEquipmentMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.GetListAllDefectToEquipmentSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIpAddressSqlite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.UpdateEquipmentSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.ListRoomByCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageNameEquipment;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageNameEquipmentImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static ru.greatlarder.technicalassistant.services.style.StyleSRC.*;

public class FragmentEquipmentOneController implements Initializable {
    @FXML
    public Label model;
    @FXML public Label manufacturer;
    @FXML public Label serialNumber;
    @FXML public Label ipAddress;
    @FXML public Label macAddress;
    @FXML public Label login;
    @FXML public Label password;
    @FXML public Label room;
    @FXML public Label indoorLocation;
    @FXML public Label date;
    @FXML public Label condition;
    @FXML public GridPane gridPane;
    @FXML public Label labelQuantityDefect;
    @FXML public ImageView imgChangeIp;
    @FXML public ImageView imgChangeLogin;
    @FXML public ImageView imgChangePassword;
    @FXML public ImageView imgChangeRoom;
    @FXML public ImageView imgChangeLocationToRoom;
    @FXML public ImageView imgConnectWebInterface;
    @FXML public ImageView imgChangeCondition;
    @FXML public Button btnSaveNewIp;
    @FXML public TextField tfNewLogin;
    @FXML public TextField tfNewPassword;
    @FXML public TextField tfNewRoomLocation;
    @FXML public Button btnSaveNewLogin;
    @FXML public Button btnSaveNewPassword;
    @FXML public Button btnSaveNewRoom;
    @FXML public Button btnSaveNewLocationToRoom;
    @FXML public ComboBox<String> menuButtonCondition;
    @FXML public Button btnSaveNewCondition;
    @FXML public HBox hBoxConditions;
    @FXML public HBox hBoxNewRoomLocations;
    @FXML public HBox hBoxNewRoom;
    @FXML public HBox hBoxNewPassword;
    @FXML public HBox hBoxNewLogin;
    @FXML public HBox hBoxNewIp;
    @FXML public TextField tfNet1;
    @FXML public TextField tfNet2;
    @FXML public TextField tfSubNet;
    @FXML public TextField tfDeviceNet;
    @FXML public TextField tfMascNet1;
    @FXML public TextField tfMascNet2;
    @FXML public TextField tfMascSub;
    @FXML public TextField tfMascDev;
    @FXML public Button btnSaveNewMasc;
    @FXML public TextField tfGatewayNet1;
    @FXML public TextField tfGatewayNet2;
    @FXML public TextField tfGatewayDev;
    @FXML public TextField tfGatewaySub;
    @FXML public Button btnSaveNewGateway;
    @FXML public ImageView imgChangeMasc;
    @FXML public ImageView imgChangeGateway;
    @FXML public Label labelSubnetMasc;
    @FXML public Label labelGateway;
    @FXML public HBox hBoxMasc;
    @FXML public HBox hBoxGateway;
    @FXML public HBox hBoxGatewayDante;
    @FXML public Label labelIpDante;
    @FXML public Label labelMascDante;
    @FXML public Label labelGatewayDante;
    @FXML public ImageView imgChangeIpDante;
    @FXML public ImageView imgChangeMascDante;
    @FXML public ImageView imgChangeGatewayDante;
    @FXML public HBox hBoxIpDanteChange;
    @FXML public TextField tfDanteNet1;
    @FXML public TextField tfDanteNet2;
    @FXML public HBox hBoxDanteMasc;
    @FXML public TextField tfDanteDev;
    @FXML public Button btnSaveDanteIp;
    @FXML public TextField tfDanteSubIp;
    @FXML public TextField tfDanteMascNet1;
    @FXML public TextField tfDanteMascNet2;
    @FXML public TextField tfDanteMascSub;
    @FXML public TextField tfDanteMascDev;
    @FXML public Button btnSaveDanteMasc;
    @FXML public TextField tfDanteGatewayNet1;
    @FXML public TextField tfDanteGatewayNet2;
    @FXML public TextField tfDanteGatewaySub;
    @FXML public TextField tfDanteGatewayDev;
    @FXML public Button btnSaveDanteGateway;
    @FXML public Label labelFrequency;
    @FXML public ImageView imgFrequencyChange;
    @FXML public HBox hBoxFrequency;
    @FXML public TextField tfFrequency1;
    @FXML public TextField tfFrequency2;
    @FXML public Button btnSaveFrequency;
    @FXML public Label labelFrequencyName;
    @FXML public Label labelMaxTimeLampName;
    @FXML public Label labelTimeWorkLampProjName;
    @FXML public Label labelMaximumLampOperatingTime;
    @FXML public Label labelTimeWorkLampProj;
    @FXML public ImageView imgChangeLampTimeWork;
    @FXML public HBox hBoxChangeTimeWorkLampProj;
    @FXML public TextField tfNewTimeWorkLampProj;
    @FXML public Button btnNewTimeWorkLampProj;
    @FXML public SplitPane splitPaneOEI;
    @FXML public Label labelOutletNumber;
    @FXML public Label labelPortNumber;
    @FXML public TextField tfNewOutletNumber;
    @FXML public Button btnSaveNewOutletNumber;
    @FXML public HBox hBoxPortNumber;
    @FXML public TextField tfPortNumber;
    @FXML public Button btnSaveNewPortNumber;
    @FXML public ImageView imgChangePortNumber;
    @FXML public Label labelTextPortNumber;
    @FXML public Label  labelTextOutletNumber;
    @FXML public ImageView imgOpenHBoxChangeOutletNumber;
    @FXML public HBox hBoxNewOutletNumber;
    @FXML public ComboBox<Object> comboBoxChoiceSwitcher;
    @FXML public ChoiceBox<String> choiceBoxPatchPanel;
    @FXML public Label labelModel;
    @FXML public Label labelManufacturer;
    @FXML public Label labelSerialNumber;
    @FXML public Label labelIpAddress;
    @FXML public Label labelMacAddress;
    @FXML public Label labelLogin;
    @FXML public Label labelPassword;
    @FXML public Label labelRoom;
    @FXML public Label labelLocation;
    @FXML public Label labelStartWork;
    @FXML public Label labelCondition;
    @FXML public Label labelManual;
    @FXML public Label labelWebInterface;
    @FXML public Label labelDefect;
    @FXML public Label labelNewLocation;
    @FXML public Label labelNewRoom;
    @FXML public Label labelNewPassword;
    @FXML public Label labelNewLogin;
    @FXML public Label labelNewIpAddress;
    @FXML public Label labelNewMasc;
    @FXML public Label labelNewGateway;
    @FXML public Label labelIpAddressDante;
    @FXML public Label labelNewIpAddressDante;
    @FXML public Label labelNewMascDante;
    @FXML public Label labelNewGatewayDante;
    @FXML public Label labelChangeFrequency;
    @FXML public Label labelNewTime;
    @FXML public Label labelMac1;
    @FXML public Label labelMac2;
    @FXML public Label labelMac3;
    @FXML public ImageView imgChangeMac1;
    @FXML public ImageView imgChangeMac2;
    @FXML public ImageView imgChangeMac3;
    @FXML public ImageView imgChangeMac;
    @FXML public HBox hBoxNewMacAddress;
    @FXML public TextField oui1;
    @FXML public TextField oui2;
    @FXML public TextField oui3;
    @FXML public TextField uaa1;
    @FXML public TextField uaa2;
    @FXML public TextField uaa3;
    @FXML public Button btnSaveNewMac;
    @FXML public HBox hBoxNewMacAddress1;
    @FXML public TextField oui11;
    @FXML public TextField oui12;
    @FXML public TextField oui13;
    @FXML public TextField uaa11;
    @FXML public TextField uaa12;
    @FXML public TextField uaa13;
    @FXML public Button btnSaveNewMac1;
    @FXML public HBox hBoxNewMacAddress2;
    @FXML public TextField oui21;
    @FXML public TextField oui22;
    @FXML public TextField oui23;
    @FXML public TextField uaa21;
    @FXML public TextField uaa22;
    @FXML public TextField uaa23;
    @FXML public HBox hBoxMacAddress3;
    @FXML public TextField oui31;
    @FXML public TextField oui32;
    @FXML public TextField oui33;
    @FXML public TextField uaa31;
    @FXML public TextField uaa32;
    @FXML public TextField uaa33;
    @FXML public Button btnSaveNewMac2;
    @FXML public Button btnSaveNewMac3;
    @FXML public Label m1;
    @FXML public Label m2;
    @FXML public Label m3;
    @FXML public Label labelMascAddressDante;
    @FXML public Label labelGatewayAddressDante;
    @FXML public Label labelDiagonal;
    @FXML public Label diagonal;
    @FXML public Label labelStorage;
    @FXML public Button btnChangeDataStorage;
    @FXML public ComboBox<String> comboBoxNewRooms;
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    Equipment equipment;
    String lang;
    SplitPane scrollPaneFragmentIdenticalData;
    User user;
    Company company;
    CheckString checkingStringWithACondition = new CheckingStringImpl();
    CheckIpAddress checkEquipmentIpAddress = new CheckingIpForEmploymentInTheDatabaseSQLite();
    CheckIpAddressDante checkIpAddressDante = new CheckingIpDanteForEmploymentInTheDatabaseSQLite();
    CheckString checkingForANumber = new CheckingStringImpl();
    LanguageNameEquipment languageNameEquipment = new LanguageNameEquipmentImpl();

    public void setLabelStorage(String labelStorage) {
        this.labelStorage.setText(labelStorage);
    }

    public void setBtnChangeDataStorage(String btnChangeDataStorage, SplitPane pane) {
        this.scrollPaneFragmentIdenticalData = pane;
        this.btnChangeDataStorage.setVisible(true);
        this.btnChangeDataStorage.setText(btnChangeDataStorage);
    }

    public void setEquip(Equipment equipment){
        this.equipment = equipment;
        setLanguage(lang);
        start();
        displayLayout(equipment);
    }

    public void setLanguage(String lang1){

        labelOutletNumber.setText(language.OUTLET_NUMBER(lang1));
        labelPortNumber.setText(language.PORT_NUMBER_IN_THE_SWITCH(lang1));
        btnSaveNewOutletNumber.setText(language.SAVE(lang1));
        btnSaveNewPortNumber.setText(language.SAVE(lang1));
        btnSaveNewMac.setText(language.SAVE(lang1));
        btnSaveNewMac1.setText(language.SAVE(lang1));
        btnSaveNewMac2.setText(language.SAVE(lang1));
        btnSaveNewMac3.setText(language.SAVE(lang1));

        labelModel.setText(language.MODEL(lang1));
        labelManufacturer.setText(language.MANUFACTURER(lang1));
        labelSerialNumber.setText(language.SERIAL_NUMBER(lang1));
        labelIpAddress.setText(language.IP_ADDRESS(lang1));
        labelMacAddress.setText(language.MAC_ADDRESS(lang1));
        labelLogin.setText(language.LOGIN(lang1));
        labelPassword.setText(language.PASSWORD(lang1));
        labelRoom.setText(language.ROOM(lang1));
        labelLocation.setText(language.LOCATION(lang1));
        labelStartWork.setText(language.START_DATE_OF_OPERATION(lang1));
        labelCondition.setText(language.CONDITION(lang1));
        labelManual.setText(language.MANUAL(lang1));
        labelWebInterface.setText(language.WEB_INTERFACE(lang1));
        labelDefect.setText(language.DEFECT(lang1));
        labelNewLocation.setText(language.NEW_LOCATION(lang1));
        labelNewRoom.setText(language.NEW_ROOM(lang1));
        labelNewPassword.setText(language.NEW_PASSWORD(lang1));
        labelNewLogin.setText(language.NEW_LOGIN(lang1));
        labelNewIpAddress.setText(language.NEW_IP_ADDRESS(lang1));
        labelNewMasc.setText(language.NEW_MASC(lang1));
        labelNewGateway.setText(language.NEW_GATEWAY(lang1));
        labelIpAddressDante.setText(language.IP_ADDRESS_DANTE(lang1));
        labelNewIpAddressDante.setText(language.NEW_IP_ADDRESS_DANTE(lang1));
        labelNewMascDante.setText(language.NEW_MASC_DANTE(lang1));
        labelNewGatewayDante.setText(language.NEW_GATEWAY_DANTE(lang1));
        labelChangeFrequency.setText(language.CHANGE_FREQUENCY(lang1));
        labelNewTime.setText(language.NEW_TIME_WORK_LAMP_PROJECTOR(lang1));
        menuButtonCondition.setPromptText(language.CHANGE_CONDITION(lang1));
        labelFrequencyName.setText(language.FREQUENCY(lang1));
    }

    public void openManual(MouseEvent mouseEvent) {
        if(equipment.getManual() != null) {
            File pdfFile = new File(fileManager.getUrlFileManual(equipment.getManual()));
            if (pdfFile.exists()) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(pdfFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void connectWebInterface(MouseEvent mouseEvent) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.setJavaScriptEnabled(true);
        webEngine.load("http://" + equipment.getIpAddress());

            Scene scene = new Scene(webView);
            Stage stage = new Stage();

            stage.setTitle(equipment.getName() + " : " + equipment.getSerialNumber());
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/"
                    + equipment.getImage()))));

            stage.setScene(scene);
            stage.show();
    }

    public void changeIp(MouseEvent mouseEvent) {
        hBoxNewIp.setVisible(true);
        hBoxNewIp.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeLogin(MouseEvent mouseEvent) {
        hBoxNewLogin.setVisible(true);
        hBoxNewLogin.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changePassword(MouseEvent mouseEvent) {
        hBoxNewPassword.setVisible(true);
        hBoxNewPassword.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeRoom(MouseEvent mouseEvent) {
        hBoxNewRoom.setVisible(true);
        hBoxNewRoom.setManaged(true);
        GetListRoom listRoom = new ListRoomByCompanySQLite();
        List<String> nameRoomList = new ArrayList<>();
        for(Room room : listRoom.getListRoom(user, company.getNameCompany(), null)){
            nameRoomList.add(room.getNameRoom());
        }
        comboBoxNewRooms.setPromptText(equipment.getRoom());
        comboBoxNewRooms.setItems(FXCollections.observableArrayList(nameRoomList));

        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeLocationToRoom(MouseEvent mouseEvent) {
        hBoxNewRoomLocations.setVisible(true);
        hBoxNewRoomLocations.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeCondition(MouseEvent mouseEvent) {
        hBoxConditions.setVisible(true);
        hBoxConditions.setManaged(true);
        menuButtonCondition.setItems(FXCollections.observableArrayList(language.status_sheet("ru")));
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void saveNewIp(ActionEvent actionEvent) {
        CheckIpAddress checkIpAddress = new CheckingIpForEmploymentInTheDatabaseSQLite();
        String ip = tfNet1.getText() + "." + tfNet2.getText() + "." + tfSubNet.getText() + "." + tfDeviceNet.getText();
        
        if(!checkIpAddress.checkIpAddress(user, company, ip)){
            equipment.setIpAddress(ip);
            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            updateEquipment.updateEquipment(user, company, equipment);
            GetEquipment getEquipment = new EquipmentByIpAddressSqlite();
            if (getEquipment.getEquipment(user, equipment.getCompany()
                    , equipment.getSerialNumber()).getIpAddress().equals(ip)) {
                ipAddress.setText(ip);
                hBoxNewIp.setVisible(false);
                hBoxNewIp.setManaged(false);
            } else {
                tfNet1.setStyle(new TextField().getStyle());
                tfNet2.setStyle(new TextField().getStyle());
                tfSubNet.setStyle(new TextField().getStyle());
                tfDeviceNet.setStyle(new TextField().getStyle());
            }
        } else {
            tfNet1.setStyle(STYLE_DANGER);
            tfNet2.setStyle(STYLE_DANGER);
            tfSubNet.setStyle(STYLE_DANGER);
            tfDeviceNet.setStyle(STYLE_DANGER);
        }

    }

    public void saveNewLogin(ActionEvent actionEvent) {
        equipment.setLogin(tfNewLogin.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getLogin().equals(
                tfNewLogin.getText())){
            login.setText(tfNewLogin.getText());
            setEquip(equipment);//------------???
            hBoxNewLogin.setVisible(false);
            hBoxNewLogin.setManaged(false);
        } else {
            hBoxNewLogin.setStyle(STYLE_DANGER);
        }
    }

    public void saveNewPassword(ActionEvent actionEvent) {
        equipment.setPassword(tfNewPassword.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();
        
        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getPassword().equals(
                tfNewPassword.getText())){
            password.setText(tfNewPassword.getText());
            setEquip(equipment);
            hBoxNewPassword.setVisible(false);
            hBoxNewPassword.setManaged(false);
        } else {
            hBoxNewPassword.setStyle(STYLE_DANGER);
        }
    }

    public void saveNewRoom(ActionEvent actionEvent) {
        equipment.setRoom(comboBoxNewRooms.getValue());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getRoom().equals(
                comboBoxNewRooms.getValue())){
            room.setText(comboBoxNewRooms.getValue());
            setEquip(equipment);
            hBoxNewRoom.setVisible(false);
            hBoxNewRoom.setManaged(false);
        } else {
            hBoxNewRoom.setStyle(STYLE_DANGER);
        }
    }

    public void saveNewLocationToRoom(ActionEvent actionEvent) {
        equipment.setLocation(tfNewRoomLocation.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();
        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getLocation().equals(
                tfNewRoomLocation.getText())){
            indoorLocation.setText(tfNewRoomLocation.getText());
            setEquip(equipment);
            hBoxNewRoomLocations.setVisible(false);
            hBoxNewRoomLocations.setManaged(false);
        } else {
            hBoxNewRoomLocations.setStyle(STYLE_DANGER);
        }
    }

    public void saveNewCondition(ActionEvent actionEvent) {
        equipment.setCondition(menuButtonCondition.getValue());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();
        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getCondition().equals(
                menuButtonCondition.getValue())){
            condition.setText(menuButtonCondition.getValue());
            setEquip(equipment);
            hBoxConditions.setVisible(false);
            hBoxConditions.setManaged(false);
        } else {
            hBoxConditions.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRelisedNet1(KeyEvent keyEvent) {
        if (!tfNet1.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfNet1.getText())) {
                if (tfNet1.getText().length() <= 3) {
                    if (Integer.parseInt(tfNet1.getText()) <= 256) {
                        tfNet1.setStyle(new TextField().getStyle());
                    } else tfNet1.setStyle(STYLE_DANGER);
                } else tfNet1.setStyle(STYLE_DANGER);
            } else tfNet1.setStyle(STYLE_DANGER);
        }else tfNet1.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedNet2(KeyEvent keyEvent) {
        if (!tfNet2.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfNet2.getText())) {
                if (tfNet2.getText().length() <= 3) {
                    if (Integer.parseInt(tfNet2.getText()) <= 256) {
                        tfNet2.setStyle(new TextField().getStyle());
                    } else tfNet2.setStyle(STYLE_DANGER);
                } else tfNet2.setStyle(STYLE_DANGER);
            } else tfNet2.setStyle(STYLE_DANGER);
        } else tfNet2.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedSubNet(KeyEvent keyEvent) {
        if (!tfSubNet.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfSubNet.getText())) {
                if (tfSubNet.getText().length() <= 3) {
                    if (Integer.parseInt(tfSubNet.getText()) <= 256) {
                        tfSubNet.setStyle(new TextField().getStyle());
                    } else tfSubNet.setStyle(STYLE_DANGER);
                } else tfSubNet.setStyle(STYLE_DANGER);
            } else tfSubNet.setStyle(STYLE_DANGER);
        }else tfSubNet.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedDeviceNet(KeyEvent keyEvent) {
        if (!tfDeviceNet.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDeviceNet.getText())){
                if(tfDeviceNet.getText().length() <= 3) {
                    if(Integer.parseInt(tfDeviceNet.getText()) <= 256) {
                        if(!checkEquipmentIpAddress.checkIpAddress(user, company
                                , tfNet1.getText() + "." + tfNet2.getText()
                                        + "." + tfSubNet.getText() + "." + tfDeviceNet.getText())){
                            tfNet1.setStyle(new TextField().getStyle());
                            tfNet2.setStyle(new TextField().getStyle());
                            tfSubNet.setStyle(new TextField().getStyle());
                            tfDeviceNet.setStyle(new TextField().getStyle());
                        } else {
                            tfNet1.setStyle(STYLE_DANGER);
                            tfNet2.setStyle(STYLE_DANGER);
                            tfSubNet.setStyle(STYLE_DANGER);
                            tfDeviceNet.setStyle(STYLE_DANGER);
                        }
                    } else tfDeviceNet.setStyle(STYLE_DANGER);
                } else tfDeviceNet.setStyle(STYLE_DANGER);
            } else tfDeviceNet.setStyle(STYLE_DANGER);
        } else tfDeviceNet.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedMascNet1(KeyEvent keyEvent) {
        if (!tfMascNet1.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfMascNet1.getText())) {
                if (tfMascNet1.getText().length() <= 3) {
                    if (Integer.parseInt(tfMascNet1.getText()) <= 256) {
                        tfMascNet1.setStyle(new TextField().getStyle());
                    } else tfMascNet1.setStyle(STYLE_DANGER);
                } else tfMascNet1.setStyle(STYLE_DANGER);
            } else tfMascNet1.setStyle(STYLE_DANGER);
        }else tfMascNet1.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedMascNet2(KeyEvent keyEvent) {
        if (!tfMascNet2.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfMascNet2.getText())) {
                if (tfMascNet2.getText().length() <= 3) {
                    if (Integer.parseInt(tfMascNet2.getText()) <= 256) {
                        tfGatewayNet1.setStyle(new TextField().getStyle());
                    } else tfMascNet2.setStyle(STYLE_DANGER);
                } else tfMascNet2.setStyle(STYLE_DANGER);
            } else tfMascNet2.setStyle(STYLE_DANGER);
        } else tfMascNet2.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedMascSub(KeyEvent keyEvent) {
        if (!tfMascSub.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfMascSub.getText())) {
                if (tfMascSub.getText().length() <= 3) {
                    if (Integer.parseInt(tfMascSub.getText()) <= 256) {
                        tfMascSub.setStyle(new TextField().getStyle());
                    } else tfMascSub.setStyle(STYLE_DANGER);
                } else tfMascSub.setStyle(STYLE_DANGER);
            } else tfMascSub.setStyle(STYLE_DANGER);
        } else tfMascSub.setStyle(new TextField().getStyle());
    }

    public void onKeyMascDev(KeyEvent keyEvent) {
        if (!tfMascDev.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfMascDev.getText())) {
                if (tfMascDev.getText().length() <= 3) {
                    if (Integer.parseInt(tfMascDev.getText()) <= 256) {
                        tfMascDev.setStyle(new TextField().getStyle());
                    } else tfMascDev.setStyle(STYLE_DANGER);
                } else tfMascDev.setStyle(STYLE_DANGER);
            } else tfMascDev.setStyle(STYLE_DANGER);
        } else tfMascDev.setStyle(new TextField().getStyle());
    }

    public void saveNewMasc(MouseEvent mouseEvent) {

        equipment.setMasc(tfMascNet1.getText() + "." + tfMascNet2.getText() + "." + tfMascSub.getText() + "." + tfMascDev.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getMasc().equals(tfMascNet1.getText() + "." + tfMascNet2.getText()
                + "." + tfMascSub.getText() + "." + tfMascDev.getText())){
            labelSubnetMasc.setText(tfMascNet1.getText() + "." + tfMascNet2.getText() + "." + tfMascSub.getText()
                    + "." + tfMascDev.getText());
            setEquip(equipment);
            hBoxMasc.setVisible(false);
            hBoxMasc.setManaged(false);
        } else {
            tfMascNet1.setStyle(new TextField().getStyle());
            tfMascNet2.setStyle(new TextField().getStyle());
            tfMascSub.setStyle(new TextField().getStyle());
            tfMascDev.setStyle(new TextField().getStyle());
        }
    }

    public void onKeyRelisedGatewayNet1(KeyEvent keyEvent) {
        if (!tfGatewayNet1.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfGatewayNet1.getText())) {
                if (tfGatewayNet1.getText().length() <= 3) {
                    if (Integer.parseInt(tfGatewayNet1.getText()) <= 256) {
                        tfGatewayNet1.setStyle(new TextField().getStyle());
                    } else tfGatewayNet1.setStyle(STYLE_DANGER);
                } else tfGatewayNet1.setStyle(STYLE_DANGER);
            } else tfGatewayNet1.setStyle(STYLE_DANGER);
        }else tfGatewayNet1.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedGatewayNet2(KeyEvent keyEvent) {
        if (!tfGatewayNet2.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfGatewayNet2.getText())) {
                if (tfGatewayNet2.getText().length() <= 3) {
                    if (Integer.parseInt(tfGatewayNet2.getText()) <= 256) {
                        tfGatewayNet2.setStyle(new TextField().getStyle());
                    } else tfGatewayNet2.setStyle(STYLE_DANGER);
                } else tfGatewayNet2.setStyle(STYLE_DANGER);
            } else tfGatewayNet2.setStyle(STYLE_DANGER);
        } else tfGatewayNet2.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedGatewaySub(KeyEvent keyEvent) {
        if (!tfGatewaySub.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfGatewaySub.getText())) {
                if (tfGatewaySub.getText().length() <= 3) {
                    if (Integer.parseInt(tfGatewaySub.getText()) <= 256) {
                        tfGatewaySub.setStyle(new TextField().getStyle());
                    } else tfGatewaySub.setStyle(STYLE_DANGER);
                } else tfGatewaySub.setStyle(STYLE_DANGER);
            } else tfGatewaySub.setStyle(STYLE_DANGER);
        } else tfGatewaySub.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedGatewayDev(KeyEvent keyEvent) {
        if (!tfGatewayDev.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfGatewayDev.getText())) {
                if (tfGatewayDev.getText().length() <= 3) {
                    if (Integer.parseInt(tfGatewayDev.getText()) <= 256) {
                        tfGatewayDev.setStyle(new TextField().getStyle());
                    } else tfGatewayDev.setStyle(STYLE_DANGER);
                } else tfGatewayDev.setStyle(STYLE_DANGER);
            } else tfGatewayDev.setStyle(STYLE_DANGER);
        }else tfGatewayDev.setStyle(new TextField().getStyle());
    }

    public void saveNewGateway(MouseEvent mouseEvent) {

        equipment.setGateway(tfGatewayNet1.getText() + "." + tfGatewayNet2.getText() + "." + tfGatewaySub.getText() + "."
                + tfGatewayDev.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getGateway()
                .equals(tfGatewayNet1.getText() + "." + tfGatewayNet2.getText() + "." + tfGatewaySub.getText() + "."
                        + tfGatewayDev.getText())){
            labelGateway.setText(tfGatewayNet1.getText() + "." + tfGatewayNet2.getText() + "." + tfGatewaySub.getText()
                    + "." + tfGatewayDev.getText());
            setEquip(equipment);
            hBoxGateway.setManaged(false);
        } else {
            tfGatewayNet1.setStyle(new TextField().getStyle());
            tfGatewayNet2.setStyle(new TextField().getStyle());
            tfGatewaySub.setStyle(new TextField().getStyle());
            tfGatewayDev.setStyle(new TextField().getStyle());
        }
    }

    public void changeMasc(MouseEvent mouseEvent) {
        hBoxMasc.setVisible(true);
        hBoxMasc.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeGateway(MouseEvent mouseEvent) {
        hBoxGateway.setVisible(true);
        this.hBoxGateway.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeDanteIp(MouseEvent mouseEvent) {
        hBoxIpDanteChange.setVisible(true);
        hBoxIpDanteChange.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeMascDante(MouseEvent mouseEvent) {
        hBoxDanteMasc.setVisible(true);
        hBoxDanteMasc.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeGatewayDante(MouseEvent mouseEvent) {
        hBoxGatewayDante.setVisible(true);
        hBoxGatewayDante.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void okrDanteNet1Ip(KeyEvent keyEvent) {
        if (!tfDanteNet1.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteNet1.getText())) {
                if (tfDanteNet1.getText().length() <= 3) {
                    if (Integer.parseInt(tfDanteNet1.getText()) <= 256) {
                        tfDanteNet1.setStyle(new TextField().getStyle());
                    } else tfDanteNet1.setStyle(STYLE_DANGER);
                } else tfDanteNet1.setStyle(STYLE_DANGER);
            } else tfDanteNet1.setStyle(STYLE_DANGER);
        }else tfDanteNet1.setStyle(new TextField().getStyle());
    }

    public void okrDanteNet2Ip(KeyEvent keyEvent) {
        if (!tfDanteNet2.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteNet2.getText())) {
                if (tfDanteNet2.getText().length() <= 3) {
                    if (Integer.parseInt(tfDanteNet2.getText()) <= 256) {
                        tfDanteNet2.setStyle(new TextField().getStyle());
                    } else tfDanteNet2.setStyle(STYLE_DANGER);
                } else tfDanteNet2.setStyle(STYLE_DANGER);
            } else tfDanteNet2.setStyle(STYLE_DANGER);
        }else tfDanteNet2.setStyle(new TextField().getStyle());
    }

    public void okrDanteSubIp(KeyEvent keyEvent) {
        if (!tfDanteSubIp.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteSubIp.getText())) {
                if (tfDanteSubIp.getText().length() <= 3) {
                    if (Integer.parseInt(tfDanteSubIp.getText()) <= 256) {
                        tfDanteSubIp.setStyle(new TextField().getStyle());
                    } else tfDanteSubIp.setStyle(STYLE_DANGER);
                } else tfDanteSubIp.setStyle(STYLE_DANGER);
            } else tfDanteSubIp.setStyle(STYLE_DANGER);
        }else tfDanteSubIp.setStyle(new TextField().getStyle());
    }

    public void okrDanteDevIp(KeyEvent keyEvent) {
        if (!tfDanteDev.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteDev.getText())){
                if(tfDanteDev.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteDev.getText()) <= 256) {
                        if(!checkIpAddressDante.checkIpAddress(user, company
                                , tfDanteNet1.getText() + "." + tfDanteNet2.getText()
                                        + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText())){
                            tfDanteNet1.setStyle(new TextField().getStyle());
                            tfDanteNet2.setStyle(new TextField().getStyle());
                            tfDanteSubIp.setStyle(new TextField().getStyle());
                            tfDanteDev.setStyle(new TextField().getStyle());
                        } else {
                            tfDanteNet1.setStyle(STYLE_DANGER);
                            tfDanteNet2.setStyle(STYLE_DANGER);
                            tfDanteSubIp.setStyle(STYLE_DANGER);
                            tfDanteDev.setStyle(STYLE_DANGER);
                        }
                    } else tfDanteDev.setStyle(STYLE_DANGER);
                } else tfDanteDev.setStyle(STYLE_DANGER);
            } else tfDanteDev.setStyle(STYLE_DANGER);
        } else tfDanteDev.setStyle(new TextField().getStyle());
    }

    public void saveDanteIp(ActionEvent actionEvent) {
        CheckIpAddressDante checkIpAddressDante = new CheckingIpDanteForEmploymentInTheDatabaseSQLite();
        if (!checkIpAddressDante.checkIpAddress(user, company, tfDanteNet1.getText() + "." + tfDanteNet2.getText()
                + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText())) {

            equipment.setDanteIpAddress(tfDanteNet1.getText() + "." + tfDanteNet2.getText()
                    + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText());
            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            updateEquipment.updateEquipment(user, company, equipment);
            GetEquipment getEquipment = new EquipmentByIdSQLite();
            Equipment e = getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId()));

            if (e.getDanteIpAddress() != null && e.getDanteIpAddress().equals(tfDanteNet1.getText() + "."
                    + tfDanteNet2.getText() + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText())) {
                labelIpDante.setText(tfDanteNet1.getText() + "." + tfDanteNet2.getText() + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText());
                setEquip(equipment);
                hBoxIpDanteChange.setVisible(false);
                hBoxIpDanteChange.setManaged(false);
            } else {
                tfDanteNet1.setStyle(new TextField().getStyle());
                tfDanteNet2.setStyle(new TextField().getStyle());
                tfDanteSubIp.setStyle(new TextField().getStyle());
                tfDanteDev.setStyle(new TextField().getStyle());
            }
        }
    }

    public void okrDanteMascNet1(KeyEvent keyEvent) {
        if (!tfDanteMascNet1.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteMascNet1.getText())){
                if(tfDanteMascNet1.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteMascNet1.getText()) <= 256) {
                        tfDanteMascNet1.setStyle(new TextField().getStyle());
                    } else tfDanteMascNet1.setStyle(STYLE_DANGER);
                } else tfDanteMascNet1.setStyle(STYLE_DANGER);
            } else tfDanteMascNet1.setStyle(STYLE_DANGER);
        } else tfDanteMascNet1.setStyle(new TextField().getStyle());
    }

    public void okrDanteMascNet2(KeyEvent keyEvent) {
        if (!tfDanteMascNet2.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteMascNet2.getText())){
                if(tfDanteMascNet2.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteMascNet2.getText()) <= 256) {
                        tfDanteMascNet2.setStyle(new TextField().getStyle());
                    } else tfDanteMascNet2.setStyle(STYLE_DANGER);
                } else tfDanteMascNet2.setStyle(STYLE_DANGER);
            } else tfDanteMascNet2.setStyle(STYLE_DANGER);
        } else tfDanteMascNet2.setStyle(new TextField().getStyle());
    }

    public void okrDanteMaskSub(KeyEvent keyEvent) {
        if (!tfDanteMascSub.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteMascSub.getText())){
                if(tfDanteMascSub.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteMascSub.getText()) <= 256) {
                        tfDanteMascSub.setStyle(new TextField().getStyle());
                    } else tfDanteMascSub.setStyle(STYLE_DANGER);
                } else tfDanteMascSub.setStyle(STYLE_DANGER);
            } else tfDanteMascSub.setStyle(STYLE_DANGER);
        } else tfDanteMascSub.setStyle(new TextField().getStyle());
    }

    public void okrDanteMaskDev(KeyEvent keyEvent) {
        if (!tfDanteMascDev.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteMascDev.getText())){
                if(tfDanteMascDev.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteMascDev.getText()) <= 256) {
                        tfDanteMascDev.setStyle(new TextField().getStyle());
                    } else tfDanteMascDev.setStyle(STYLE_DANGER);
                } else tfDanteMascDev.setStyle(STYLE_DANGER);
            } else tfDanteMascDev.setStyle(STYLE_DANGER);
        } else tfDanteMascDev.setStyle(new TextField().getStyle());
    }

    public void saveDanteMasc(ActionEvent actionEvent) {

        equipment.setDanteMasc(tfDanteMascNet1.getText() + "." + tfDanteMascNet2.getText() + "." + tfDanteMascSub.getText()
                + "." + tfDanteMascDev.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getDanteMasc()
                .equals(tfDanteMascNet1.getText() + "." + tfDanteMascNet2.getText()
                + "." + tfDanteMascSub.getText() + "." + tfDanteMascDev.getText())){
            labelMascDante.setText(tfDanteMascNet1.getText() + "." + tfDanteMascNet2.getText() + "." + tfDanteMascSub.getText()
                    + "." + tfDanteMascDev.getText());
            setEquip(equipment);
            hBoxDanteMasc.setVisible(false);
            hBoxDanteMasc.setManaged(false);
        } else {
            tfDanteMascNet1.setStyle(new TextField().getStyle());
            tfDanteMascNet2.setStyle(new TextField().getStyle());
            tfDanteMascSub.setStyle(new TextField().getStyle());
            tfDanteMascDev.setStyle(new TextField().getStyle());
        }
    }

    public void okrDanteGatewayNet1(KeyEvent keyEvent) {
        if (!tfDanteGatewayNet1.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteGatewayNet1.getText())){
                if(tfDanteGatewayNet1.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteGatewayNet1.getText()) <= 256) {
                        tfDanteGatewayNet1.setStyle(new TextField().getStyle());
                    } else tfDanteGatewayNet1.setStyle(STYLE_DANGER);
                } else tfDanteGatewayNet1.setStyle(STYLE_DANGER);
            } else tfDanteGatewayNet1.setStyle(STYLE_DANGER);
        } else tfDanteGatewayNet1.setStyle(new TextField().getStyle());
    }

    public void okrDanteGatewayNet2(KeyEvent keyEvent) {
        if (!tfDanteGatewayNet2.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteGatewayNet2.getText())){
                if(tfDanteGatewayNet2.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteGatewayNet2.getText()) <= 256) {
                        tfDanteGatewayNet2.setStyle(new TextField().getStyle());
                    } else tfDanteGatewayNet2.setStyle(STYLE_DANGER);
                } else tfDanteGatewayNet2.setStyle(STYLE_DANGER);
            } else tfDanteGatewayNet2.setStyle(STYLE_DANGER);
        } else tfDanteGatewayNet2.setStyle(new TextField().getStyle());
    }

    public void okrDanteGatewaySub(KeyEvent keyEvent) {
        if (!tfDanteGatewaySub.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteGatewaySub.getText())){
                if(tfDanteGatewaySub.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteGatewaySub.getText()) <= 256) {
                        tfDanteGatewaySub.setStyle(new TextField().getStyle());
                    } else tfDanteGatewaySub.setStyle(STYLE_DANGER);
                } else tfDanteGatewaySub.setStyle(STYLE_DANGER);
            } else tfDanteGatewaySub.setStyle(STYLE_DANGER);
        } else tfDanteGatewaySub.setStyle(new TextField().getStyle());
    }

    public void okrDanteGatewayDev(KeyEvent keyEvent) {
        if (!tfDanteGatewayDev.getText().isEmpty()) {
            if (checkingForANumber.checkingForANumber(tfDanteGatewayDev.getText())){
                if(tfDanteGatewayDev.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteGatewayDev.getText()) <= 256) {
                        tfDanteGatewayDev.setStyle(new TextField().getStyle());
                    } else tfDanteGatewayDev.setStyle(STYLE_DANGER);
                } else tfDanteGatewayDev.setStyle(STYLE_DANGER);
            } else tfDanteGatewayDev.setStyle(STYLE_DANGER);
        } else tfDanteGatewayDev.setStyle(new TextField().getStyle());
    }

    public void saveDanteGateway(ActionEvent actionEvent) {

        equipment.setDanteGateway(tfDanteGatewayNet1.getText() + "." + tfDanteGatewayNet2.getText() + "."
                        + tfDanteGatewaySub.getText() + "." + tfDanteGatewayDev.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())).getDanteGateway()
                .equals(tfDanteGatewayNet1.getText() + "." + tfDanteGatewayNet2.getText() + "." + tfDanteGatewaySub.getText()
                        + "." + tfDanteGatewayDev.getText())){
            labelGatewayDante.setText(tfDanteGatewayNet1.getText() + "." + tfDanteGatewayNet2.getText() + "."
                    + tfDanteGatewaySub.getText() + "." + tfDanteGatewayDev.getText());
            setEquip(equipment);
            hBoxGatewayDante.setVisible(false);
            hBoxGatewayDante.setManaged(false);
        } else {
            tfDanteGatewayNet1.setStyle(new TextField().getStyle());
            tfDanteGatewayNet2.setStyle(new TextField().getStyle());
            tfDanteGatewaySub.setStyle(new TextField().getStyle());
            tfDanteGatewayDev.setStyle(new TextField().getStyle());
        }
    }

    public void start(){
        hBoxNewMacAddress.setVisible(false);
        hBoxNewMacAddress.setManaged(false);
        hBoxNewMacAddress1.setVisible(false);
        hBoxNewMacAddress1.setManaged(false);
        hBoxNewMacAddress2.setVisible(false);
        hBoxNewMacAddress2.setManaged(false);
        hBoxMacAddress3.setVisible(false);
        hBoxMacAddress3.setManaged(false);
        hBoxFrequency.setVisible(false);
        hBoxFrequency.setManaged(false);
        hBoxNewIp.setVisible(false);
        hBoxNewIp.setManaged(false);
        hBoxMasc.setVisible(false);
        hBoxMasc.setManaged(false);
        hBoxGateway.setVisible(false);
        hBoxGateway.setManaged(false);
        hBoxIpDanteChange.setVisible(false);
        hBoxIpDanteChange.setManaged(false);
        hBoxDanteMasc.setVisible(false);
        hBoxDanteMasc.setManaged(false);
        hBoxGatewayDante.setVisible(false);
        hBoxGatewayDante.setManaged(false);
        hBoxNewLogin.setVisible(false);
        hBoxNewLogin.setManaged(false);
        hBoxNewPassword.setVisible(false);
        hBoxNewPassword.setManaged(false);
        hBoxNewRoom.setVisible(false);
        hBoxNewRoom.setManaged(false);
        hBoxNewRoomLocations.setVisible(false);
        hBoxNewRoomLocations.setManaged(false);
        hBoxConditions.setVisible(false);
        hBoxConditions.setManaged(false);
        hBoxChangeTimeWorkLampProj.setVisible(false);
        hBoxChangeTimeWorkLampProj.setManaged(false);
        hBoxNewOutletNumber.setVisible(false);
        hBoxNewOutletNumber.setManaged(false);
        hBoxPortNumber.setVisible(false);
        hBoxPortNumber.setManaged(false);

        m1.setVisible(false);
        m1.setManaged(false);
        labelMac1.setVisible(false);
        labelMac1.setManaged(false);
        imgChangeMac1.setVisible(false);
        imgChangeMac1.setManaged(false);

        m2.setVisible(false);
        m2.setManaged(false);
        labelMac2.setVisible(false);
        labelMac2.setManaged(false);
        imgChangeMac2.setVisible(false);
        imgChangeMac2.setManaged(false);

        m3.setVisible(false);
        m3.setManaged(false);
        labelMac3.setVisible(false);
        labelMac3.setManaged(false);
        imgChangeMac3.setVisible(false);
        imgChangeMac3.setManaged(false);

        labelFrequencyName.setVisible(false);
        labelFrequencyName.setManaged(false);
        labelFrequency.setVisible(false);
        labelFrequency.setManaged(false);
        imgFrequencyChange.setVisible(false);
        imgFrequencyChange.setManaged(false);

        labelIpAddressDante.setVisible(false);
        labelIpAddressDante.setManaged(false);
        labelIpDante.setVisible(false);
        labelIpDante.setManaged(false);
        imgChangeIpDante.setVisible(false);
        imgChangeIpDante.setManaged(false);

        labelMascAddressDante.setVisible(false);
        labelMascAddressDante.setManaged(false);
        labelMascDante.setVisible(false);
        labelMascDante.setManaged(false);
        imgChangeMascDante.setVisible(false);
        imgChangeMascDante.setManaged(false);

        labelGatewayAddressDante.setVisible(false);
        labelGatewayAddressDante.setManaged(false);
        labelGatewayDante.setVisible(false);
        labelGatewayDante.setManaged(false);
        imgChangeGatewayDante.setVisible(false);
        imgChangeGatewayDante.setManaged(false);

        labelMaxTimeLampName.setVisible(false);
        labelMaxTimeLampName.setManaged(false);
        labelMaximumLampOperatingTime.setVisible(false);
        labelMaximumLampOperatingTime.setManaged(false);

        labelTimeWorkLampProjName.setVisible(false);
        labelTimeWorkLampProjName.setManaged(false);
        labelTimeWorkLampProj.setVisible(false);
        labelTimeWorkLampProj.setManaged(false);
        imgChangeLampTimeWork.setVisible(false);
        imgChangeLampTimeWork.setManaged(false);

        splitPaneOEI.setVisible(false);
        splitPaneOEI.setManaged(false);

        labelDiagonal.setVisible(false);
        labelDiagonal.setManaged(false);
        diagonal.setVisible(false);
        diagonal.setManaged(false);
    }

    public void changeFrequency(MouseEvent mouseEvent) {
        hBoxFrequency.setVisible(true);
        hBoxFrequency.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void okrFrequency1(KeyEvent keyEvent) {
        if (!tfFrequency1.getText().trim().isEmpty()) {
            if(checkingStringWithACondition.checkingStringWithACondition(tfFrequency1.getText())){
                if (tfFrequency1.getText().length() <= 3) {
                    tfFrequency1.setStyle(new TextField().getStyle());
                } else tfFrequency1.setStyle(STYLE_DANGER);
            } else tfFrequency1.setStyle(STYLE_DANGER);
        } else tfFrequency1.setStyle(new TextField().getStyle());
    }

    public void okrFrequency2(KeyEvent keyEvent) {
        CheckFrequency checkFrequency = new CheckingFrequencyDatabaseSQLite();
        if (!tfFrequency2.getText().trim().isEmpty()) {
            if (checkingStringWithACondition.checkingStringWithACondition(tfFrequency2.getText())) {
                if(tfFrequency2.getText().length() <= 3) {
                    if (!checkFrequency.checkFrequency(user, equipment.getCompany(), (tfFrequency1.getText() + "." + tfFrequency2.getText()))) {
                        tfFrequency1.setStyle(new TextField().getStyle());
                        tfFrequency2.setStyle(new TextField().getStyle());
                    } else {
                        tfFrequency1.setStyle(STYLE_DANGER);
                        tfFrequency2.setStyle(STYLE_DANGER);
                    }
                } else tfFrequency2.setStyle(STYLE_DANGER);
            } else tfFrequency2.setStyle(STYLE_DANGER);
        } else tfFrequency2.setStyle(new TextField().getStyle());
    }

    public void saveFrequency(ActionEvent actionEvent) {

        ((Microphone)equipment).setFrequency(tfFrequency1.getText() + "." + tfFrequency2.getText());
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        String frequency = ((Microphone)(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())))).getFrequency();

        if(frequency != null && frequency.equals(tfFrequency1.getText() + "." + tfFrequency2.getText())){
            setEquip(equipment);
            hBoxFrequency.setVisible(false);
            hBoxFrequency.setManaged(false);
            labelFrequency.setText(tfFrequency1.getText() + "." + tfFrequency2.getText());
        } else {
            tfFrequency1.setStyle(STYLE_DANGER);
            tfFrequency2.setStyle(STYLE_DANGER);
        }

    }

    public void displayLayout(Equipment equipment){

        model.setText(equipment.getModel());
        manufacturer.setText(equipment.getManufacturer());
        serialNumber.setText(equipment.getSerialNumber());
        macAddress.setText(equipment.getMacAddress());

        if(equipment instanceof Microphone microphone){
            labelFrequencyName.setVisible(true);
            labelFrequencyName.setManaged(true);
            labelFrequency.setVisible(true);
            labelFrequency.setManaged(true);
            labelFrequency.setText(((Microphone) equipment).getFrequency());
            imgFrequencyChange.setVisible(true);
            imgFrequencyChange.setManaged(true);

            labelIpAddressDante.setVisible(true);
            labelIpAddressDante.setManaged(true);
            labelIpDante.setVisible(true);
            labelIpDante.setManaged(true);
            labelIpDante.setText(equipment.getDanteIpAddress());
            imgChangeIpDante.setVisible(true);
            imgChangeIpDante.setManaged(true);

            labelMascAddressDante.setVisible(true);
            labelMascAddressDante.setManaged(true);
            labelMascDante.setVisible(true);
            labelMascDante.setManaged(true);
            labelMascDante.setText(equipment.getDanteMasc());
            imgChangeMascDante.setVisible(true);
            imgChangeMascDante.setManaged(true);

            labelGatewayAddressDante.setVisible(true);
            labelGatewayAddressDante.setManaged(true);
            labelGatewayDante.setVisible(true);
            labelGatewayDante.setManaged(true);
            labelGatewayDante.setText(equipment.getDanteGateway());
            imgChangeGatewayDante.setVisible(true);
            imgChangeGatewayDante.setManaged(true);

        }
        if(equipment instanceof Projector projector){
            labelMaxTimeLampName.setVisible(true);
            labelMaxTimeLampName.setManaged(true);
            labelMaximumLampOperatingTime.setVisible(true);
            labelMaximumLampOperatingTime.setManaged(true);
            labelMaximumLampOperatingTime.setText(((Projector) equipment).getMaximumLampOperatingTimeProjector().toString());
            labelTimeWorkLampProjName.setVisible(true);
            labelTimeWorkLampProjName.setManaged(true);
            labelTimeWorkLampProj.setVisible(true);
            labelTimeWorkLampProj.setManaged(true);
            labelTimeWorkLampProj.setText(((Projector) equipment).getTimeWorkLampProjector().toString());
            imgChangeLampTimeWork.setVisible(true);
            imgChangeLampTimeWork.setManaged(true);
            updateAudio();
        }
        if(equipment instanceof NetworkSwitch networkSwitch){
            splitPaneOEI.setVisible(true);
            splitPaneOEI.setManaged(true);

            labelIpAddressDante.setVisible(true);
            labelIpAddressDante.setManaged(true);
            labelIpDante.setVisible(true);
            labelIpDante.setManaged(true);
            labelIpDante.setText(equipment.getDanteIpAddress());
            imgChangeIpDante.setVisible(true);
            imgChangeIpDante.setManaged(true);

            labelMascAddressDante.setVisible(true);
            labelMascAddressDante.setManaged(true);
            labelMascDante.setVisible(true);
            labelMascDante.setManaged(true);
            labelMascDante.setText(equipment.getDanteMasc());
            imgChangeMascDante.setVisible(true);
            imgChangeMascDante.setManaged(true);

            labelGatewayAddressDante.setVisible(true);
            labelGatewayAddressDante.setManaged(true);
            labelGatewayDante.setVisible(true);
            labelGatewayDante.setManaged(true);
            labelGatewayDante.setText(equipment.getDanteGateway());
            imgChangeGatewayDante.setVisible(true);
            imgChangeGatewayDante.setManaged(true);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentPort.fxml"));
            try {
                splitPaneOEI.getItems().add(loader.load());
                FragmentPortController portController = loader.getController();
                portController.setPort(((NetworkSwitch) equipment));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(equipment instanceof VideoTransmitter videoTransmitter) {
            updateAudio();
        }
        if(equipment instanceof VideoReceiver videoReceiver) {
            updateAudio();
        }
        if(equipment instanceof AcousticSpeaker acousticSpeaker) {
            updateAudio();
        }
        if(equipment instanceof AudioAmplifier audioAmplifier) {
            updateAudio();
        }
        if(equipment instanceof AudioInterface audioInterface) {
            updateAudio();
        }
        if(equipment instanceof AudioProcessor audioProcessor) {
            updateAudio();
        }
        if(equipment instanceof MatrixSwitcher matrixSwitcher) {
            updateAudio();
        }
        if(equipment instanceof TvPanel tvPanel){
            labelDiagonal.setVisible(true);
            labelDiagonal.setManaged(true);
            diagonal.setVisible(true);
            diagonal.setManaged(true);
            diagonal.setText(((TvPanel) equipment).getDiagonal());
        }
        if(equipment instanceof TouchControlPanel touchControlPanel){
            labelDiagonal.setVisible(true);
            labelDiagonal.setManaged(true);
            diagonal.setVisible(true);
            diagonal.setManaged(true);
            diagonal.setText(((TouchControlPanel) equipment).getDiagonal());
        }

        ipAddress.setText(equipment.getIpAddress());
        labelSubnetMasc.setText(equipment.getMasc());
        labelGateway.setText(equipment.getGateway());
        login.setText(equipment.getLogin());
        password.setText(equipment.getPassword());
        room.setText(equipment.getRoom());
        indoorLocation.setText(equipment.getLocation());
        condition.setText(equipment.getCondition());
        date.setText(equipment.getDateWork().toString());

        GetListDefect getListDefect = new GetListAllDefectToEquipmentSQLite();
        labelQuantityDefect.setText(String.valueOf(getListDefect.getListDefect(user, company.getNameCompany(), String.valueOf(equipment.getId())).size()));

        labelTextOutletNumber.setText(equipment.getOutletNumber());
        labelTextPortNumber.setText(equipment.getPortNumberInTheSwitch().toString());

    }
    private void updateAudio(){
        labelIpAddressDante.setVisible(true);
        labelIpAddressDante.setManaged(true);
        labelIpDante.setVisible(true);
        labelIpDante.setManaged(true);
        labelIpDante.setText(equipment.getDanteIpAddress());
        imgChangeIpDante.setVisible(true);
        imgChangeIpDante.setManaged(true);

        labelMascAddressDante.setVisible(true);
        labelMascAddressDante.setManaged(true);
        labelMascDante.setVisible(true);
        labelMascDante.setManaged(true);
        labelMascDante.setText(equipment.getDanteMasc());
        imgChangeMascDante.setVisible(true);
        imgChangeMascDante.setManaged(true);

        labelGatewayAddressDante.setVisible(true);
        labelGatewayAddressDante.setManaged(true);
        labelGatewayDante.setVisible(true);
        labelGatewayDante.setManaged(true);
        labelGatewayDante.setText(equipment.getDanteGateway());
        imgChangeGatewayDante.setVisible(true);
        imgChangeGatewayDante.setManaged(true);

        m1.setVisible(true);
        m1.setManaged(true);
        m2.setVisible(true);
        m2.setManaged(true);
        m3.setVisible(true);
        m3.setManaged(true);
        labelMac1.setVisible(true);
        labelMac1.setManaged(true);
        labelMac1.setText(equipment.getMacAddress1());
        labelMac2.setVisible(true);
        labelMac2.setManaged(true);
        labelMac2.setText(equipment.getMacAddress2());
        labelMac3.setVisible(true);
        labelMac3.setManaged(true);
        labelMac3.setText(equipment.getMacAddress3());
        imgChangeMac1.setVisible(true);
        imgChangeMac1.setManaged(true);
        imgChangeMac2.setVisible(true);
        imgChangeMac2.setManaged(true);
        imgChangeMac3.setVisible(true);
        imgChangeMac3.setManaged(true);
    }

    public void changeTimeWorkLampProj(MouseEvent mouseEvent) {
        hBoxChangeTimeWorkLampProj.setVisible(true);
        this.hBoxChangeTimeWorkLampProj.setManaged(true);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void okrNewTimeWorkLampProj(KeyEvent keyEvent) {
        if (!tfNewTimeWorkLampProj.getText().isEmpty()) {
            if (!checkingForANumber.checkingForANumber(tfNewTimeWorkLampProj.getText())) {
                tfNewTimeWorkLampProj.setStyle(STYLE_DANGER);
            } else if (checkingForANumber.checkingForANumber(tfNewTimeWorkLampProj.getText())) {
                tfNewTimeWorkLampProj.setStyle(new TextField().getStyle());
            } else {
                tfNewTimeWorkLampProj.setStyle(new TextField().getStyle());
            }
        }
    }

    public void saveNewTimeWorkLampProj(MouseEvent mouseEvent) {

        ((Projector)equipment).setTimeWorkLampProjector(Integer.valueOf(tfNewTimeWorkLampProj.getText()));
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);
        GetEquipment getEquipment = new EquipmentByIdSQLite();

        String time = String.valueOf(((Projector)(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId())))).getTimeWorkLampProjector());

        if(time.equals(tfNewTimeWorkLampProj.getText())){
            labelTimeWorkLampProj.setText(time);
            hBoxChangeTimeWorkLampProj.setVisible(false);
            hBoxChangeTimeWorkLampProj.setManaged(false);
            setEquip(equipment);
            Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.sizeToScene();
        } else tfNewTimeWorkLampProj.setStyle(STYLE_DANGER);
    }

    public void onKeyRelisedNewOutletNumber(KeyEvent keyEvent) {
        if(!tfNewOutletNumber.getText().isEmpty() && tfNewOutletNumber.getText() != null){
            tfNewOutletNumber.setStyle(new TextField().getStyle());
            tfNewOutletNumber.setStyle(new TextField().getStyle());
        } else tfNewOutletNumber.setStyle(STYLE_DANGER);
    }

    public void saveNewOutletNumber(MouseEvent mouseEvent) {
        if (tfNewOutletNumber.getText() != null && !tfNewOutletNumber.getText().isEmpty()) {

            equipment.setOutletNumber(tfNewTimeWorkLampProj.getText());
            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            updateEquipment.updateEquipment(user, company, equipment);

            tfNewOutletNumber.setStyle(STYLE_EXCELLENT);
            setEquip(equipment);
            hBoxNewOutletNumber.setStyle(new HBox().getStyle());
        } else {
            hBoxNewOutletNumber.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRelisedPortNumber(KeyEvent keyEvent) {

        if(!tfPortNumber.getText().isEmpty() && tfPortNumber.getText() != null && checkingForANumber.checkingForANumber(tfPortNumber.getText())){
            tfPortNumber.setStyle(new TextField().getStyle());
            if(Integer.parseInt(tfPortNumber.getText()) <= 48) {
                tfPortNumber.setStyle(new TextField().getStyle());

                if (comboBoxChoiceSwitcher.getValue() != null && tfPortNumber.getText() != null) {
                    GetEquipment getEquipment = new EquipmentByIdSQLite();
                    NetworkSwitch networkSwitch = (NetworkSwitch) getEquipment.getEquipment(user, equipment.getCompany()
                            , comboBoxChoiceSwitcher.getValue().toString());
                    if (networkSwitch.getEquipmentList().get(Integer.parseInt(tfPortNumber.getText())-1) == null) {
                        tfPortNumber.setStyle(new TextField().getStyle());
                    } else {
                        tfPortNumber.setStyle(STYLE_DANGER);
                    }
                } else hBoxPortNumber.setStyle(STYLE_DANGER);

            } else tfPortNumber.setStyle(STYLE_DANGER);
        } else tfPortNumber.setStyle(STYLE_DANGER);
    }

    public void openHBoxChangePortNumber(MouseEvent mouseEvent) {
        GetListEquipment getListEquipment = new ListEquipmentByNameSQLite();
        List<Equipment> listEquipment = new ArrayList<>(getListEquipment.getListEquipment(user
                , company.getNameCompany(), languageNameEquipment.getNetworkSwitch(user.getLanguage())));
        if(!listEquipment.isEmpty()) {
            hBoxPortNumber.setVisible(true);
            hBoxPortNumber.setManaged(true);
            List<String> nameSwitch = new ArrayList<>();
            for(Equipment name : listEquipment){
                nameSwitch.add(name.getSerialNumber());
            }
            GetEquipment getEquipment = new EquipmentByIdSQLite();
            comboBoxChoiceSwitcher.setValue(getEquipment.getEquipment(user, company.getNameCompany()
                    , String.valueOf(equipment.getIdNetworkSwitcher())).getSerialNumber());
            comboBoxChoiceSwitcher.setItems(FXCollections.observableArrayList(nameSwitch));
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.sizeToScene();
        }
    }

    public void openHBoxOutlet(MouseEvent mouseEvent) {
        hBoxNewOutletNumber.setVisible(true);
        hBoxNewOutletNumber.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void saveNewPortSwitcher(MouseEvent actionEvent) {
        if (comboBoxChoiceSwitcher.getValue() != null && tfPortNumber.getText() != null) {
            GetEquipment getEquipment = new EquipmentByIdSQLite();
            NetworkSwitch networkSwitch = (NetworkSwitch) getEquipment.getEquipment(user, equipment.getCompany()
                    , comboBoxChoiceSwitcher.getValue().toString());
            if (networkSwitch.getEquipmentList().get(Integer.parseInt(tfPortNumber.getText())-1) == null) {
                equipment.setPortNumberInTheSwitch(Integer.valueOf(tfPortNumber.getText()));
                equipment.setIdNetworkSwitcher(networkSwitch.getId());

                UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
                updateEquipment.updateEquipment(user, company, equipment);
                setEquip(equipment);
                tfPortNumber.setStyle(STYLE_EXCELLENT);
                hBoxPortNumber.setStyle(new HBox().getStyle());
            } else {
                hBoxPortNumber.setStyle(STYLE_DANGER);
            }
        } else hBoxPortNumber.setStyle(STYLE_DANGER);
    }

    public void onActionMenuButtonCondition(MouseEvent actionEvent) {
        menuButtonCondition.setItems(FXCollections.observableArrayList(language.status_sheet(lang)));
    }

    public void onKeyOui1(KeyEvent keyEvent) {
        if (oui1.getText().isEmpty()) {
            oui1.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui1.getText()) || oui1.getText().length() > 2) {
            oui1.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui1.getText())) {
            oui1.setStyle(new TextField().getStyle());
        } else oui1.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2(KeyEvent keyEvent) {
        if (oui2.getText().isEmpty()) {
            oui2.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui2.getText()) || oui2.getText().length() > 2) {
            oui2.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui2.getText())) {
            oui2.setStyle(new TextField().getStyle());
        } else oui2.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3(KeyEvent keyEvent) {
        if (oui3.getText().isEmpty()) {
            oui3.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui3.getText()) || oui3.getText().length() > 2) {
            oui3.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui3.getText())) {
            oui3.setStyle(new TextField().getStyle());
        } else oui3.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1(KeyEvent keyEvent) {
        if (uaa1.getText().isEmpty()) {
            uaa1.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa1.getText()) || uaa1.getText().length() > 2) {
            uaa1.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa1.getText())) {
            uaa1.setStyle(new TextField().getStyle());
        } else uaa1.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2(KeyEvent keyEvent) {
        if (uaa2.getText().isEmpty()) {
            uaa2.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa2.getText()) || uaa2.getText().length() > 2) {
            uaa2.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa2.getText())) {
            uaa2.setStyle(new TextField().getStyle());
        } else uaa2.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3(KeyEvent keyEvent) {
        if (uaa3.getText().isEmpty()) {
            uaa3.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa3.getText()) || uaa3.getText().length() > 2) {
            uaa3.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa3.getText())) {
            uaa3.setStyle(new TextField().getStyle());
        } else uaa3.setStyle(new TextField().getStyle());

        if (!checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText().trim().isEmpty()) {
            hBoxNewMacAddress.setStyle(new HBox().getStyle());
            CheckMacAddress checkMacAddress = new CheckingMacAddressSQLite();
            if (!checkMacAddress.checkingEquipmentMacAddress(user, equipment.getCompany(), checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText())) {
                hBoxNewMacAddress.setStyle(new HBox().getStyle());
            } else {
                hBoxNewMacAddress.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxNewMacAddress.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui1_1(KeyEvent keyEvent) {
        if (oui11.getText().isEmpty()) {
            oui11.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui11.getText()) || oui11.getText().length() > 2) {
            oui11.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui11.getText())) {
            oui11.setStyle(new TextField().getStyle());
        } else oui11.setStyle(new TextField().getStyle());
    }

    public void onKeyOui1_2(KeyEvent keyEvent) {
        if (oui21.getText().isEmpty()) {
            oui21.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui21.getText()) || oui21.getText().length() > 2) {
            oui21.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui21.getText())) {
            oui21.setStyle(new TextField().getStyle());
        } else oui21.setStyle(new TextField().getStyle());
    }

    public void onKeyOui1_3(KeyEvent keyEvent) {
        if (oui31.getText().isEmpty()) {
            oui31.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui31.getText()) || oui31.getText().length() > 2) {
            oui31.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui31.getText())) {
            oui31.setStyle(new TextField().getStyle());
        } else oui31.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_1(KeyEvent keyEvent) {
        if (uaa11.getText().isEmpty()) {
            uaa11.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa11.getText()) || uaa11.getText().length() > 2) {
            uaa11.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa11.getText())) {
            uaa11.setStyle(new TextField().getStyle());
        } else uaa11.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_2(KeyEvent keyEvent) {
        if (uaa12.getText().isEmpty()) {
            uaa12.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa12.getText()) || uaa12.getText().length() > 2) {
            uaa12.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa21.getText())) {
            uaa12.setStyle(new TextField().getStyle());
        } else uaa12.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_3(KeyEvent keyEvent) {
        if (uaa13.getText().isEmpty()) {
            uaa13.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa13.getText()) || uaa13.getText().length() > 2) {
            uaa13.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa13.getText())) {
            uaa13.setStyle(new TextField().getStyle());
        } else uaa13.setStyle(new TextField().getStyle());

        if (!checkMac(oui11, oui12, oui13, uaa11, uaa12, uaa13).getText().trim().isEmpty()) {
            hBoxNewMacAddress1.setStyle(new HBox().getStyle());
            CheckMac1Address checkMacAddress1 = new CheckingMac1AddressSQLite();
            if (!checkMacAddress1.checkingEquipmentMac1Address(user, equipment.getCompany(), checkMac(oui11, oui12, oui13, uaa11, uaa12, uaa13).getText())) {
                hBoxNewMacAddress1.setStyle(new HBox().getStyle());
            } else {
                hBoxNewMacAddress1.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxNewMacAddress1.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui2_1(KeyEvent keyEvent) {
        if (oui21.getText().isEmpty()) {
            oui21.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui21.getText()) || oui21.getText().length() > 2) {
            oui21.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui21.getText())) {
            oui21.setStyle(new TextField().getStyle());
        } else oui21.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_2(KeyEvent keyEvent) {
        if (oui22.getText().isEmpty()) {
            oui22.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui22.getText()) || oui22.getText().length() > 2) {
            oui22.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui22.getText())) {
            oui22.setStyle(new TextField().getStyle());
        } else oui22.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_3(KeyEvent keyEvent) {
        if (oui23.getText().isEmpty()) {
            oui23.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui23.getText()) || oui23.getText().length() > 2) {
            oui23.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui23.getText())) {
            oui23.setStyle(new TextField().getStyle());
        } else oui23.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_1(KeyEvent keyEvent) {
        if (uaa21.getText().isEmpty()) {
            uaa21.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa21.getText()) || uaa21.getText().length() > 2) {
            uaa21.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa21.getText())) {
            uaa21.setStyle(new TextField().getStyle());
        } else uaa21.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_2(KeyEvent keyEvent) {
        if (uaa22.getText().isEmpty()) {
            uaa22.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa22.getText()) || uaa22.getText().length() > 2) {
            uaa22.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa22.getText())) {
            uaa22.setStyle(new TextField().getStyle());
        } else uaa22.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_3(KeyEvent keyEvent) {
        if (uaa23.getText().isEmpty()) {
            uaa23.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa23.getText()) || uaa23.getText().length() > 2) {
            uaa23.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa23.getText())) {
            uaa23.setStyle(new TextField().getStyle());
        } else uaa23.setStyle(new TextField().getStyle());

        if (!checkMac(oui21, oui22, oui23, uaa21, uaa22, uaa23).getText().trim().isEmpty()) {
            hBoxNewMacAddress2.setStyle(new HBox().getStyle());
            CheckMac2Address checkMacAddress2 = new CheckingMac2AddressSQLite();
            if (!checkMacAddress2.checkingEquipmentMac2Address(user, equipment.getCompany(), checkMac(oui21, oui22, oui23, uaa21, uaa22, uaa23).getText())) {
                hBoxNewMacAddress2.setStyle(new HBox().getStyle());
            } else {
                hBoxNewMacAddress2.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxNewMacAddress2.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui3_1(KeyEvent keyEvent) {
        if (oui31.getText().isEmpty()) {
            oui31.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui31.getText()) || oui31.getText().length() > 2) {
            oui31.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui31.getText())) {
            oui31.setStyle(new TextField().getStyle());
        } else oui31.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_2(KeyEvent keyEvent) {
        if (oui32.getText().isEmpty()) {
            oui32.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui32.getText()) || oui23.getText().length() > 2) {
            oui32.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui32.getText())) {
            oui32.setStyle(new TextField().getStyle());
        } else oui32.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_3(KeyEvent keyEvent) {
        if (oui33.getText().isEmpty()) {
            oui33.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(oui33.getText()) || oui33.getText().length() > 2) {
            oui33.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(oui33.getText())) {
            oui33.setStyle(new TextField().getStyle());
        } else oui33.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_1(KeyEvent keyEvent) {
        if (uaa31.getText().isEmpty()) {
            uaa31.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa31.getText()) || uaa31.getText().length() > 2) {
            uaa31.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa31.getText())) {
            uaa31.setStyle(new TextField().getStyle());
        } else uaa31.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_2(KeyEvent keyEvent) {
        if (uaa32.getText().isEmpty()) {
            uaa32.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa32.getText()) || uaa32.getText().length() > 2) {
            uaa32.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa32.getText())) {
            uaa32.setStyle(new TextField().getStyle());
        } else uaa32.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_3(KeyEvent keyEvent) {
        if (uaa33.getText().isEmpty()) {
            uaa33.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition.checkingStringWithACondition(uaa33.getText()) || uaa33.getText().length() > 2) {
            uaa33.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition.checkingStringWithACondition(uaa33.getText())) {
            uaa33.setStyle(new TextField().getStyle());
        } else uaa33.setStyle(new TextField().getStyle());

        if (!checkMac(oui13, oui23, oui33, uaa13, uaa23, uaa33).getText().trim().isEmpty()) {
            hBoxMacAddress3.setStyle(new HBox().getStyle());
            CheckMac3Address checkMacAddress3 = new CheckingMac3AddressSQLite();
            if (!checkMacAddress3.checkingEquipmentMac3Address(user, equipment.getCompany(), checkMac(oui13, oui23, oui33, uaa13, uaa23, uaa33).getText())) {
                hBoxMacAddress3.setStyle(new HBox().getStyle());
            } else {
                hBoxMacAddress3.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress3.setStyle(STYLE_DANGER);
        }
    }

    public void saveNewMac3(MouseEvent mouseEvent) {
        CheckMac3Address checkMacAddress3 = new CheckingMac3AddressSQLite();
        if(!checkMacAddress3.checkingEquipmentMac3Address(user, equipment.getCompany(), checkMac(oui31, oui32, oui33, uaa31, uaa32, uaa33).getText())) {
            hBoxMacAddress3.setStyle(new HBox().getStyle());

            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            equipment.setMacAddress3(checkMac(oui31, oui32, oui33, uaa31, uaa32, uaa33).getText());
            updateEquipment.updateEquipment(user, company, equipment);

            if(!checkMacAddress3.checkingEquipmentMac3Address(user, equipment.getCompany(), checkMac(oui31, oui32, oui33, uaa31, uaa32, uaa33).getText())){
                labelMac3.setText(checkMac(oui13, oui23, oui33, uaa13, uaa23, uaa33).getText());
                hBoxMacAddress3.setVisible(false);
                hBoxMacAddress3.setManaged(false);
                btnSaveNewMac3.setVisible(false);
                btnSaveNewMac3.setManaged(false);
            }else hBoxMacAddress3.setStyle(STYLE_DANGER);
        } else hBoxMacAddress3.setStyle(STYLE_DANGER);
    }

    public void saveNewMac2(MouseEvent mouseEvent) {
        CheckMac2Address checkMacAddress2 = new CheckingMac2AddressSQLite();
        if(!checkMacAddress2.checkingEquipmentMac2Address(user, equipment.getCompany(), checkMac(oui21, oui22, oui23, uaa21, uaa22, uaa23).getText())) {
            hBoxNewMacAddress2.setStyle(new HBox().getStyle());

            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            equipment.setMacAddress2(checkMac(oui21, oui22, oui23, uaa21, uaa22, uaa23).getText());
            updateEquipment.updateEquipment(user, company, equipment);

            if(!checkMacAddress2.checkingEquipmentMac2Address(user, equipment.getCompany(), checkMac(oui21, oui22, oui23, uaa21, uaa22, uaa23).getText())){
                labelMac2.setText(checkMac(oui12, oui22, oui32, uaa12, uaa22, uaa32).getText());
                hBoxNewMacAddress2.setVisible(false);
                hBoxNewMacAddress2.setManaged(false);
                btnSaveNewMac2.setVisible(false);
                btnSaveNewMac2.setManaged(false);
            }else hBoxNewMacAddress2.setStyle(STYLE_DANGER);
        } else hBoxNewMacAddress2.setStyle(STYLE_DANGER);
    }

    public void saveNewMac1(MouseEvent mouseEvent) {
        CheckMac1Address checkMacAddress1 = new CheckingMac1AddressSQLite();
        if(!checkMacAddress1.checkingEquipmentMac1Address(user, equipment.getCompany(), checkMac(oui11, oui12, oui13, uaa11, uaa12, uaa13).getText())) {
            hBoxNewMacAddress1.setStyle(new HBox().getStyle());

            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            equipment.setMacAddress1(checkMac(oui11, oui12, oui13, uaa11, uaa12, uaa13).getText());
            updateEquipment.updateEquipment(user, company, equipment);

            if(!checkMacAddress1.checkingEquipmentMac1Address(user, equipment.getCompany(), checkMac(oui11, oui12, oui13, uaa11, uaa12, uaa13).getText())){
                labelMac1.setText(checkMac(oui11, oui21, oui31, uaa11, uaa21, uaa31).getText());
                hBoxNewMacAddress1.setVisible(false);
                hBoxNewMacAddress1.setManaged(false);
                btnSaveNewMac1.setVisible(false);
                btnSaveNewMac1.setManaged(false);
            }else hBoxNewMacAddress1.setStyle(STYLE_DANGER);
        } else hBoxNewMacAddress1.setStyle(STYLE_DANGER);
    }

    public void saveBtnSaveNewMac(MouseEvent mouseEvent) {
        CheckMacAddress checkMacAddress = new CheckingMacAddressSQLite();
        if(!checkMacAddress.checkingEquipmentMacAddress(user, equipment.getCompany(), checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText())) {
            hBoxNewMacAddress.setStyle(new HBox().getStyle());

            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            equipment.setMacAddress(checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText());
            updateEquipment.updateEquipment(user, company, equipment);
            if(!checkMacAddress.checkingEquipmentMacAddress(user, equipment.getCompany(), checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText())){
                macAddress.setText(checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText());
                hBoxNewMacAddress.setVisible(false);
                hBoxNewMacAddress.setManaged(false);
                btnSaveNewMac.setVisible(false);
                btnSaveNewMac.setManaged(false);
            }else hBoxNewMacAddress.setStyle(STYLE_DANGER);
        } else hBoxNewMacAddress.setStyle(STYLE_DANGER);
    }


    public TextField checkMac(TextField ou1, TextField ou2, TextField ou3, TextField ua1, TextField ua2, TextField ua3) {

        TextField textField = new TextField();

        if (!ou1.getText().trim().isEmpty() && !ou2.getText().trim().isEmpty() && !ou3.getText().trim().isEmpty()
                && !ua1.getText().trim().isEmpty() && !ua2.getText().trim().isEmpty() && !ua3.getText().trim().isEmpty()) {
            if (ou1.getText().length() == 2 && ou2.getText().length() == 2 && ou3.getText().length() == 2
                    && ua1.getText().length() == 2 && ua2.getText().length() == 2 && ua3.getText().length() == 2) {
                textField.setText(oui1.getText() + ":" + oui2.getText() + ":" + oui3.getText() + ":"
                        + uaa1.getText() + ":" + uaa2.getText() + ":" + uaa3.getText());
            }
        } else {
            ou1.setStyle(STYLE_WARNING);
            ou2.setStyle(STYLE_WARNING);
            ou3.setStyle(STYLE_WARNING);
            ua1.setStyle(STYLE_WARNING);
            ua2.setStyle(STYLE_WARNING);
            ua3.setStyle(STYLE_WARNING);
        }
        return textField;
    }

    public void imgOpenChangeMac(MouseEvent mouseEvent) {
        hBoxNewMacAddress.setVisible(true);
        hBoxNewMacAddress.setManaged(true);
        btnSaveNewMac.setVisible(true);
        btnSaveNewMac.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void imgOpenChangeMac1(MouseEvent mouseEvent) {
        hBoxNewMacAddress1.setVisible(true);
        hBoxNewMacAddress1.setManaged(true);
        btnSaveNewMac1.setVisible(true);
        btnSaveNewMac1.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void imgOpenChangeMac2(MouseEvent mouseEvent) {
        hBoxNewMacAddress2.setVisible(true);
        hBoxNewMacAddress2.setManaged(true);
        btnSaveNewMac2.setVisible(true);
        btnSaveNewMac2.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void imgOpenChangeMac3(MouseEvent mouseEvent) {
        hBoxMacAddress3.setVisible(true);
        hBoxMacAddress3.setManaged(true);
        btnSaveNewMac3.setVisible(true);
        btnSaveNewMac3.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void changeDataInStorage(MouseEvent mouseEvent) {
        if (labelStorage.getText().equals("Локальное хранилище") || labelStorage.getText().equals("Local storage")){
            UpdateEquipment updateEquipment = new UpdateEquipmentMySQL();
            updateEquipment.updateEquipment(user, company, equipment);
            scrollPaneFragmentIdenticalData.getItems().clear();
        }
        if (labelStorage.getText().equals("Внешнее хранилище") || labelStorage.getText().equals("External storage")){
            UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
            updateEquipment.updateEquipment(user, company, equipment);
            scrollPaneFragmentIdenticalData.getItems().clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lang = GlobalLinkMainController.getMainController().getLang();
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
    }
}
