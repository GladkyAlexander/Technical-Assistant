package ru.greatlarder.technicalassistant.controller.reception;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddDatabaseSettings;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsReceptionController implements ObserverLang, Initializable {
    @FXML public BorderPane borderPaneSettingsReception;
    @FXML public Button btnSettingsMail;
    @FXML public Button btnSettingsDataBase;
    Company company;
    String lang;
    Language language = new LanguageImpl();
    User user;

    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();

    public void openSettingsMail(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_mail_settings.fxml"));
        try {
            borderPaneSettingsReception.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void openSettingsDB(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_database_settings.fxml"));
        try {
            borderPaneSettingsReception.setCenter(loader.load());
            FragmentAddDatabaseSettings controller = loader.getController();
            controller.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        updateLang(new DataLang(lang));
        handlerLang.registerObserverLang(this);
    }
}
