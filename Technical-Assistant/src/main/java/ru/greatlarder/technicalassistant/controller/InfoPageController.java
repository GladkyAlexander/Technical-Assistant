package ru.greatlarder.technicalassistant.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoPageController implements ObserverLang, Initializable {
    @FXML
    public Label labelHello;
    @FXML public Label labelMail;
    @FXML public Label labelPhone;
    @FXML public Label labelSite;
    @FXML public GridPane gridPaneInfo;
    String lang;
    Language language = new LanguageImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }
    public void setLanguage(String lang) {
        labelHello.setText(language.INFO_TEXT(lang));
        labelMail.setText(language.EMAIL(lang));
        labelPhone.setText(language.PHONE(lang));
        labelSite.setText(language.WEBSITE(lang));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
        handlerLang.registerObserverLang(this);
    }
}
