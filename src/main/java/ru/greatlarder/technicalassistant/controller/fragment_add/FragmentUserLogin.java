package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

public class FragmentUserLogin implements ObserverLang{
    public GridPane gridPaneUserLogin;
    public Label labelUserLogin;
    public Label labelLogin;
    public Label labelPassword;
    public Button btnEnter;
    public TextField tfLogin;
    public TextField tfPassword;
    String lang;
    Language language = new LanguageImpl();
    UserRepository userRepository = new UserRepositoryImpl();

    public void enter(ActionEvent actionEvent) {
        if(userRepository.getUserLoginPassword(tfLogin.getText(), tfPassword.getText()) != null){
            GlobalLinkMainController.getMainController().loadUser(userRepository.getUserLoginPassword(tfLogin.getText(), tfPassword.getText()));
        } else gridPaneUserLogin.setStyle(StyleSRC.STYLE_WARNING);
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLang(lang);
    }

    private void setLang(String lang) {
        labelUserLogin.setText(language.ENTER_THE_DATA(lang));
        labelLogin.setText(language.LOGIN(lang));
        labelPassword.setText(language.PASSWORD(lang));
        btnEnter.setText(language.ENTER(lang));
    }
}
