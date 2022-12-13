package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentChangeSettingsUser;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentIdenticalData;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddCompanyController;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddMailSettingsController;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentRegistrationUserController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.DataVerification;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.*;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EventsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.RoomsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.SeatingRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EventsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.RoomsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.SeatingRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javafx.geometry.Pos.CENTER;

public class SettingsEngineerController implements ObserverLang, ObserverUser, ObserverCompany {
    @FXML
    public BorderPane borderPaneSettings;
    @FXML public Label labelReferenceDirectory;
    @FXML public Label labelReferenceDataBase;
    @FXML public Label tfRefDirApp;
    @FXML public TextField tfRefDirDB;
    @FXML public Button btnCompanyChoice;
    @FXML public Button btnRegistry;
    @FXML public Label labelDSL;
    @FXML public Button btnSettingsMail;
    public String lang;
    @FXML public Button btnSendToAnExternalDatabase;
    @FXML public Button btnGetDataFromAnExternalDatabase;
    @FXML public Button btnCompareData;
    @FXML public Button btnSettingsUser;
    @FXML public Button buttonCompanyDataSettings;
    @FXML public GridPane gridPaneUpdateExternalDB;
    @FXML public Button buttonListOfRooms;
    @FXML public Button buttonSeatingList;
    @FXML public Button buttonEquipment;
    @FXML public Button buttonListOfEvents;
    @FXML public Button buttonCompanyData;
    Language language = new LanguageImpl();
    private User user;
    private Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    HandlerUserListener handlerUserListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerUserListener;
    HandlerCompanyListener handlerCompanyListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerCompanyListener;
    FragmentRegistrationUserController fragmentRegistrationUserController;
    DataVerification dataVerification = new DataVerification();
    List<Company> companyListExternal;
    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }

    public void openLayoutSettingsUser(ActionEvent actionEvent) {
        borderPaneSettings.getChildren().remove(borderPaneSettings.getCenter());
        FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
        try {
            borderPaneSettings.setRight(loaderRegistration.load());
            handlerLang.registerObserverLang(loaderRegistration.getController());

            fragmentRegistrationUserController = loaderRegistration.getController();
            fragmentRegistrationUserController.updateLang(new DataLang(lang));
            fragmentRegistrationUserController.loadPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openSettingsMail(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_mail_settings.fxml"));
        try {
            borderPaneSettings.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());

            FragmentAddMailSettingsController addSettings = loader.getController();
            addSettings.updateLang(new DataLang(this.lang));
            addSettings.updateUser(new DataUser(this.user));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void actionBtnCompany(ActionEvent actionEvent) {
        borderPaneSettings.getChildren().remove(borderPaneSettings.getCenter());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_company.fxml"));
        try {
            borderPaneSettings.setRight(loader.load());
            handlerLang.registerObserverLang(loader.getController());

            if(company != null) {
                FragmentAddCompanyController fragmentAddCompanyController = loader.getController();
                fragmentAddCompanyController.updateLang(new DataLang(this.lang));
                fragmentAddCompanyController.loadFragment();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLanguage(String lang){
        btnRegistry.setText(language.REGISTRY(lang));
        btnCompanyChoice.setText(language.ADD_A_COMPANY(lang));
        labelDSL.setText(language.DATE_STORAGE_LOCATION(lang));
        labelReferenceDirectory.setText(language.APPLICATION_FOLDER(lang));
        labelReferenceDataBase.setText(language.DATABASE_HOSTING_FOLDER(lang));
        btnSettingsMail.setText(language.MAIL_SETTINGS(lang));
        btnSendToAnExternalDatabase.setText(language.UPDATE_DATA_IN_AN_EXTERNAL_DATABASE(lang));
        btnGetDataFromAnExternalDatabase.setText(language.GET_DATA_FROM_AN_EXTERNAL_DATABASE(lang));
        btnCompareData.setText(language.COMPARE_DATA(lang));
        btnSettingsUser.setText(language.PROFILE_SETTINGS(lang));
        buttonCompanyDataSettings.setText(language.COMPANY_DATA_SETTINGS(lang));
        buttonCompanyData.setText(language.COMPANY_DATA(lang));
        buttonListOfRooms.setText(language.LIST_OF_ROOMS(lang));
        buttonSeatingList.setText(language.SEATING_LIST(lang));
        buttonEquipment.setText(language.EQUIPMENT(lang));
        buttonListOfEvents.setText(language.LIST_OF_EVENTS(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        loadPage();
    }

    public void loadPage() {
        btnCompareData.setVisible(false);
        btnCompareData.setManaged(false);
        FileManager fileManager = new FileManagerImpl();
        tfRefDirApp.setText(fileManager.folderProject());
        tfRefDirDB.setText(fileManager.folderDB());
        tfRefDirDB.setPrefColumnCount(tfRefDirDB.getText().length());
        if(user == null){
            btnCompanyChoice.setDisable(true);
            btnSettingsMail.setDisable(true);
        } else {
            btnCompanyChoice.setDisable(false);
            btnSettingsMail.setDisable(false);
            btnRegistry.setDisable(true);
            borderPaneSettings.getChildren().remove(borderPaneSettings.getRight());
        }
        visibleGridUpdateExternalDB(false);
    }

    private void visibleGridUpdateExternalDB(boolean b) {
        gridPaneUpdateExternalDB.setVisible(b);
        gridPaneUpdateExternalDB.setManaged(b);

    }

    public void sendToAnExternalDatabase(ActionEvent mouseEvent) {
        visibleGridUpdateExternalDB(!gridPaneUpdateExternalDB.isVisible());
    }

    public void getDataFromAnExternalDatabase(MouseEvent mouseEvent) {
        CompanyRepositoryMySQL companyRepositoryMySQL = new CompanyRepositoryMySQLImpl();
            Task<List<Company>> task = new Task<List<Company>>() {
                @Override
                protected List<Company> call() throws Exception {
                    return companyRepositoryMySQL.getListCompany(user);
                }
            };
            ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
            progressIndicator.visibleProperty();
            borderPaneSettings.setCenter(progressIndicator);
            task.setOnSucceeded((succeededEvent)->{
                progressIndicator.visibleProperty().bind(task.runningProperty());
                companyListExternal = task.getValue();
                if(!companyListExternal.isEmpty()){
                    btnCompareData.setVisible(true);
                    btnCompareData.setManaged(true);
                    borderPaneSettings.setCenter(new Label(language.DATA_UPLOADED(lang) + ". " + language.COMPARE_DATA(lang)));
                } else borderPaneSettings.setCenter(new Label(language.THE_EXTERNAL_DATABASE_IS_EMPTY(lang)));
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }

    public void compareData(MouseEvent mouseEvent) {
        List<Equipment> equipmentListExternal = new ArrayList<>();
        for (Company company1 : companyListExternal){
            if (company1.getNameCompany().equals(company.getNameCompany())){
                equipmentListExternal = company1.getEquipmentList();
            }
        }
        if(!dataVerification.getEquipmentList(company.getEquipmentList(), equipmentListExternal).isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentIdenticalData.fxml"));
            try {
                borderPaneSettings.setCenter(loader.load());
                handlerLang.registerObserverLang(loader.getController());

                FragmentIdenticalData fragmentIdenticalData = loader.getController();
                fragmentIdenticalData.updateLang(new DataLang(this.lang));
                fragmentIdenticalData.loadFragment(dataVerification.getEquipmentList(company.getEquipmentList(), equipmentListExternal));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else borderPaneSettings.setCenter(new Label(language.THE_DATA_IS_UP_TO_DATE(lang)));
    }

    public void openSettingsUser(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragment_change_settings_user.fxml"));
        try {
            borderPaneSettings.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());

            FragmentChangeSettingsUser fragmentChangeSettingsUser = loader.getController();
            fragmentChangeSettingsUser.updateLang(new DataLang(this.lang));
            fragmentChangeSettingsUser.updateUser(new DataUser(this.user));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCompanyDataSettings(MouseEvent mouseEvent) {
        borderPaneSettings.getChildren().remove(borderPaneSettings.getCenter());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_company.fxml"));
        try {
            borderPaneSettings.setRight(loader.load());
            handlerLang.registerObserverLang(loader.getController());

            if(company != null) {
                FragmentAddCompanyController fragmentAddCompanyController = loader.getController();
                fragmentAddCompanyController.updateLang(new DataLang(this.lang));
                fragmentAddCompanyController.loadChangeCompanyFragment(company);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadCompanyData(MouseEvent mouseEvent) {
        CompanyRepositoryMySQL companyRepositoryMySQL = new CompanyRepositoryMySQLImpl();
        if (companyRepositoryMySQL.getCompanyByName(user, company.getNameCompany()) == null){
            Task<Void> task = new Task() {
                @Override
                protected Object call() throws Exception {
                    companyRepositoryMySQL.setCompany(user, company);
                    return null;
                }
            };
            ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
            progressIndicator.setStyle(StyleSRC.STYLE_PROGRESS_BAR);
            progressIndicator.visibleProperty();
            VBox vBox = new VBox();
            vBox.setAlignment(CENTER);
            vBox.getChildren().add(progressIndicator);
            vBox.getChildren().add(new Label(language.DO_NOT_DISCONNECT_THE_CONNECTION(lang)));
            borderPaneSettings.setCenter(vBox);
            task.setOnSucceeded((succeededEvent)->{
                progressIndicator.visibleProperty().bind(task.runningProperty());
                borderPaneSettings.getChildren().remove(vBox);
                borderPaneSettings.setCenter(new Label(language.ADDED(lang)));
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        } else {
            Task<Void> task = new Task() {
                @Override
                protected Object call() throws Exception {
                    companyRepositoryMySQL.updateCompany(user, company);
                    return null;
                }
            };
            ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
            progressIndicator.setStyle(StyleSRC.STYLE_PROGRESS_BAR);
            progressIndicator.visibleProperty();
            VBox vBox = new VBox();
            vBox.setAlignment(CENTER);
            vBox.getChildren().add(progressIndicator);
            vBox.getChildren().add(new Label(language.DO_NOT_DISCONNECT_THE_CONNECTION(lang)));
            borderPaneSettings.setCenter(vBox);
            task.setOnSucceeded((succeededEvent)->{
                progressIndicator.visibleProperty().bind(task.runningProperty());
                borderPaneSettings.getChildren().remove(vBox);
                borderPaneSettings.setCenter(new Label(language.ADDED(lang)));
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        }
    }

    public void updateListOfRooms(MouseEvent mouseEvent) {
        RoomsRepository repository = new RoomsRepositoryImpl();
        Task task = new Task() {
            @Override
            protected Object call() throws Exception {
                ConnectMySQL connectMySQL = new ConnectMySQL(user);
                connectMySQL.createListRoomNameTableMySQL();

                ListRoomNameRepositoryMySQL listRoomNameRepositoryMySQL = new ListRoomNameRepositoryMySQLImpl();
                listRoomNameRepositoryMySQL.setRoomNameList(user, repository.getListRoomForCompany(company.getNameCompany()));

                return null;
            }
        };
        ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
        progressIndicator.visibleProperty();
        borderPaneSettings.setCenter(progressIndicator);
        task.setOnSucceeded((succeededEvent)->{
            progressIndicator.visibleProperty().bind(task.runningProperty());
            borderPaneSettings.setCenter(new Label(language.ADDED(lang)));
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

    public void updateSeatingList(MouseEvent mouseEvent) {
        SeatingRepository repository = new SeatingRepositoryImpl();
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                ConnectMySQL connectMySQL = new ConnectMySQL(user);
                connectMySQL.createListSeatingArrangementsNameTableMySQL();

                ListSeatingArrangementsNameRepositoryMySQL listSeatingArrangementsNameRepositoryMySQL = new ListSeatingArrangementsNameRepositoryMySQLImpl();
                listSeatingArrangementsNameRepositoryMySQL.setSeatingArrangementsNameList(user, repository.getListSeatingArrangementsForCompany(company.getNameCompany()));

                return null;
            }
        };
        ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
        progressIndicator.visibleProperty();
        borderPaneSettings.setCenter(progressIndicator);
        task.setOnSucceeded((succeededEvent)->{
            progressIndicator.visibleProperty().bind(task.runningProperty());
            borderPaneSettings.setCenter(new Label(language.ADDED(lang)));
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

    public void updateEquipment(MouseEvent mouseEvent) {

        EquipmentRepository repository = new EquipmentRepositoryImpl();
        Task<Void> task = new Task<Void>() {

            @Override
            protected Void call() throws Exception {
                ConnectMySQL connectMySQL = new ConnectMySQL(user);
                connectMySQL.createEquipmentTableMySQL();
                EquipmentRepositoryMySQL repositoryE = new EquipmentRepositoryMySQLImpl();
                repositoryE.updateEquipmentList(user, company.getNameCompany(), repository.getListEquipmentForCompany(company.getNameCompany()));
                return null;
            }
        };
        ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
        progressIndicator.visibleProperty();
        borderPaneSettings.setCenter(progressIndicator);
        task.setOnSucceeded((succeededEvent)->{
            progressIndicator.visibleProperty().bind(task.runningProperty());
            borderPaneSettings.setCenter(new Label(language.ADDED(lang)));
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

    public void updateListOfEvents(MouseEvent mouseEvent) {
        EventsRepository repository = new EventsRepositoryImpl();
        Task<Void> task = new Task<Void>(){

            @Override
            protected Void call() throws Exception {
                ConnectMySQL connectMySQL = new ConnectMySQL(user);
                connectMySQL.createListEventNameTableMySQL();
                ListEventNameRepositoryMySQL listEventNameRepositoryMySQL = new ListEventNameRepositoryMySQLImpl();
                listEventNameRepositoryMySQL.setEventNameList(user, repository.getListEventsForCompany(company.getNameCompany()), company.getNameCompany());

                return null;
            }
        };
        ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
        progressIndicator.visibleProperty();
        borderPaneSettings.setCenter(progressIndicator);
        task.setOnSucceeded((succeededEvent)->{
            progressIndicator.visibleProperty().bind(task.runningProperty());
            borderPaneSettings.setCenter(new Label(language.ADDED(lang)));
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }
}
