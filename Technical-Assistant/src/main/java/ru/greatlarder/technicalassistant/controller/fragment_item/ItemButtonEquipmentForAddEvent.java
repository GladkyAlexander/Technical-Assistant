package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.Equipment;

import java.util.Objects;

public class ItemButtonEquipmentForAddEvent {
    @FXML
    public Label labelNameEquipment;
    @FXML public Label labelSerialNumberEquipment;
    @FXML public GridPane gridPane;
    @FXML public ImageView imgEquipment;
    Equipment equipment;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void loadFragment(Equipment equipment){
        setEquipment(equipment);
        labelNameEquipment.setText(this.equipment.getName());
        labelSerialNumberEquipment.setText(this.equipment.getSerialNumber());
        imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/" + equipment.getImage()))));
    }
    public void close() {
        ((HBox) gridPane.getParent()).getChildren().remove(gridPane);
    }

}
