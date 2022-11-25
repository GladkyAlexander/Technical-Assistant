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
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.RoomsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl.RoomsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemRoom implements ObserverLang, Initializable {

    @FXML public ImageView imgRoom;
    @FXML public Label labelNameRoom;
    @FXML public HBox hBoxRoom;
    @FXML public ImageView imgChangeRoom;
    @FXML public HBox hBoxChangeRoom;
    @FXML public TextField tfNewNameRoom;
    @FXML public Button btnSave;
    private String lang;
    public Room room;

    public void setRoom(Room room) {
        this.room = room;
    }

    Language language = new LanguageImpl();

    public void setImgRoom(Image imgRoom) {
        this.imgRoom.setImage(imgRoom);
    }

    public void setLabelNameRoom(String labelNameRoom) {
        this.labelNameRoom.setText(labelNameRoom);
    }

    public void loadUi(Boolean enabled) {
        hBoxChangeRoom.setVisible(enabled);
        hBoxChangeRoom.setManaged(enabled);
    }

    public void openChangeNameRoom(MouseEvent mouseEvent) {
      loadUi(true);
    }

    public void saveNewNameRoom(MouseEvent mouseEvent) {
        RoomsRepository repository = new RoomsRepositoryImpl();
        repository.changeRoom(room, tfNewNameRoom.getText(), "nameRoom");
        Room r = repository.getRoomForName(tfNewNameRoom.getText());
        setRoom(r);
        setLabelNameRoom(r.getNameRoom());
        tfNewNameRoom.clear();
        loadUi(false);
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang) {
        btnSave.setText(language.SAVE(lang));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUi(false);
    }

}
