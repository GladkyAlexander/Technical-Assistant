package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
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
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_DANGER;
import static ru.greatlarder.technicalassistant.services.style.StyleSRC.STYLE_WARNING;

public class FragmentAddEquipmentController implements ObserverLang, ObserverCompany {
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
    public TextField textFieldRoom;
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
    public CheckBox checkModel;
    @FXML
    public CheckBox checkManufacturer;
    @FXML
    public CheckBox checkSerialNumber;
    @FXML
    public CheckBox checkMacAddress;
    @FXML
    public CheckBox checkIpv4;
    @FXML
    public CheckBox checkFrequency;
    @FXML
    public CheckBox checkIpv4Dante;
    @FXML
    public CheckBox checkNumberPortSwitcher;
    @FXML
    public HBox hBoxMacAddress;
    @FXML public Label labelCondition;
    @FXML public Label labelFrequency;
    @FXML public Label labelInstruction;
    @FXML public Label labelMaxTimeWorkLamp;
    @FXML public Label labelDateTheWork;
    @FXML public Label labelLocationToRoom;
    @FXML public Label labelRoom;
    @FXML public Label labelLogin;
    @FXML public Label labelPassword;
    @FXML public Label labelModel;
    @FXML public Label labelManufacturer;
    @FXML public Label labelSerialNumber;
    private String lang;
    Language language = new LanguageImpl();
    Company company;
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    List<Equipment> listNetworkSwitcher;
    FileManager fileManager = new FileManagerImpl();
    String nameFileManual;

