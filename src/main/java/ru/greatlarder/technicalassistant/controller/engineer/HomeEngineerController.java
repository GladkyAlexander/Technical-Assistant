package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.Mail;
import ru.greatlarder.technicalassistant.services.tables.ListMail;
import ru.greatlarder.technicalassistant.services.tables.ListTask;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;
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
    public SplitPane splitPaneHF;
    @FXML
    public Label labelNumberOfDevices;
    @FXML
    public Label labelNumberOfFaultyDevices;
    @FXML
    public Label labelNumberOfDevicesOperatingForMoreThanFiveYears;
    @FXML
    public Label labelNumberOfTools;
    @FXML
    public Button btnUpMail;
    @FXML public TabPane tabPaneEngineerHome;
    Language language = new LanguageImpl();
    String lang;
    User user;
    private Company company;
    HandlerLang handlerLang = new HandlerLang();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        loadFragment();
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
        handlerLang.onNewDataLang(new DataLang(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }

    private void setLanguage(String lang) {
        labelNumberOfDevices.setText(language.NUMBER_OF_DEVICES(lang));
        labelNumberOfFaultyDevices.setText(language.NUMBER_OF_FAULTY_DEVICES(lang));
        labelNumberOfDevicesOperatingForMoreThanFiveYears.setText(language.THE_NUMBER_OF_DEVICES_OPERATING_FOR_MORE_THAN_5_YEARS(lang));
        labelNumberOfTools.setText(language.NUMBER_OF_TOOLS(lang));
    }

    private void setNumberOfDevice(List<Equipment> equipmentList) {
        labelQuantityEquipment.setText(String.valueOf(equipmentList.size()));
    }

    private void setNumberOfFaultyDevices(List<Equipment> equipmentList) {
        List<Equipment> faultyDevicesList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (equipment.getCondition().equals("Faulty") || equipment.getCondition().equals("Неисправно")) {
                faultyDevicesList.add(equipment);
            }
        }
        labelQuantityDefectEquipment.setText(String.valueOf(faultyDevicesList.size()));
    }

    private void setNumberOfDevicesOperatingForMoreThanFiveYears(List<Equipment> equipmentList) {
        List<Equipment> devicesOperatingForMoreThanFiveYears = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            LocalDate from = LocalDate.now();
            LocalDate to = LocalDate.parse(String.valueOf(equipment.getDateWork()));
            long day = DAYS.between(to, from);
            if (day > 1825) {
                devicesOperatingForMoreThanFiveYears.add(equipment);
            }
        }
        labelQuantityEquipmentWorkFive.setText(String.valueOf(devicesOperatingForMoreThanFiveYears.size()));
    }

    private void setNumberOfTools(List<Tool> toolList) {
        labelQuantityTheTool.setText(String.valueOf(toolList.size()));
    }

    public void uploadMail(ActionEvent mouseEvent) {
        if(splitPaneHF.getItems().size() == 2) {
            splitPaneHF.getItems().remove(1);
        }
        loadMail();
    }

    public void loadFragment() {
        if (company != null) {
            for (Company company1 : user.getCompanyList()) {
                if (company.equals(company1)) {
                    setNumberOfDevice(company1.getEquipmentList());
                    setNumberOfFaultyDevices(company1.getEquipmentList());
                    setNumberOfDevicesOperatingForMoreThanFiveYears(company1.getEquipmentList());
                    setNumberOfTools(company1.getToolList());
                }
            }
            tabPaneEngineerHome.getTabs().clear();
            tabPaneEngineerHome.getTabs().add(new Tab(language.ALL_ACTIVE_APPLICATIONS(lang),loadTasksActive()));
            tabPaneEngineerHome.getTabs().add(new Tab(language.ALL_APPLICATIONS(lang), loadTasksAll()));
        }
        if(user.getMailSettings().size() > 0){
            loadMail();
        }
    }

    private ListView<Task> loadTasksActive() {
        List<Task> taskActiveList = new ArrayList<>();
        for (Task task : company.getTaskList()) {
            if (task.getStatus() == 1) {
                taskActiveList.add(task);
            }
        }
        ListTask listTask = new ListTask();
        handlerLang.registerObserverLang(listTask);
        handlerCompanyListener.registerObserverCompany(listTask);
        handlerLang.onNewDataLang(new DataLang(lang));
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));

        return listTask.upBox(taskActiveList);
    }

    private ListView<Task> loadTasksAll() {
        ListTask listTask = new ListTask();
        handlerLang.registerObserverLang(listTask);
        handlerCompanyListener.registerObserverCompany(listTask);
        handlerLang.onNewDataLang(new DataLang(lang));
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
        return listTask.upBox(company.getTaskList());
    }

    private void loadMail(){
        javafx.concurrent.Task<ListView<Task>> task = new javafx.concurrent.Task<ListView<Task>>() {
            @Override
            protected ListView<Task> call() throws Exception {
                ListMail listMail = new ListMail();
                handlerLang.registerObserverLang(listMail);
                handlerCompanyListener.registerObserverCompany(listMail);
                handlerLang.onNewDataLang(new DataLang(lang));
                handlerCompanyListener.onNewDataCompany(new DataCompany(company));
                Mail mail = new Mail(user.getMailSettings().get(0));

                return listMail.upBox(mail.getListOfTasks());
            }
        };
        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        gridPaneHomeFragment.add(progressBar, 0,4, 2,1);
        task.setOnSucceeded((succeededEvent)->{
            if(splitPaneHF.getItems().size() == 2){
                splitPaneHF.getItems().remove(1);
            }
            splitPaneHF.setDividerPositions(0.15f, 0,85f);
            splitPaneHF.getItems().add(1, task.getValue());
            gridPaneHomeFragment.getChildren().remove(progressBar);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();

    }
}
