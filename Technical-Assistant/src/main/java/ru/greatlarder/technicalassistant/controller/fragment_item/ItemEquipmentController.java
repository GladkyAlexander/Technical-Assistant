package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.database.GetBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment.BookingEquipmentByLocalDateAndEquipmentMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class ItemEquipmentController {

    @FXML public ImageView ivPhoto;
    @FXML public Label model;
    @FXML public Label serialNumber;
    @FXML public Label date;
    @FXML public VBox vBox;
    private Equipment equipment;
    public void setEquipment(Equipment equipment){
        this.equipment = equipment;
        model.setText(equipment.getModel());
        serialNumber.setText(equipment.getSerialNumber());
        date.setText(equipment.getDateWork().toString());
        if(equipment.getImage() != null){
            ivPhoto.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/" + equipment.getImage()))));
        } else {
            ivPhoto.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("ru/greatlarder/technicalassistant/images/all_equipment.png"))));
        }
    }

    public void setSt(){
        Task<BookingEquipment> task = new Task<>() {
            @Override
            protected BookingEquipment call() {
                GetBookingEquipment getBookingEquipment = new BookingEquipmentByLocalDateAndEquipmentMySQL();
                return getBookingEquipment.getBookingEquipment(GlobalLinkMainController.getMainController().getUser()
                    , LocalDate.now(), String.valueOf(equipment.getId()));
            }
        };

        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        vBox.getChildren().add(progressBar);
        progressBar.progressProperty().bind(task.progressProperty());

        task.setOnSucceeded((t) ->{
            BookingEquipment b = task.getValue();
            if (b != null) {
                vBox.setStyle(StyleSRC.STYLE_WARNING);
            }
            vBox.getChildren().remove(progressBar);
        });

        Platform.runLater(() -> {
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });

    }

}
