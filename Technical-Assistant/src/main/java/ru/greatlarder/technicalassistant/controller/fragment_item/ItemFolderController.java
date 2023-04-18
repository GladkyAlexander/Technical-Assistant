package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ItemFolderController {
    @FXML
    public Label labelNameFolder;
    @FXML public HBox hBoxOFI;
    @FXML public ImageView imgFolder;
    private File file;

    public void setLabelNameFolder(String labelNameFolder) {
        this.labelNameFolder.setText(labelNameFolder);
    }

    public void setImgFolder(String imgFolder) {
        this.imgFolder.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgFolder))));
    }
    public void setFile(File file){
        this.file = file;
    }

    public void openFile() {

        if(file != null) {
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
