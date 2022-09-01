package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.Microphone;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;
import ru.greatlarder.technicalassistant.repository.DefectRepository;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.repository.impl.DefectRepositoryImpl;
import ru.greatlarder.technicalassistant.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_DANGER;
import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_EXCELLENT;

public class FragmentEquipmentOneItemController implements ObserverLang {
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
    @FXML public TextField tfNewRoom;
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
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    Language language = new LanguageImpl();
    DefectRepository defectRepository = new DefectRepositoryImpl();
    FileManager fileManager = new FileManagerImpl();
    Equipment equipment;
    String lang;

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

    public void openManual(javafx.scene.input.MouseEvent mouseEvent) {
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

    public void connectWebInterface(javafx.scene.input.MouseEvent mouseEvent) {
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

    public void SaveNewIp(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "ipAddress",
                tfNet1.getText() + "." + tfNet2.getText() + "." + tfSubNet.getText() + "." + tfDeviceNet.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getIpAddress().equals(tfNet1.getText() + "." + tfNet2.getText()
                + "." + tfSubNet.getText() + "." + tfDeviceNet.getText())){
            ipAddress.setText(tfNet1.getText() + "." + tfNet2.getText() + "." + tfSubNet.getText() + "." + tfDeviceNet.getText());
            hBoxNewIp.setVisible(false);
            hBoxNewIp.setManaged(false);
        } else {
            tfNet1.setStyle(new TextField().getStyle());
            tfNet2.setStyle(new TextField().getStyle());
            tfSubNet.setStyle(new TextField().getStyle());
            tfDeviceNet.setStyle(new TextField().getStyle());
        }
    }

    public void SaveNewLogin(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "login", tfNewLogin);
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(
                tfNewLogin.getText())){
            login.setText(tfNewLogin.getText());
            hBoxNewLogin.setVisible(false);
            hBoxNewLogin.setManaged(false);
        } else {
            hBoxNewLogin.setStyle(STYLE_DANGER);
        }
    }

    public void SaveNewPassword(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "password", tfNewPassword);
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(
                tfNewPassword.getText())){
            password.setText(tfNewPassword.getText());
            hBoxNewPassword.setVisible(false);
            hBoxNewPassword.setManaged(false);
        } else {
            hBoxNewPassword.setStyle(STYLE_DANGER);
        }
    }

    public void SaveNewRoom(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "room", tfNewRoom);
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(
                tfNewRoom.getText())){
            room.setText(tfNewRoom.getText());
            hBoxNewRoom.setVisible(false);
            hBoxNewRoom.setManaged(false);
        } else {
            hBoxNewRoom.setStyle(STYLE_DANGER);
        }
    }

    public void SaveNewLocationToRoom(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "location", tfNewRoomLocation.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(
                tfNewRoomLocation.getText())){
            indoorLocation.setText(tfNewRoomLocation.getText());
            hBoxNewRoomLocations.setVisible(false);
            hBoxNewRoomLocations.setManaged(false);
        } else {
            hBoxNewRoomLocations.setStyle(STYLE_DANGER);
        }
    }

    public void SaveNewCondition(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "condition", menuButtonCondition.getValue().toString());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(
                menuButtonCondition.getValue().toString())){
            condition.setText(menuButtonCondition.getValue().toString());
            hBoxConditions.setVisible(false);
            hBoxConditions.setManaged(false);
        } else {
            hBoxConditions.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRelisedNet1(KeyEvent keyEvent) {
        if (!tfNet1.getText().isEmpty()) {
            if (checkingForANumber(tfNet1.getText())) {
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
            if (checkingForANumber(tfNet2.getText())) {
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
            if (checkingForANumber(tfSubNet.getText())) {
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
            if (checkingForANumber(tfDeviceNet.getText())){
                if(tfDeviceNet.getText().length() <= 3) {
                    if(Integer.parseInt(tfDeviceNet.getText()) <= 256) {
                        if(!checkEquipmentIpAddress(equipment.getCompany()
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
            if (checkingForANumber(tfMascNet1.getText())) {
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
            if (checkingForANumber(tfMascNet2.getText())) {
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
            if (checkingForANumber(tfMascSub.getText())) {
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
            if (checkingForANumber(tfMascDev.getText())) {
                if (tfMascDev.getText().length() <= 3) {
                    if (Integer.parseInt(tfMascDev.getText()) <= 256) {
                        tfMascDev.setStyle(new TextField().getStyle());
                    } else tfMascDev.setStyle(STYLE_DANGER);
                } else tfMascDev.setStyle(STYLE_DANGER);
            } else tfMascDev.setStyle(STYLE_DANGER);
        } else tfMascDev.setStyle(new TextField().getStyle());
    }

    public void saveNewMasc(MouseEvent mouseEvent) {
        equipmentRepository.change(equipment.getId(), "masc",
                tfMascNet1.getText() + "." + tfMascNet2.getText() + "." + tfMascSub.getText() + "." + tfMascDev.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getMasc().equals(tfMascNet1.getText() + "." + tfMascNet2.getText()
                + "." + tfMascSub.getText() + "." + tfMascDev.getText())){
            labelSubnetMasc.setText(tfMascNet1.getText() + "." + tfMascNet2.getText() + "." + tfMascSub.getText()
                    + "." + tfMascDev.getText());
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
            if (checkingForANumber(tfGatewayNet1.getText())) {
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
            if (checkingForANumber(tfGatewayNet2.getText())) {
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
            if (checkingForANumber(tfGatewaySub.getText())) {
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
            if (checkingForANumber(tfGatewayDev.getText())) {
                if (tfGatewayDev.getText().length() <= 3) {
                    if (Integer.parseInt(tfGatewayDev.getText()) <= 256) {
                        tfGatewayDev.setStyle(new TextField().getStyle());
                    } else tfGatewayDev.setStyle(STYLE_DANGER);
                } else tfGatewayDev.setStyle(STYLE_DANGER);
            } else tfGatewayDev.setStyle(STYLE_DANGER);
        }else tfGatewayDev.setStyle(new TextField().getStyle());
    }

    public void saveNewGateway(MouseEvent mouseEvent) {
        equipmentRepository.change(equipment.getId(), "gateway",
                tfGatewayNet1.getText() + "." + tfGatewayNet2.getText() + "." + tfGatewaySub.getText() + "."
                        + tfGatewayDev.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getGateway().equals(tfGatewayNet1.getText() + "."
                + tfGatewayNet2.getText() + "." + tfGatewaySub.getText() + "." + tfGatewayDev.getText())){
            labelGateway.setText(tfGatewayNet1.getText() + "." + tfGatewayNet2.getText() + "." + tfGatewaySub.getText()
                    + "." + tfGatewayDev.getText());
            hBoxGateway.setVisible(false);
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
            if (checkingForANumber(tfDanteNet1.getText())) {
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
            if (checkingForANumber(tfDanteNet2.getText())) {
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
            if (checkingForANumber(tfDanteSubIp.getText())) {
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
            if (checkingForANumber(tfDanteDev.getText())){
                if(tfDanteDev.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteDev.getText()) <= 256) {
                        if(!checkEquipmentIpAddressDante(equipment.getCompany()
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
        equipmentRepository.change(equipment.getId(), "ipAddressDante",
                tfDanteNet1.getText() + "." + tfDanteNet2.getText() + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getDanteIpAddress() != null && equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getDanteIpAddress().equals(tfDanteNet1.getText() + "."
                + tfDanteNet2.getText() + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText())){
            labelIpDante.setText(tfDanteNet1.getText() + "." + tfDanteNet2.getText() + "." + tfDanteSubIp.getText() + "." + tfDanteDev.getText());
            hBoxIpDanteChange.setVisible(false);
            hBoxIpDanteChange.setManaged(false);
        } else {
            tfDanteNet1.setStyle(new TextField().getStyle());
            tfDanteNet2.setStyle(new TextField().getStyle());
            tfDanteSubIp.setStyle(new TextField().getStyle());
            tfDanteDev.setStyle(new TextField().getStyle());
        }
    }

    public void okrDanteMascNet1(KeyEvent keyEvent) {
        if (!tfDanteMascNet1.getText().isEmpty()) {
            if (checkingForANumber(tfDanteMascNet1.getText())){
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
            if (checkingForANumber(tfDanteMascNet2.getText())){
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
            if (checkingForANumber(tfDanteMascSub.getText())){
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
            if (checkingForANumber(tfDanteMascDev.getText())){
                if(tfDanteMascDev.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteMascDev.getText()) <= 256) {
                        tfDanteMascDev.setStyle(new TextField().getStyle());
                    } else tfDanteMascDev.setStyle(STYLE_DANGER);
                } else tfDanteMascDev.setStyle(STYLE_DANGER);
            } else tfDanteMascDev.setStyle(STYLE_DANGER);
        } else tfDanteMascDev.setStyle(new TextField().getStyle());
    }

    public void saveDanteMasc(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "mascDante",
                tfDanteMascNet1.getText() + "." + tfDanteMascNet2.getText() + "." + tfDanteMascSub.getText()
                        + "." + tfDanteMascDev.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getDanteMasc().equals(tfDanteMascNet1.getText() + "." + tfDanteMascNet2.getText()
                + "." + tfDanteMascSub.getText() + "." + tfDanteMascDev.getText())){
            labelMascDante.setText(tfDanteMascNet1.getText() + "." + tfDanteMascNet2.getText() + "." + tfDanteMascSub.getText()
                    + "." + tfDanteMascDev.getText());
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
            if (checkingForANumber(tfDanteGatewayNet1.getText())){
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
            if (checkingForANumber(tfDanteGatewayNet2.getText())){
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
            if (checkingForANumber(tfDanteGatewaySub.getText())){
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
            if (checkingForANumber(tfDanteGatewayDev.getText())){
                if(tfDanteGatewayDev.getText().length() <= 3) {
                    if(Integer.parseInt(tfDanteGatewayDev.getText()) <= 256) {
                        tfDanteGatewayDev.setStyle(new TextField().getStyle());
                    } else tfDanteGatewayDev.setStyle(STYLE_DANGER);
                } else tfDanteGatewayDev.setStyle(STYLE_DANGER);
            } else tfDanteGatewayDev.setStyle(STYLE_DANGER);
        } else tfDanteGatewayDev.setStyle(new TextField().getStyle());
    }

    public void saveDanteGateway(ActionEvent actionEvent) {
        equipmentRepository.change(equipment.getId(), "gatewayDante",
                tfDanteGatewayNet1.getText() + "." + tfDanteGatewayNet2.getText() + "."
                        + tfDanteGatewaySub.getText() + "." + tfDanteGatewayDev.getText());
        if(equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                , equipment.getSerialNumber()).getDanteGateway().equals(tfDanteGatewayNet1.getText() + "."
                + tfDanteGatewayNet2.getText() + "." + tfDanteGatewaySub.getText() + "." + tfDanteGatewayDev.getText())){
            labelGatewayDante.setText(tfDanteGatewayNet1.getText() + "." + tfDanteGatewayNet2.getText() + "."
                    + tfDanteGatewaySub.getText() + "." + tfDanteGatewayDev.getText());
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
        hBoxFrequency.setVisible(false);
        hBoxFrequency.setManaged(false);
        hBoxChangeTimeWorkLampProj.setVisible(false);
        hBoxChangeTimeWorkLampProj.setManaged(false);
        splitPaneOEI.setVisible(false);
        splitPaneOEI.setManaged(false);
        hBoxPortNumber.setVisible(false);
        hBoxPortNumber.setManaged(false);
        hBoxNewOutletNumber.setVisible(false);
        hBoxNewOutletNumber.setManaged(false);
    }

    public void changeFrequency(MouseEvent mouseEvent) {
        hBoxFrequency.setVisible(true);
        this.hBoxFrequency.setManaged(true);
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void okrFrequency1(KeyEvent keyEvent) {
        if (!tfFrequency1.getText().trim().isEmpty()) {
            if(checkingStringWithACondition(tfFrequency1.getText())){
                if (tfFrequency1.getText().length() <= 3) {
                    tfFrequency1.setStyle(new TextField().getStyle());
                } else tfFrequency1.setStyle(STYLE_DANGER);
            } else tfFrequency1.setStyle(STYLE_DANGER);
        } else tfFrequency1.setStyle(new TextField().getStyle());
    }

    public void okrFrequency2(KeyEvent keyEvent) {
        if (!tfFrequency2.getText().trim().isEmpty()) {
            if (checkingStringWithACondition(tfFrequency2.getText())) {
                if(tfFrequency2.getText().length() <= 3) {
                    if (!checkingFrequency(equipment.getCompany(), tfFrequency1.getText() + "." + tfFrequency2.getText())) {
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

        equipmentRepository.change(equipment.getId(), "frequencyMicrophone", tfFrequency1.getText() + "." + tfFrequency2.getText());
        if(((Microphone)equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber())).getFrequency() != null
                && ((Microphone)equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber())).getFrequency()
                .equals(tfFrequency1.getText() + "." + tfFrequency2.getText())){
            hBoxFrequency.setVisible(false);
            this.hBoxFrequency.setManaged(false);
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
            labelFrequency.setVisible(true);
            labelFrequency.setManaged(true);
            labelFrequencyName.setVisible(true);
            labelFrequencyName.setManaged(true);
            imgFrequencyChange.setVisible(true);
            imgFrequencyChange.setManaged(true);
            labelFrequency.setText(((Microphone) equipment).getFrequency());
        } else {

            labelFrequency.setVisible(false);
            labelFrequency.setManaged(false);
            labelFrequencyName.setVisible(false);
            labelFrequencyName.setManaged(false);
            imgFrequencyChange.setVisible(false);
            imgFrequencyChange.setManaged(false);

        }

        if(equipment instanceof Projector projector){
            labelMaxTimeLampName.setVisible(true);
            labelMaxTimeLampName.setManaged(true);
            labelMaximumLampOperatingTime.setVisible(true);
            labelMaximumLampOperatingTime.setManaged(true);
            labelTimeWorkLampProjName.setVisible(true);
            labelTimeWorkLampProjName.setManaged(true);
            labelTimeWorkLampProj.setVisible(true);
            labelTimeWorkLampProj.setManaged(true);
            imgChangeLampTimeWork.setVisible(true);
            imgChangeLampTimeWork.setManaged(true);
            labelMaximumLampOperatingTime.setText(((Projector) equipment).getMaximumLampOperatingTimeProjector().toString());
            labelTimeWorkLampProj.setText(((Projector) equipment).getTimeWorkLampProjector().toString());
        } else {
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
        }

        if(equipment instanceof NetworkSwitch){
            hBoxPortNumber.setVisible(false);
            hBoxPortNumber.setManaged(false);
            splitPaneOEI.setVisible(true);
            splitPaneOEI.setManaged(true);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentPort.fxml"));
            try {
                splitPaneOEI.getItems().add(loader.load());
                FragmentPortController portController = loader.getController();
                portController.setPort(((NetworkSwitch) equipment));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            splitPaneOEI.setVisible(false);
            splitPaneOEI.setManaged(false);
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

        labelQuantityDefect.setText(String.valueOf(defectRepository.getListAllDefectToEquipment(equipment.getSerialNumber()).size()));
        labelTextOutletNumber.setText(equipment.getOutletNumber());
        labelTextPortNumber.setText(equipment.getPortNumberInTheSwitch().toString());
    }

    public void changeTimeWorkLampProj(MouseEvent mouseEvent) {
        hBoxChangeTimeWorkLampProj.setVisible(true);
        this.hBoxChangeTimeWorkLampProj.setManaged(true);
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.sizeToScene();
    }

    public void okrNewTimeWorkLampProj(KeyEvent keyEvent) {
        if (!tfNewTimeWorkLampProj.getText().isEmpty()) {
            if (!checkingForANumber(tfNewTimeWorkLampProj.getText())) {
                tfNewTimeWorkLampProj.setStyle(STYLE_DANGER);
            } else if (checkingForANumber(tfNewTimeWorkLampProj.getText())) {
                tfNewTimeWorkLampProj.setStyle(new TextField().getStyle());
            } else {
                tfNewTimeWorkLampProj.setStyle(new TextField().getStyle());
            }
        }
    }

    public void saveNewTimeWorkLampProj(MouseEvent mouseEvent) {
        equipmentRepository.change(equipment.getId(), "timeWorkLampProjector"
                ,Integer.valueOf(tfNewTimeWorkLampProj.getText()));
        if(((Projector)equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()))
                .getTimeWorkLampProjector().equals(Integer.valueOf(tfNewTimeWorkLampProj.getText()))){
            labelTimeWorkLampProj.setText(((Projector) equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                    , equipment.getSerialNumber())).getTimeWorkLampProjector().toString());
            hBoxChangeTimeWorkLampProj.setVisible(false);
            this.hBoxChangeTimeWorkLampProj.setManaged(false);
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
            equipmentRepository.change(equipment.getId(), "outletNumber", tfNewOutletNumber.getText());
            tfNewOutletNumber.setStyle(STYLE_EXCELLENT);
            hBoxNewOutletNumber.setStyle(new HBox().getStyle());
        } else {
            hBoxNewOutletNumber.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRelisedPortNumber(KeyEvent keyEvent) {

        if(!tfPortNumber.getText().isEmpty() && tfPortNumber.getText() != null && checkingForANumber(tfPortNumber.getText())){
            tfPortNumber.setStyle(new TextField().getStyle());
            if(Integer.parseInt(tfPortNumber.getText()) <= 48) {
                tfPortNumber.setStyle(new TextField().getStyle());

                if (comboBoxChoiceSwitcher.getValue() != null && tfPortNumber.getText() != null) {
                    NetworkSwitch networkSwitch = (NetworkSwitch) equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
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
        if(equipmentRepository.getListEquipmentByName(Language.NETWORK_SWITCH_RU, equipment.getCompany()).size() != 0) {
            hBoxPortNumber.setVisible(true);
            hBoxPortNumber.setManaged(true);
            List<String> nameSwitch = new ArrayList<>();
            for(Equipment name : equipmentRepository.getListEquipmentByName(Language.NETWORK_SWITCH_RU, equipment.getCompany())){
                nameSwitch.add(name.getSerialNumber());
            }
            comboBoxChoiceSwitcher.setValue(equipmentRepository.getEquipmentById(equipment.getIdNetworkSwitcher(), equipment.getCompany()).getSerialNumber());
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

    public void saveNewPortSwitcher(ActionEvent actionEvent) {
        if (comboBoxChoiceSwitcher.getValue() != null && tfPortNumber.getText() != null) {
            NetworkSwitch networkSwitch = (NetworkSwitch) equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany()
                    , comboBoxChoiceSwitcher.getValue().toString());
            if (networkSwitch.getEquipmentList().get(Integer.parseInt(tfPortNumber.getText())-1) == null) {
                equipmentRepository.change(equipment.getId(), "portNumberInTheSwitch", tfPortNumber.getText());
                equipmentRepository.change(equipment.getId(), "idNetworkSwitcher", networkSwitch.getId());
                tfPortNumber.setStyle(STYLE_EXCELLENT);
                hBoxPortNumber.setStyle(new HBox().getStyle());
            } else {
                hBoxPortNumber.setStyle(STYLE_DANGER);
            }
        } else hBoxPortNumber.setStyle(STYLE_DANGER);
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);

    }
    public boolean checkingForANumber(String value) {
        return value.chars().allMatch(Character :: isDigit);
    }
    public boolean checkingStringWithACondition(String value) {
        return value.matches("^\\w+$");
    }
    public boolean checkingFrequency(String nameCompany, String frequency) {
        for (Equipment equipment : equipmentRepository.getListEquipmentForCompany(nameCompany)){
            if (equipment instanceof Microphone microphone
                    && (frequency.equals(((Microphone) equipment).getFrequency()))){
                return true;
            }
        }
        return false;
    }
    public boolean checkEquipmentIpAddress(String nameCompany, String ipAddress) {
        for (String str : equipmentRepository.getListIpAddressForCompany(nameCompany)){
            if (ipAddress.equals(str)) return true;
        }
        return false;
    }
    public boolean checkEquipmentIpAddressDante(String nameCompany, String ipAddress) {
        for (String str : equipmentRepository.getListIpAddressDanteForCompany(nameCompany)){
            if (ipAddress.equals(str)) return true;
        }
        return false;
    }

    public void onActionMenuButtonCondition(MouseEvent actionEvent) {
        menuButtonCondition.setItems(FXCollections.observableArrayList(language.status_sheet(lang)));
    }
}
