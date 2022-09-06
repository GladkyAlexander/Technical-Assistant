package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.MailSettingsRepository;
import ru.greatlarder.technicalassistant.repository.impl.MailSettingsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class FragmentAddMailSettingsController implements ObserverLang, ObserverUser {
    @FXML
    public Label labelTheme;
    @FXML public TextField tfTheme;
    @FXML public Label labelInfoTheme;
    @FXML public Label labalOpisanie;
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
    private User user;
    MailSettingsRepository mailSettingsRepository = new MailSettingsRepositoryImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }
    public void setLanguage(String lang) {
        labelMailAddress.setText(language.MAIL_FOR_MONITORING_EMAILS(lang));
        labelPasswordMail.setText(language.EMAIL_PASSWORD(lang));
        labelHost.setText(language.HOST(lang));
        labelTheme.setText(language.SUBJECT_OF_THE_LETTER(lang));
        labelInfoTheme.setText(language.ENTER_THE_FIRST_WORD(lang));
        labalOpisanie.setText(language.THE_WORDS_SHOULD(lang));
        btnSave.setText(language.SAVE(lang));
    }
    public void save(ActionEvent actionEvent) {

        MailSettings mailSettings = new MailSettings();
        mailSettings.setIdUser(user.getId());
        mailSettings.setMailMonitoring(tfMailAddress.getText());
        mailSettings.setPasswordMailMonitoring(tfPasswordMail.getText());
        mailSettings.setHostMailMonitoring(tfHost.getText());
        mailSettings.setSubjectOfTheLetter(tfTheme.getText());
        mailSettingsRepository.setMailSettings(mailSettings);

        if((user.getMailSettings().size() + 1) == mailSettingsRepository.getListMailSettingsByUser(user.getId()).size()){
            gridPaneAddLetterTemplate.setStyle(StyleSRC.STYLE_EXCELLENT);
            user.setMailSettings(mailSettingsRepository.getListMailSettingsByUser(mailSettings.getIdUser()));
            gridPaneAddLetterTemplate.getChildren().clear();
            gridPaneAddLetterTemplate.setStyle(new GridPane().getStyle());
            GlobalLinkMainController.getMainController().updateUser();
        } else gridPaneAddLetterTemplate.setStyle(StyleSRC.STYLE_WARNING);

    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
}
