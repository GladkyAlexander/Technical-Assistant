package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

public class FragmentFolderItemController {
    @FXML
    public Label labelNameFolder;
    @FXML public HBox hBoxOFI;
    @FXML public ImageView imgFolder;

    public void setLabelNameFolder(String labelNameFolder) {
        this.labelNameFolder.setText(labelNameFolder);
    }

    public void setImgFolder(String imgFolder) {
        this.imgFolder.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgFolder))));
    }
}
