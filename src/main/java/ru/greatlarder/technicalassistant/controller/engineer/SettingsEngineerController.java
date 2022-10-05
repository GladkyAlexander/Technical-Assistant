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
import ru.greatlarder.technicalassistant.controller.fragment.FragmentIdenticalData;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentRegistrationUserController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.ExternalDatabase;
import ru.greatlarder.technicalassistant.repository.impl.ExternalDatabaseRepositoryImpl;
import ru.greatlarder.technicalassistant.services.DataVerification;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    Language language = new LanguageImpl();
    User user;
    Company company;
    HandlerLang handlerLang = new HandlerLang();
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    FragmentRegistrationUserController fragmentRegistrationUserController;
    DataVerification dataVerification = new DataVerification();
    List<Company> companyListExternal;
    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
        handlerLang.onNewDataLang(new DataLang(dataLang.getLanguage()));
    }

    public void openLayoutSettingsUser(ActionEvent actionEvent) {
        borderPaneSettings.getChildren().remove(borderPaneSettings.getCenter());
        FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
        try {
            borderPaneSettings.setRight(loaderRegistration.load());
            handlerLang.registerObserverLang(loaderRegistration.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            fragmentRegistrationUserController = loaderRegistration.getController();
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
            handlerLang.onNewDataLang(new DataLang(lang));
            handlerUserListener.registerObserverUser(loader.getController());
            handlerUserListener.onNewDataUser(new DataUser(user));
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
            handlerLang.onNewDataLang(new DataLang(lang));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLanguage(String lang){
        btnRegistry.setText(language.PROFILE_SETTINGS(lang));
        btnCompanyChoice.setText(language.ADD_A_COMPANY(lang));
        labelDSL.setText(language.DATE_STORAGE_LOCATION(lang));
        labelReferenceDirectory.setText(language.APPLICATION_FOLDER(lang));
        labelReferenceDataBase.setText(language.DATABASE_HOSTING_FOLDER(lang));
        btnSettingsMail.setText(language.MAIL_SETTINGS(lang));
        btnSendToAnExternalDatabase.setText(language.UPDATE_DATA_IN_AN_EXTERNAL_DATABASE(lang));
        btnGetDataFromAnExternalDatabase.setText(language.GET_DATA_FROM_AN_EXTERNAL_DATABASE(lang));
        btnCompareData.setText(language.COMPARE_DATA(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        loadPage();
        handlerUserListener.onNewDataUser(new DataUser(user));
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
    }

    public void openFragmentSettingsExternalDB(MouseEvent mouseEvent) {
        borderPaneSettings.getChildren().remove(borderPaneSettings.getCenter());
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_external_database_settings.fxml"));
        try {
            borderPaneSettings.setRight(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAnExternalDatabase(MouseEvent mouseEvent) {
        ExternalDatabase externalDatabase = new ExternalDatabaseRepositoryImpl();
        for (Company company : user.getCompanyList()){
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    externalDatabase.sendCompanyDetailis(company);
                    return null;
                }
            };
            ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
            progressIndicator.visibleProperty();
            borderPaneSettings.setCenter(progressIndicator);
            task.setOnSucceeded((succeededEvent)->{
                progressIndicator.visibleProperty().bind(task.runningProperty());
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        }
    }

    public void getDataFromAnExternalDatabase(MouseEvent mouseEvent) {
        ExternalDatabase externalDatabase = new ExternalDatabaseRepositoryImpl();
            Task<List<Company>> task = new Task<List<Company>>() {
                @Override
                protected List<Company> call() throws Exception {
                    return externalDatabase.acceptDataFromAllCompanies();
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
                handlerLang.onNewDataLang(new DataLang(lang));
                FragmentIdenticalData fragmentIdenticalData = loader.getController();
                fragmentIdenticalData.loadFragment(dataVerification.getEquipmentList(company.getEquipmentList(), equipmentListExternal));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else borderPaneSettings.setCenter(new Label(language.THE_DATA_IS_UP_TO_DATE(lang)));
    }
}
