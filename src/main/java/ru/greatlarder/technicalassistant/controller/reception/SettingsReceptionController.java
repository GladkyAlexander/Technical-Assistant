package ru.greatlarder.technicalassistant.controller.reception;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddDatabaseSettings;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddMailSettingsController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;

public class SettingsReceptionController implements ObserverLang, ObserverCompany, ObserverUser {
    @FXML public BorderPane borderPaneSettingsReception;
    @FXML public Button btnSettingsMail;
    @FXML public Button btnSettingsDataBase;
    private Company company;
    private String lang;
    Language language = new LanguageImpl();
    private User user;
    HandlerLang handlerLang = new HandlerLang();
    HandlerUserListener handlerUserListener = new HandlerUserListener();

    public void openSettingsMail(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_mail_settings.fxml"));
        try {
            borderPaneSettingsReception.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentAddMailSettingsController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else this.company = dataCompany.getCompany();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }
    private void setLanguage(String l){
        btnSettingsMail.setText(language.MAIL_SETTINGS(l));
        btnSettingsDataBase.setText(language.DATABASE_SETTINGS(l));
    }
    public void loadFragment(){

    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }

    public void openSettingsDB(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_database_settings.fxml"));
        try {
            borderPaneSettingsReception.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentAddDatabaseSettings controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
            controller.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BorderPane getBorderPaneSettingsReception() {
        return borderPaneSettingsReception;
    }
}
