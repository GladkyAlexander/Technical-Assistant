package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

public class UpsController {

    @FXML public Label labelText;
    @FXML public TextArea textAreaError;
    @FXML public Button btnUpdate;
    @FXML public Button btnMoreDetailed;
    Language language = new LanguageImpl();
    String message;

    public void updateLang(String lang) {
        labelText.setText(language.CHECK_YOUR_INTERNET_CONNECTION(lang));
        btnUpdate.setText(language.DATA_UPDATED(lang));
        btnMoreDetailed.setText(language.MORE_DETAILED(lang));
    }

    public void loadFragmentUpsController(String errorText){
        this.message = errorText;
        textAreaError.setVisible(false);
    }

    public void update(ActionEvent actionEvent) {
    }

    public void openTextError(ActionEvent actionEvent) {
        textAreaError.setVisible(true);
        textAreaError.setText(message);
    }
}
