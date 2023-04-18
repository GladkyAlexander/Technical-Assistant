package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.CheckForANumber;
import ru.greatlarder.technicalassistant.services.check.CheckingForANumberImpl;
import ru.greatlarder.technicalassistant.services.check.check_user.CheckUser;
import ru.greatlarder.technicalassistant.services.check.check_user.CheckUserLogin;
import ru.greatlarder.technicalassistant.services.check.check_user.CheckUserSQLite;
import ru.greatlarder.technicalassistant.services.check.check_user.CheckingUserLoginSQLite;
import ru.greatlarder.technicalassistant.services.database.GetUser;
import ru.greatlarder.technicalassistant.services.database.SetUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.SetUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FragmentRegistrationUserController implements ObserverLang, Initializable {
    @FXML
    public Label labelLastName;
    @FXML
    public Label labelFirstName;
    @FXML
    public Label labelEmailAddress;
    @FXML
    public TextField tfLastName;
    @FXML
    public TextField tfFirstName;
    @FXML
    public TextField tfMailAddress;
    @FXML
    public Button btnSave;
    @FXML
    public GridPane gridPaneAdd;
    @FXML
    public Label labelPhone;
    @FXML
    public TextField tfPhone;
    @FXML
    public MenuButton menuButtonLanguage;
    @FXML
    public MenuItem menuItemRu;
    @FXML
    public MenuItem menuItemEn;
    @FXML
    public ComboBox<String> comboBoxPost;
    @FXML public Label labelRegister;
    @FXML public Label labelLogin;
    @FXML public Label labelPassword;
    @FXML public TextField tfLogin;
    @FXML public TextField tfPassword;
    @FXML public Label labelNameServer;
    @FXML public Label labelPortServer;
    @FXML public Label labelNameDbExternal;
    @FXML public Label labelUserDbExternal;
    @FXML public Label labelPasswordDbExternal;
    @FXML public TextField tfNameServer;
    @FXML public TextField tfPortServerDbExternal;
    @FXML public TextField tfNameDbExternal;
    @FXML public TextField tfNameUserDbExternal;
    @FXML public TextField tfPasswordDbExternal;
    @FXML public Label labelNameCompany;
    @FXML public TextField tfNameCompanyUser;
    @FXML public Button buttonIn;
    @FXML public Label labelServerFTP;
    @FXML public Label labelPortFTP;
    @FXML public Label labelUserFTP;
    @FXML public Label labelPasswordFTP;
    @FXML public TextField tfServerFTP;
    @FXML public TextField tfPortFTP;
    @FXML public TextField tfUserFTP;
    @FXML public TextField tfPasswordFTP;
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    String lang;
    SetUser setUser = new SetUserSQLite();

    public void saveUser() {
        User user = new User();
        user.setLastName(tfLastName.getText());
        user.setFirstName(tfFirstName.getText());
        user.setMailAddress(tfMailAddress.getText());
        user.setPhone(tfPhone.getText());
        user.setLogin(tfLogin.getText());
        user.setPassword(tfPassword.getText());
        user.setPost(comboBoxPost.getValue());
        user.setCompanyAffiliation(tfNameCompanyUser.getText());
        if(comboBoxPost.getValue().equals(language.RECEPTION_SECRETARY(lang))){
            fileManager.createDirectoryCompany(tfNameCompanyUser.getText());
        }
        user.setServer(tfNameServer.getText());
        user.setPort(tfPortServerDbExternal.getText());
        user.setNameDB(tfNameDbExternal.getText());
        user.setUserDB(tfNameUserDbExternal.getText());
        user.setPasswordDB(tfPasswordDbExternal.getText());
        user.setServerFTP(tfServerFTP.getText());
        user.setPortFTP(Integer.parseInt(tfPortFTP.getText()));
        user.setUserFTP(tfUserFTP.getText());
        user.setPasswordFTP(tfPasswordFTP.getText());

        if(menuButtonLanguage.getText().equals("Россия")){
            user.setLanguage("Русский");
        }
        if(menuButtonLanguage.getText().equals("England")){
            user.setLanguage("English");
        }
        CheckUser checkUser = new CheckUserSQLite();
        if (user.getLastName() != null && user.getLogin() != null && user.getPassword() != null){
            if(!checkUser.checkUser(user)){
                setUser.setUser(user);
            }else gridPaneAdd.setStyle(StyleSRC.STYLE_DANGER);
        }
        GetUser getUser = new GetUserSQLite();
        User ut = getUser.getUser(user.getLogin(), user.getPassword());
        if (ut.getLastName().equals(user.getLastName()) && ut.getFirstName().equals(user.getFirstName())
        && ut.getLogin().equals(user.getLogin()) && ut.getPassword().equals(user.getPassword())){
            handlerLang.unregisterObserverLang(this);
            gridPaneAdd.setStyle(StyleSRC.STYLE_EXCELLENT);
            GlobalLinkMainController.getMainController().updateUser(new DataUser(ut));
        } else gridPaneAdd.setStyle(StyleSRC.STYLE_DANGER);
    }

    public void loadPage() {
        gridPaneAdd.setStyle(StyleSRC.STYLE_ORDINARY);
        comboBoxPost.setItems(FXCollections.observableArrayList(language.LIST_POST(lang)));
    }

    public void setMenuItemRu(ActionEvent actionEvent) {
        menuButtonLanguage.setText(menuItemRu.getText());
    }

    public void setMenuItemEn(ActionEvent actionEvent) {
        menuButtonLanguage.setText(menuItemEn.getText());
    }

    public void setLanguage(String lange) {
        labelRegister.setText(language.REGISTRY(lange));
        this.labelLastName.setText(language.LAST_NAME(lange));
        labelFirstName.setText(language.FIRST_NAME(lange));
        labelEmailAddress.setText(language.EMAIL(lange));
        labelPhone.setText(language.PHONE(lange));
        labelLogin.setText(language.LOGIN(lange));
        labelPassword.setText(language.PASSWORD(lange));
        comboBoxPost.setPromptText(language.POST(lange));
        menuButtonLanguage.setText(language.SELECT_A_COUNTRY(lange));
        btnSave.setText(language.SAVE(lange));
        comboBoxPost.setItems(FXCollections.observableArrayList(language.LIST_POST(lange)));
        labelNameServer.setText(language.SERVER_HOSTNAME(lange));
        labelPortServer.setText(language.PORT(lange));
        labelNameDbExternal.setText(language.DATABASE_NAME(lange));
        labelUserDbExternal.setText(language.DATABASE_USER_NAME(lange));
        labelPasswordDbExternal.setText(language.PASSWORD_DATABASE(lange));
        labelNameCompany.setText(language.THE_COMPANY_YOU_WORK_FOR(lange));
        buttonIn.setText(language.ENTER(lange));
        labelServerFTP.setText(language.SERVER(lang) + " FTP");
        labelPortFTP.setText(language.PORT(lang) + " FTP");
        labelUserFTP.setText(language.USER(lang) + " FTP");
        labelPasswordFTP.setText(language.PASSWORD(lang) + " FTP");
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    public void onKeyRelisedTFLogin(KeyEvent keyEvent) {
        CheckUserLogin checkUserLogin = new CheckingUserLoginSQLite();
            if(checkUserLogin.checkUserLogin(tfLogin.getText())){
                tfLogin.setStyle(StyleSRC.STYLE_DANGER);
            } else tfLogin.setStyle(new TextField().getStyle());
    }

    public void onKeyRelisedTFPassword(KeyEvent keyEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handlerLang.registerObserverLang(this);
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        loadPage();
    }

    public void input() {
        FXMLLoader loaderUserLogin = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentUserLogin.fxml"));
        try {
            handlerLang.unregisterObserverLang(this);
            GlobalLinkMainController.getMainController().borderPaneMainPage.setCenter(loaderUserLogin.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onKeyReleasedPortFTP() {
        CheckForANumber checked = new CheckingForANumberImpl();
        if (!checked.checkingForANumber(tfPortFTP.getText())){
            tfPortFTP.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            tfPortFTP.setStyle(new TextField().getStyle());
        }
    }
}
