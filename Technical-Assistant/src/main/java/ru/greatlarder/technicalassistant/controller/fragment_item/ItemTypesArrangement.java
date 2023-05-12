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
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.mysql.names.GetNamesByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.GetNamesEventFormatByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.UpdateNamesByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements.UpdateSeatingArrangementsMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.GetEventFormatByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.UpdateEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetSeatingArrangementsByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.UpdateSeatingArrangementsSQLite;
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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemTypesArrangement implements Initializable {
    @FXML public VBox vBox;
    @FXML public ImageView imgLogo;
    @FXML public Label labelSeating;
    @FXML public ImageView imgChange;
    @FXML public HBox hBoxNewName;
    @FXML public Label labelNewName;
    @FXML public TextField textFieldNewName;
    @FXML public ImageView imgNewImage;
    @FXML public Button btnSave;
    SeatingArrangements seatingArrangements;
    SeatingArrangements seatingArrangementsResult;
    FileManager fileManager = new FileManagerImpl();
    User user;
    Company company;
    String lang;
    int flag = 0;
    Image img ;
    
    public void setSeatingArrangements(SeatingArrangements seatingArrangements) {
        this.seatingArrangements = seatingArrangements;
        loadFragment(seatingArrangements);
    }
    
    private void loadFragment(SeatingArrangements seatingArrangements) {
        setImgLogo(new Image(seatingArrangements.getUrlImageSeatingArrangements()));
        setLabelSeating(seatingArrangements.getNameSeatingArrangements());
        hBoxNewName.setVisible(false);
        hBoxNewName.setManaged(false);
        flag = 0;
    }
    
    public void setImgLogo(Image imgLogo) {
        this.imgLogo.setImage(imgLogo);
    }
    
    public void setLabelSeating(String labelSeating) {
        this.labelSeating.setText(labelSeating);
    }
    
    public void openChangePane() {
        if(flag == 0){
            flag = 1;
            hBoxNewName.setVisible(true);
            hBoxNewName.setManaged(true);
            textFieldNewName.setText(labelSeating.getText());
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
            SeatingArrangements r = new SeatingArrangements();
            r.setId(seatingArrangements.getId());
            r.setNameSeatingArrangements(textFieldNewName.getText());
            if(imgNewImage.getImage().getUrl().substring(imgNewImage.getImage().getUrl().lastIndexOf('/') + 1).equals("upphoto.png")){
                r.setUrlImageSeatingArrangements(imgLogo.getImage().getUrl());
            } else r.setUrlImageSeatingArrangements(imgNewImage.getImage().getUrl());
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
    private Names saveSQLite(SeatingArrangements room1) {
        UpdateSeatingArrangements updateSeatingArrangements = new UpdateSeatingArrangementsSQLite();
        updateSeatingArrangements.updateSeatingArrangements(user, company.getNameCompany(), room1, room1.getId());
        
        GetSeatingArrangements getSeatingArrangements = new GetSeatingArrangementsByIdSQLite();
        SeatingArrangements roomSQLite = getSeatingArrangements.getSeatingArrangements(user, company.getNameCompany(), String.valueOf(room1.getId()));
        seatingArrangementsResult = roomSQLite;
        if(roomSQLite != null){
            GetNames getEventFormat = new GetNamesEventFormatByNameMySQL();
            Names namesMySQL = getEventFormat.getNames(user, company.getNameCompany(), seatingArrangements.getNameSeatingArrangements());
            if (namesMySQL != null) {
                namesMySQL.setNames(roomSQLite.getNameSeatingArrangements());
                namesMySQL.setNameCompany(roomSQLite.getNameCompany());
                namesMySQL.setDomain("seating_arrangements");
                Path path = Paths.get(roomSQLite.getUrlImageSeatingArrangements());
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
                setSeatingArrangements(seatingArrangementsResult);
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
