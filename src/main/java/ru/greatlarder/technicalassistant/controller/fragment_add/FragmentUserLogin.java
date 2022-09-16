package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;

public class FragmentUserLogin implements ObserverLang{
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
    UserRepository userRepository = new UserRepositoryImpl();
    HandlerLang handlerLang = new HandlerLang();

    public void enter(MouseEvent actionEvent) {
        if(userRepository.getUserLoginPassword(tfLogin.getText(), tfPassword.getText()) != null){
            GlobalLinkMainController.getMainController().loadUser(userRepository.getUserLoginPassword(tfLogin.getText(), tfPassword.getText()));
        } else gridPaneUserLogin.setStyle(StyleSRC.STYLE_WARNING);
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLang(lang);
        handlerLang.onNewDataLang(new DataLang(lang));
    }

    private void setLang(String lang) {
        labelUserLogin.setText(language.ENTER_THE_DATA(lang));
        labelLogin.setText(language.LOGIN(lang));
        labelPassword.setText(language.PASSWORD(lang));
        btnEnter.setText(language.ENTER(lang));
        btnRegistration.setText(language.REGISTRY(lang));
    }

    public void onActionRegistration(ActionEvent actionEvent) {
        FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
        try {
            GlobalLinkMainController.getMainController().borderPaneMainPage.setCenter(loaderRegistration.load());
            handlerLang.registerObserverLang(loaderRegistration.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentRegistrationUserController fragmentRegistrationUserController = loaderRegistration.getController();
            fragmentRegistrationUserController.loadPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
