package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.equipment.Microphone;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;
import ru.greatlarder.technicalassistant.domain.spinetix.SpinetixHMP200;
import ru.greatlarder.technicalassistant.domain.spinetix.SpinetixHMP300;
import ru.greatlarder.technicalassistant.domain.spinetix.SpinetixHMP400;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.domain.wirenboard.WirenBoard6;
import ru.greatlarder.technicalassistant.domain.wirenboard.WirenBoard7;
import ru.greatlarder.technicalassistant.services.check.*;
import ru.greatlarder.technicalassistant.services.check.check_equipment.*;
import ru.greatlarder.technicalassistant.services.check.check_equipment.check_equipment_sqlite.*;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;
import ru.greatlarder.technicalassistant.services.database.SetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentBySerialNumberSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.SetEquipmentSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.ListRoomByCompanySQLite;
import ru.greatlarder.technicalassistant.services.get.GetEquipmentConservation;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageNameEquipment;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageNameEquipmentImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Long.MAX_VALUE;
import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_DANGER;
import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_WARNING;

public class FragmentAddEquipmentController implements Initializable {
    @FXML
    public ImageView imgOk;
    @FXML
    public Label labelOk;
    @FXML
    public ImageView close;
    @FXML
    public TextField textFiledModel;
    @FXML
    public TextField textFieldManufacturer;
    @FXML
    public TextField textFieldSerialNumber;
    @FXML
    public Label labelInputMak;
    @FXML
    public TextField oui1;
    @FXML
    public TextField oui2;
    @FXML
    public TextField oui3;
    @FXML
    public TextField uaa1;
    @FXML
    public TextField uaa2;
    @FXML
    public TextField uaa3;
    @FXML
    public TextField textFieldLogin;
    @FXML
    public TextField textFieldPassword;
    @FXML
    public TextField textFieldLocation;
    @FXML
    public DatePicker textFieldDateOfCommissioning;
    @FXML
    public HBox hBoxTCP;
    @FXML
    public Label labelIpv4;
    @FXML
    public TextField network1;
    @FXML
    public TextField network2;
    @FXML
    public TextField subnet;
    @FXML
    public TextField device;
    @FXML
    public HBox hBoxMasc;
    @FXML
    public Label labelMasc;
    @FXML
    public TextField networkMasc1;
    @FXML
    public TextField networkMasc2;
    @FXML
    public TextField subnetMasc;
    @FXML
    public TextField deviceMasc;
    @FXML
    public HBox hBoxGateway;
    @FXML
    public Label labelGateway;
    @FXML
    public TextField gateway1;
    @FXML
    public TextField gateway2;
    @FXML
    public TextField subnetGateway;
    @FXML
    public TextField deviceGateway;
    @FXML
    public ImageView pdfFile;
    @FXML
    public Button btnSaveEquipment;
    @FXML
    public HBox hBoxFrequency;
    @FXML
    public TextField textFieldFrequency1;
    @FXML
    public TextField textFieldFrequency2;
    @FXML
    public TextField maximumLampOperatingTime;
    @FXML
    public ComboBox<String> cmbEquipmentType;
    @FXML
    public HBox hBoxMaxLamp;
    @FXML
    public GridPane gridPane;
    @FXML
    public ComboBox<String> comboBoxStatusSelection;
    @FXML
    public HBox hBoxTCPDante;
    @FXML
    public Label labelIpv4Dante;
    @FXML
    public TextField network1Dante;
    @FXML
    public TextField network2Dante;
    @FXML
    public TextField subnetDante;
    @FXML
    public TextField deviceDante;
    @FXML
    public HBox hBoxMascDante;
    @FXML
    public Label labelMascDante;
    @FXML
    public TextField networkMasc1Dante;
    @FXML
    public TextField subnetMascDante;
    @FXML
    public TextField deviceMascDante;
    @FXML
    public HBox hBoxGatewayDante;
    @FXML
    public Label labelGatewayDante;
    @FXML
    public TextField gateway1Dante;
    @FXML
    public TextField gateway2Dante;
    @FXML
    public TextField subnetGatewayDante;
    @FXML
    public TextField deviceGatewayDante;
    @FXML
    public TextField networkMasc2Dante;
    @FXML
    public HBox hBoxDiagonal;
    @FXML
    public Label labelDiagonal;
    @FXML
    public TextField tfDiagonal;
    @FXML
    public Label labelDum;
    @FXML
    public HBox hBoxLaptop;
    @FXML
    public Label labelOs;
    @FXML
    public TextField tfOs;
    @FXML
    public HBox hBoxPortSwitcher;
    @FXML
    public Label labelPortSwitcher;
    @FXML
    public TextField tfPortSwitcher;
    @FXML
    public HBox hBoxOutlander;
    @FXML
    public Label labelOutlet;
    @FXML
    public TextField tfOutlet;
    @FXML
    public HBox hBoxNetworkSwitcher;
    @FXML
    public Label labelChoiseNetworkSwitcher;
    @FXML
    public ChoiceBox<String> choiceBoxNetworkSvitcher;
    @FXML
    public HBox hBoxMacAddress;
    @FXML
    public Label labelCondition;
    @FXML
    public Label labelFrequency;
    @FXML
    public Label labelInstruction;
    @FXML
    public Label labelMaxTimeWorkLamp;
    @FXML
    public Label labelDateTheWork;
    @FXML
    public Label labelLocationToRoom;
    @FXML
    public Label labelRoom;
    @FXML
    public Label labelLogin;
    @FXML
    public Label labelPassword;
    @FXML
    public Label labelModel;
    @FXML
    public Label labelManufacturer;
    @FXML
    public Label labelSerialNumber;
    @FXML
    public HBox hBoxMacAddress1;
    @FXML
    public TextField oui11;
    @FXML
    public TextField oui21;
    @FXML
    public TextField oui31;
    @FXML
    public TextField uaa11;
    @FXML
    public TextField uaa21;
    @FXML
    public TextField uaa31;
    @FXML
    public HBox hBoxMacAddress2;
    @FXML
    public TextField oui12;
    @FXML
    public TextField oui22;
    @FXML
    public TextField oui32;
    @FXML
    public TextField uaa12;
    @FXML
    public TextField uaa22;
    @FXML
    public TextField uaa32;
    @FXML
    public HBox hBoxMacAddress3;
    @FXML
    public TextField oui13;
    @FXML
    public TextField oui23;
    @FXML
    public TextField oui33;
    @FXML
    public TextField uaa13;
    @FXML
    public TextField uaa23;
    @FXML
    public TextField uaa33;
    @FXML
    public Button btnAddMacDop;
    @FXML public HBox hBoxSelectionByManufacturerMediaPlayer;
    @FXML public ImageView imgSpinetix;
    @FXML public HBox hBoxSelectionByDeviceMediaPlayer;
    @FXML public ImageView imjHMP200;
    @FXML public HBox hBoxSelectionByDeviceController;
    @FXML public ImageView imgWirenBoardLogo;
    @FXML public ImageView imgWB7;
    @FXML public ImageView imgWB6;
    @FXML public HBox hBoxSelectionByManufacturerController;
    @FXML public ImageView imgHMP300;
    @FXML public ImageView imgHMP400;
    @FXML public ComboBox<String> comboBoxRooms;
    @FXML public HBox hBoxTopAddEquipment;
    @FXML public Label labelType;
    @FXML public ComboBox<String> comboBoxType;
    @FXML public Label labelVisibleUser;
    @FXML public ComboBox<Integer> comboBoxUserVisible;
    @FXML public ScrollPane scrollPane;
    private String lang;
    Language language = new LanguageImpl();
    LanguageNameEquipment languageNameEquipment = new LanguageNameEquipmentImpl();
    Company company;
    List<Equipment> listNetworkSwitcher = new ArrayList<>();
    FileManager fileManager = new FileManagerImpl();
    String logoImg =null;
    CheckIpAddress checkIpAddress = new CheckingIpForEmploymentInTheDatabaseSQLite();
    User user;
    CheckString checkString = new CheckingStringImpl();
    CheckString checkForANumber = new CheckingStringImpl();
    Equipment conservationEquipment;
    GetEquipmentConservation getEquipmentConservation = new GetEquipmentConservation();
    int flag = 0;

