package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetRoom;
import ru.greatlarder.technicalassistant.services.database.UpdateRoom;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.RoomByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.UpdateRoomSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemRoom implements Initializable {

    @FXML public ImageView imgRoom;
    @FXML public Label labelNameRoom;
    @FXML public HBox hBoxRoom;
    @FXML public ImageView imgChangeRoom;
    @FXML public HBox hBoxChangeRoom;
    @FXML public TextField tfNewNameRoom;
    @FXML public Button btnSave;
    String lang;
    Room room;
    User user;
    Company company;
    String whereToSave;
    Language language = new LanguageImpl();

    public void setRoom(Room room) {
        this.room = room;
        labelNameRoom.setText(room.getNameRoom());
        loadUi(false);
    }

    public void setImgRoom(Image imgRoom) {
        this.imgRoom.setImage(imgRoom);
    }
    public void loadUi(Boolean enabled) {
        hBoxChangeRoom.setVisible(enabled);
        hBoxChangeRoom.setManaged(enabled);
    }

    public void openChangeNameRoom(MouseEvent mouseEvent) {
        tfNewNameRoom.setText(room.getNameRoom());
        loadUi(true);
    }

    public void saveNewNameRoom(MouseEvent mouseEvent) {
        tfNewNameRoom.setStyle(new TextField().getStyle());
        if(whereToSave == null) {
            UpdateRoom updateRoom = new UpdateRoomSQLite();
            room.setNameRoom(tfNewNameRoom.getText());
            updateRoom.updateRoom(user, room, room.getId());
            GetRoom getRoom = new RoomByIdSQLite();
            Room r = getRoom.getRoom(user, company.getNameCompany(), String.valueOf(room.getId()));
            if(r != null){
                setRoom(r);
                tfNewNameRoom.clear();
            } else tfNewNameRoom.setStyle(StyleSRC.STYLE_DANGER);
        }
    }

    private void setLanguage(String lang) {
        btnSave.setText(language.SAVE(lang));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadUi(false);
        setLanguage(lang);
    }

}
