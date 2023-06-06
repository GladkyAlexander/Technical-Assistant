package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FragmentUserLogin implements ObserverLang, Initializable {
    @FXML public GridPane gridPaneUserLogin;
    @FXML public Label labelUserLogin;
    @FXML public Label labelLogin;
    @FXML public Label labelPassword;
    @FXML public Button btnEnter;
    @FXML public TextField tfLogin;
    @FXML public TextField tfPassword;
    @FXML public Button btnRegistration;
    String lang;
    Language language = new LanguageImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    public void enter() {
        GetUser getUser = new GetUserSQLite();
        User user = getUser.getUser(tfLogin.getText(), tfPassword.getText());
        if(user != null) {
             handlerLang.unregisterObserverLang(this);
             GlobalLinkMainController.getMainController().updateUser(new DataUser(user));
        } else {
            FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                    getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
            try {
                handlerLang.unregisterObserverLang(this);
                GlobalLinkMainController.getMainController().borderPaneMainPage.setCenter(loaderRegistration.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLang(this.lang);
    }

    private void setLang(String lang) {
        labelUserLogin.setText(language.ENTER_THE_DATA(lang));
        labelLogin.setText(language.LOGIN(lang));
        labelPassword.setText(language.PASSWORD(lang));
        btnEnter.setText(language.ENTER(lang));
        btnRegistration.setText(language.REGISTRY(lang));
    }

    public void onActionRegistration() {
        FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
        try {
            handlerLang.unregisterObserverLang(this);
            GlobalLinkMainController.getMainController().borderPaneMainPage.setCenter(loaderRegistration.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handlerLang.registerObserverLang(this);
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLang(lang);
    }
}
