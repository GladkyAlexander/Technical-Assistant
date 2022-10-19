package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ItemRoomMin {
    @FXML public VBox vBoxItemRoomMin;
    @FXML public Label labelNameRoomIRM;
    @FXML public ImageView imgRoomIRM;

    public void setvBoxItemRoomMin(VBox vBoxItemRoomMin) {
        this.vBoxItemRoomMin = vBoxItemRoomMin;
    }

    public void setLabelNameRoomIRM(Label labelNameRoomIRM) {
        this.labelNameRoomIRM = labelNameRoomIRM;
    }

    public void setImgRoomIRM(ImageView imgRoomIRM) {
        this.imgRoomIRM = imgRoomIRM;
    }
}