    private void setLang(String lang) {
        labelModel.setText(language.MODEL(lang));
        labelManufacturer.setText(language.MANUFACTURER(lang));
        labelSerialNumber.setText(language.SERIAL_NUMBER(lang));
        labelInputMak.setText(language.MAC_ADDRESS(lang));
        labelLogin.setText(language.LOGIN(lang));
        labelPassword.setText(language.PASSWORD(lang));
        labelRoom.setText(language.ROOM(lang));
        labelLocationToRoom.setText(language.LOCATION_IN_THE_ROOM(lang));
        labelDateTheWork.setText(language.START_DATE_OF_OPERATION(lang));
        labelCondition.setText(language.CONDITION(lang));
        labelInstruction.setText(language.INSTRUCTION(lang));
        labelIpv4.setText(language.IP_ADDRESS(lang));
        labelMaxTimeWorkLamp.setText(language.MAXIMUM_LAMP_OPERATING_TIME(lang));
        labelFrequency.setText(language.FREQUENCY(lang));
        labelIpv4Dante.setText(language.IP_ADDRESS_DANTE(lang));
        labelChoiseNetworkSwitcher.setText(language.SELECT_A_NETWORK_SWITCH(lang));
        labelPortSwitcher.setText(language.PORT_NUMBER_IN_THE_SWITCH(lang));
        labelOutlet.setText(language.SOCKET_NUMBER(lang));
        labelDiagonal.setText(language.DIAGONAL(lang));
        labelDum.setText(language.INCHES(lang));
        labelOs.setText(language.ENTER_THE_NAME_OF_THE_OPERATING_SYSTEM(lang));
        btnSaveEquipment.setText(language.SAVE(lang));
        cmbEquipmentType.setPromptText(language.SELECT_THE_NAME_OF_THE_EQUIPMENT(lang));
        comboBoxStatusSelection.setPromptText(language.SELECT_THE_DEVICE_STATUS(lang));
        comboBoxStatusSelection.setItems(FXCollections.observableArrayList(language.status_sheet(lang)));
        comboBoxRooms.setPromptText(language.CHOOSE_A_ROOM(lang));
        labelType.setText(language.TYPE_NAME(lang));
        labelVisibleUser.setText(language.USER_VISIBILITY(lang));
        comboBoxType.setPromptText(language.SELECT_THE_DEVICE_TYPE(lang));
        comboBoxUserVisible.setPromptText(language.SELECT_DEVICE_VISIBILITY_FOR_THE_USER(lang));
    }

