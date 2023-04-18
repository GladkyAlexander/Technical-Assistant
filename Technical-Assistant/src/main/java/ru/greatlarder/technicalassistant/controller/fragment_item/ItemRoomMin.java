package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.user.User;

public class ItemRoomMin {
    @FXML public HBox vBoxItemRoomMin;
    @FXML public Label labelNameRoomIRM;
    @FXML public ImageView imgRoomIRM;
    public User user;

    public void setUser(User user) {
        this.user = user;
    }

    public void setvBoxItemRoomMin(HBox vBoxItemRoomMin) {
        this.vBoxItemRoomMin = vBoxItemRoomMin;
    }

    public void setLabelNameRoomIRM(String labelNameRoomIRM) {
        this.labelNameRoomIRM.setText(labelNameRoomIRM);
    }

    public void setImgRoomIRM(ImageView imgRoomIRM) {
        this.imgRoomIRM = imgRoomIRM;
    }
}