    public FragmentAddEquipmentController() {
    }

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
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }

    public void loadFragment(){
        List<String> list = new ArrayList<>(equipmentRepository.getListEquipmentName(lang));
        this.listNetworkSwitcher = new ArrayList<>(equipmentRepository.getListEquipmentByName(language.NETWORK_SWITCH(lang), company.getNameCompany()));

        cmbEquipmentType.setItems(FXCollections.observableArrayList(list));
        comboBoxStatusSelection.setItems(FXCollections.observableArrayList(language.status_sheet(lang)));

        List<String> list1 = new ArrayList<>();
        for (Equipment f : listNetworkSwitcher) {
            list1.add(f.getSerialNumber());
        }

        choiceBoxNetworkSvitcher.setItems(FXCollections.observableArrayList(list1));

        cmbEquipmentType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            updatePanes((String) newValue);
        });
        updatePanes("");
        updateCheckBox();
    }
    public void updatePanes(String value) {

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

    }
    public void updateCheckBox() {
        checkModel.setVisible(false);
        checkModel.setManaged(false);
        checkManufacturer.setVisible(false);
        checkManufacturer.setManaged(false);
        checkSerialNumber.setVisible(false);
        checkSerialNumber.setManaged(false);
        checkMacAddress.setVisible(false);
        checkMacAddress.setManaged(false);
        checkIpv4.setVisible(false);
        checkIpv4.setManaged(false);
        checkFrequency.setVisible(false);
        checkFrequency.setManaged(false);
        checkIpv4Dante.setVisible(false);
        checkIpv4Dante.setManaged(false);
        checkNumberPortSwitcher.setVisible(false);
        checkNumberPortSwitcher.setManaged(false);
    }
    public void onKeyModel() {
        checkModel.setVisible(true);
        checkModel.setManaged(true);
        if (!textFiledModel.getText().trim().isEmpty()) {
            textFiledModel.setStyle(new TextField().getStyle());
            checkModel.setSelected(true);
        } else if (textFiledModel.getText().trim().isEmpty()) {
            textFiledModel.setStyle(STYLE_WARNING);
            checkModel.setSelected(false);
        }
    }

    public void onKeyManufacturer() {
        checkManufacturer.setVisible(true);
        checkManufacturer.setManaged(true);
        if (!textFieldManufacturer.getText().trim().isEmpty()) {
            textFieldManufacturer.setStyle(new TextField().getStyle());
            checkManufacturer.setSelected(true);
        } else if (textFieldManufacturer.getText().trim().isEmpty()) {
            textFieldManufacturer.setStyle(STYLE_WARNING);
            checkManufacturer.setSelected(false);
        }
    }

    public void onKeySerialNumber() {
        if (!textFieldSerialNumber.getText().trim().isEmpty()) {
            checkSerialNumber.setVisible(true);
            checkSerialNumber.setManaged(true);
            if (equipmentRepository.getEquipmentBySerialNumber(company.getNameCompany(), textFieldSerialNumber.getText()) == null) {
                checkSerialNumber.setSelected(true);
                textFieldSerialNumber.setStyle(new TextField().getStyle());
            } else {
                checkSerialNumber.setSelected(false);
                textFieldSerialNumber.setStyle(STYLE_DANGER);
            }
        } else if (textFieldSerialNumber.getText().trim().isEmpty()) {
            textFieldSerialNumber.setStyle(STYLE_WARNING);
            checkSerialNumber.setSelected(false);
        }
    }

    public void onKeyOui1() {
        if (oui1.getText().isEmpty()) {
            oui1.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition(oui1.getText()) || oui1.getText().length() > 2) {
            oui1.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition(oui1.getText())) {
            oui1.setStyle(new TextField().getStyle());
        } else oui1.setStyle(new TextField().getStyle());
    }

    public void onKeyOui2() {
        if (oui2.getText().isEmpty()) {
            oui2.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition(oui2.getText()) || oui2.getText().length() > 2) {
            oui2.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition(oui2.getText())) {
            oui2.setStyle(new TextField().getStyle());
        } else oui2.setStyle(new TextField().getStyle());
    }

    public void onKeyOui3() {
        if (oui3.getText().isEmpty()) {
            oui3.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition(oui3.getText()) || oui3.getText().length() > 2) {
            oui3.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition(oui3.getText())) {
            oui3.setStyle(new TextField().getStyle());
        } else oui3.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa1() {
        if (uaa1.getText().isEmpty()) {
            uaa1.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition(uaa1.getText()) || uaa1.getText().length() > 2) {
            uaa1.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition(uaa1.getText())) {
            uaa1.setStyle(new TextField().getStyle());
        } else uaa1.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa2() {
        if (uaa2.getText().isEmpty()) {
            uaa2.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition(uaa2.getText()) || uaa2.getText().length() > 2) {
            uaa2.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition(uaa2.getText())) {
            uaa2.setStyle(new TextField().getStyle());
        } else uaa2.setStyle(new TextField().getStyle());
    }

    public void onKeyUaa3() {
        checkMacAddress.setVisible(true);
        checkMacAddress.setManaged(true);
        if (uaa3.getText().isEmpty()) {
            uaa3.setStyle(new TextField().getStyle());
        } else if (!checkingStringWithACondition(uaa3.getText()) || uaa3.getText().length() > 2) {
            uaa3.setStyle(STYLE_DANGER);
        } else if (checkingStringWithACondition(uaa3.getText())) {
            uaa3.setStyle(new TextField().getStyle());
        } else uaa3.setStyle(new TextField().getStyle());

        if (!checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText().trim().isEmpty()) {
            hBoxMacAddress.setStyle(new HBox().getStyle());
            if (checkMacAddress(company.getNameCompany(), checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText())) {
                checkMacAddress.setSelected(true);
                hBoxMacAddress.setStyle(new HBox().getStyle());
            } else {
                checkMacAddress.setSelected(false);
                hBoxMacAddress.setStyle(STYLE_DANGER);
            }
        } else {
            checkMacAddress.setSelected(false);
            hBoxMacAddress.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyLogin() {
        if (!textFieldLogin.getText().trim().isEmpty() && checkingStringWithACondition(textFieldLogin.getText())) {
            textFieldLogin.setStyle(new TextField().getStyle());
        } else if (textFieldLogin.getText().trim().isEmpty()) {
            textFieldLogin.setStyle(STYLE_WARNING);
        } else if (!textFieldLogin.getText().trim().isEmpty() && !checkingStringWithACondition(textFieldLogin.getText())) {
            textFieldLogin.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRoom() {
        if (!textFieldRoom.getText().trim().isEmpty()) {
            textFieldRoom.setStyle(new TextField().getStyle());
        } else if (textFieldRoom.getText().trim().isEmpty()) {
            textFieldRoom.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyLocation() {
        if (!textFieldLocation.getText().trim().isEmpty()) {
            textFieldLocation.setStyle(new TextField().getStyle());
        } else if (textFieldLocation.getText().trim().isEmpty()) {
            textFieldLocation.setStyle(STYLE_WARNING);
        }
    }

    public void onKeyDatePicker(KeyEvent keyEvent) {
    }

    public void onKeyReleased() {
        if (!network1.getText().isEmpty()) {
            if (checkingForANumber(network1.getText())) {
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
            if (checkingForANumber(network2.getText())) {
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
            if (checkingForANumber(subnet.getText())) {
                if (subnet.getText().length() <= 3) {
                    if (Integer.parseInt(subnet.getText()) <= 256) {
                        subnet.setStyle(new TextField().getStyle());
                    } else subnet.setStyle(STYLE_DANGER);
                } else subnet.setStyle(STYLE_DANGER);
            } else subnet.setStyle(STYLE_DANGER);
        } else subnet.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedDevice() {
        checkIpv4.setVisible(true);
        checkIpv4.setManaged(true);
        if (!device.getText().isEmpty()) {
            if (checkingForANumber(device.getText())) {
                if (device.getText().length() <= 3) {
                    if (Integer.parseInt(device.getText()) <= 256) {
                        if (!checkEquipmentIpAddress(company.getNameCompany()
                                , network1.getText() + "." + network2.getText()
                                        + "." + subnet.getText() + "." + device.getText())) {
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
        if (!checkNet(network1, network2, subnet, device).getText().trim().isEmpty()) {
            if (!checkEquipmentIpAddress(company.getNameCompany(), checkNet(network1, network2, subnet, device).getText())) {
                hBoxTCP.setStyle(new HBox().getStyle());
                checkIpv4.setSelected(true);
            } else {
                hBoxTCP.setStyle(STYLE_DANGER);
                checkIpv4.setSelected(false);
            }
        } else {
            hBoxTCP.setStyle(STYLE_DANGER);
            checkIpv4.setSelected(false);
        }
    }

    public void onKeyReleasedMasc() {
        if (!networkMasc1.getText().isEmpty()) {
            if (checkingForANumber(networkMasc1.getText())) {
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
            if (checkingForANumber(networkMasc2.getText())) {
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
            if (checkingForANumber(subnetMasc.getText())) {
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
            if (checkingForANumber(deviceMasc.getText())) {
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
            if (checkingForANumber(gateway1.getText())) {
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
            if (checkingForANumber(gateway2.getText())) {
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
            if (checkingForANumber(subnetGateway.getText())) {
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
            if (checkingForANumber(deviceGateway.getText())) {
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
            System.out.println("Broke out :" + e);
        }
    }

    public void onKeyReleasedFrequency1() {
        if (!textFieldFrequency1.getText().trim().isEmpty()) {
            if (checkingStringWithACondition(textFieldFrequency1.getText())) {
                if (textFieldFrequency1.getText().length() <= 3) {
                    textFieldFrequency1.setStyle(new TextField().getStyle());
                } else textFieldFrequency1.setStyle(STYLE_DANGER);
            } else textFieldFrequency1.setStyle(STYLE_DANGER);
        } else textFieldFrequency1.setStyle(new TextField().getStyle());
    }

    public void onKeyReleasedFrequency2() {
        checkFrequency.setVisible(true);
        checkFrequency.setManaged(false);
        if (!textFieldFrequency2.getText().isEmpty()) {
            if (checkingStringWithACondition(textFieldFrequency2.getText())) {
                if (textFieldFrequency2.getText().length() <= 3) {
                    if (!checkingFrequency(company.getNameCompany(), textFieldFrequency1.getText() + "." + textFieldFrequency2.getText())) {
                        textFieldFrequency1.setStyle(new TextField().getStyle());
                        textFieldFrequency2.setStyle(new TextField().getStyle());
                    } else {
                        textFieldFrequency1.setStyle(STYLE_DANGER);
                        textFieldFrequency2.setStyle(STYLE_DANGER);
                    }
                } else textFieldFrequency2.setStyle(STYLE_DANGER);
            } else textFieldFrequency2.setStyle(STYLE_DANGER);
        } else textFieldFrequency2.setStyle(new TextField().getStyle());
        if (!checkingFrequency(company.getNameCompany(), textFieldFrequency1.getText() + "." + textFieldFrequency2.getText())) {
            hBoxFrequency.setStyle(new HBox().getStyle());
            checkFrequency.setSelected(true);
        } else {
            hBoxFrequency.setStyle(STYLE_DANGER);
            checkFrequency.setSelected(false);
        }
    }

    public void onKeyPressedMaximumLampOperatingTime() {
        if (!checkingForANumber(maximumLampOperatingTime.getText()) || maximumLampOperatingTime.getText().length() > 7) {
            maximumLampOperatingTime.setStyle(STYLE_DANGER);
        } else if (checkingForANumber(maximumLampOperatingTime.getText())) {
            maximumLampOperatingTime.setStyle(new TextField().getStyle());
        } else {
            maximumLampOperatingTime.setStyle(new TextField().getStyle());
        }
    }

    public void onKeyReleasedNetDante(KeyEvent keyEvent) {
        if (!network1Dante.getText().isEmpty()) {
            if (checkingForANumber(network1Dante.getText())) {
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
            if (checkingForANumber(network2Dante.getText())) {
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
            if (checkingForANumber(subnetDante.getText())) {
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
            if (checkingForANumber(deviceDante.getText())) {
                if (deviceDante.getText().length() <= 3) {
                    if (Integer.parseInt(deviceDante.getText()) <= 256) {
                        if (!checkEquipmentIpAddressDante(company.getNameCompany()
                                , network1Dante.getText() + "." + network2Dante.getText()
                                        + "." + subnetDante.getText() + "." + deviceDante.getText())) {
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
        if (!checkNet(network1Dante, network2Dante, subnetDante, deviceDante).getText().trim().isEmpty()) {
            if (!checkEquipmentIpAddressDante(company.getNameCompany(), checkNet(network1Dante, network2Dante, subnetDante, deviceDante).getText())) {
                hBoxTCPDante.setStyle(new HBox().getStyle());
                checkIpv4Dante.setSelected(true);
            } else {
                hBoxTCPDante.setStyle(STYLE_DANGER);
                checkIpv4Dante.setSelected(false);
            }
        } else {
            hBoxTCPDante.setStyle(STYLE_DANGER);
            checkIpv4Dante.setSelected(false);
        }
    }

    public void onKeyReleasedMasc1Dante(KeyEvent keyEvent) {
        if (!networkMasc1Dante.getText().isEmpty()) {
            if (checkingForANumber(networkMasc1Dante.getText())) {
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
            if (checkingForANumber(networkMasc2Dante.getText())) {
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
            if (checkingForANumber(subnetMascDante.getText())) {
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
            if (checkingForANumber(deviceMascDante.getText())) {
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
            if (checkingForANumber(gateway1Dante.getText())) {
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
            if (checkingForANumber(gateway2Dante.getText())) {
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
            if (checkingForANumber(subnetGatewayDante.getText())) {
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
            if (checkingForANumber(deviceGatewayDante.getText())) {
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
                if (checkingForANumber(tfPortSwitcher.getText())) {
                    if (tfPortSwitcher.getText().length() <= 2) {
                        if (Integer.parseInt(tfPortSwitcher.getText()) <= 48) {
                            tfPortSwitcher.setStyle(new TextField().getStyle());
                        } else tfPortSwitcher.setStyle(STYLE_DANGER);
                    } else tfPortSwitcher.setStyle(STYLE_DANGER);
                } else tfPortSwitcher.setStyle(STYLE_DANGER);
            } else tfPortSwitcher.setStyle(new TextField().getStyle());

            if (!checkingTheSwitchPort((NetworkSwitch) equipmentRepository.getEquipmentBySerialNumber(choiceBoxNetworkSvitcher.getValue().toString()
                    , company.getNameCompany()), Integer.parseInt(tfPortSwitcher.getText()))) {
                hBoxPortSwitcher.setStyle(new HBox().getStyle());
                checkNumberPortSwitcher.setSelected(true);
            } else {
                hBoxPortSwitcher.setStyle(STYLE_DANGER);
                checkNumberPortSwitcher.setSelected(false);
            }
        } else {
            hBoxNetworkSwitcher.setStyle(STYLE_DANGER);
            hBoxPortSwitcher.setStyle(STYLE_DANGER);
        }
    }

    public void onKeyRelisedOutlet(KeyEvent keyEvent) {
        if (tfOutlet.getText().isEmpty()) {
            tfOutlet.setStyle(new TextField().getStyle());
        } else if (checkingStringWithACondition(tfOutlet.getText())) {
            tfOutlet.setStyle(new TextField().getStyle());
        } else tfOutlet.setStyle(new TextField().getStyle());
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

    public TextField checkNet(TextField network1, TextField network2, TextField subnet, TextField device) {

        TextField textField = new TextField();

        if (!network1.getText().trim().isEmpty() && !network2.getText().trim().isEmpty()
                && !subnet.getText().trim().isEmpty() && !device.getText().trim().isEmpty()) {

            textField.setText(network1.getText() + "." + network2.getText() + "."
                    + subnet.getText() + "." + device.getText());

        } else {
            network1.setStyle(STYLE_WARNING);
            network2.setStyle(STYLE_WARNING);
            subnet.setStyle(STYLE_WARNING);
            device.setStyle(STYLE_WARNING);
        }

        return textField;
    }
    public boolean checkingStringWithACondition(String value) {
        return value.matches("^\\w+$");
    }
    public boolean checkMacAddress(String nameCompany, String macAddress) {
        if(equipmentRepository.getEquipmentByMacAddress(nameCompany, macAddress) == null){
            return true;
        } else return false;
    }
    public boolean checkingForANumber(String value) {
        return value.chars().allMatch(Character :: isDigit);
    }
    public boolean checkEquipmentIpAddress(String nameCompany, String ipAddress) {
        for (String str : equipmentRepository.getListIpAddressForCompany(nameCompany)){
            if (ipAddress.equals(str)) return true;
        }
        return false;
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
    public boolean checkEquipmentIpAddressDante(String nameCompany, String ipAddress) {
        for (String str : equipmentRepository.getListIpAddressDanteForCompany(nameCompany)){
            if (ipAddress.equals(str)) return true;
        }
        return false;
    }
    public boolean checkingTheSwitchPort(NetworkSwitch networkSwitch, int numberPort) {
        HashMap<Integer, Equipment> hashMap = new HashMap<>();
        hashMap.put(1, networkSwitch.getPort1());
        hashMap.put(2, networkSwitch.getPort2());
        hashMap.put(3, networkSwitch.getPort3());
        hashMap.put(4, networkSwitch.getPort4());
        hashMap.put(5, networkSwitch.getPort5());
        hashMap.put(6, networkSwitch.getPort6());
        hashMap.put(7, networkSwitch.getPort7());
        hashMap.put(8, networkSwitch.getPort8());
        hashMap.put(9, networkSwitch.getPort9());
        hashMap.put(10, networkSwitch.getPort10());
        hashMap.put(11, networkSwitch.getPort11());
        hashMap.put(12, networkSwitch.getPort12());
        hashMap.put(13, networkSwitch.getPort13());
        hashMap.put(14, networkSwitch.getPort14());
        hashMap.put(15, networkSwitch.getPort15());
        hashMap.put(16, networkSwitch.getPort16());
        hashMap.put(17, networkSwitch.getPort17());
        hashMap.put(18, networkSwitch.getPort18());
        hashMap.put(19, networkSwitch.getPort19());
        hashMap.put(20, networkSwitch.getPort20());
        hashMap.put(21, networkSwitch.getPort21());
        hashMap.put(22, networkSwitch.getPort22());
        hashMap.put(23, networkSwitch.getPort23());
        hashMap.put(24, networkSwitch.getPort24());
        hashMap.put(25, networkSwitch.getPort25());
        hashMap.put(26, networkSwitch.getPort26());
        hashMap.put(27, networkSwitch.getPort27());
        hashMap.put(28, networkSwitch.getPort28());
        hashMap.put(29, networkSwitch.getPort29());
        hashMap.put(30, networkSwitch.getPort30());
        hashMap.put(31, networkSwitch.getPort31());
        hashMap.put(32, networkSwitch.getPort32());
        hashMap.put(33, networkSwitch.getPort33());
        hashMap.put(34, networkSwitch.getPort34());
        hashMap.put(35, networkSwitch.getPort35());
        hashMap.put(36, networkSwitch.getPort36());
        hashMap.put(37, networkSwitch.getPort37());
        hashMap.put(38, networkSwitch.getPort38());
        hashMap.put(39, networkSwitch.getPort39());
        hashMap.put(40, networkSwitch.getPort40());
        hashMap.put(41, networkSwitch.getPort41());
        hashMap.put(42, networkSwitch.getPort42());
        hashMap.put(43, networkSwitch.getPort43());
        hashMap.put(44, networkSwitch.getPort44());
        hashMap.put(45, networkSwitch.getPort45());
        hashMap.put(46, networkSwitch.getPort46());
        hashMap.put(47, networkSwitch.getPort47());
        hashMap.put(48, networkSwitch.getPort48());

        Equipment value = hashMap.get(numberPort);

        if (value == null){
            return true;
        } else return false;
    }
    public Equipment getEquipment() {
        Equipment result = null;
        switch ((String) this.cmbEquipmentType.getValue()) {
            case Language.PROJECTOR_RU:
                Projector equipment = new Projector();
                loadEquipment(equipment);
                equipment.setTimeWorkLampProjector(0);
                equipment.setImage("projector.png");
                if (!maximumLampOperatingTime.getText().trim().isEmpty()) {
                    equipment.setMaximumLampOperatingTimeProjector(Integer.valueOf(maximumLampOperatingTime.getText()));
                } else equipment.setMaximumLampOperatingTimeProjector(null);
                result = equipment;

                break;
            case Language.MICROPHONE_RU:
                Microphone equipment1 = new Microphone();
                loadEquipment(equipment1);
                equipment1.setFrequency(textFieldFrequency1.getText() + "." + textFieldFrequency2.getText());
                equipment1.setImage("microphone.png");
                result = equipment1;
                break;

            case Language.NETWORK_SWITCH_RU:
                NetworkSwitch equipment2 = new NetworkSwitch();
                loadEquipment(equipment2);
                equipment2.setImage("projector.png");
                result = equipment2;
                break;

            case Language.ACOUSTIC_SPEAKER_RU:
                AcousticSpeaker equipment3 = new AcousticSpeaker();
                loadEquipment(equipment3);
                equipment3.setImage("projector.png");
                result = equipment3;
                break;

            case Language.CONTROL_PROCESSOR_RU:
                ControlProcessor equipment4 = new ControlProcessor();
                loadEquipment(equipment4);
                equipment4.setImage("projector.png");
                result = equipment4;
                break;

            case Language.AUDIO_PROCESSOR_RU:
                AudioProcessor equipment5 = new AudioProcessor();
                loadEquipment(equipment5);
                equipment5.setImage("projector.png");
                result = equipment5;
                break;

            case Language.AUDIO_AMPLIFIER_RU:
                AudioAmplifier equipment6 = new AudioAmplifier();
                loadEquipment(equipment6);
                equipment6.setImage("projector.png");
                result = equipment6;
                break;

            case Language.AUDIO_INTERFACE_RU:
                AudioInterface equipment7 = new AudioInterface();
                loadEquipment(equipment7);
                equipment7.setImage("projector.png");
                result = equipment7;
                break;

            case Language.TV_PANEL_RU:
                TvPanel equipment8 = new TvPanel();
                loadEquipment(equipment8);
                equipment8.setImage("projector.png");
                equipment8.setDiagonal(tfDiagonal.getText());
                result = equipment8;
                break;

            case Language.TV_TUNER_RU:
                TvTuner equipment9 = new TvTuner();
                loadEquipment(equipment9);
                equipment9.setImage("projector.png");
                result = equipment9;
                break;

            case Language.MEDIA_PLAYER_RU:
                MediaPlayer equipment10 = new MediaPlayer();
                loadEquipment(equipment10);
                equipment10.setImage("projector.png");
                result = equipment10;
                break;

            case Language.LAPTOP_RU:
                Laptop equipment11 = new Laptop();
                loadEquipment(equipment11);
                equipment11.setImage("projector.png");
                equipment11.setOs(tfOs.getText());
                result = equipment11;
                break;

            case Language.VIDEO_TRANSMITTER_RU:
                VideoTransmitter equipment12 = new VideoTransmitter();
                loadEquipment(equipment12);
                equipment12.setImage("projector.png");
                result = equipment12;
                break;

            case Language.VIDEO_RECEIVER_RU:
                VideoReceiver equipment13 = new VideoReceiver();
                loadEquipment(equipment13);
                equipment13.setImage("projector.png");
                result = equipment13;
                break;

            case Language.MATRIX_SWITCHER_RU:
                MatrixSwitcher equipment14 = new MatrixSwitcher();
                loadEquipment(equipment14);
                equipment14.setImage("projector.png");
                result = equipment14;
                break;

            case Language.TOUCH_CONTROL_PANEL_RU:
                TouchControlPanel equipment15 = new TouchControlPanel();
                loadEquipment(equipment15);
                equipment15.setImage("projector.png");
                equipment15.setDiagonal(tfDiagonal.getText());
                result = equipment15;
                break;
        }

        return result;
    }
    public Equipment loadEquipment(Equipment equipment) {
        Equipment returnEquipment = null;

        if (equipment instanceof Projector) {
            returnEquipment = new Projector();
        }
        if (equipment instanceof Microphone) {
            returnEquipment = new Microphone();
        }
        if (equipment instanceof NetworkSwitch) {
            returnEquipment = new NetworkSwitch();
        }
        if (equipment instanceof AcousticSpeaker) {
            returnEquipment = new AcousticSpeaker();
        }
        if (equipment instanceof ControlProcessor) {
            returnEquipment = new ControlProcessor();
        }
        if (equipment instanceof AudioProcessor) {
            returnEquipment = new AudioProcessor();
        }
        if (equipment instanceof AudioAmplifier) {
            returnEquipment = new AudioAmplifier();
        }
        if (equipment instanceof AudioInterface) {
            returnEquipment = new AudioInterface();
        }
        if (equipment instanceof TvPanel) {
            returnEquipment = new TvPanel();
        }
        if (equipment instanceof TvTuner) {
            returnEquipment = new TvTuner();
        }
        if (equipment instanceof MediaPlayer) {
            returnEquipment = new MediaPlayer();
        }
        if (equipment instanceof Laptop) {
            returnEquipment = new Laptop();
        }
        if (equipment instanceof VideoTransmitter) {
            returnEquipment = new VideoTransmitter();
        }
        if (equipment instanceof VideoReceiver) {
            returnEquipment = new VideoReceiver();
        }
        if (equipment instanceof MatrixSwitcher) {
            returnEquipment = new MatrixSwitcher();
        }
        if (equipment instanceof TouchControlPanel) {
            returnEquipment = new TouchControlPanel();
        }
        equipment.setName(cmbEquipmentType.getValue().toString());
        equipment.setModel(textFiledModel.getText());
        equipment.setManufacturer(textFieldManufacturer.getText());
        equipment.setSerialNumber(textFieldSerialNumber.getText());
        equipment.setMacAddress(checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText());
        equipment.setLogin(textFieldLogin.getText());
        equipment.setPassword(textFieldPassword.getText());
        equipment.setRoom(textFieldRoom.getText());
        equipment.setLocation(textFieldLocation.getText());
        if (textFieldDateOfCommissioning.getValue() == null) {
            equipment.setDateWork(LocalDate.now());
            textFieldDateOfCommissioning.setPromptText(language.TODAY_DATE_WILL_BE_SET_RU(lang));
        } else equipment.setDateWork(textFieldDateOfCommissioning.getValue());
        if(!checkNet(network1, network2, subnet, device).getText().isEmpty()) {
            equipment.setIpAddress(checkNet(network1, network2, subnet, device).getText());
        }
        equipment.setMasc(checkNet(networkMasc1, networkMasc2, subnetMasc, deviceMasc).getText());
        equipment.setGateway(checkNet(gateway1, gateway2, subnetGateway, deviceGateway).getText());
        if(!checkNet(networkMasc1Dante, networkMasc2Dante, subnetMascDante, deviceMascDante).getText().isEmpty()) {
            equipment.setDanteIpAddress(checkNet(network1Dante, network2Dante, subnetDante, deviceDante).getText());
        }
        equipment.setDanteMasc(checkNet(networkMasc1Dante, networkMasc2Dante, subnetMascDante, deviceMascDante).getText());
        equipment.setDanteGateway(checkNet(gateway1Dante, gateway2Dante, subnetGatewayDante, deviceGatewayDante).getText());
        equipment.setCondition(comboBoxStatusSelection.getValue().toString());
        equipment.setCompany(company.getNameCompany());
        equipment.setManual(nameFileManual);
        if (choiceBoxNetworkSvitcher.getValue() != null) {
            equipment.setIdNetworkSwitcher((equipmentRepository.getEquipmentBySerialNumber(company.getNameCompany(), choiceBoxNetworkSvitcher.getValue().toString())).getId());
            equipment.setPortNumberInTheSwitch(Integer.parseInt(tfPortSwitcher.getText()));
            equipment.setOutletNumber(tfOutlet.getText());
        }
        return equipment;
    }
    public void saveEquipment(MouseEvent mouseEvent) {
        if (cmbEquipmentType.getValue() != null) {
            cmbEquipmentType.setStyle(new ComboBox().getStyle());
            if (comboBoxStatusSelection.getValue() != null) {
                comboBoxStatusSelection.setStyle(new ComboBox().getStyle());

                if (getCheckBoxList()) {

                    Equipment equipment = getEquipment();
                   if (this.cmbEquipmentType.getValue() != null) {
                        if (equipment != null) {
                            equipmentRepository.setEquipment(equipment);
                            if (equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()) != null) {
                                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
                                labelOk.setText(language.EQUIPMENT(language) + " " + language.SERIAL_NUMBER(lang) + " : " + equipment.getSerialNumber() + " " + language.ADDED(lang));
                                btnSaveEquipment.setDisable(true);
                                GlobalLinkMainController.getMainController().updateUser();
                            } else {
                                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                                labelOk.setText(equipment.getSerialNumber() + " : " + language.WILL_NOT_BE_ADDED(lang));
                            }
                        } else {
                            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                            labelOk.setText(textFieldSerialNumber.getText() + " : " + language.WILL_NOT_BE_ADDED(lang));
                        }
                    } else {
                        imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                        labelOk.setText(language.FILL_IN_THE_FIELDS(lang));
                    }
                } else {
                    imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                    labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
                }
            } else {
                comboBoxStatusSelection.setStyle(STYLE_DANGER);
            }
        } else {
            cmbEquipmentType.setStyle(STYLE_DANGER);
        }
    }

    public void addNameEquipment(MouseEvent mouseEvent) {
    }

    public void closeAddEquipmentController(MouseEvent mouseEvent) {
        GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getRight()
        );
    }
    public Boolean getCheckBoxList() {
        if (checkModel.isSelected() && checkManufacturer.isSelected() && checkSerialNumber.isSelected()) {
            if (checkMacAddress.isSelected() || (!checkMacAddress.isSelected() && !checkMac(oui1, oui2, oui3, uaa1, uaa2, uaa3).getText().trim().isEmpty())) {

                hBoxMacAddress.setStyle(new HBox().getStyle());
                if (checkIpv4.isSelected() || (!checkIpv4.isSelected() && checkNet(network1, network2, subnet, device).getText().trim().isEmpty())) {

                    hBoxTCP.setStyle(new HBox().getStyle());

                    if (checkIpv4Dante.isSelected() || (!checkIpv4Dante.isSelected() && checkNet(network1Dante, network2Dante, subnetDante, deviceDante).getText().trim().isEmpty())){

                        hBoxTCPDante.setStyle(new HBox().getStyle());
                        if (checkNumberPortSwitcher.isSelected() || (!checkNumberPortSwitcher.isSelected() && tfPortSwitcher.getText().trim().isEmpty())) {

                            hBoxNetworkSwitcher.setStyle(new HBox().getStyle());
                            hBoxPortSwitcher.setStyle(new HBox().getStyle());
                            return true;
                        } else { hBoxNetworkSwitcher.setStyle(STYLE_DANGER);
                            hBoxPortSwitcher.setStyle(STYLE_DANGER);
                            return false;
                        }
                    } else {
                        hBoxTCPDante.setStyle(STYLE_DANGER);
                        return false;
                    }
                } else {
                    hBoxTCP.setStyle(STYLE_DANGER);
                    return false;
                }
            } else {
                hBoxMacAddress.setStyle(STYLE_DANGER);
                return false;
            }
        } else return false;

    }
}
