package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.domain.spinetix.SpinetixHMP200;
import ru.greatlarder.technicalassistant.domain.spinetix.SpinetixHMP300;
import ru.greatlarder.technicalassistant.domain.spinetix.SpinetixHMP400;
import ru.greatlarder.technicalassistant.domain.wirenboard.WirenBoard6;
import ru.greatlarder.technicalassistant.domain.wirenboard.WirenBoard7;
import ru.greatlarder.technicalassistant.services.check.CheckEquipment;
import ru.greatlarder.technicalassistant.services.check.impl.CheckEquipmentImpl;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.RoomsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.RoomsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;
import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_DANGER;
import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_WARNING;

public class FragmentAddEquipmentController implements ObserverLang, ObserverCompany, ObserverUser {
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
    @FXML public HBox hBoxSelectionByManufacturerMediaplayer;
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
    private String lang;
    Language language = new LanguageImpl();
    private Company company;
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    RoomsRepository roomsRepository = new RoomsRepositoryImpl();
    List<Equipment> listNetworkSwitcher;
    FileManager fileManager = new FileManagerImpl();
    String nameFileManual;
    private String logoImg =null;
    CheckEquipment checkEquipment = new CheckEquipmentImpl();
    private User user;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLang(lang);
    }

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
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else {
            this.company = dataCompany.getCompany();
            loadFragment();
        }
    }

    public void loadFragment() {
        List<String> listNameEquipment = new ArrayList<>(equipmentRepository.getListEquipmentName(lang));
        this.listNetworkSwitcher = new ArrayList<>(equipmentRepository.getListEquipmentByName(language.NETWORK_SWITCH(lang), company.getNameCompany()));

        cmbEquipmentType.setItems(FXCollections.observableArrayList(listNameEquipment));

        comboBoxStatusSelection.setItems(FXCollections.observableArrayList(language.status_sheet(lang)));

        List<String> roomName = new ArrayList<>();
        for (Room r : roomsRepository.getListRoomForCompany(company.getNameCompany())){
            roomName.add(r.getNameRoom());
        }
        comboBoxRooms.setItems(FXCollections.observableArrayList(roomName));

        List<String> list1 = new ArrayList<>();
        for (Equipment f : listNetworkSwitcher) {
            list1.add(f.getSerialNumber());
        }

        choiceBoxNetworkSvitcher.setItems(FXCollections.observableArrayList(list1));

        cmbEquipmentType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePanes((String) newValue);
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

        this.hBoxMaxLamp.setVisible(value.equals(language.PROJECTOR(lang)));
        this.hBoxMaxLamp.setManaged(value.equals(language.PROJECTOR(lang)));

        this.hBoxFrequency.setVisible(value.equals(language.MICROPHONE(lang)));
        this.hBoxFrequency.setManaged(value.equals(language.MICROPHONE(lang)));

        boolean b = value.equals(language.MICROPHONE(lang)) || value.equals(language.AUDIO_PROCESSOR(lang)) || value.equals(language.AUDIO_AMPLIFIER(lang))
                || value.equals(language.ACOUSTIC_SPEAKER(lang)) || value.equals(language.AUDIO_INTERFACE(lang)) || value.equals(language.MATRIX_SWITCHER(lang));
        this.hBoxTCPDante.setVisible(b);
        this.hBoxTCPDante.setManaged(b);
        this.hBoxMascDante.setVisible(b);
        this.hBoxMascDante.setManaged(b);
        this.hBoxGatewayDante.setVisible(b);
        this.hBoxGatewayDante.setManaged(b);

        boolean c = value.equals(language.TV_PANEL(lang)) || value.equals(language.TOUCH_CONTROL_PANEL(lang));
        this.hBoxDiagonal.setVisible(c);
        this.hBoxDiagonal.setManaged(c);

        this.hBoxLaptop.setVisible(value.equals(language.LAPTOP(lang)));
        this.hBoxLaptop.setManaged(value.equals(language.LAPTOP(lang)));

        boolean d = !value.equals(language.NETWORK_SWITCH(lang));
        this.hBoxOutlander.setVisible(d);
        this.hBoxOutlander.setManaged(d);
        this.hBoxPortSwitcher.setVisible(d);
        this.hBoxPortSwitcher.setManaged(d);
        this.hBoxNetworkSwitcher.setVisible(d);
        this.hBoxNetworkSwitcher.setManaged(d);

        boolean r = value.equals(language.MEDIA_PLAYER(lang));
        this.hBoxSelectionByManufacturerMediaplayer.setVisible(r);
        this.hBoxSelectionByManufacturerMediaplayer.setManaged(r);
        boolean cont = value.equals(language.CONTROLLER(lang));
        this.hBoxSelectionByManufacturerController.setVisible(cont);
        this.hBoxSelectionByManufacturerController.setManaged(cont);
    }

    public void onKeyModel() {
        if (!textFiledModel.getText().trim().isEmpty()) {
            textFiledModel.setStyle(new TextField().getStyle());
        } else if (textFiledModel.getText().trim().isEmpty()) {
            textFiledModel.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyManufacturer() {
        if (!textFieldManufacturer.getText().trim().isEmpty()) {
            textFieldManufacturer.setStyle(new TextField().getStyle());
        } else if (textFieldManufacturer.getText().trim().isEmpty()) {
            textFieldManufacturer.setStyle(STYLE_WARNING);
        }
    }

    public void onKeySerialNumber() {
        if (!textFieldSerialNumber.getText().trim().isEmpty()) {
            if (equipmentRepository.getEquipmentBySerialNumber(company.getNameCompany(), textFieldSerialNumber.getText()) == null) {
                textFieldSerialNumber.setStyle(new TextField().getStyle());
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
        } else if (!checkEquipment.checkingStringWithACondition(oui1.getText()) || oui1.getText().length() > 2) {
            oui1.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui1.getText())) {
            oui1.setStyle(new TextField().getStyle());
        } else oui1.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2() {
        if (oui2.getText().isEmpty()) {
            oui2.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui2.getText()) || oui2.getText().length() > 2) {
            oui2.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui2.getText())) {
            oui2.setStyle(new TextField().getStyle());
        } else oui2.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3() {
        if (oui3.getText().isEmpty()) {
            oui3.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui3.getText()) || oui3.getText().length() > 2) {
            oui3.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui3.getText())) {
            oui3.setStyle(new TextField().getStyle());
        } else oui3.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1() {
        if (uaa1.getText().isEmpty()) {
            uaa1.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa1.getText()) || uaa1.getText().length() > 2) {
            uaa1.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa1.getText())) {
            uaa1.setStyle(new TextField().getStyle());
        } else uaa1.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2() {
        if (uaa2.getText().isEmpty()) {
            uaa2.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa2.getText()) || uaa2.getText().length() > 2) {
            uaa2.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa2.getText())) {
            uaa2.setStyle(new TextField().getStyle());
        } else uaa2.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3() {
        if (uaa3.getText().isEmpty()) {
            uaa3.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa3.getText()) || uaa3.getText().length() > 2) {
            uaa3.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa3.getText())) {
            uaa3.setStyle(new TextField().getStyle());
        } else uaa3.setStyle(new TextField().getStyle());
        if (checkEquipment.getMacAddressEquipment(oui1.getText(), oui2.getText(), oui3.getText(), uaa1.getText(), uaa2.getText(), uaa3.getText()) != null) {
            hBoxMacAddress.setStyle(new HBox().getStyle());
            if (!checkEquipment.checkingEquipmentMacAddress(checkEquipment.getMacAddressEquipment(oui1.getText(), oui2.getText(), oui3.getText(), uaa1.getText(), uaa2.getText(), uaa3.getText()), company.getNameCompany())) {
                hBoxMacAddress.setStyle(new HBox().getStyle());
            } else {
                hBoxMacAddress.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyLogin() {
        if (!textFieldLogin.getText().trim().isEmpty() && checkEquipment.checkingStringWithACondition(textFieldLogin.getText())) {
            textFieldLogin.setStyle(new TextField().getStyle());
        } else if (textFieldLogin.getText().trim().isEmpty()) {
            textFieldLogin.setStyle(STYLE_WARNING);
        } else if (!textFieldLogin.getText().trim().isEmpty() && !checkEquipment.checkingStringWithACondition(textFieldLogin.getText())) {
            textFieldLogin.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyLocation() {
        if (!textFieldLocation.getText().trim().isEmpty()) {
            textFieldLocation.setStyle(new TextField().getStyle());
        } else if (textFieldLocation.getText().trim().isEmpty()) {
            textFieldLocation.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyReleased() {
        if (!network1.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(network1.getText())) {
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
            if (checkEquipment.checkingForANumber(network2.getText())) {
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
            if (checkEquipment.checkingForANumber(subnet.getText())) {
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
            if (checkEquipment.checkingForANumber(device.getText())) {
                if (device.getText().length() <= 3) {
                    if (Integer.parseInt(device.getText()) <= 256) {
                        if (!checkEquipment.checkingForAddressOccupancy(checkEquipment.getIpAddressEquipment(network1.getText(), network2.getText(),
                                subnet.getText(), device.getText()), company.getNameCompany())) {
                            network1.setStyle(new TextField().getStyle());
                            network2.setStyle(new TextField().getStyle());
                            subnet.setStyle(new TextField().getStyle());
                            device.setStyle(new TextField().getStyle());
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
            if (checkEquipment.checkingForANumber(networkMasc1.getText())) {
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
            if (checkEquipment.checkingForANumber(networkMasc2.getText())) {
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
            if (checkEquipment.checkingForANumber(subnetMasc.getText())) {
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
            if (checkEquipment.checkingForANumber(deviceMasc.getText())) {
                if (deviceMasc.getText().length() <= 3) {
                    if (Integer.parseInt(deviceMasc.getText()) <= 256) {
                        deviceMasc.setStyle(new TextField().getStyle());
                    } else deviceMasc.setStyle(STYLE_DANGER);
                } else deviceMasc.setStyle(STYLE_DANGER);
            } else deviceMasc.setStyle(STYLE_DANGER);
        } else deviceMasc.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGateway() {
        if (!gateway1.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(gateway1.getText())) {
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
            if (checkEquipment.checkingForANumber(gateway2.getText())) {
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
            if (checkEquipment.checkingForANumber(subnetGateway.getText())) {
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
            if (checkEquipment.checkingForANumber(deviceGateway.getText())) {
                if (deviceGateway.getText().length() <= 3) {
                    if (Integer.parseInt(deviceGateway.getText()) <= 256) {
                        deviceGateway.setStyle(new TextField().getStyle());
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

            nameFileManual = file.getName();
            try {
                FileUtils.copyFile(file, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    public void onKeyReleasedFrequency1() {
        if (!textFieldFrequency1.getText().trim().isEmpty()) {
            if (checkEquipment.checkingStringWithACondition(textFieldFrequency1.getText())) {
                if (textFieldFrequency1.getText().length() <= 3) {
                    textFieldFrequency1.setStyle(new TextField().getStyle());
                } else textFieldFrequency1.setStyle(STYLE_DANGER);
            } else textFieldFrequency1.setStyle(STYLE_DANGER);
        } else textFieldFrequency1.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedFrequency2() {
        if (!textFieldFrequency2.getText().isEmpty()) {
            if (checkEquipment.checkingStringWithACondition(textFieldFrequency2.getText())) {
                if (textFieldFrequency2.getText().length() <= 3) {
                    if (!checkEquipment.checkingFrequency(checkEquipment.getFrequency(textFieldFrequency1.getText(), textFieldFrequency2.getText()), company.getNameCompany())) {
                        textFieldFrequency1.setStyle(new TextField().getStyle());
                        textFieldFrequency2.setStyle(new TextField().getStyle());
                        hBoxFrequency.setStyle(new HBox().getStyle());
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
        if (!checkEquipment.checkingForANumber(maximumLampOperatingTime.getText()) || maximumLampOperatingTime.getText().length() > 7) {
            maximumLampOperatingTime.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingForANumber(maximumLampOperatingTime.getText())) {
            maximumLampOperatingTime.setStyle(new TextField().getStyle());
        } else {
            maximumLampOperatingTime.setStyle(new TextField().getStyle());
        }
    }

    public void onKeyReleasedNetDante(KeyEvent keyEvent) {
        if (!network1Dante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(network1Dante.getText())) {
                if (network1Dante.getText().length() <= 3) {
                    if (Integer.parseInt(network1Dante.getText()) <= 256) {
                        network1Dante.setStyle(new TextField().getStyle());
                    } else network1Dante.setStyle(STYLE_DANGER);
                } else network1Dante.setStyle(STYLE_DANGER);
            } else network1Dante.setStyle(STYLE_DANGER);
        } else network1Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedNet2Dante(KeyEvent keyEvent) {
        if (!network2Dante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(network2Dante.getText())) {
                if (network2Dante.getText().length() <= 3) {
                    if (Integer.parseInt(network2Dante.getText()) <= 256) {
                        network2Dante.setStyle(new TextField().getStyle());
                    } else network2Dante.setStyle(STYLE_DANGER);
                } else network2Dante.setStyle(STYLE_DANGER);
            } else network2Dante.setStyle(STYLE_DANGER);
        } else network2Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedSubnetDante(KeyEvent keyEvent) {
        if (!subnetDante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(subnetDante.getText())) {
                if (subnetDante.getText().length() <= 3) {
                    if (Integer.parseInt(subnetDante.getText()) <= 256) {
                        subnetDante.setStyle(new TextField().getStyle());
                    } else subnetDante.setStyle(STYLE_DANGER);
                } else subnetDante.setStyle(STYLE_DANGER);
            } else subnetDante.setStyle(STYLE_DANGER);
        } else subnetDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedDeviceDante(KeyEvent keyEvent) {
        hBoxTCPDante.setVisible(true);
        hBoxTCPDante.setManaged(true);
        if (!deviceDante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(deviceDante.getText())) {
                if (deviceDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceDante.getText()) <= 256) {
                        if (!checkEquipment.checkingForAddressOccupancyDante(checkEquipment.getIpAddressEquipment(network1Dante.getText(), network2Dante.getText()
                                , subnetDante.getText(), deviceDante.getText()), company.getNameCompany())){
                            network1Dante.setStyle(new TextField().getStyle());
                            network2Dante.setStyle(new TextField().getStyle());
                            subnetDante.setStyle(new TextField().getStyle());
                            deviceDante.setStyle(new TextField().getStyle());
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

    public void onKeyReleasedMasc1Dante(KeyEvent keyEvent) {
        if (!networkMasc1Dante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(networkMasc1Dante.getText())) {
                if (networkMasc1Dante.getText().length() <= 3) {
                    if (Integer.parseInt(networkMasc1Dante.getText()) <= 256) {
                        networkMasc1Dante.setStyle(new TextField().getStyle());
                    } else networkMasc1Dante.setStyle(STYLE_DANGER);
                } else networkMasc1Dante.setStyle(STYLE_DANGER);
            } else networkMasc1Dante.setStyle(STYLE_DANGER);
        } else networkMasc1Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMasc2Dante(KeyEvent keyEvent) {
        if (!networkMasc2Dante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(networkMasc2Dante.getText())) {
                if (networkMasc2Dante.getText().length() <= 3) {
                    if (Integer.parseInt(networkMasc2Dante.getText()) <= 256) {
                        networkMasc2Dante.setStyle(new TextField().getStyle());
                    } else networkMasc2Dante.setStyle(STYLE_DANGER);
                } else networkMasc2Dante.setStyle(STYLE_DANGER);
            } else networkMasc2Dante.setStyle(STYLE_DANGER);
        } else networkMasc2Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMascSubnetDante(KeyEvent keyEvent) {
        if (!subnetMascDante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(subnetMascDante.getText())) {
                if (subnetMascDante.getText().length() <= 3) {
                    if (Integer.parseInt(subnetMascDante.getText()) <= 256) {
                        subnetMascDante.setStyle(new TextField().getStyle());
                    } else subnetMascDante.setStyle(STYLE_DANGER);
                } else subnetMascDante.setStyle(STYLE_DANGER);
            } else subnetMascDante.setStyle(STYLE_DANGER);
        } else subnetMascDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedMascDeviceDante(KeyEvent keyEvent) {
        if (!deviceMascDante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(deviceMascDante.getText())) {
                if (deviceMascDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceMascDante.getText()) <= 256) {
                        deviceMascDante.setStyle(new TextField().getStyle());
                    } else deviceMascDante.setStyle(STYLE_DANGER);
                } else deviceMascDante.setStyle(STYLE_DANGER);
            } else deviceMascDante.setStyle(STYLE_DANGER);
        } else deviceMascDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewayDante(KeyEvent keyEvent) {
        if (!gateway1Dante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(gateway1Dante.getText())) {
                if (gateway1Dante.getText().length() <= 3) {
                    if (Integer.parseInt(gateway1Dante.getText()) <= 256) {
                        gateway1Dante.setStyle(new TextField().getStyle());
                    } else gateway1Dante.setStyle(STYLE_DANGER);
                } else gateway1Dante.setStyle(STYLE_DANGER);
            } else gateway1Dante.setStyle(STYLE_DANGER);
        } else gateway1Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGateway2Dante(KeyEvent keyEvent) {
        if (!gateway2Dante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(gateway2Dante.getText())) {
                if (gateway2Dante.getText().length() <= 3) {
                    if (Integer.parseInt(gateway2Dante.getText()) <= 256) {
                        gateway2Dante.setStyle(new TextField().getStyle());
                    } else gateway2Dante.setStyle(STYLE_DANGER);
                } else gateway2Dante.setStyle(STYLE_DANGER);
            } else gateway2Dante.setStyle(STYLE_DANGER);
        } else gateway2Dante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewaySubnetDante(KeyEvent keyEvent) {
        if (!subnetGatewayDante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(subnetGatewayDante.getText())) {
                if (subnetGatewayDante.getText().length() <= 3) {
                    if (Integer.parseInt(subnetGatewayDante.getText()) <= 256) {
                        subnetGatewayDante.setStyle(new TextField().getStyle());
                    } else subnetGatewayDante.setStyle(STYLE_DANGER);
                } else subnetGatewayDante.setStyle(STYLE_DANGER);
            } else subnetGatewayDante.setStyle(STYLE_DANGER);
        } else subnetGatewayDante.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedGatewayDeviceDante(KeyEvent keyEvent) {
        if (!deviceGatewayDante.getText().isEmpty()) {
            if (checkEquipment.checkingForANumber(deviceGatewayDante.getText())) {
                if (deviceGatewayDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceGatewayDante.getText()) <= 256) {
                        deviceGatewayDante.setStyle(new TextField().getStyle());
                    } else deviceGatewayDante.setStyle(STYLE_DANGER);
                } else deviceGatewayDante.setStyle(STYLE_DANGER);
            } else deviceGatewayDante.setStyle(STYLE_DANGER);
        } else deviceGatewayDante.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedPortSwitcher(KeyEvent keyEvent) {
        if (choiceBoxNetworkSvitcher.getValue() != null) {
            hBoxNetworkSwitcher.setStyle(new HBox().getStyle());
            hBoxPortSwitcher.setStyle(new HBox().getStyle());
            if (!tfPortSwitcher.getText().isEmpty()) {
                if (checkEquipment.checkingForANumber(tfPortSwitcher.getText())) {
                    if (tfPortSwitcher.getText().length() <= 2) {
                        if (Integer.parseInt(tfPortSwitcher.getText()) <= 48) {
                            tfPortSwitcher.setStyle(new TextField().getStyle());
                        } else tfPortSwitcher.setStyle(STYLE_DANGER);
                    } else tfPortSwitcher.setStyle(STYLE_DANGER);
                } else tfPortSwitcher.setStyle(STYLE_DANGER);
            } else tfPortSwitcher.setStyle(new TextField().getStyle());

            if (!checkEquipment.checkingTheSwitchPort((NetworkSwitch) equipmentRepository.getEquipmentBySerialNumber(choiceBoxNetworkSvitcher.getValue().toString()
                    , company.getNameCompany()), Integer.parseInt(tfPortSwitcher.getText()))) {
                hBoxPortSwitcher.setStyle(new HBox().getStyle());
            } else {
                hBoxPortSwitcher.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxNetworkSwitcher.setStyle(STYLE_DANGER);
            hBoxPortSwitcher.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRelisedOutlet(KeyEvent keyEvent) {
        if (tfOutlet.getText().isEmpty()) {
            tfOutlet.setStyle(new TextField().getStyle());
        } else if (checkEquipment.checkingStringWithACondition(tfOutlet.getText())) {
            tfOutlet.setStyle(new TextField().getStyle());
        } else tfOutlet.setStyle(new TextField().getStyle());
    }

    public Equipment getEquipment() {
        Equipment result = null;
        switch (this.cmbEquipmentType.getValue()) {
            case Language.PROJECTOR_RU -> {
                Projector equipment = new Projector();
                result = loadEquipment(equipment);
            }
            case Language.MICROPHONE_RU -> {
                Microphone equipment1 = new Microphone();
                result = loadEquipment(equipment1);
            }
            case Language.NETWORK_SWITCH_RU -> {
                NetworkSwitch equipment2 = new NetworkSwitch();
                result = loadEquipment(equipment2);
            }
            case Language.ACOUSTIC_SPEAKER_RU -> {
                AcousticSpeaker equipment3 = new AcousticSpeaker();
                result = loadEquipment(equipment3);
            }
            case Language.CONTROL_PROCESSOR_RU -> {
                ControlProcessor equipment4 = new ControlProcessor();
                result = loadEquipment(equipment4);
            }
            case Language.AUDIO_PROCESSOR_RU -> {
                AudioProcessor equipment5 = new AudioProcessor();
                result = loadEquipment(equipment5);
            }
            case Language.AUDIO_AMPLIFIER_RU -> {
                AudioAmplifier equipment6 = new AudioAmplifier();
                result = loadEquipment(equipment6);
            }
            case Language.AUDIO_INTERFACE_RU -> {
                AudioInterface equipment7 = new AudioInterface();
                result = loadEquipment(equipment7);
            }
            case Language.TV_PANEL_RU -> {
                TvPanel equipment8 = new TvPanel();
                result = loadEquipment(equipment8);
            }
            case Language.TV_TUNER_RU -> {
                TvTuner equipment9 = new TvTuner();
                result = loadEquipment(equipment9);
            }
            case Language.MEDIA_PLAYER_RU -> {
                MediaPlayer equipment10 = new MediaPlayer();
                result = loadEquipment(equipment10);
            }
            case Language.LAPTOP_RU -> {
                Laptop equipment11 = new Laptop();
                result = loadEquipment(equipment11);
            }
            case Language.VIDEO_TRANSMITTER_RU -> {
                VideoTransmitter equipment12 = new VideoTransmitter();
                result = loadEquipment(equipment12);
            }
            case Language.VIDEO_RECEIVER_RU -> {
                VideoReceiver equipment13 = new VideoReceiver();
                result = loadEquipment(equipment13);
            }
            case Language.MATRIX_SWITCHER_RU -> {
                MatrixSwitcher equipment14 = new MatrixSwitcher();
                result = loadEquipment(equipment14);
            }
            case Language.TOUCH_CONTROL_PANEL_RU -> {
                TouchControlPanel equipment15 = new TouchControlPanel();
                result = loadEquipment(equipment15);
            }
            case Language.CONTROLLER_RU -> {
                Controller equipment16 = new Controller();
                result = loadEquipment(equipment16);
            }
        }

        return result;
    }

    public Equipment loadEquipment(Equipment equipment) {
        Equipment returnEquipment = null;
        if (equipment instanceof Projector) {
            returnEquipment = new Projector();
            ((Projector) returnEquipment).setTimeWorkLampProjector(0);
            returnEquipment.setImage("projector.png");
            if (!maximumLampOperatingTime.getText().trim().isEmpty()) {
                ((Projector) returnEquipment).setMaximumLampOperatingTimeProjector(Integer.valueOf(maximumLampOperatingTime.getText()));
            } else ((Projector) returnEquipment).setMaximumLampOperatingTimeProjector(null);
        }
        if (equipment instanceof Microphone) {
            returnEquipment = new Microphone();
            ((Microphone) returnEquipment).setFrequency(textFieldFrequency1.getText() + "." + textFieldFrequency2.getText());
            returnEquipment.setImage("microphone.png");
        }
        if (equipment instanceof NetworkSwitch) {
            returnEquipment = new NetworkSwitch();
            returnEquipment.setImage("network_switch.png");
        }
        if (equipment instanceof AcousticSpeaker) {
            returnEquipment = new AcousticSpeaker();
            returnEquipment.setImage("as.png");
        }
        if (equipment instanceof ControlProcessor) {
            returnEquipment = new ControlProcessor();
            returnEquipment.setImage("control_processor.png");
        }
        if (equipment instanceof AudioProcessor) {
            returnEquipment = new AudioProcessor();
            returnEquipment.setImage("audio_processor.png");
        }
        if (equipment instanceof AudioAmplifier) {
            returnEquipment = new AudioAmplifier();
            returnEquipment.setImage("audio_amplifer.png");
        }
        if (equipment instanceof AudioInterface) {
            returnEquipment = new AudioInterface();
            returnEquipment.setImage("audio_interface.png");
        }
        if (equipment instanceof TvPanel) {
            returnEquipment = new TvPanel();
            returnEquipment.setImage("tv_panel.png");
            ((TvPanel) returnEquipment).setDiagonal(tfDiagonal.getText());
        }
        if (equipment instanceof TvTuner) {
            returnEquipment = new TvTuner();
            returnEquipment.setImage("tv_tuner.png");
        }
        if (equipment instanceof MediaPlayer) {
            returnEquipment = new MediaPlayer();
            if(logoImg != null){
                returnEquipment.setImage(logoImg);
            }else returnEquipment.setImage("media_player.png");
        }
        if (equipment instanceof Laptop) {
            returnEquipment = new Laptop();
            returnEquipment.setImage("laptop.png");
            ((Laptop) returnEquipment).setOs(tfOs.getText());
        }
        if (equipment instanceof VideoTransmitter) {
            returnEquipment = new VideoTransmitter();
            returnEquipment.setImage("tx_rx.jpg");
        }
        if (equipment instanceof VideoReceiver) {
            returnEquipment = new VideoReceiver();
            returnEquipment.setImage("tx_rx.jpg");
        }
        if (equipment instanceof MatrixSwitcher) {
            returnEquipment = new MatrixSwitcher();
            returnEquipment.setImage("matrix_switcher.jpg");
        }
        if (equipment instanceof TouchControlPanel) {
            returnEquipment = new TouchControlPanel();
            returnEquipment.setImage("control_patch_panel.png");
            ((TouchControlPanel) returnEquipment).setDiagonal(tfDiagonal.getText());
        }
        if (equipment instanceof Controller) {
            returnEquipment = new Controller();
            if(logoImg != null){
                returnEquipment.setImage(logoImg);
            }else returnEquipment.setImage("control_processor.png");
        }

        returnEquipment.setName(cmbEquipmentType.getValue());
        returnEquipment.setModel(textFiledModel.getText());
        returnEquipment.setManufacturer(textFieldManufacturer.getText());
        returnEquipment.setSerialNumber(textFieldSerialNumber.getText());
        returnEquipment.setMacAddress(checkEquipment.getMacAddressEquipment(oui1.getText(), oui2.getText(), oui3.getText(), uaa1.getText(), uaa2.getText(), uaa3.getText()));
        returnEquipment.setMacAddress1(checkEquipment.getMacAddressEquipment(oui11.getText(), oui21.getText(), oui31.getText(), uaa11.getText(), uaa21.getText(), uaa31.getText()));
        returnEquipment.setMacAddress2(checkEquipment.getMacAddressEquipment(oui12.getText(), oui22.getText(), oui32.getText(), uaa12.getText(), uaa22.getText(), uaa32.getText()));
        returnEquipment.setMacAddress3(checkEquipment.getMacAddressEquipment(oui13.getText(), oui23.getText(), oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()));
        returnEquipment.setLogin(textFieldLogin.getText());
        returnEquipment.setPassword(textFieldPassword.getText());
        if(comboBoxRooms.getValue() != null) {
            returnEquipment.setRoom(comboBoxRooms.getValue());
        }
        returnEquipment.setLocation(textFieldLocation.getText());
        if (textFieldDateOfCommissioning.getValue() == null) {
            returnEquipment.setDateWork(LocalDate.now());
            textFieldDateOfCommissioning.setPromptText(language.TODAY_DATE_WILL_BE_SET_RU(lang));
        } else returnEquipment.setDateWork(textFieldDateOfCommissioning.getValue());

        if (checkEquipment.getIpAddressEquipment(network1.getText(), network2.getText(), subnet.getText(), device.getText()) != null
        && !checkEquipment.checkingForAddressOccupancy(checkEquipment.getIpAddressEquipment(network1.getText(), network2.getText(), subnet.getText(), device.getText()),
                company.getNameCompany())) {
            returnEquipment.setIpAddress(checkEquipment.getIpAddressEquipment(network1.getText(), network2.getText(), subnet.getText(), device.getText()));
        } else returnEquipment.setIpAddress(null);

        returnEquipment.setMasc(checkEquipment.getIpAddressEquipment(networkMasc1.getText(), networkMasc2.getText(), subnetMasc.getText(), deviceMasc.getText()));
        returnEquipment.setGateway(checkEquipment.getIpAddressEquipment(gateway1.getText(), gateway2.getText(), subnetGateway.getText(), deviceGateway.getText()));

        if (checkEquipment.getIpAddressEquipment(networkMasc1Dante.getText(), networkMasc2Dante.getText(), subnetMascDante.getText(), deviceMascDante.getText()) != null
                && !checkEquipment.checkingForAddressOccupancyDante(checkEquipment.getIpAddressEquipment(networkMasc1Dante.getText(), networkMasc2Dante.getText(), subnetMascDante.getText(), deviceMascDante.getText()),
                company.getNameCompany())) {
            returnEquipment.setDanteIpAddress(checkEquipment.getIpAddressEquipment(networkMasc1Dante.getText(), networkMasc2Dante.getText(), subnetMascDante.getText(), deviceMascDante.getText()));
        } else returnEquipment.setDanteIpAddress(null);

        returnEquipment.setDanteMasc(checkEquipment.getIpAddressEquipment(networkMasc1Dante.getText(), networkMasc2Dante.getText(), subnetMascDante.getText(), deviceMascDante.getText()));
        returnEquipment.setDanteGateway(checkEquipment.getIpAddressEquipment(gateway1Dante.getText(), gateway2Dante.getText(), subnetGatewayDante.getText(), deviceGatewayDante.getText()));
        returnEquipment.setCondition(comboBoxStatusSelection.getValue().toString());
        returnEquipment.setCompany(company.getNameCompany());
        returnEquipment.setManual(nameFileManual);

        if (choiceBoxNetworkSvitcher.getValue() != null) {
            returnEquipment.setIdNetworkSwitcher((equipmentRepository.getEquipmentBySerialNumber(company.getNameCompany(), choiceBoxNetworkSvitcher.getValue().toString())).getId());
            returnEquipment.setPortNumberInTheSwitch(Integer.parseInt(tfPortSwitcher.getText()));
            returnEquipment.setOutletNumber(tfOutlet.getText());
        }

        if(returnEquipment.getSerialNumber() != null || !returnEquipment.getSerialNumber().isEmpty()){
            return returnEquipment;
        } else return null;
    }

    public void saveEquipment(MouseEvent mouseEvent) {
        if (cmbEquipmentType.getValue() != null) {
            cmbEquipmentType.setStyle(new ComboBox<String>().getStyle());
            if (comboBoxStatusSelection.getValue() != null) {
                comboBoxStatusSelection.setStyle(new ComboBox<String>().getStyle());
                    Equipment equipment = getEquipment();
                        if (equipment != null) {
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    equipmentRepository.setEquipment(equipment);
                                    return null;
                                }
                            };
                            ProgressBar progressBar= new ProgressBar(task.getProgress());
                            progressBar.visibleProperty();
                            progressBar.setMaxWidth(MAX_VALUE);
                            progressBar.setMaxHeight(MAX_VALUE);
                            GlobalLinkStartEngineerController.getStartEngineerController().vBoxLeftButton.getChildren().add(progressBar);
                            task.setOnSucceeded((succeededEvent)->{
                                progressBar.visibleProperty().bind(task.runningProperty());
                                if (equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()) != null) {
                                    imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
                                    labelOk.setText(language.EQUIPMENT(lang) + " " + language.SERIAL_NUMBER(lang) + " : " + equipment.getSerialNumber() + " " + language.ADDED(lang));
                                    clear();
                                    GlobalLinkMainController.getMainController().updateUser(new DataUser(userRepository.getUserLoginPassword(this.user.getLogin(), this.user.getPassword())));
                                    GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(
                                            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getRight()
                                    );
                                } else {
                                    imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                                    labelOk.setText(equipment.getSerialNumber() + " : " + language.WILL_NOT_BE_ADDED(lang));
                                }
                            });

                            ExecutorService executorService = Executors.newFixedThreadPool(1);
                            executorService.execute(task);
                            executorService.shutdown();
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
    }

    public void closeAddEquipmentController(MouseEvent mouseEvent) {
        GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getRight()
        );
    }

    public void onActionBtnMacDop(ActionEvent actionEvent) {
        this.hBoxMacAddress1.setVisible(true);
        this.hBoxMacAddress1.setManaged(true);
        this.hBoxMacAddress2.setVisible(true);
        this.hBoxMacAddress2.setManaged(true);
        this.hBoxMacAddress3.setVisible(true);
        this.hBoxMacAddress3.setManaged(true);
    }

    public void onKeyOui1_1(KeyEvent keyEvent) {
        if (oui11.getText().isEmpty()) {
            oui11.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui11.getText()) || oui11.getText().length() > 2) {
            oui11.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui11.getText())) {
            oui11.setStyle(new TextField().getStyle());
        } else oui11.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_1(KeyEvent keyEvent) {
        if (oui21.getText().isEmpty()) {
            oui21.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui21.getText()) || oui21.getText().length() > 2) {
            oui21.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui21.getText())) {
            oui21.setStyle(new TextField().getStyle());
        } else oui21.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_1(KeyEvent keyEvent) {
        if (oui31.getText().isEmpty()) {
            oui31.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui31.getText()) || oui31.getText().length() > 2) {
            oui31.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui31.getText())) {
            oui31.setStyle(new TextField().getStyle());
        } else oui31.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_1(KeyEvent keyEvent) {
        if (uaa11.getText().isEmpty()) {
            uaa11.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa11.getText()) || uaa11.getText().length() > 2) {
            uaa11.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa11.getText())) {
            uaa11.setStyle(new TextField().getStyle());
        } else uaa11.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_1(KeyEvent keyEvent) {
        if (uaa21.getText().isEmpty()) {
            uaa21.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa21.getText()) || uaa21.getText().length() > 2) {
            uaa21.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa21.getText())) {
            uaa21.setStyle(new TextField().getStyle());
        } else uaa21.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_1(KeyEvent keyEvent) {
        if (uaa31.getText().isEmpty()) {
            uaa31.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa31.getText()) || uaa31.getText().length() > 2) {
            uaa31.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa31.getText())) {
            uaa31.setStyle(new TextField().getStyle());
        } else uaa31.setStyle(new TextField().getStyle());

        if (checkEquipment.getMacAddressEquipment(oui11.getText(), oui21.getText(), oui31.getText(),uaa11.getText(), uaa21.getText(), uaa31.getText()) != null) {
            hBoxMacAddress1.setStyle(new HBox().getStyle());
            if (!checkEquipment.checkingEquipmentMac1Address(checkEquipment.getMacAddressEquipment(oui11.getText()
                    , oui21.getText(), oui31.getText(),uaa11.getText(), uaa21.getText(), uaa31.getText()), company.getNameCompany())) {
                hBoxMacAddress1.setStyle(new HBox().getStyle());
            } else {
                hBoxMacAddress1.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress1.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui1_2(KeyEvent keyEvent) {
        if (oui12.getText().isEmpty()) {
            oui12.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui12.getText()) || oui12.getText().length() > 2) {
            oui12.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui12.getText())) {
            oui12.setStyle(new TextField().getStyle());
        } else oui12.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_2(KeyEvent keyEvent) {
        if (oui22.getText().isEmpty()) {
            oui22.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui22.getText()) || oui22.getText().length() > 2) {
            oui22.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui22.getText())) {
            oui22.setStyle(new TextField().getStyle());
        } else oui22.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_2(KeyEvent keyEvent) {
        if (oui32.getText().isEmpty()) {
            oui32.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui32.getText()) || oui32.getText().length() > 2) {
            oui32.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui32.getText())) {
            oui32.setStyle(new TextField().getStyle());
        } else oui32.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_2(KeyEvent keyEvent) {
        if (uaa12.getText().isEmpty()) {
            uaa12.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa12.getText()) || uaa12.getText().length() > 2) {
            uaa12.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa12.getText())) {
            uaa12.setStyle(new TextField().getStyle());
        } else uaa12.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_2(KeyEvent keyEvent) {
        if (uaa22.getText().isEmpty()) {
            uaa22.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa22.getText()) || uaa22.getText().length() > 2) {
            uaa22.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa22.getText())) {
            uaa22.setStyle(new TextField().getStyle());
        } else uaa22.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_2(KeyEvent keyEvent) {
        if (uaa32.getText().isEmpty()) {
            uaa32.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa32.getText()) || uaa32.getText().length() > 2) {
            uaa32.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa32.getText())) {
            uaa32.setStyle(new TextField().getStyle());
        } else uaa32.setStyle(new TextField().getStyle());

        if (checkEquipment.getMacAddressEquipment(oui12.getText(), oui22.getText(), oui32.getText(), uaa12.getText(), uaa22.getText(), uaa32.getText()) != null) {
            hBoxMacAddress2.setStyle(new HBox().getStyle());
            if (!checkEquipment.checkingEquipmentMac2Address(checkEquipment.getMacAddressEquipment(oui12.getText(), oui22.getText()
                    , oui32.getText(), uaa12.getText(), uaa22.getText(), uaa32.getText()), company.getNameCompany())) {
                hBoxMacAddress2.setStyle(new HBox().getStyle());
            } else {
                hBoxMacAddress2.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress2.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyOui1_3(KeyEvent keyEvent) {
        if (oui13.getText().isEmpty()) {
            oui13.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui13.getText()) || oui13.getText().length() > 2) {
            oui13.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui13.getText())) {
            oui13.setStyle(new TextField().getStyle());
        } else oui13.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2_3(KeyEvent keyEvent) {
        if (oui23.getText().isEmpty()) {
            oui23.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui23.getText()) || oui23.getText().length() > 2) {
            oui23.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui23.getText())) {
            oui23.setStyle(new TextField().getStyle());
        } else oui23.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3_3(KeyEvent keyEvent) {
        if (oui33.getText().isEmpty()) {
            oui33.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(oui33.getText()) || oui33.getText().length() > 2) {
            oui33.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(oui33.getText())) {
            oui33.setStyle(new TextField().getStyle());
        } else oui33.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1_3(KeyEvent keyEvent) {
        if (uaa13.getText().isEmpty()) {
            uaa13.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa13.getText()) || uaa13.getText().length() > 2) {
            uaa13.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa13.getText())) {
            uaa13.setStyle(new TextField().getStyle());
        } else uaa13.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2_3(KeyEvent keyEvent) {
        if (uaa23.getText().isEmpty()) {
            uaa23.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa23.getText()) || uaa23.getText().length() > 2) {
            uaa23.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa23.getText())) {
            uaa23.setStyle(new TextField().getStyle());
        } else uaa23.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3_3(KeyEvent keyEvent) {
        if (uaa33.getText().isEmpty()) {
            uaa33.setStyle(new TextField().getStyle());
        } else if (!checkEquipment.checkingStringWithACondition(uaa33.getText()) || uaa33.getText().length() > 2) {
            uaa33.setStyle(STYLE_DANGER);
        } else if (checkEquipment.checkingStringWithACondition(uaa33.getText())) {
            uaa33.setStyle(new TextField().getStyle());
        } else uaa33.setStyle(new TextField().getStyle());

        if (checkEquipment.getMacAddressEquipment(oui13.getText(), oui23.getText(), oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()) != null) {
            hBoxMacAddress3.setStyle(new HBox().getStyle());
            if (!checkEquipment.checkingEquipmentMac3Address(checkEquipment.getMacAddressEquipment(oui13.getText(), oui23.getText()
                    , oui33.getText(), uaa13.getText(), uaa23.getText(), uaa33.getText()), company.getNameCompany())) {
                hBoxMacAddress3.setStyle(new HBox().getStyle());
            } else {
                hBoxMacAddress3.setStyle(STYLE_DANGER);
            }
        } else {
            hBoxMacAddress3.setStyle(STYLE_DANGER);
        }
    }

    public void onActionCmbEquipmentType(MouseEvent actionEvent) {
    
    }

    public void openHMP200(MouseEvent mouseEvent) throws URISyntaxException {
        textFiledModel.setText(SpinetixHMP200.model);
        textFieldManufacturer.setText(SpinetixHMP200.manufacturer);
        pdfFile.setDisable(true);
            File file1 = new File(getClass().getResource(SpinetixHMP200.instruction).toURI());
            File file = new File(fileManager.folderManual() + "\\" + file1.getName());
            nameFileManual = file1.getName();
            try {
                FileUtils.copyFile(file1, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            logoImg = SpinetixHMP200.logo;
    }

    public void openHMP400(MouseEvent mouseEvent) throws URISyntaxException {
        textFiledModel.setText(SpinetixHMP400.model);
        textFieldManufacturer.setText(SpinetixHMP400.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(getClass().getResource(SpinetixHMP400.instruction).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        nameFileManual = file1.getName();
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = SpinetixHMP400.logo;
    }

    public void openWirenboard(MouseEvent mouseEvent) {
        this.hBoxSelectionByDeviceController.setVisible(true);
        this.hBoxSelectionByDeviceController.setManaged(true);
    }

    public void wb7open(MouseEvent mouseEvent) throws URISyntaxException {
        textFiledModel.setText(WirenBoard7.model);
        textFieldManufacturer.setText(WirenBoard7.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(getClass().getResource(WirenBoard7.instruction).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        nameFileManual = file1.getName();
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = WirenBoard7.logo;
    }

    public void openWB6(MouseEvent mouseEvent) throws URISyntaxException {
        textFiledModel.setText(WirenBoard6.model);
        textFieldManufacturer.setText(WirenBoard7.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(getClass().getResource(WirenBoard6.instruction).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        nameFileManual = file1.getName();
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = WirenBoard6.logo;
    }

    public void openSpinetix(MouseEvent mouseEvent) {
        this.hBoxSelectionByDeviceMediaPlayer.setVisible(true);
        this.hBoxSelectionByDeviceMediaPlayer.setManaged(true);
    }

    public void openHMP300(MouseEvent mouseEvent) throws URISyntaxException {
        textFiledModel.setText(SpinetixHMP300.model);
        textFieldManufacturer.setText(SpinetixHMP300.manufacturer);
        pdfFile.setDisable(true);
        File file1 = new File(getClass().getResource(SpinetixHMP300.instruction).toURI());
        File file = new File(fileManager.folderManual() + "\\" + file1.getName());
        nameFileManual = file1.getName();
        try {
            FileUtils.copyFile(file1, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoImg = SpinetixHMP300.logo;
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
