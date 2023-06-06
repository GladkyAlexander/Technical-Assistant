package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListMailSettings;
import ru.greatlarder.technicalassistant.services.database.SetMailSettings;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.GetListMailSettingsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.SetMailSettingsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentAddMailSettingsController implements Initializable {
    @FXML
    public Label labelTheme;
    @FXML public TextField tfTheme;
    @FXML public Label labelInfoTheme;
    @FXML public Label labelMailAddress;
    @FXML public Label labelPasswordMail;
    @FXML public Label labelHost;
    @FXML public TextField tfMailAddress;
    @FXML public TextField tfPasswordMail;
    @FXML public TextField tfHost;
    @FXML public Button btnSave;
    @FXML public GridPane gridPaneAddLetterTemplate;
    Language language = new LanguageImpl();
    String lang;
    User user;

    public void setLanguage(String lang) {
        labelMailAddress.setText(language.MAIL_FOR_MONITORING_EMAILS(lang));
        labelPasswordMail.setText(language.EMAIL_PASSWORD(lang));
        labelHost.setText(language.HOST(lang));
        labelTheme.setText(language.SUBJECT_OF_THE_LETTER(lang));
        labelInfoTheme.setText(language.ENTER_THE_FIRST_WORD(lang));
        btnSave.setText(language.SAVE(lang));
    }
    public void save() {

        MailSettings mailSettings = new MailSettings();
        mailSettings.setIdUser(user.getId());
        mailSettings.setMailMonitoring(tfMailAddress.getText());
        mailSettings.setPasswordMailMonitoring(tfPasswordMail.getText());
        mailSettings.setHostMailMonitoring(tfHost.getText());
        mailSettings.setSubjectOfTheLetter(tfTheme.getText());

        SetMailSettings setMailSettings = new SetMailSettingsSQLite();
        setMailSettings.setMailSettings(user, mailSettings);

        GetListMailSettings getListMailSettings = new GetListMailSettingsSQLite();

        if((user.getMailSettings().size() + 1) == getListMailSettings.getListMailSettings(user, null).size()){
            gridPaneAddLetterTemplate.setStyle(StyleSRC.STYLE_EXCELLENT);
            user.setMailSettings(getListMailSettings.getListMailSettings(user, null));
            gridPaneAddLetterTemplate.getChildren().clear();
            gridPaneAddLetterTemplate.setStyle(new GridPane().getStyle());
            GlobalLinkMainController.getMainController().updateUser(new DataUser(user));
            ((BorderPane) gridPaneAddLetterTemplate.getParent()).getChildren().remove(gridPaneAddLetterTemplate);
         } else gridPaneAddLetterTemplate.setStyle(StyleSRC.STYLE_WARNING);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
    }
}
