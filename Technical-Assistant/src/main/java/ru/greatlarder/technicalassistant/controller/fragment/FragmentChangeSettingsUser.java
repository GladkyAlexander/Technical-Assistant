package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.CheckForANumber;
import ru.greatlarder.technicalassistant.services.check.CheckingForANumberImpl;
import ru.greatlarder.technicalassistant.services.database.GetUser;
import ru.greatlarder.technicalassistant.services.database.UpdateUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.UpdateUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentChangeSettingsUser implements Initializable, ObserverLang {
    @FXML
    public GridPane gridPaneFCSU;
    @FXML
    public Label labelLastName;
    @FXML
    public Label labelFirstName;
    @FXML
    public Label labelMail;
    @FXML
    public Label labelPhone;
    @FXML
    public Label labelSettingsExternalDB;
    @FXML
    public Label labelServer;
    @FXML
    public Label labelPort;
    @FXML
    public Label labelNameDB;
    @FXML
    public Label labelUserDB;
    @FXML
    public Label labelPasswordDB;
    @FXML
    public Button btnSave;
    @FXML
    public TextField tfMail;
    @FXML
    public TextField tfPhone;
    @FXML
    public TextField tfServer;
    @FXML
    public TextField tfPort;
    @FXML
    public TextField tfNameDatabase;
    @FXML
    public TextField tfUserDb;
    @FXML
    public TextField tfPasswordDB;
    @FXML
    public Label labelSettings;
    public Label labelFTPServerSettings;
    public Label labelServerFTP;
    public Label labelPortFTP;
    public Label labelUserFTP;
    public Label labelPasswordFTP;
    public TextField tfPasswordFTP;
    public TextField tfUserFTP;
    public TextField tfPortFTP;
    public TextField tfServerFTP;
    String lang;
    User user;
    Language language = new LanguageImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();

    public void save(MouseEvent mouseEvent) {
        UpdateUser updateUser = new UpdateUserSQLite();
        if (!tfMail.getText().isEmpty()) {
            user.setMailAddress(tfMail.getText());
        }
        if(!tfPhone.getText().isEmpty()) {
            user.setPhone(tfPhone.getText());
        }
        if(!tfServer.getText().isEmpty()) {
            user.setServer(tfServer.getText());
        }
        if(!tfPort.getText().isEmpty()) {
            user.setPort(tfPort.getText());
        }
        if(!tfNameDatabase.getText().isEmpty()) {
            user.setNameDB(tfNameDatabase.getText());
        }
        if(!tfUserDb.getText().isEmpty()) {
            user.setUserDB(tfUserDb.getText());
        }
        if(!tfPasswordDB.getText().isEmpty()) {
            user.setPasswordDB(tfPasswordDB.getText());
        }
        if(!tfServerFTP.getText().isEmpty()){
            user.setServerFTP(tfServerFTP.getText());
        }
        if(!tfPortFTP.getText().isEmpty()){
            user.setPortFTP(Integer.parseInt(tfPortFTP.getText()));
        }
        if(!tfUserFTP.getText().isEmpty()){
            user.setUserFTP(tfUserFTP.getText());
        }
        if(!tfPasswordFTP.getText().isEmpty()){
            user.setPasswordFTP(tfPasswordFTP.getText());
        }

        updateUser.changeUser(user);

        handlerLang.unregisterObserverLang(this);
        GetUser getUser = new GetUserSQLite();
        GlobalLinkMainController.getMainController().updateUser(new DataUser(getUser.getUser(user.getLogin(), user.getPassword())));
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
        labelFTPServerSettings.setText(language.FTP_SERVER_SETTINGS(lang));
        labelServerFTP.setText(language.SERVER(lang) + " FTP");
        labelPortFTP.setText(language.PORT(lang) + " FTP");
        labelUserFTP.setText(language.USER(lang) + " FTP");
        labelPasswordFTP.setText(language.PASSWORD(lang) + " FTP");
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
        tfServerFTP.setPromptText(user.getServerFTP());
        tfPortFTP.setPromptText(String.valueOf(user.getPortFTP()));
        tfUserFTP.setPromptText(user.getUserFTP());
        tfPasswordFTP.setPromptText(user.getPasswordFTP());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        updateLang(new DataLang(lang));
        labelLastName.setText(user.getLastName());
        labelFirstName.setText(user.getFirstName());
        handlerLang.registerObserverLang(this);
    }

    public void onKeyRelisedPortFTP(KeyEvent keyEvent) {
        CheckForANumber check = new CheckingForANumberImpl();
        if(!check.checkingForANumber(tfPortFTP.getText())){
            tfPortFTP.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            tfPortFTP.setStyle(new TextField().getStyle());
        }
    }
}
