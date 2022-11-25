package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;

public class ItemProgressIndicator {

    @FXML public ProgressIndicator progress;

    public void setProgress(ProgressIndicator progress) {
        this.progress = progress;
    }
}
