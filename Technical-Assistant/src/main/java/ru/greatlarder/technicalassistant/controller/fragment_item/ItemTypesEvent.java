package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.Names;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.mysql.event_format.GetEventFormatByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.GetNamesByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.GetNamesEventFormatByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.UpdateNamesByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.UpdateNamesByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.room.RoomByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.room.RoomByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.room.UpdateRoomMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.GetEventFormatByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.UpdateEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.RoomByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.UpdateRoomSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageWarningsImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemTypesEvent implements Initializable {

    @FXML public ImageView img_event;
    @FXML public Label labelEvent;
    @FXML public ImageView imgChange;
    @FXML public HBox hBoxNewName;
    @FXML public Label labelNewName;
    @FXML public TextField textFieldNewName;
    @FXML public ImageView imgNewImage;
    @FXML public Button btnSave;
    EventFormat events;
    EventFormat eventFormatResult;
    FileManager fileManager = new FileManagerImpl();
    User user;
    Company company;
    String lang;
    int flag = 0;
    Image img ;

    public void setEvents(EventFormat events) {
        this.events = events;
        loadFragment(events);
    }
    private void loadFragment(EventFormat ro) {
        setImg_event(new Image(ro.getUrlImageEvent()));
        setLabelEvent(ro.getNameRoom());
        hBoxNewName.setVisible(false);
        hBoxNewName.setManaged(false);
        flag = 0;
    }

    public void setImg_event(Image img_event) {
        this.img_event.setImage(img_event);
    }

    public void setLabelEvent(String labelEvent) {
        this.labelEvent.setText(labelEvent);
    }
    
    public void openChangePane() {
        if(flag == 0){
            flag = 1;
            hBoxNewName.setVisible(true);
            hBoxNewName.setManaged(true);
            textFieldNewName.setText(labelNewName.getText());
        } else {
            flag = 0;
            hBoxNewName.setVisible(false);
            hBoxNewName.setManaged(false);
        }
    }
    
    public void selectANewImage() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        
        File chooserFile = fileChooser.showOpenDialog(imgNewImage.getScene().getWindow());
        
        if (chooserFile != null && chooserFile.length() < 494592) {
            Image image = new Image(chooserFile.toURI().toString());
            imgNewImage.setImage(new Image(imagePicker(image)));
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            LanguageWarnings languageWarnings = new LanguageWarningsImpl();
            alert.setTitle(languageWarnings.INCORRECT_IMAGE(lang));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            alert.setContentText(languageWarnings.THE_PICTURE_SHOULD_BE_NO_MORE_THAN_483_KB(lang));
            alert.showAndWait();
        }
    }
    
    public void save() {
        if(!textFieldNewName.getText().isEmpty()) {
            EventFormat r = new EventFormat();
            r.setId(events.getId());
            r.setNameRoom(textFieldNewName.getText());
            if(imgNewImage.getImage().getUrl().substring(imgNewImage.getImage().getUrl().lastIndexOf('/') + 1).equals("upphoto.png")){
                r.setUrlImageEvent(img_event.getImage().getUrl());
            } else r.setUrlImageEvent(imgNewImage.getImage().getUrl());
            r.setNameCompany(company.getNameCompany());
            saveMySQL(saveSQLite(r));
        }else hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        hBoxNewName.setVisible(false);
        hBoxNewName.setManaged(false);
        if(user instanceof Engineer){
            imgChange.setVisible(true);
            imgChange.setManaged(true);
        } else {
            imgChange.setVisible(false);
            imgChange.setManaged(false);
        }
        img = new Image(imagePicker(imgNewImage.getImage()));
    }
    private void setLanguage(String lan){
        Language language = new LanguageImpl();
        labelNewName.setText(language.NEW_ROOM(lan));
        btnSave.setText(language.SAVE(lan));
    }
    public String imagePicker(Image image) {
        String name = image.getUrl().substring(image.getUrl().lastIndexOf('/') + 1);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png",
                new FileOutputStream(fileManager.folderImage() + '/' + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileManager.folderImage() + '\\' + name;
    }
    private Names saveSQLite(EventFormat room1) {
        UpdateEventFormat updateEvent = new UpdateEventFormatSQLite();
        updateEvent.updateEvent(user, room1);
        GetEventFormat getEventSQLite = new GetEventFormatByIdSQLite();
        EventFormat roomSQLite = getEventSQLite.getEventFormat(user, company.getNameCompany(), String.valueOf(room1.getId()));
        eventFormatResult = roomSQLite;
        if(roomSQLite != null){
            GetNames getEventFormat = new GetNamesEventFormatByNameMySQL();
            Names namesMySQL = getEventFormat.getNames(user, company.getNameCompany(), events.getNameEventFormat());
            if (namesMySQL != null) {
                namesMySQL.setNames(roomSQLite.getNameRoom());
                namesMySQL.setNameCompany(roomSQLite.getNameCompany());
                
                Path path = Paths.get(roomSQLite.getUrlImageEvent());
                File file = path.toFile();
                namesMySQL.setUrl(file);
                
                return namesMySQL;
            }else {
                hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
                return null;
            }
        } else {
            hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
    }
    
    private void saveMySQL(Names room2) {
        if(room2 != null){
            UpdateNames updateNames = new UpdateNamesByIdMySQL();
            GetNames getNames = new GetNamesByIdMySQL();
            updateNames.updateNames(user, room2, String.valueOf(room2.getId()));
            Names r = getNames.getNames(user, company.getNameCompany(), String.valueOf(room2.getId()));
            if (r != null && textFieldNewName.getText().equals(r.getNames())) {
                textFieldNewName.clear();
                hBoxNewName.setStyle(new HBox().getStyle());
                imgNewImage.setImage(img);
                setEvents(eventFormatResult);
            } else {
                hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
            }
        }
    }
    public void setFragmentMySQL(){
        imgChange.setDisable(true);
        imgChange.setVisible(false);
        imgChange.setManaged(false);
    }
}
