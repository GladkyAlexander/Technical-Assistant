package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

public class HomeEngineerController implements ObserverLang, ObserverUser, ObserverCompany {
    @FXML
    public Label labelQuantityEquipment;
    @FXML
    public Label labelQuantityDefectEquipment;
    @FXML
    public Label labelQuantityEquipmentWorkFive;
    @FXML
    public Label labelQuantityTheTool;
    @FXML
    public BorderPane borderPaneHomePage;
    @FXML
    public GridPane gridPaneHomeFragment;
    @FXML
    public ImageView imgMail;
    @FXML
    public ProgressBar progressBar;
    @FXML public SplitPane splitPaneHF;
    @FXML public Label labelNumberOfDevices;
    @FXML public Label labelNumberOfFaultyDevices;
    @FXML public Label labelNumberOfDevicesOperatingForMoreThanFiveYears;
    @FXML public Label labelNumberOfTools;
    Language language = new LanguageImpl();
    User user;
    private Company company;

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        if (company != null){
            for (Company company1 : user.getCompanyList()){
                if(company.equals(company1)){
                    setNumberOfDevice(company1.getEquipmentList());
                    setNumberOfFaultyDevices(company1.getEquipmentList());
                    setNumberOfDevicesOperatingForMoreThanFiveYears(company1.getEquipmentList());
                    setNumberOfTools(company1.getToolList());
                }
            }
        }
    }

    @Override
    public void updateLang(DataLang dataLang) {
        setLanguage(dataLang.getLanguage());
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
    private void setLanguage(String lang){
        labelNumberOfDevices.setText(language.NUMBER_OF_DEVICES(lang));
        labelNumberOfFaultyDevices.setText(language.NUMBER_OF_FAULTY_DEVICES(lang));
        labelNumberOfDevicesOperatingForMoreThanFiveYears.setText(language.THE_NUMBER_OF_DEVICES_OPERATING_FOR_MORE_THAN_5_YEARS(lang));
        labelNumberOfTools.setText(language.NUMBER_OF_TOOLS(lang));
    }
    private void setNumberOfDevice(List<Equipment> equipmentList){
        labelQuantityEquipment.setText(String.valueOf(equipmentList.size()));
    }
    private void setNumberOfFaultyDevices(List<Equipment> equipmentList){
        List<Equipment> faultyDevicesList = new ArrayList<>();
        for (Equipment equipment : equipmentList){
            if(equipment.getCondition().equals("Faulty") || equipment.getCondition().equals("Неисправно")){
                faultyDevicesList.add(equipment);
            }
        }
        labelQuantityDefectEquipment.setText(String.valueOf(faultyDevicesList.size()));
    }

    private void setNumberOfDevicesOperatingForMoreThanFiveYears(List<Equipment> equipmentList){
        List<Equipment> devicesOperatingForMoreThanFiveYears = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            LocalDate from = LocalDate.now();
            LocalDate to = LocalDate.parse(String.valueOf(equipment.getDateWork()));
            long day = DAYS.between(to, from);
            if (day > 1825){
                devicesOperatingForMoreThanFiveYears.add(equipment);
            }
        }
        labelQuantityEquipmentWorkFive.setText(String.valueOf(devicesOperatingForMoreThanFiveYears.size()));
    }

    private void setNumberOfTools(List<Tool> toolList ){
        labelQuantityTheTool.setText(String.valueOf(toolList.size()));
    }
    public void uploadMail(MouseEvent mouseEvent) {
        if (user != null) {
            splitPaneHF.getItems().remove(1);
        }
    }
}
