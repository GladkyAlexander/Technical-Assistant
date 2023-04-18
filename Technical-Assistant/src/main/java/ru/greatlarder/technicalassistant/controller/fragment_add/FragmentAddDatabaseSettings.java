package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetUser;
import ru.greatlarder.technicalassistant.services.database.UpdateUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.UpdateUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentAddDatabaseSettings implements Initializable {

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
    String lang1;
    User user;
    Language language = new LanguageImpl();

    private void setLanguage(String lang) {
        labelNameServerExternalDB.setText(language.SERVER_HOSTNAME(lang));
        labelPortExternalDB.setText(language.PORT(lang));
        labelNameExternalDB.setText(language.DATABASE_NAME(lang));
        labelUserExternalDB.setText(language.DATABASE_USER_NAME(lang));
        labelPasswordExternalDB.setText(language.PASSWORD_DATABASE(lang));
        labelHeading.setText(language.EXTERNAL_DATABASE_SETTINGS(lang));
        btnSave.setText(language.SAVE(lang));
    }

    public void loadFragment(){
        labelFirstName.setText(user.getFirstName());
        labelLastName.setText(user.getLastName());
    }

    public void save(MouseEvent mouseEvent) {

        if(user != null) {
            user.setServer(tfNameServerExternalDB.getText());
            user.setPort(tfPortExternalDB.getText());
            user.setNameDB(tfUserExternalDB.getText());
            user.setUserDB(tfUserExternalDB.getText());
            user.setPasswordDB(tfPasswordExternalDB.getText());
            UpdateUser changeUser = new UpdateUserSQLite();
            changeUser.changeUser(user);
            GetUser getUser = new GetUserSQLite();
            GlobalLinkMainController.getMainController().updateUser(new DataUser(getUser.getUser(this.user.getLogin(), this.user.getPassword())));
            gridPaneAddDBExternalSettings.setStyle(StyleSRC.STYLE_EXCELLENT);
            gridPaneAddDBExternalSettings.setStyle(new GridPane().getStyle());
            gridPaneAddDBExternalSettings.getChildren().clear();
        }
      
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang1 = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang1);
    }
}
