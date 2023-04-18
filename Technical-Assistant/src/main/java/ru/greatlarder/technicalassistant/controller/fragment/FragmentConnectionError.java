package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentConnectionError implements Initializable {
    @FXML public Label label;
    Language language = new LanguageImpl();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        label.setText(language.CHECK_YOUR_INTERNET_CONNECTION(GlobalLinkMainController.getMainController().getLang()));
    }
}
