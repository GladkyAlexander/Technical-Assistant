package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class FragmentChangeSettingsUser implements ObserverLang, ObserverUser {
    @FXML public GridPane gridPaneFCSU;
    @FXML public Label labelLastName;
    @FXML public Label labelFirstName;
    @FXML public Label labelMail;
    @FXML public Label labelPhone;
    @FXML public Label labelSettingsExternalDB;
    @FXML public Label labelServer;
    @FXML public Label labelPort;
    @FXML public Label labelNameDB;
    @FXML public Label labelUserDB;
    @FXML public Label labelPasswordDB;
    @FXML public Button btnSave;
    @FXML public TextField tfMail;
    @FXML public TextField tfPhone;
    @FXML public TextField tfServer;
    @FXML public TextField tfPort;
    @FXML public TextField tfNameDatabase;
    @FXML public TextField tfUserDb;
    @FXML public TextField tfPasswordDB;
    @FXML public Label labelSettings;
    private String lang;
    private User user;
    Language language = new LanguageImpl();

    public void save(MouseEvent mouseEvent) {
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang) {
        labelMail.setText(language.EMAIL(lang));
        labelPhone.setText(language.PHONE(lang));
        labelSettingsExternalDB.setText(language.SETTINGS_EXTERNAL_DATABASE(lang));
        labelServer.setText(language.SERVER_HOSTNAME(lang));
        labelPort.setText(language.PORT(lang));
        labelNameDB.setText(language.DATABASE_NAME(lang));
        labelUserDB.setText(language.DATABASE_USER_NAME(lang));
        labelPasswordDB.setText(language.PASSWORD_DATABASE(lang));
        btnSave.setText(language.SAVE(lang));
        labelSettings.setText(language.SETTINGS(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        loadFragment(user);
    }

    public void loadFragment(User user) {
        labelLastName.setText(user.getLastName());
        labelFirstName.setText(user.getFirstName());
        tfMail.setPromptText(user.getMailAddress());
        tfPhone.setPromptText(user.getPhone());
        tfServer.setPromptText(user.getServer());
        tfPort.setPromptText(user.getPort());
        tfNameDatabase.setPromptText(user.getNameDB());
        tfUserDb.setPromptText(user.getUserDB());
        tfPasswordDB.setPromptText(user.getPasswordDB());
    }
}
