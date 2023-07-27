package ru.greatlarder.technicalassistant.controller.fragment;

import jakarta.mail.FetchProfile;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import org.controlsfx.control.PropertySheet;
import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.CheckString;
import ru.greatlarder.technicalassistant.services.check.CheckingStringImpl;
import ru.greatlarder.technicalassistant.services.check.check_user.CheckUserLogin;
import ru.greatlarder.technicalassistant.services.check.check_user.CheckingUserLoginSQLite;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.GetMailSettingsByIdUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.SetMailSettingsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.UpdateMailSettingsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.UpdateUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.GetHostAndPortSSL;
import ru.greatlarder.technicalassistant.services.mail.impl.GetHostAndPortSSLRuImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentChangeSettingsUser implements Initializable, ObserverLang {
    
    
    @FXML public GridPane gridPaneAdd;
    @FXML public Label labelLastName;
    @FXML public Label labelFirstName;
    @FXML public Label labelEmailAddress;
    @FXML public Label labelPhone;
    @FXML public TextField tfLastName;
    @FXML public TextField tfFirstName;
    @FXML public TextField tfMailAddress;
    @FXML public TextField tfPhone;
    @FXML public Label labelLogin;
    @FXML public Label labelPassword;
    @FXML public TextField tfLogin;
    @FXML public TextField tfPassword;
    @FXML public Label labelNameCompany;
    @FXML public TextField tfNameCompanyUser;
    @FXML public Button btnOpenSettingMail;
    @FXML public ImageView imgSetMail;
    @FXML public GridPane gridPaneMail;
    @FXML public Label labelPasswordMail;
    @FXML public TextField tfPasswordMail;
    @FXML public Label labelHost;
    @FXML public ComboBox<String> comboBoxHost;
    @FXML public TextField tfHost;
    @FXML public Button btnAddSettingsBD;
    @FXML public ImageView imgSetDB;
    @FXML public GridPane gridPaneBD;
    @FXML public Label labelNameDbExternal;
    @FXML public Label labelNameServer;
    @FXML public TextField tfNameServer;
    @FXML public Label labelPortServer;
    @FXML public TextField tfPortServerDbExternal;
    @FXML public TextField tfNameDbExternal;
    @FXML public Label labelUserDbExternal;
    @FXML public TextField tfNameUserDbExternal;
    @FXML public Label labelPasswordDbExternal;
    @FXML public TextField tfPasswordDbExternal;
    @FXML public Button btnOpenSettingFTP;
    @FXML public ImageView imgSetFTP;
    @FXML public GridPane gridPaneFtp;
    @FXML public Label labelServerFTP;
    @FXML public TextField tfServerFTP;
    @FXML public Label labelPortFTP;
    @FXML public TextField tfPortFTP;
    @FXML public Label labelUserFTP;
    @FXML public TextField tfUserFTP;
    @FXML public Label labelPasswordFTP;
    @FXML public TextField tfPasswordFTP;
    @FXML public Button btnSave;
    CheckString checkString = new CheckingStringImpl();
    int flagMailSettings = 0;
    int flagDBSettings = 0;
    int flagFTPSettings = 0;
    String lang;
    User user;
    Language language = new LanguageImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    MailSettings mailSettings;
    GetHostAndPortSSL getHostAndPortSSL = new GetHostAndPortSSLRuImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lange) {
        this.labelLastName.setText(language.LAST_NAME(lange));
        labelFirstName.setText(language.FIRST_NAME(lange));
        labelEmailAddress.setText(language.EMAIL(lange));
        labelPhone.setText(language.PHONE(lange));
        labelLogin.setText(language.LOGIN(lange));
        labelPassword.setText(language.PASSWORD(lange));
        btnSave.setText(language.SAVE(lange));
        labelPasswordMail.setText(language.MAIL_PASSWORD(lange));
        labelNameServer.setText(language.SERVER_HOSTNAME(lange));
        labelPortServer.setText(language.PORT(lange));
        labelNameDbExternal.setText(language.DATABASE_NAME(lange));
        labelUserDbExternal.setText(language.DATABASE_USER_NAME(lange));
        labelPasswordDbExternal.setText(language.PASSWORD_DATABASE(lange));
        labelNameCompany.setText(language.THE_COMPANY_YOU_WORK_FOR(lange));
        labelServerFTP.setText(language.SERVER(lang) + " FTP");
        labelPortFTP.setText(language.PORT(lang) + " FTP");
        labelUserFTP.setText(language.USER(lang) + " FTP");
        labelPasswordFTP.setText(language.PASSWORD(lang) + " FTP");
        btnOpenSettingMail.setText(language.MAIL_SETTINGS(lange));
        btnAddSettingsBD.setText(language.SETTINGS_EXTERNAL_DATABASE(lang));
        btnOpenSettingFTP.setText(language.FTP_SERVER_SETTINGS(lang));
        comboBoxHost.setPromptText(language.SELECT_A_MAIL_SERVICE(lang));
    }

    public void loadFragment(User user) {
        tfLastName.setText(user.getLastName());
        tfFirstName.setText(user.getFirstName());
        tfMailAddress.setText(user.getMailAddress());
        tfPhone.setText(user.getPhone());
        tfLogin.setText(user.getLogin());
        tfPassword.setText(user.getPassword());
        tfNameCompanyUser.setText(user.getCompanyAffiliation());
        
        if(mailSettings != null) {
            if(mailSettings.getPasswordMailMonitoring() != null && mailSettings.getHostMailMonitoring() != null){
                tfPasswordMail.setText(mailSettings.getPasswordMailMonitoring());
                comboBoxHost.setValue((getHostAndPortSSL.getServersByNameServer(mailSettings.getHostMailMonitoring(), "IMAP")).getNameServer());
            } else {
                comboBoxHost.setValue(language.SELECT(lang) + " HOST ");
            }
            
        }
        
        if(lang.equals("Русский")){
            comboBoxHost.setVisible(true);
            comboBoxHost.setManaged(true);
            tfHost.setVisible(false);
            tfHost.setManaged(false);
            
            
            comboBoxHost.setItems(FXCollections.observableArrayList(getHostAndPortSSL.getListServersName()));
            
            if(mailSettings != null){
                for (String f : comboBoxHost.getItems()){
                    if(f.equals(mailSettings.getHostMailMonitoring())){
                        comboBoxHost.setValue(f);
                    }
                }
            }
            
        }else {
            comboBoxHost.setVisible(false);
            comboBoxHost.setManaged(false);
            tfHost.setVisible(true);
            tfHost.setManaged(true);
            if(mailSettings != null){
                tfHost.setText(mailSettings.getHostMailMonitoring());
            }
        }
        
        tfNameServer.setText(user.getServer());
        tfPortServerDbExternal.setText(user.getPort());
        tfNameDbExternal.setText(user.getNameDB());
        tfNameUserDbExternal.setText(user.getUserDB());
        tfPasswordDbExternal.setText(user.getPasswordDB());
        
        tfServerFTP.setText(user.getServerFTP());
        tfPortFTP.setText(String.valueOf(user.getPortFTP()));
        tfUserFTP.setText(user.getUserFTP());
        tfPasswordFTP.setText(user.getPasswordFTP());
        
        imgSetMail.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowLeft.png"))));
        imgSetDB.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowLeft.png"))));
        imgSetFTP.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowLeft.png"))));
        gridPaneMail.setVisible(false);
        gridPaneMail.setManaged(false);
        gridPaneBD.setVisible(false);
        gridPaneBD.setManaged(false);
        gridPaneFtp.setVisible(false);
        gridPaneFtp.setManaged(false);
    }
    
    public void openSettingsMail() {
        
        if (flagMailSettings == 0){
            flagMailSettings = 1;
            imgSetMail.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowBotton.png"))));
            gridPaneMail.setVisible(true);
            gridPaneMail.setManaged(true);
            
        } else {
            flagMailSettings = 0;
            imgSetMail.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowLeft.png"))));
            gridPaneMail.setVisible(false);
            gridPaneMail.setManaged(false);
        }
    }
    public void openSettingsDB() {
        if (flagDBSettings == 0){
            flagDBSettings = 1;
            imgSetDB.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowBotton.png"))));
            gridPaneBD.setVisible(true);
            gridPaneBD.setManaged(true);
        } else {
            flagDBSettings = 0;
            imgSetDB.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowLeft.png"))));
            gridPaneBD.setVisible(false);
            gridPaneBD.setManaged(false);
        }
    }
    public void openSettingsFTP() {
        if (flagFTPSettings == 0){
            flagFTPSettings = 1;
            imgSetFTP.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowBotton.png"))));
            gridPaneFtp.setVisible(true);
            gridPaneFtp.setManaged(true);
            
        } else {
            flagFTPSettings = 0;
            imgSetFTP.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/arrowLeft.png"))));
            gridPaneFtp.setVisible(false);
            gridPaneFtp.setManaged(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        GetMailSettings getMailSettings = new GetMailSettingsByIdUserSQLite();
        this.mailSettings = getMailSettings.getMailSettings(user, String.valueOf(user.getId()));
        updateLang(new DataLang(lang));
        handlerLang.registerObserverLang(this);
        loadFragment(user);
    }
    
    public void onKeyReleasedPortFTP() {
        CheckString checked = new CheckingStringImpl();
        if (!checked.checkingForANumber(tfPortFTP.getText())){
            tfPortFTP.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            tfPortFTP.setStyle(new TextField().getStyle());
        }
    }
    public void onKeyRelisedTFLogin() {
        CheckUserLogin checkUserLogin = new CheckingUserLoginSQLite();
        if(checkUserLogin.checkUserLogin(tfLogin.getText())){
            tfLogin.setStyle(StyleSRC.STYLE_DANGER);
        } else tfLogin.setStyle(new TextField().getStyle());
    }
    
    public void onKeyRelisedTFPassword() {
    }
    
    public User loadUser(){
        TextField t = new TextField();
        User userl = new User();
        userl.setId(user.getId());
        //--------------------------------------------------user-------------------------
        if(!tfLastName.getText().isEmpty()){
            tfLastName.setStyle(t.getStyle());
            userl.setLastName(tfLastName.getText());
        } else {
            tfLastName.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        if(!tfFirstName.getText().isEmpty()) {
            tfFirstName.setStyle(t.getStyle());
            userl.setFirstName(tfFirstName.getText());
        } else {
            tfFirstName.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        
        if(!tfMailAddress.getText().isEmpty() && checkString.checkingMail(tfMailAddress.getText())){
            tfMailAddress.setStyle(t.getStyle());
            userl.setMailAddress(tfMailAddress.getText());
        } else {
            tfMailAddress.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        if (!tfPhone.getText().isEmpty() && checkString.checkingForPhone(tfPhone.getText())){
            tfPhone.setStyle(t.getStyle());
            userl.setPhone(tfPhone.getText());
        } else {
            tfPhone.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        if(!tfLogin.getText().isEmpty()){
            tfLogin.setStyle(t.getStyle());
            userl.setLogin(tfLogin.getText());
        } else {
            tfLogin.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        if (!tfPassword.getText().isEmpty()) {
            tfPassword.setStyle(t.getStyle());
            userl.setPassword(tfPassword.getText());
        } else {
            tfPassword.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        userl.setPost(user.getPost());
        
        if(!tfNameCompanyUser.getText().isEmpty()){
            tfNameCompanyUser.setStyle(t.getStyle());
            userl.setCompanyAffiliation(tfNameCompanyUser.getText());
        } else {
            tfNameCompanyUser.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
        //---------------------------------------------------------mail---------------------------------------------
        
        //--------------------------------------------------------db------------------------------------------------
        
        userl.setServer(tfNameServer.getText());
        
        if(!tfPortServerDbExternal.getText().isEmpty() && checkString.checkingForANumber(tfPortServerDbExternal.getText())){
            tfPortServerDbExternal.setStyle(t.getStyle());
            userl.setPort(tfPortServerDbExternal.getText());
        } else if(!tfPortServerDbExternal.getText().isEmpty() && !checkString.checkingForANumber(tfPortServerDbExternal.getText())){
            tfPortServerDbExternal.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        } else userl.setPort(tfPortServerDbExternal.getText());
        
        
        userl.setNameDB(tfNameDbExternal.getText());
        userl.setUserDB(tfNameUserDbExternal.getText());
        userl.setPasswordDB(tfPasswordDbExternal.getText());
        
        //----------------------------------------------------------------ftp-----------------------------------------
        userl.setServerFTP(tfServerFTP.getText());
        
        if(!tfPortFTP.getText().isEmpty()){
            userl.setPortFTP(Integer.parseInt(tfPortFTP.getText()));
        }
        userl.setUserFTP(tfUserFTP.getText());
        userl.setPasswordFTP(tfPasswordFTP.getText());
        
        userl.setLanguage(user.getLanguage());
        
        user = userl;
        
        return user;
    }
    
    public void saveUser() {
        UpdateUser updateUser = new UpdateUserSQLite();
        if(loadUser() != null) {
            
            updateUser.changeUser(user);
            
            if(mailSettings != null){
                MailSettings ms = new MailSettings();
                ms.setId(mailSettings.getId());
                ms.setMailMonitoring(tfMailAddress.getText());
                ms.setPasswordMailMonitoring(tfPasswordMail.getText());
                ms.setHostMailMonitoring(getHostAndPortSSL.getServersByName(comboBoxHost.getValue()).getServerIMAP());
                ms.setIdUser(user.getId());
                
                UpdateMailSettings updateMailSettings = new UpdateMailSettingsSQLite();
                updateMailSettings.updateMailSettings(user, ms, ms.getId());
                
            } else {
                MailSettings ms = new MailSettings();
                if(!tfPasswordMail.getText().isEmpty() && !comboBoxHost.getValue().isEmpty()){
                    ms.setMailMonitoring(tfMailAddress.getText());
                    ms.setPasswordMailMonitoring(tfPasswordMail.getText());
                    ms.setHostMailMonitoring(getHostAndPortSSL.getServersByName(comboBoxHost.getValue()).getServerIMAP());
                    ms.setIdUser(user.getId());
                    
                    SetMailSettings setMailSettings = new SetMailSettingsSQLite();
                    setMailSettings.setMailSettings(user, ms);
                }
            }
            
            handlerLang.unregisterObserverLang(this);
            GetUser getUser = new GetUserSQLite();
            GlobalLinkMainController.getMainController().updateUser(
                new DataUser(getUser.getUser(user.getLogin(), user.getPassword())));
        }
        
    }
}
