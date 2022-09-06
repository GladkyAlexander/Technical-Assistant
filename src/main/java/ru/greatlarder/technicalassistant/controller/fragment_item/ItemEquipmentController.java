package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentEquipmentOneController;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkFragmentEquipmentController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;

import java.io.IOException;
import java.time.LocalDate;

public class ItemEquipmentController implements ObserverLang {

    @FXML
    public ImageView ivPhoto;
    @FXML public Label model;
    @FXML public Label serialNumber;
    @FXML public Label date;
    @FXML public GridPane gridPaneILE;
    private String lang;
    HandlerLang handlerLang = new HandlerLang();
    private Equipment equipment;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        handlerLang.onNewDataLang(new DataLang(dataLang.getLanguage()));
    }
    public void setIvPhoto(Image img) {
        this.ivPhoto.setImage(img);
    }
    public void setModel(String modelEq) {
        this.model.setText(modelEq);
    }
    public void setSerialNumber(String serialNumberEq) {
        this.serialNumber.setText(serialNumberEq);
    }
    public void setDate(LocalDate dateEq) {
        this.date.setText(dateEq.toString());
    }
    public void setEquipment(Equipment equipment){
        this.equipment = equipment;
    }

    public void openEquipment(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));
        try {
            GlobalLinkFragmentEquipmentController.getFragmentEquipmentController().tabPaneEquipment2.getTabs().add(
                    new Tab(equipment.getName() + " : " + equipment.getSerialNumber(), loader.load()));
            handlerLang.registerObserverLang(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentEquipmentOneController fragmentEquipmentOneItemController = loader.getController();
            fragmentEquipmentOneItemController.setEquip(equipment);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
