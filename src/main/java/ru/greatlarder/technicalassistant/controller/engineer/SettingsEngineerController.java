package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentRegistrationUserController;
import ru.greatlarder.technicalassistant.domain.User;
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

public class SettingsEngineerController implements ObserverLang, ObserverUser {
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
    Language language = new LanguageImpl();
    User user;
    HandlerLang handlerLang = new HandlerLang();
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    FragmentRegistrationUserController fragmentRegistrationUserController;
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/addLetterTemplate.fxml"));
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
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        loadPage();
        handlerUserListener.onNewDataUser(new DataUser(user));
    }

    public void loadPage() {

        FileManager fileManager = new FileManagerImpl();
        tfRefDirApp.setText(fileManager.folderProject());
        tfRefDirDB.setText(fileManager.folderDB());

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
}
