package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import ru.greatlarder.technicalassistant.domain.BookingEquipment;

public class ItemBookingEquipment {
    @FXML public AnchorPane pane;
    @FXML public Label labelDate;

    BookingEquipment bookingEquipment;

    public void loadFragment(BookingEquipment bookingEquipment){
        this.bookingEquipment = bookingEquipment;
        labelDate.setText(String.valueOf(bookingEquipment.getDate().getDayOfMonth()));
        if(bookingEquipment.getConditionBooking() == 0){
            pane.setStyle("-fx-background-color: #00FF00;");
        } else {
            pane.setStyle("-fx-background-color: #FF0000;");
        }
    }
}
