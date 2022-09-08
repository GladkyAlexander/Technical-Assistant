package ru.greatlarder.technicalassistant.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

public class InfoPageController implements ObserverLang {
    @FXML
    public Label labelHello;
    @FXML public Label labelMail;
    @FXML public Label labelPhone;
    @FXML public Label labelSite;
    @FXML public GridPane gridPaneInfo;
    private String lang;
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
}
