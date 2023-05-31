package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListAffairs;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.GetListMailSettings;
import ru.greatlarder.technicalassistant.services.database.GetListTool;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.GetListAffairsActiveSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.GetListAffairsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.GetListMailSettingsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.tool.GetListToolAllSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewMail;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewEmailMin;
import ru.greatlarder.technicalassistant.services.tables.TableViewTask;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;
import static java.time.temporal.ChronoUnit.DAYS;

public class HomeEngineerController implements ObserverLang, Initializable{
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
    public TabPane tabPaneEngineerHome;
    Language language = new LanguageImpl();
    String lang;
    User user;
    Company company;
    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang) {
        labelNumberOfDevices.setText(language.NUMBER_OF_DEVICES(lang));
        labelNumberOfFaultyDevices.setText(language.NUMBER_OF_FAULTY_DEVICES(lang));
        labelNumberOfDevicesOperatingForMoreThanFiveYears.setText(language.THE_NUMBER_OF_DEVICES_OPERATING_FOR_MORE_THAN_5_YEARS(lang));
        labelNumberOfTools.setText(language.NUMBER_OF_TOOLS(lang));
    }

    private void setNumberOfFaultyDevices(List<Equipment> equipmentList) {
        List<Equipment> faultyDevicesList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if(equipment.getCondition() != null){
                if (equipment.getCondition().equals("Faulty") || equipment.getCondition().equals("Неисправно")) {
                    faultyDevicesList.add(equipment);
                }
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

    public void loadFragment() {
        GlobalLinkMainController.getMainController().getHandlerLang().registerObserverLang(this);
        if (company != null) {
            GetListEquipment getListEquipment = new ListEquipmentByNameCompanySQLite();
            List<Equipment> equipmentList = getListEquipment.getListEquipment(user, company.getNameCompany(), company.getNameCompany());
            labelQuantityEquipment.setText(String.valueOf(equipmentList.size()));
            
            setNumberOfFaultyDevices(equipmentList);
            setNumberOfDevicesOperatingForMoreThanFiveYears(equipmentList);
            
            GetListTool getListTool = new GetListToolAllSQLite();
            setNumberOfTools(getListTool.getListTool(user, company.getNameCompany(), null));

            tabPaneEngineerHome.getTabs().add(loadTasksActive());
           //tabPaneEngineerHome.getTabs().add(loadTasksAll());
          
            GetListMailSettings getListMailSettings = new GetListMailSettingsSQLite();

            MailSettings ms = null;
            for (MailSettings mailSettings : getListMailSettings.getListMailSettings(user, String.valueOf(user.getId()))){
                ms = mailSettings;
            }
            if (ms != null) {
                loadMail(ms);
            }

        }

    }

    private Tab loadTasksActive() {

        Tab t = new Tab(language.ALL_ACTIVE_APPLICATIONS(lang));
        Task<ListView<Affairs>> ts = new Task<>() {
            @Override
            protected ListView<Affairs> call() {
                GetListAffairs getListAffairsActive = new GetListAffairsActiveSQLite();
                return new TableViewTask().upBox(getListAffairsActive.getListAffairs(user, company.getNameCompany(), null));
            }
        };
        ProgressBar progressBar = new ProgressBar(ts.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        t.setContent(progressBar);
        ts.setOnSucceeded((succeededEvent) -> {
            t.setContent(ts.getValue());
        });
        Platform.runLater(() -> {
            progressBar.setProgress(ts.getProgress());
            progressBar.visibleProperty().bind(ts.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(ts);
            executorService.shutdown();
        });

        return t;
    }

   /* private Tab loadTasksAll() {
        Tab tA = new Tab(language.ALL_APPLICATIONS(lang));
        Task<ListView<Affairs>> ts = new Task<ListView<Affairs>>() {
            @Override
            protected ListView<Affairs> call() throws Exception {
                GetListAffairs getListAffairs = new GetListAffairsSQLite();
                return new TableViewTask().upBox(getListAffairs.getListAffairs(user, company.getNameCompany(), null));
            }
        };
        ProgressBar progressBar = new ProgressBar(ts.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        tA.setContent(progressBar);
        ts.setOnSucceeded((succeededEvent) -> {
            tA.setContent(ts.getValue());
        });
        Platform.runLater(() -> {
            progressBar.setProgress(ts.getProgress());
            progressBar.visibleProperty().bind(ts.runningProperty());
                    ExecutorService executorService = Executors.newFixedThreadPool(1);
                    executorService.execute(ts);
                    executorService.shutdown();
                }
        );

        return tA;
    }*/


    private void loadMail(MailSettings mailSettings) {
        Task<ListView<Email>> task = new Task<>() {
            @Override
            protected ListView<Email> call() {
                GetListViewMail getListViewMail = new ListViewEmailMin();
                return getListViewMail.getListViewEmail(user, mailSettings);
            }
        };
        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        gridPaneHomeFragment.add(progressBar, 0, 4, 2, 1);
        task.setOnSucceeded((succeededEvent) -> {
            splitPaneHF.getItems().add(task.getValue());
            splitPaneHF.setDividerPosition(0, 0);
        });
        Platform.runLater(()->{
            progressBar.setProgress(task.getProgress());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        loadFragment();
    }

}