    public void loadFragment() {
        GetListEquipment getListEquipment = new ListEquipmentByNameCompanySQLite();
        List<Equipment> equipmentList = getListEquipment.getListEquipment(user, company.getNameCompany(), null);
        
        LanguageNameEquipment languageNameEquipment = new LanguageNameEquipmentImpl();

        for (Equipment e : equipmentList){
            if(e.getName().equals(languageNameEquipment.getNetworkSwitch(lang))) {
                this.listNetworkSwitcher.add(e);
            }
        }
        cmbEquipmentType.setItems(FXCollections.observableArrayList(languageNameEquipment.getListNameEquipment(user.getLanguage())));

        comboBoxStatusSelection.setItems(FXCollections.observableArrayList(language.status_sheet(lang)));

        List<String> roomName = new ArrayList<>();
        GetListRoom listRoom = new ListRoomByCompanySQLite();

        for (Room r : listRoom.getListRoom(user, company.getNameCompany(), null)){
            roomName.add(r.getNameRoom());
        }
        comboBoxRooms.setItems(FXCollections.observableArrayList(roomName));

        List<String> list1 = new ArrayList<>();
        for (Equipment f : listNetworkSwitcher) {
            list1.add(f.getSerialNumber());
        }

        choiceBoxNetworkSvitcher.setItems(FXCollections.observableArrayList(list1));

        cmbEquipmentType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePanes(newValue);
        });
        updatePanes("");
    }

    public void updatePanes(String value) {
        this.hBoxMacAddress1.setVisible(false);
        this.hBoxMacAddress1.setManaged(false);
        this.hBoxMacAddress2.setVisible(false);
        this.hBoxMacAddress2.setManaged(false);
        this.hBoxMacAddress3.setVisible(false);
        this.hBoxMacAddress3.setManaged(false);

        this.hBoxSelectionByDeviceMediaPlayer.setVisible(false);
        this.hBoxSelectionByDeviceMediaPlayer.setManaged(false);
        this.hBoxSelectionByDeviceController.setVisible(false);
        this.hBoxSelectionByDeviceController.setManaged(false);

        this.hBoxMaxLamp.setVisible(value.equals(languageNameEquipment.getProjector(lang)));
        this.hBoxMaxLamp.setManaged(value.equals(languageNameEquipment.getProjector(lang)));

        this.hBoxFrequency.setVisible(value.equals(languageNameEquipment.getMicrophone(lang)));
        this.hBoxFrequency.setManaged(value.equals(languageNameEquipment.getMicrophone(lang)));

        boolean b = value.equals(languageNameEquipment.getMicrophone(lang)) || value.equals(languageNameEquipment.getAudioProcessor(lang))
            || value.equals(languageNameEquipment.getAudioAmplifier(lang)) || value.equals(languageNameEquipment.getAudioAmplifier(lang))
            || value.equals(languageNameEquipment.getAudioInterface(lang)) || value.equals(languageNameEquipment.getMatrixSwitcher(lang));
        this.hBoxTCPDante.setVisible(b);
        this.hBoxTCPDante.setManaged(b);
        this.hBoxMascDante.setVisible(b);
        this.hBoxMascDante.setManaged(b);
        this.hBoxGatewayDante.setVisible(b);
        this.hBoxGatewayDante.setManaged(b);

        boolean c = value.equals(languageNameEquipment.getTvPanel(lang)) || value.equals(languageNameEquipment.getTouchControlPanel(lang));
        this.hBoxDiagonal.setVisible(c);
        this.hBoxDiagonal.setManaged(c);

        this.hBoxLaptop.setVisible(value.equals(languageNameEquipment.getLaptop(lang)));
        this.hBoxLaptop.setManaged(value.equals(languageNameEquipment.getLaptop(lang)));

        boolean d = !value.equals(languageNameEquipment.getNetworkSwitch(lang));
        this.hBoxOutlander.setVisible(d);
        this.hBoxOutlander.setManaged(d);
        this.hBoxPortSwitcher.setVisible(d);
        this.hBoxPortSwitcher.setManaged(d);
        this.hBoxNetworkSwitcher.setVisible(d);
        this.hBoxNetworkSwitcher.setManaged(d);

        boolean r = value.equals(languageNameEquipment.getMediaPlayer(lang));
        this.hBoxSelectionByManufacturerMediaPlayer.setVisible(r);
        this.hBoxSelectionByManufacturerMediaPlayer.setManaged(r);
        boolean cont = value.equals(language.CONTROLLER(lang));
        this.hBoxSelectionByManufacturerController.setVisible(cont);
        this.hBoxSelectionByManufacturerController.setManaged(cont);
        
        conservationEquipment = getEquipmentConservation.getEquipment(user, value);
    }

    public void onKeyModel() {
        if (!textFiledModel.getText().trim().isEmpty()) {
            textFiledModel.setStyle(new TextField().getStyle());
            conservationEquipment.setModel(textFiledModel.getText());
        } else if (textFiledModel.getText().trim().isEmpty()) {
            textFiledModel.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyManufacturer() {
        if (!textFieldManufacturer.getText().trim().isEmpty()) {
            textFieldManufacturer.setStyle(new TextField().getStyle());
            conservationEquipment.setManufacturer(textFieldManufacturer.getText());
        } else if (textFieldManufacturer.getText().trim().isEmpty()) {
            textFieldManufacturer.setStyle(STYLE_WARNING);
        }
    }

    public void onKeySerialNumber() {
        if (!textFieldSerialNumber.getText().trim().isEmpty()) {
            GetEquipment getEquipment = new EquipmentBySerialNumberSQLite();
            if (getEquipment.getEquipment(user, company.getNameCompany(), textFieldSerialNumber.getText()) == null) {
                textFieldSerialNumber.setStyle(new TextField().getStyle());
                conservationEquipment.setSerialNumber(textFieldSerialNumber.getText());
            } else {
               textFieldSerialNumber.setStyle(STYLE_DANGER);
            }
        } else if (textFieldSerialNumber.getText().trim().isEmpty()) {
            textFieldSerialNumber.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyOui1() {
        if (oui1.getText().isEmpty()) {
            oui1.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui1.getText()) || oui1.getText().length() > 2) {
            oui1.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui1.getText())) {
            oui1.setStyle(new TextField().getStyle());
        } else oui1.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2() {
        if (oui2.getText().isEmpty()) {
            oui2.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui2.getText()) || oui2.getText().length() > 2) {
            oui2.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui2.getText())) {
            oui2.setStyle(new TextField().getStyle());
        } else oui2.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3() {
        if (oui3.getText().isEmpty()) {
            oui3.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui3.getText()) || oui3.getText().length() > 2) {
            oui3.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui3.getText())) {
            oui3.setStyle(new TextField().getStyle());
        } else oui3.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1() {
        if (uaa1.getText().isEmpty()) {
            uaa1.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa1.getText()) || uaa1.getText().length() > 2) {
            uaa1.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa1.getText())) {
            uaa1.setStyle(new TextField().getStyle());
        } else uaa1.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2() {
        if (uaa2.getText().isEmpty()) {
            uaa2.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa2.getText()) || uaa2.getText().length() > 2) {
            uaa2.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa2.getText())) {
            uaa2.setStyle(new TextField().getStyle());
        } else uaa2.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3() {
        CheckMacAddress checkMacAddress = new CheckingMacAddressSQLite();
        if (uaa3.getText().isEmpty()) {
            uaa3.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa3.getText()) || uaa3.getText().length() > 2) {
            uaa3.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa3.getText())) {
            uaa3.setStyle(new TextField().getStyle());
        } else uaa3.setStyle(new TextField().getStyle());
        if (getMacAddressEquipment(oui1.getText(), oui2.getText(), oui3.getText(), uaa1.getText(), uaa2.getText(), uaa3.getText()) != null) {
            hBoxMacAddress.setStyle(new HBox().getStyle());
            if (!checkMacAddress.checkingEquipmentMacAddress(user, company.getNameCompany()
                    ,getMacAddressEquipment(oui1.getText(), oui2.getText(), oui3.getText(), uaa1.getText(), uaa2.getText(), uaa3.getText()))) {
                hBoxMacAddress.setStyle(new HBox().getStyle());
                conservationEquipment.setMacAddress(getMacAddressEquipment(oui1.getText(), oui2.getText(), oui3.getText(), uaa1.getText(), uaa2.getText(), uaa3.getText()));
            } else {
                hBoxMacAddress.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyLogin() {
        if (!textFieldLogin.getText().trim().isEmpty() && checkString.checkingStringWithACondition(textFieldLogin.getText())) {
            textFieldLogin.setStyle(new TextField().getStyle());
            conservationEquipment.setLogin(textFieldLogin.getText());
        } else if (textFieldLogin.getText().trim().isEmpty()) {
            textFieldLogin.setStyle(STYLE_WARNING);
        } else if (!textFieldLogin.getText().trim().isEmpty() && !checkString.checkingStringWithACondition(textFieldLogin.getText())) {
            textFieldLogin.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyLocation() {
        if (!textFieldLocation.getText().trim().isEmpty()) {
            textFieldLocation.setStyle(new TextField().getStyle());
            conservationEquipment.setLocation(textFieldLocation.getText());
        } else if (textFieldLocation.getText().trim().isEmpty()) {
            textFieldLocation.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyReleased() {
        if (!network1.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(network1.getText())) {
                if (network1.getText().length() <= 3) {
                    if (Integer.parseInt(network1.getText()) <= 256) {
                        network1.setStyle(new TextField().getStyle());
                    } else network1.setStyle(STYLE_DANGER);
                } else network1.setStyle(STYLE_DANGER);
            } else network1.setStyle(STYLE_DANGER);
        } else network1.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedNet2() {
        if (!network2.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(network2.getText())) {
                if (network2.getText().length() <= 3) {
                    if (Integer.parseInt(network2.getText()) <= 256) {
                        network2.setStyle(new TextField().getStyle());
                    } else network2.setStyle(STYLE_DANGER);
                } else network2.setStyle(STYLE_DANGER);
            } else network2.setStyle(STYLE_DANGER);
        } else network2.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedSubnet() {
        if (!subnet.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(subnet.getText())) {
                if (subnet.getText().length() <= 3) {
                    if (Integer.parseInt(subnet.getText()) <= 256) {
                        subnet.setStyle(new TextField().getStyle());
                    } else subnet.setStyle(STYLE_DANGER);
                } else subnet.setStyle(STYLE_DANGER);
            } else subnet.setStyle(STYLE_DANGER);
        } else subnet.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedDevice() {
        if (!device.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(device.getText())) {
                if (device.getText().length() <= 3) {
                    if (Integer.parseInt(device.getText()) <= 256) {
                        if (!checkIpAddress.checkIpAddress(user, company, getIpAddressEquipment(network1.getText(), network2.getText(),
                                subnet.getText(), device.getText()))) {
                            network1.setStyle(new TextField().getStyle());
                            network2.setStyle(new TextField().getStyle());
                            subnet.setStyle(new TextField().getStyle());
                            device.setStyle(new TextField().getStyle());
                            conservationEquipment.setIpAddress(getIpAddressEquipment(network1.getText(), network2.getText(),
                                    subnet.getText(), device.getText()));
                        } else {
                            network1.setStyle(STYLE_DANGER);
                            network2.setStyle(STYLE_DANGER);
                            subnet.setStyle(STYLE_DANGER);
                            device.setStyle(STYLE_DANGER);
                        }
                    } else device.setStyle(STYLE_DANGER);
                } else device.setStyle(STYLE_DANGER);
            } else device.setStyle(STYLE_DANGER);
        } else device.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMasc() {
        if (!networkMasc1.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(networkMasc1.getText())) {
                if (networkMasc1.getText().length() <= 3) {
                    if (Integer.parseInt(networkMasc1.getText()) <= 256) {
                        networkMasc1.setStyle(new TextField().getStyle());
                    } else networkMasc1.setStyle(STYLE_DANGER);
                } else networkMasc1.setStyle(STYLE_DANGER);
            } else networkMasc1.setStyle(STYLE_DANGER);
        } else networkMasc1.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMasc2() {
        if (!networkMasc2.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(networkMasc2.getText())) {
                if (networkMasc2.getText().length() <= 3) {
                    if (Integer.parseInt(networkMasc2.getText()) <= 256) {
                        networkMasc2.setStyle(new TextField().getStyle());
                    } else networkMasc2.setStyle(STYLE_DANGER);
                } else networkMasc2.setStyle(STYLE_DANGER);
            } else networkMasc2.setStyle(STYLE_DANGER);
        } else networkMasc2.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMascSubnet() {
        if (!subnetMasc.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(subnetMasc.getText())) {
                if (subnetMasc.getText().length() <= 3) {
                    if (Integer.parseInt(subnetMasc.getText()) <= 256) {
                        subnetMasc.setStyle(new TextField().getStyle());
                    } else subnetMasc.setStyle(STYLE_DANGER);
                } else subnetMasc.setStyle(STYLE_DANGER);
            } else subnetMasc.setStyle(STYLE_DANGER);
        } else subnetMasc.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMascDevice() {
        if (!deviceMasc.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(deviceMasc.getText())) {
                if (deviceMasc.getText().length() <= 3) {
                    if (Integer.parseInt(deviceMasc.getText()) <= 256) {
                        deviceMasc.setStyle(new TextField().getStyle());
                        conservationEquipment.setMasc(getIpAddressEquipment(networkMasc1.getText(), networkMasc2.getText(),
                                subnetMasc.getText(), deviceMasc.getText()));
                    } else deviceMasc.setStyle(STYLE_DANGER);
                } else deviceMasc.setStyle(STYLE_DANGER);
            } else deviceMasc.setStyle(STYLE_DANGER);
        } else deviceMasc.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGateway() {
        if (!gateway1.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(gateway1.getText())) {
                if (gateway1.getText().length() <= 3) {
                    if (Integer.parseInt(gateway1.getText()) <= 256) {
                        gateway1.setStyle(new TextField().getStyle());
                    } else gateway1.setStyle(STYLE_DANGER);
                } else gateway1.setStyle(STYLE_DANGER);
            } else gateway1.setStyle(STYLE_DANGER);
        } else gateway1.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGateway2() {
        if (!gateway2.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(gateway2.getText())) {
                if (gateway2.getText().length() <= 3) {
                    if (Integer.parseInt(gateway2.getText()) <= 256) {
                        gateway2.setStyle(new TextField().getStyle());
                    } else gateway2.setStyle(STYLE_DANGER);
                } else gateway2.setStyle(STYLE_DANGER);
            } else gateway2.setStyle(STYLE_DANGER);
        } else gateway2.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewaySubnet() {
        if (!subnetGateway.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(subnetGateway.getText())) {
                if (subnetGateway.getText().length() <= 3) {
                    if (Integer.parseInt(subnetGateway.getText()) <= 256) {
                        subnetGateway.setStyle(new TextField().getStyle());
                    } else subnetGateway.setStyle(STYLE_DANGER);
                } else subnetGateway.setStyle(STYLE_DANGER);
            } else subnetGateway.setStyle(STYLE_DANGER);
        } else subnetGateway.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewayDevice() {
        if (!deviceGateway.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(deviceGateway.getText())) {
                if (deviceGateway.getText().length() <= 3) {
                    if (Integer.parseInt(deviceGateway.getText()) <= 256) {
                        deviceGateway.setStyle(new TextField().getStyle());
                        conservationEquipment.setGateway(getIpAddressEquipment(gateway1.getText(), gateway2.getText(),
                                subnetGateway.getText(), deviceGateway.getText()));
                    } else deviceGateway.setStyle(STYLE_DANGER);
                } else deviceGateway.setStyle(STYLE_DANGER);
            } else deviceGateway.setStyle(STYLE_DANGER);
        } else deviceGateway.setStyle(new TextField().getStyle());
    }

    public void addManual() {

            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(" .Pdf", "*.*");
            fileChooser.getExtensionFilters().add(extensionFilter);
            File file = fileChooser.showOpenDialog(pdfFile.getScene().getWindow());
            File file1 = new File(fileManager.folderManual() + "\\" + file.getName());
            conservationEquipment.setManual(file.getName());
            try {
                FileUtils.copyFile(file, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void onKeyReleasedFrequency1() {
        if (!textFieldFrequency1.getText().trim().isEmpty()) {
            if (checkString.checkingStringWithACondition(textFieldFrequency1.getText())) {
                if (textFieldFrequency1.getText().length() <= 3) {
                    textFieldFrequency1.setStyle(new TextField().getStyle());
                } else textFieldFrequency1.setStyle(STYLE_DANGER);
            } else textFieldFrequency1.setStyle(STYLE_DANGER);
        } else textFieldFrequency1.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedFrequency2() {
        CheckFrequency checkFrequency = new CheckingFrequencyDatabaseSQLite();
        if (!textFieldFrequency2.getText().isEmpty()) {
            if (checkString.checkingStringWithACondition(textFieldFrequency2.getText())) {
                if (textFieldFrequency2.getText().length() <= 3) {
                    if (!checkFrequency.checkFrequency(user, company.getNameCompany(), getFrequency(textFieldFrequency1.getText(), textFieldFrequency2.getText()))) {
                        textFieldFrequency1.setStyle(new TextField().getStyle());
                        textFieldFrequency2.setStyle(new TextField().getStyle());
                        hBoxFrequency.setStyle(new HBox().getStyle());
                        ((Microphone)conservationEquipment).setFrequency(getFrequency(textFieldFrequency1.getText(), textFieldFrequency2.getText()));
                    } else {
                        textFieldFrequency1.setStyle(STYLE_DANGER);
                        textFieldFrequency2.setStyle(STYLE_DANGER);
                        hBoxFrequency.setStyle(STYLE_DANGER);
                    }
                } else {
                    textFieldFrequency2.setStyle(STYLE_DANGER);
                    hBoxFrequency.setStyle(STYLE_DANGER);
                }
            } else {
                textFieldFrequency2.setStyle(STYLE_DANGER);
                hBoxFrequency.setStyle(STYLE_DANGER);
            }
        } else {
            textFieldFrequency2.setStyle(new TextField().getStyle());
            hBoxFrequency.setStyle(new HBox().getStyle());
        }

    }

    public void onKeyPressedMaximumLampOperatingTime() {
        if (!checkForANumber.checkingForANumber(maximumLampOperatingTime.getText()) || maximumLampOperatingTime.getText().length() > 7) {
            maximumLampOperatingTime.setStyle(STYLE_DANGER);
        } else if (checkForANumber.checkingForANumber(maximumLampOperatingTime.getText())) {
            maximumLampOperatingTime.setStyle(new TextField().getStyle());
            ((Projector)conservationEquipment).setMaximumLampOperatingTimeProjector(Integer.parseInt(maximumLampOperatingTime.getText()));
        } else {
            maximumLampOperatingTime.setStyle(new TextField().getStyle());
        }
    }

    public void onKeyReleasedNetDante() {
        if (!network1Dante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(network1Dante.getText())) {
                if (network1Dante.getText().length() <= 3) {
                    if (Integer.parseInt(network1Dante.getText()) <= 256) {
                        network1Dante.setStyle(new TextField().getStyle());
                    } else network1Dante.setStyle(STYLE_DANGER);
                } else network1Dante.setStyle(STYLE_DANGER);
            } else network1Dante.setStyle(STYLE_DANGER);
        } else network1Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedNet2Dante() {
        if (!network2Dante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(network2Dante.getText())) {
                if (network2Dante.getText().length() <= 3) {
                    if (Integer.parseInt(network2Dante.getText()) <= 256) {
                        network2Dante.setStyle(new TextField().getStyle());
                    } else network2Dante.setStyle(STYLE_DANGER);
                } else network2Dante.setStyle(STYLE_DANGER);
            } else network2Dante.setStyle(STYLE_DANGER);
        } else network2Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedSubnetDante() {
        if (!subnetDante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(subnetDante.getText())) {
                if (subnetDante.getText().length() <= 3) {
                    if (Integer.parseInt(subnetDante.getText()) <= 256) {
                        subnetDante.setStyle(new TextField().getStyle());
                    } else subnetDante.setStyle(STYLE_DANGER);
                } else subnetDante.setStyle(STYLE_DANGER);
            } else subnetDante.setStyle(STYLE_DANGER);
        } else subnetDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedDeviceDante() {
        CheckIpAddressDante checkIpAddressDante = new CheckingIpDanteForEmploymentInTheDatabaseSQLite();
        hBoxTCPDante.setVisible(true);
        hBoxTCPDante.setManaged(true);
        if (!deviceDante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(deviceDante.getText())) {
                if (deviceDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceDante.getText()) <= 256) {
                        if (!checkIpAddressDante.checkIpAddress(user, company, getIpAddressEquipment(network1Dante.getText(), network2Dante.getText()
                                , subnetDante.getText(), deviceDante.getText()))){
                            network1Dante.setStyle(new TextField().getStyle());
                            network2Dante.setStyle(new TextField().getStyle());
                            subnetDante.setStyle(new TextField().getStyle());
                            deviceDante.setStyle(new TextField().getStyle());
                            conservationEquipment.setDanteIpAddress(getIpAddressEquipment(network1Dante.getText(), network2Dante.getText()
                                    , subnetDante.getText(), deviceDante.getText()));
                        } else {
                            network1Dante.setStyle(STYLE_DANGER);
                            network2Dante.setStyle(STYLE_DANGER);
                            subnetDante.setStyle(STYLE_DANGER);
                            deviceDante.setStyle(STYLE_DANGER);
                        }
                    } else deviceDante.setStyle(STYLE_DANGER);
                } else deviceDante.setStyle(STYLE_DANGER);
            } else deviceDante.setStyle(STYLE_DANGER);
        } else deviceDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMasc1Dante() {
        if (!networkMasc1Dante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(networkMasc1Dante.getText())) {
                if (networkMasc1Dante.getText().length() <= 3) {
                    if (Integer.parseInt(networkMasc1Dante.getText()) <= 256) {
                        networkMasc1Dante.setStyle(new TextField().getStyle());
                    } else networkMasc1Dante.setStyle(STYLE_DANGER);
                } else networkMasc1Dante.setStyle(STYLE_DANGER);
            } else networkMasc1Dante.setStyle(STYLE_DANGER);
        } else networkMasc1Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMasc2Dante() {
        if (!networkMasc2Dante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(networkMasc2Dante.getText())) {
                if (networkMasc2Dante.getText().length() <= 3) {
                    if (Integer.parseInt(networkMasc2Dante.getText()) <= 256) {
                        networkMasc2Dante.setStyle(new TextField().getStyle());
                    } else networkMasc2Dante.setStyle(STYLE_DANGER);
                } else networkMasc2Dante.setStyle(STYLE_DANGER);
            } else networkMasc2Dante.setStyle(STYLE_DANGER);
        } else networkMasc2Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMascSubnetDante() {
        if (!subnetMascDante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(subnetMascDante.getText())) {
                if (subnetMascDante.getText().length() <= 3) {
                    if (Integer.parseInt(subnetMascDante.getText()) <= 256) {
                        subnetMascDante.setStyle(new TextField().getStyle());
                    } else subnetMascDante.setStyle(STYLE_DANGER);
                } else subnetMascDante.setStyle(STYLE_DANGER);
            } else subnetMascDante.setStyle(STYLE_DANGER);
        } else subnetMascDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMascDeviceDante() {
        if (!deviceMascDante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(deviceMascDante.getText())) {
                if (deviceMascDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceMascDante.getText()) <= 256) {
                        deviceMascDante.setStyle(new TextField().getStyle());
                        conservationEquipment.setDanteMasc(getIpAddressEquipment(networkMasc1Dante.getText(), networkMasc2Dante.getText()
                                , subnetMascDante.getText(), deviceMascDante.getText()));
                    } else deviceMascDante.setStyle(STYLE_DANGER);
                } else deviceMascDante.setStyle(STYLE_DANGER);
            } else deviceMascDante.setStyle(STYLE_DANGER);
        } else deviceMascDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewayDante() {
        if (!gateway1Dante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(gateway1Dante.getText())) {
                if (gateway1Dante.getText().length() <= 3) {
                    if (Integer.parseInt(gateway1Dante.getText()) <= 256) {
                        gateway1Dante.setStyle(new TextField().getStyle());
                    } else gateway1Dante.setStyle(STYLE_DANGER);
                } else gateway1Dante.setStyle(STYLE_DANGER);
            } else gateway1Dante.setStyle(STYLE_DANGER);
        } else gateway1Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGateway2Dante() {
        if (!gateway2Dante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(gateway2Dante.getText())) {
                if (gateway2Dante.getText().length() <= 3) {
                    if (Integer.parseInt(gateway2Dante.getText()) <= 256) {
                        gateway2Dante.setStyle(new TextField().getStyle());
                    } else gateway2Dante.setStyle(STYLE_DANGER);
                } else gateway2Dante.setStyle(STYLE_DANGER);
            } else gateway2Dante.setStyle(STYLE_DANGER);
        } else gateway2Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewaySubnetDante() {
        if (!subnetGatewayDante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(subnetGatewayDante.getText())) {
                if (subnetGatewayDante.getText().length() <= 3) {
                    if (Integer.parseInt(subnetGatewayDante.getText()) <= 256) {
                        subnetGatewayDante.setStyle(new TextField().getStyle());
                    } else subnetGatewayDante.setStyle(STYLE_DANGER);
                } else subnetGatewayDante.setStyle(STYLE_DANGER);
            } else subnetGatewayDante.setStyle(STYLE_DANGER);
        } else subnetGatewayDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewayDeviceDante() {
        if (!deviceGatewayDante.getText().isEmpty()) {
            if (checkForANumber.checkingForANumber(deviceGatewayDante.getText())) {
                if (deviceGatewayDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceGatewayDante.getText()) <= 256) {
                        deviceGatewayDante.setStyle(new TextField().getStyle());
                        conservationEquipment.setDanteGateway(getIpAddressEquipment(gateway1.getText(), gateway2Dante.getText()
                                , subnetGatewayDante.getText(), deviceGatewayDante.getText()));
                    } else deviceGatewayDante.setStyle(STYLE_DANGER);
                } else deviceGatewayDante.setStyle(STYLE_DANGER);
            } else deviceGatewayDante.setStyle(STYLE_DANGER);
        } else deviceGatewayDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedPortSwitcher() {
        if (choiceBoxNetworkSvitcher.getValue() != null) {
            hBoxNetworkSwitcher.setStyle(new HBox().getStyle());
            hBoxPortSwitcher.setStyle(new HBox().getStyle());
            if (!tfPortSwitcher.getText().isEmpty()) {
                if (checkForANumber.checkingForANumber(tfPortSwitcher.getText())) {
                    if (tfPortSwitcher.getText().length() <= 2) {
                        if (Integer.parseInt(tfPortSwitcher.getText()) <= 48) {
                            tfPortSwitcher.setStyle(new TextField().getStyle());
                        } else tfPortSwitcher.setStyle(STYLE_DANGER);
                    } else tfPortSwitcher.setStyle(STYLE_DANGER);
                } else tfPortSwitcher.setStyle(STYLE_DANGER);
            } else tfPortSwitcher.setStyle(new TextField().getStyle());

            CheckPortNetworkSwich checkPortNetworkSwich = new CheckingPortNetworkSwichSQLite();
            GetEquipment getEquipment = new EquipmentBySerialNumberSQLite();
            if(!checkPortNetworkSwich.checkingTheSwitchPort((NetworkSwitch) getEquipment.getEquipment(
                    user, company.getNameCompany(), choiceBoxNetworkSvitcher.getValue())
                    , Integer.parseInt(tfPortSwitcher.getText()))){
                hBoxPortSwitcher.setStyle(new HBox().getStyle());
                conservationEquipment.setPortNumberInTheSwitch(Integer.parseInt(tfPortSwitcher.getText()));
            } else {
                hBoxPortSwitcher.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxNetworkSwitcher.setStyle(STYLE_DANGER);
            hBoxPortSwitcher.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyReleasedOutlet() {
        if (tfOutlet.getText().isEmpty()) {
            tfOutlet.setStyle(new TextField().getStyle());
        } else if (checkString.checkingStringWithACondition(tfOutlet.getText())) {
            tfOutlet.setStyle(new TextField().getStyle());
            conservationEquipment.setOutletNumber(tfOutlet.getText());
        } else tfOutlet.setStyle(new TextField().getStyle());
    }
   
    public void saveEquipment() {
        if (cmbEquipmentType.getValue() != null) {
            cmbEquipmentType.setStyle(new ComboBox<String>().getStyle());
            if (comboBoxStatusSelection.getValue() != null) {
                comboBoxStatusSelection.setStyle(new ComboBox<String>().getStyle());
                conservationEquipment.setCondition(comboBoxStatusSelection.getValue());
                
                conservationEquipment.setUserVisibility(comboBoxUserVisible.getValue() != 0);
                    if(comboBoxRooms.getValue() != null){
                        conservationEquipment.setRoom(comboBoxRooms.getValue());
                    }
                    if(comboBoxType.getValue() != null){
                        conservationEquipment.setType(comboBoxType.getValue());
                    }
                    Equipment equipment = getEquipmentConservation.loadEquipment(user, company, conservationEquipment);
             if (equipment != null) {
                            Task<Integer> task = new Task<>() {
                                @Override
                                protected Integer call() {
                                    SetEquipment setEquipment = new SetEquipmentSQLite();
                                    return setEquipment.setEquipment(user, company.getNameCompany(), equipment);
                                }
                            };
                            ProgressBar progressBar= new ProgressBar(task.getProgress());
                            progressBar.visibleProperty();
                            progressBar.setMaxWidth(MAX_VALUE);
                            progressBar.setMaxHeight(MAX_VALUE);
                            GlobalLinkStartEngineerController.getStartEngineerController().vBoxLeftButton.getChildren().add(progressBar);
                            task.setOnSucceeded((succeededEvent)->{
                                progressBar.visibleProperty().bind(task.runningProperty());
                                Integer idEquipment = task.getValue();
                                if(idEquipment != null){
                                    imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
                                    labelOk.setText(language.EQUIPMENT(lang) + " " + language.SERIAL_NUMBER(lang) + " : " + equipment.getSerialNumber() + " " + language.ADDED(lang));
                                    clear();
                                    GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(
                                            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getRight()
                                    );
                                } else {
                                    imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                                    labelOk.setText(equipment.getSerialNumber() + " : " + language.WILL_NOT_BE_ADDED(lang));
                                }
                             
                            });
                            Platform.runLater(()->{
                                ExecutorService executorService = Executors.newFixedThreadPool(1);
                                executorService.execute(task);
                                executorService.shutdown();
                            });

                        } else {
                            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                            labelOk.setText(textFieldSerialNumber.getText() + " : " + language.WILL_NOT_BE_ADDED(lang));
                        }
            } else {
                comboBoxStatusSelection.setStyle(STYLE_DANGER);
            }
        } else {
            cmbEquipmentType.setStyle(STYLE_DANGER);
        }
    }
    public void clear(){
        textFiledModel.clear();
        textFieldManufacturer.clear();
        textFieldSerialNumber.clear();
        oui1.clear();
        oui2.clear();
        oui3.clear();
        uaa1.clear();
        uaa2.clear();
        uaa3.clear();
        textFieldLogin.clear();
        textFieldPassword.clear();
        comboBoxRooms.getItems().clear();
        textFieldLocation.clear();
        network1.clear();
        network2.clear();
        subnet.clear();
        device.clear();
        networkMasc1.clear();
        networkMasc2.clear();
        subnetMasc.clear();
        deviceMasc.clear();
        gateway1.clear();
        gateway2.clear();
        subnetGateway.clear();
        deviceGateway.clear();
        textFieldFrequency1.clear();
        textFieldFrequency2.clear();
        maximumLampOperatingTime.clear();
        network1Dante.clear();
        network2Dante.clear();
        subnetDante.clear();
        deviceDante.clear();
        networkMasc1Dante.clear();
        subnetMascDante.clear();
        deviceMascDante.clear();
        gateway1Dante.clear();
        gateway2Dante.clear();
        subnetGatewayDante.clear();
        deviceGatewayDante.clear();
        networkMasc2Dante.clear();
        tfDiagonal.clear();
        tfOs.clear();
        tfPortSwitcher.clear();
        tfOutlet.clear();
        oui11.clear();
        oui21.clear();
        oui31.clear();
        uaa11.clear();
        uaa21.clear();
        uaa31.clear();
        oui12.clear();
        oui22.clear();
        oui32.clear();
        uaa12.clear();
        uaa22.clear();
        uaa32.clear();
        oui13.clear();
        oui23.clear();
        oui33.clear();
        uaa13.clear();
        uaa23.clear();
        uaa33.clear();
        comboBoxType.getItems().clear();
        textFieldDateOfCommissioning.getEditor().clear();
        comboBoxStatusSelection.getItems().clear();
        comboBoxUserVisible.getItems().clear();
    }

    public void closeAddEquipmentController() {
        ((BorderPane)scrollPane.getParent()).getChildren().remove(scrollPane);
    }
    public void onActionBtnMacDop() {
        boolean a;
        if(flag == 0){
            flag = 1;
            a = true;
        } else {
            flag = 0;
            a = false;
        }
        this.hBoxMacAddress1.setVisible(a);
        this.hBoxMacAddress1.setManaged(a);
        this.hBoxMacAddress2.setVisible(a);
        this.hBoxMacAddress2.setManaged(a);
        this.hBoxMacAddress3.setVisible(a);
        this.hBoxMacAddress3.setManaged(a);
    }

    public void onKeyOui1_1() {
        if (oui11.getText().isEmpty()) {
            oui11.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui11.getText()) || oui11.getText().length() > 2) {
            oui11.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui11.getText())) {
            oui11.setStyle(new TextField().getStyle());
        } else oui11.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_1() {
        if (oui21.getText().isEmpty()) {
            oui21.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui21.getText()) || oui21.getText().length() > 2) {
            oui21.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui21.getText())) {
            oui21.setStyle(new TextField().getStyle());
        } else oui21.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_1() {
        if (oui31.getText().isEmpty()) {
            oui31.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui31.getText()) || oui31.getText().length() > 2) {
            oui31.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui31.getText())) {
            oui31.setStyle(new TextField().getStyle());
        } else oui31.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_1() {
        if (uaa11.getText().isEmpty()) {
            uaa11.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa11.getText()) || uaa11.getText().length() > 2) {
            uaa11.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa11.getText())) {
            uaa11.setStyle(new TextField().getStyle());
        } else uaa11.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_1() {
        if (uaa21.getText().isEmpty()) {
            uaa21.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa21.getText()) || uaa21.getText().length() > 2) {
            uaa21.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa21.getText())) {
            uaa21.setStyle(new TextField().getStyle());
        } else uaa21.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_1() {
        if (uaa31.getText().isEmpty()) {
            uaa31.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa31.getText()) || uaa31.getText().length() > 2) {
            uaa31.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa31.getText())) {
            uaa31.setStyle(new TextField().getStyle());
        } else uaa31.setStyle(new TextField().getStyle());
        CheckMac1Address checkMac1Address = new CheckingMac1AddressSQLite();
        if (getMacAddressEquipment(oui11.getText(), oui21.getText(), oui31.getText(),uaa11.getText(), uaa21.getText(), uaa31.getText()) != null) {
            hBoxMacAddress1.setStyle(new HBox().getStyle());
            if (!checkMac1Address.checkingEquipmentMac1Address(user, company.getNameCompany(), getMacAddressEquipment(oui13.getText(), oui23.getText()
                    , oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()))) {
                hBoxMacAddress1.setStyle(new HBox().getStyle());
                conservationEquipment.setMacAddress1(getMacAddressEquipment(
                        oui11.getText(), oui21.getText(), oui31.getText(),uaa11.getText(), uaa21.getText(), uaa31.getText()));
            } else {
                hBoxMacAddress1.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress1.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui1_2() {
        if (oui12.getText().isEmpty()) {
            oui12.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui12.getText()) || oui12.getText().length() > 2) {
            oui12.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui12.getText())) {
            oui12.setStyle(new TextField().getStyle());
        } else oui12.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_2() {
        if (oui22.getText().isEmpty()) {
            oui22.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui22.getText()) || oui22.getText().length() > 2) {
            oui22.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui22.getText())) {
            oui22.setStyle(new TextField().getStyle());
        } else oui22.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_2() {
        if (oui32.getText().isEmpty()) {
            oui32.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui32.getText()) || oui32.getText().length() > 2) {
            oui32.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui32.getText())) {
            oui32.setStyle(new TextField().getStyle());
        } else oui32.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_2() {
        if (uaa12.getText().isEmpty()) {
            uaa12.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa12.getText()) || uaa12.getText().length() > 2) {
            uaa12.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa12.getText())) {
            uaa12.setStyle(new TextField().getStyle());
        } else uaa12.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_2() {
        if (uaa22.getText().isEmpty()) {
            uaa22.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa22.getText()) || uaa22.getText().length() > 2) {
            uaa22.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa22.getText())) {
            uaa22.setStyle(new TextField().getStyle());
        } else uaa22.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_2() {
        if (uaa32.getText().isEmpty()) {
            uaa32.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa32.getText()) || uaa32.getText().length() > 2) {
            uaa32.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa32.getText())) {
            uaa32.setStyle(new TextField().getStyle());
        } else uaa32.setStyle(new TextField().getStyle());
        CheckMac2Address checkMac2Address = new CheckingMac2AddressSQLite();
        if (getMacAddressEquipment(oui12.getText(), oui22.getText(), oui32.getText(), uaa12.getText(), uaa22.getText(), uaa32.getText()) != null) {
            hBoxMacAddress2.setStyle(new HBox().getStyle());
            if (!checkMac2Address.checkingEquipmentMac2Address(user, company.getNameCompany(), getMacAddressEquipment(oui13.getText(), oui23.getText()
                    , oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()))) {
                hBoxMacAddress2.setStyle(new HBox().getStyle());
                conservationEquipment.setMacAddress2(getMacAddressEquipment(
                        oui12.getText(), oui22.getText(), oui32.getText(),uaa12.getText(), uaa22.getText(), uaa32.getText()));
            } else {
                hBoxMacAddress2.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress2.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui1_3() {
        if (oui13.getText().isEmpty()) {
            oui13.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui13.getText()) || oui13.getText().length() > 2) {
            oui13.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui13.getText())) {
            oui13.setStyle(new TextField().getStyle());
        } else oui13.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_3() {
        if (oui23.getText().isEmpty()) {
            oui23.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui23.getText()) || oui23.getText().length() > 2) {
            oui23.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui23.getText())) {
            oui23.setStyle(new TextField().getStyle());
        } else oui23.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_3() {
        if (oui33.getText().isEmpty()) {
            oui33.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(oui33.getText()) || oui33.getText().length() > 2) {
            oui33.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(oui33.getText())) {
            oui33.setStyle(new TextField().getStyle());
        } else oui33.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_3() {
        if (uaa13.getText().isEmpty()) {
            uaa13.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa13.getText()) || uaa13.getText().length() > 2) {
            uaa13.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa13.getText())) {
            uaa13.setStyle(new TextField().getStyle());
        } else uaa13.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_3() {
        if (uaa23.getText().isEmpty()) {
            uaa23.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa23.getText()) || uaa23.getText().length() > 2) {
            uaa23.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa23.getText())) {
            uaa23.setStyle(new TextField().getStyle());
        } else uaa23.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_3() {
        if (uaa33.getText().isEmpty()) {
            uaa33.setStyle(new TextField().getStyle());
        } else if (!checkString.checkingStringWithACondition(uaa33.getText()) || uaa33.getText().length() > 2) {
            uaa33.setStyle(STYLE_DANGER);
        } else if (checkString.checkingStringWithACondition(uaa33.getText())) {
            uaa33.setStyle(new TextField().getStyle());
        } else uaa33.setStyle(new TextField().getStyle());
        CheckMac3Address checkMac3Address = new CheckingMac3AddressSQLite();
        if (getMacAddressEquipment(oui13.getText(), oui23.getText(), oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()) != null) {
            hBoxMacAddress3.setStyle(new HBox().getStyle());
            if (!checkMac3Address.checkingEquipmentMac3Address(user, company.getNameCompany(), getMacAddressEquipment(oui13.getText(), oui23.getText()
                    , oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()))) {
                hBoxMacAddress3.setStyle(new HBox().getStyle());
                conservationEquipment.setMacAddress1(getMacAddressEquipment(
                        oui13.getText(), oui23.getText(), oui33.getText(),uaa13.getText(), uaa23.getText(), uaa33.getText()));
            } else {
                hBoxMacAddress3.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress3.setStyle(STYLE_DANGER);
        }
    }

    public void onActionCmbEquipmentType() {
    
    }

    public void openHMP200() throws URISyntaxException {
        textFiledModel.setText(SpinetixHMP200.model);
        textFieldManufacturer.setText(SpinetixHMP200.manufacturer);
        conservationEquipment.setModel(SpinetixHMP200.model);
        conservationEquipment.setManufacturer(SpinetixHMP200.manufacturer);
        pdfFile.setDisable(true);
            File file1 = new File(Objects.requireNonNull(getClass().getResource(SpinetixHMP200.instruction)).toURI());
            File file = new File(fileManager.folderManual() + "\\" + file1.getName());
            conservationEquipment.setManual(file1.getName());
            try {
                FileUtils.copyFile(file1, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logoImg = SpinetixHMP200.logo;
    }

    public void openHMP400() throws URISyntaxException {
        textFiledModel.setText(SpinetixHMP400.model);
        textFieldManufacturer.setText(SpinetixHMP400.manufacturer);
        conservationEquipment.setModel(SpinetixHMP400.model);
        conservationEquipment.setManufacturer(SpinetixHMP400.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(Objects.requireNonNull(getClass().getResource(SpinetixHMP400.instruction)).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        conservationEquipment.setManual(file1.getName());
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = SpinetixHMP400.logo;
    }

    public void openWirenBoard() {
        this.hBoxSelectionByDeviceController.setVisible(true);
        this.hBoxSelectionByDeviceController.setManaged(true);
    }

    public void wb7open() throws URISyntaxException {
        textFiledModel.setText(WirenBoard7.model);
        textFieldManufacturer.setText(WirenBoard7.manufacturer);
        conservationEquipment.setModel(WirenBoard7.model);
        conservationEquipment.setManufacturer(WirenBoard7.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(Objects.requireNonNull(getClass().getResource(WirenBoard7.instruction)).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        conservationEquipment.setManual(file1.getName());
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = WirenBoard7.logo;
    }

    public void openWB6() throws URISyntaxException {
        textFiledModel.setText(WirenBoard6.model);
        textFieldManufacturer.setText(WirenBoard7.manufacturer);
        conservationEquipment.setModel(WirenBoard6.model);
        conservationEquipment.setManufacturer(WirenBoard7.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(Objects.requireNonNull(getClass().getResource(WirenBoard6.instruction)).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        conservationEquipment.setManual(file1.getName());
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = WirenBoard6.logo;
    }

    public void openSpinetix() {
        this.hBoxSelectionByDeviceMediaPlayer.setVisible(true);
        this.hBoxSelectionByDeviceMediaPlayer.setManaged(true);
    }

    public void openHMP300() throws URISyntaxException {
        textFiledModel.setText(SpinetixHMP300.model);
        textFieldManufacturer.setText(SpinetixHMP300.manufacturer);
        conservationEquipment.setModel(SpinetixHMP300.model);
        conservationEquipment.setManufacturer(SpinetixHMP300.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(Objects.requireNonNull(getClass().getResource(SpinetixHMP300.instruction)).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        conservationEquipment.setManual(file1.getName());
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = SpinetixHMP300.logo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.mainController.getLang();
        setLang(lang);
    }

    private String getMacAddressEquipment(String oui1, String oui2, String oui3, String uaa1, String uaa2, String uaa3) {
        String macAddress = null;

        if (!oui1.trim().isEmpty() && !oui2.trim().isEmpty() && !oui3.trim().isEmpty()
                && !uaa1.trim().isEmpty() && !uaa2.trim().isEmpty() && !uaa3.trim().isEmpty()) {
            if (oui1.length() == 2 && oui2.length() == 2 && oui3.length() == 2
                    && uaa1.length() == 2 && uaa2.length() == 2 && uaa3.length() == 2) {
                macAddress = oui1 + ":" + oui2 + ":" + oui3 + ":"
                        + uaa1 + ":" + uaa2 + ":" + uaa3;
            }
        }
        return macAddress;
    }

    private String getIpAddressEquipment(String network1, String network2, String subnet, String device) {
        String ipAddress = null;
        if (!network1.trim().isEmpty() && !network2.trim().isEmpty()
                && !subnet.trim().isEmpty() && !device.trim().isEmpty()) {
            ipAddress = network1 + "." + network2 + "." + subnet + "." + device;
        }
        return ipAddress;
    }
    private String getFrequency(String frequency1, String frequency2) {
        if(frequency1 != null && !frequency1.isEmpty() && frequency2 != null && !frequency2.isEmpty()){
            return frequency1 + "." + frequency2;
        }
        return null;
    }

    public void fillType() {
        List<String> x = new ArrayList<>();
        x.add("Portable");
        x.add("Stationary");
        comboBoxType.getItems().addAll(x);
    }

    public void fillVisible() {
        List<Integer> x = new ArrayList<>();
        x.add(0);
        x.add(1);
        comboBoxUserVisible.getItems().addAll(x);
    }
    
}
