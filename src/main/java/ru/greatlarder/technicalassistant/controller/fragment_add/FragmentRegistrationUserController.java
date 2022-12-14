package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;

import java.util.List;

public class FragmentRegistrationUserController implements ObserverLang{
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
    UserRepository userRepository = new UserRepositoryImpl();
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().getHandlerUserListener();
    String lang;

    public void saveUser(ActionEvent actionEvent) {
        UserRepository userRepository = new UserRepositoryImpl();
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
        if(menuButtonLanguage.getText().equals("????????????")){
            user.setLanguage("??????????????");
        }
        if(menuButtonLanguage.getText().equals("England")){
            user.setLanguage("English");
        }

        if (user.getLastName() != null && user.getLogin() != null && user.getPassword() != null){
            if(checkUser(user) != null){
                userRepository.setUser(user);
            }else gridPaneAdd.setStyle(StyleSRC.STYLE_DANGER);
        }
        User ut = userRepository.getUserLoginPassword(user.getLogin(), user.getPassword());
        if (ut.getLastName().equals(user.getLastName()) && ut.getFirstName().equals(user.getFirstName())
        && ut.getLogin().equals(user.getLogin()) && ut.getPassword().equals(user.getPassword())){
            gridPaneAdd.setStyle(StyleSRC.STYLE_EXCELLENT);
            handlerUserListener.onNewDataUser(new DataUser(ut));
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
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    public void onKeyRelisedTFLogin(KeyEvent keyEvent) {
        List<User> users = userRepository.getListUser();
        for(User user : users){
            if(user.getLogin().equals(tfLogin.getText())){
                tfLogin.setStyle(StyleSRC.STYLE_DANGER);
            } else tfLogin.setStyle(new TextField().getStyle());
        }
    }

    public void onKeyRelisedTFPassword(KeyEvent keyEvent) {
    }
    private User checkUser(User user){
        for (User user1 : userRepository.getListUser()){
                if (user1.getLogin().equals(user.getLogin())) {
                    tfLogin.setStyle(StyleSRC.STYLE_DANGER);
                    return null;
                } else if (user1.getLastName().equals(user.getLastName())) {
                    tfLastName.setStyle(StyleSRC.STYLE_DANGER);
                    return null;
                }
        }
       return user;
    }
}
