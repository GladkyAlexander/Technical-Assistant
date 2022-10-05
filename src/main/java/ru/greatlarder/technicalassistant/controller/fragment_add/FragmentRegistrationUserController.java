package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

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
    UserRepository userRepository = new UserRepositoryImpl();
    Language lines = new LanguageImpl();
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
        user.setServer(tfNameServer.getText());
        user.setPort(tfPortServerDbExternal.getText());
        user.setNameDB(tfNameDbExternal.getText());
        user.setUserDB(tfNameUserDbExternal.getText());
        user.setPasswordDB(tfPasswordDbExternal.getText());
        if(menuButtonLanguage.getText().equals("Россия")){
            user.setLanguage("Русский");
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
            GlobalLinkMainController.getMainController().loadUser(ut);
        } else gridPaneAdd.setStyle(StyleSRC.STYLE_DANGER);
    }

    public void loadPage() {
        gridPaneAdd.setStyle(StyleSRC.STYLE_ORDINARY);
        comboBoxPost.setItems(FXCollections.observableArrayList(lines.LIST_POST(lang)));
    }

    public void setMenuItemRu(ActionEvent actionEvent) {
        menuButtonLanguage.setText(menuItemRu.getText());
    }

    public void setMenuItemEn(ActionEvent actionEvent) {
        menuButtonLanguage.setText(menuItemEn.getText());
    }

    public void setLanguage(String lange) {
        labelRegister.setText(lines.REGISTRY(lange));
        this.labelLastName.setText(lines.LAST_NAME(lange));
        labelFirstName.setText(lines.FIRST_NAME(lange));
        labelEmailAddress.setText(lines.EMAIL(lange));
        labelPhone.setText(lines.PHONE(lange));
        labelLogin.setText(lines.LOGIN(lange));
        labelPassword.setText(lines.PASSWORD(lange));
        comboBoxPost.setPromptText(lines.POST(lange));
        menuButtonLanguage.setText(lines.SELECT_A_COUNTRY(lange));
        btnSave.setText(lines.SAVE(lange));
        comboBoxPost.setItems(FXCollections.observableArrayList(lines.LIST_POST(lange)));
        labelNameServer.setText(lines.SERVER_HOSTNAME(lange));
        labelPortServer.setText(lines.PORT(lange));
        labelNameDbExternal.setText(lines.DATABASE_NAME(lange));
        labelUserDbExternal.setText(lines.DATABASE_USER_NAME(lange));
        labelPasswordDbExternal.setText(lines.PASSWORD_DATABASE(lange));
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
