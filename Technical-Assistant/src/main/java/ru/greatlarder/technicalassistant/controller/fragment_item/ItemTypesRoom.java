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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetRoom;
import ru.greatlarder.technicalassistant.services.database.UpdateRoom;
import ru.greatlarder.technicalassistant.services.database.mysql.room.RoomByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.room.RoomByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.room.UpdateRoomMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.RoomByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.UpdateRoomSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemTypesRoom implements Initializable {
    @FXML public VBox vBox;
    @FXML public ImageView imgLogoRoom;
    @FXML public Label labelNameRoom;
    @FXML public ImageView imgChange;
    @FXML public HBox hBoxNewName;
    @FXML public Label labelNewName;
    @FXML public TextField textFieldNewName;
    @FXML public ImageView imgNewImage;
    @FXML public Button btnSave;
    FileManager fileManager = new FileManagerImpl();
    Room room;
    User user;
    Company company;
    String lang;
    int flag = 0;
    Image img ;
    public void setImgLogoRoom(String imgLogoRoom) {
        this.imgLogoRoom.setImage(new Image(imgLogoRoom));
    }
    
    public void setLabelNameRoom(String labelNameRoom) {
        this.labelNameRoom.setText(labelNameRoom);
    }
    
    public void setRoom(Room room) {
        this.room = room;
        loadFragment(room);
    }
    
    private void loadFragment(Room ro) {
        setImgLogoRoom(ro.getUrlLogoRoom());
        setLabelNameRoom(ro.getNameRoom());
        hBoxNewName.setVisible(false);
        hBoxNewName.setManaged(false);
        flag = 0;
    }
    
    public void openChangePane() {
        if(flag == 0){
            flag = 1;
            hBoxNewName.setVisible(true);
            hBoxNewName.setManaged(true);
            textFieldNewName.setText(labelNameRoom.getText());
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
            Room r = new Room();
            r.setId(room.getId());
            r.setNameRoom(textFieldNewName.getText());
            if(imgNewImage.getImage().getUrl().substring(imgNewImage.getImage().getUrl().lastIndexOf('/') + 1).equals("upphoto.png")){
                r.setUrlLogoRoom(imgLogoRoom.getImage().getUrl());
            } else r.setUrlLogoRoom(imgNewImage.getImage().getUrl());
            r.setNameCompanyForRoom(company.getNameCompany());
            saveMySQL(saveSQLite(r));
        }else hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
    }
    
    private Room saveSQLite(Room room1) {
        UpdateRoom updateRoom = new UpdateRoomSQLite();
        updateRoom.updateRoom(user, room1, room1.getId());
        GetRoom getRoomSQLite = new RoomByIdSQLite();
        Room roomSQLite = getRoomSQLite.getRoom(user, company.getNameCompany(), String.valueOf(room1.getId()));
        if(roomSQLite != null){
            GetRoom getRoom = new RoomByNameMySQL();
            Room roomMySQL = getRoom.getRoom(user, company.getNameCompany(), room.getNameRoom());
            if (roomMySQL != null) {
                roomMySQL.setNameRoom(roomSQLite.getNameRoom());
                roomMySQL.setNameCompanyForRoom(roomSQLite.getNameCompanyForRoom());
                roomMySQL.setUrlLogoRoom(roomSQLite.getUrlLogoRoom());
                
                return roomMySQL;
            }else {
                hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
                return null;
            }
        } else {
            hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
            return null;
        }
    }
    
    private void saveMySQL(Room room2) {
        if(room2 != null){
            UpdateRoom updateRoom = new UpdateRoomMySQL();
            GetRoom getRoom = new RoomByIdMySQL();
            updateRoom.updateRoom(user, room2, room2.getId());
            Room r = getRoom.getRoom(user, company.getNameCompany(), String.valueOf(room2.getId()));
            if (r != null && textFieldNewName.getText().equals(r.getNameRoom())) {
                textFieldNewName.clear();
                hBoxNewName.setStyle(new HBox().getStyle());
                imgNewImage.setImage(img);
                setRoom(r);
            } else {
                hBoxNewName.setStyle(StyleSRC.STYLE_DANGER);
            }
        }
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
    
    public void setFragmentMySQL(){
        imgChange.setDisable(true);
        imgChange.setVisible(false);
        imgChange.setManaged(false);
    }
}
