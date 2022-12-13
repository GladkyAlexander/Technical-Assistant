package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class FragmentAddDatabaseSettings implements ObserverLang, ObserverUser {

    @FXML public Label labelNameServerExternalDB;
    @FXML public Label labelPortExternalDB;
    @FXML public Label labelNameExternalDB;
    @FXML public TextField tfNameServerExternalDB;
    @FXML public TextField tfPortExternalDB;
    @FXML public TextField tfNameExternalDB;
    @FXML public Label labelUserExternalDB;
    @FXML public Label labelPasswordExternalDB;
    @FXML public TextField tfUserExternalDB;
    @FXML public TextField tfPasswordExternalDB;
    @FXML public Label labelFirstName;
    @FXML public Label labelLastName;
    @FXML public Label labelHeading;
    @FXML public Button btnSave;
    @FXML public GridPane gridPaneAddDBExternalSettings;
    private String lang1;
    private User user;
    Language language = new LanguageImpl();
    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang1 = dataLang.getLanguage();
        setLanguage(lang1);
    }

    private void setLanguage(String lang) {
        labelNameServerExternalDB.setText(language.SERVER_HOSTNAME(lang));
        labelPortExternalDB.setText(language.PORT(lang));
        labelNameExternalDB.setText(language.DATABASE_NAME(lang));
        labelUserExternalDB.setText(language.DATABASE_USER_NAME(lang));
        labelPasswordExternalDB.setText(language.PASSWORD_DATABASE(lang));
        labelHeading.setText(language.EXTERNAL_DATABASE_SETTINGS(lang));
        btnSave.setText(language.SAVE(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
    public void loadFragment(){
        //this.user = userIn;
        labelFirstName.setText(user.getFirstName());
        labelLastName.setText(user.getLastName());
    }

    public void save(MouseEvent mouseEvent) {

        if(user != null) {
            userRepository.change(user, "server", tfNameServerExternalDB.getText());
            userRepository.change(user, "port", tfPortExternalDB.getText());
            userRepository.change(user, "nameDB", tfNameExternalDB.getText());
            userRepository.change(user, "userDB", tfUserExternalDB.getText());
            userRepository.change(user, "passwordDB", tfPasswordExternalDB.getText());
        }
        GlobalLinkMainController.getMainController().updateUser();
        gridPaneAddDBExternalSettings.setStyle(StyleSRC.STYLE_EXCELLENT);
        gridPaneAddDBExternalSettings.setStyle(new GridPane().getStyle());
        gridPaneAddDBExternalSettings.getChildren().clear();
    }

}
