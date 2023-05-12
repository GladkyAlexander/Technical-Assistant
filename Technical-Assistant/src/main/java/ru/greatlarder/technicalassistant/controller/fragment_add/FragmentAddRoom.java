package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesRoom;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListRoom;
import ru.greatlarder.technicalassistant.services.database.SetRoom;
import ru.greatlarder.technicalassistant.services.database.mysql.room.SetRoomMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.ListRoomByCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.SetRoomSQLite;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class FragmentAddRoom implements Initializable {
    @FXML public VBox vBox;
    @FXML public ImageView closeAdRoom;
    @FXML public TextField tfNewRoom;
    @FXML public Label labelImageRoom;
    @FXML public ImageView imgLogoRoom;
    @FXML public Button btnAddRoom;
    @FXML public Label labelRooms;
    @FXML public Label labelNameCompany;
    @FXML public ListView<Room> listViewRooms;
    @FXML public Label labelNameRoom;
    Image img ;
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    User user;
    Company company;
    String lang;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadLang(lang);
    }
    private void loadLang(String lang) {
        labelNameRoom.setText(language.NEW_ROOM(lang));
        labelImageRoom.setText(language.IMAGE(lang));
        labelRooms.setText(language.ROOMS(lang));
        btnAddRoom.setText(language.ADD(lang));
    }
    public void loadFragment(Company company){
        this.company = company;
        if(company != null){
            img = new Image(imagePicker(imgLogoRoom.getImage()));
            labelNameCompany.setText(company.getNameCompany());
            loadListEvent(company);
        }
    }
    private void loadListEvent(Company comp) {
        listViewRooms.getItems().clear();
        
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        vBox.getChildren().add(6, progressBar);
        Task<ObservableList<Room>> task = new Task<>() {
            @Override
            protected ObservableList<Room> call() {
                GetListRoom getListRoom = new ListRoomByCompanySQLite();
                return FXCollections.observableArrayList(getListRoom.getListRoom(user, comp.getNameCompany(), null));
            }
        };
        task.setOnSucceeded((s)->{
            listViewRooms.setItems(task.getValue());
            listViewRooms.setCellFactory(param-> new ListCell<>(){
                final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_room.fxml"));
                Node node;
                ItemTypesRoom controller;
                {
                    try {
                        node = loader.load();
                        controller = loader.getController();
                        setPrefWidth(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                @Override
                protected void updateItem(Room room, boolean b) {
                    super.updateItem(room, b);
                    if(b){
                        setGraphic(null);
                    } else {
                        controller.setRoom(room);
                        controller.setLabelNameRoom(room.getNameRoom());
                        if(room.getUrlLogoRoom() != null) {
                            controller.setImgLogoRoom(String.valueOf(room.getUrlLogoRoom()));
                        } else controller.setImgLogoRoom("/ru/greatlarder/technicalassistant/images/all_room_active.png");
                        setGraphic(node);
                    }
                }
            });
            vBox.getChildren().remove(progressBar);
        });
        Platform.runLater(()->{
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }
    
    public void closeAddCompanyController() {
        ((BorderPane)vBox.getParent()).getChildren().remove(vBox);
    }
    
    public void loadLogoRoom() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        
        File chooserFile = fileChooser.showOpenDialog(imgLogoRoom.getScene().getWindow());
        
        if (chooserFile != null && chooserFile.length() < 494592) {
            Image image = new Image(chooserFile.toURI().toString());
            imgLogoRoom.setImage(new Image(imagePicker(image)));
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
    
    public void addNewRoom() {
        if(company != null){
            Room room = new Room();
            if(!tfNewRoom.getText().isEmpty()) {
                room.setNameRoom(tfNewRoom.getText());
                if(imgLogoRoom.getImage().getUrl().substring(imgLogoRoom.getImage().getUrl().lastIndexOf('/') + 1).equals("upphoto.png")){
                    room.setUrlLogoRoom(imagePicker(imgLogoRoom.getImage()));
                } else room.setUrlLogoRoom(imgLogoRoom.getImage().getUrl());
                room.setNameCompanyForRoom(company.getNameCompany());
                if (saveRoom(room) != null) {
                    tfNewRoom.clear();
                    tfNewRoom.setStyle(new TextField().getStyle());
                    imgLogoRoom.setImage(img);
                    loadListEvent(company);
                } else {
                    vBox.setStyle(StyleSRC.STYLE_DANGER);
                }
            }else tfNewRoom.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            btnAddRoom.setDisable(false);
        }
    }
    private Integer saveRoom(Room room){
        LanguageWarnings languageWarnings = new LanguageWarningsImpl();
        
        SetRoom setRoomSQLite = new SetRoomSQLite();
        SetRoom setRoomMySQL = new SetRoomMySQL();
        
        Integer resultMySQL = setRoomMySQL.setRoom(user, company.getNameCompany(), room);
        Integer resultSQLite = setRoomSQLite.setRoom(user, company.getNameCompany(), room);
        
        int res1 = 0;
        int res2 = 0;
        
        if(resultSQLite == null ){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            
            alert.setTitle(languageWarnings.Error_when_adding_to_the_database(lang));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            alert.setContentText(languageWarnings.Error_when_adding_to_the_database_SQLite(lang));
            alert.showAndWait();
        } else {
            res1 = 1;
        }
        if(resultMySQL == null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
           
            alert.setTitle(languageWarnings.Error_when_adding_to_the_database(lang));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            alert.setContentText(languageWarnings.Error_when_adding_to_the_database_MySQL(lang));
            alert.showAndWait();
        } else {
            res2 = 1;
        }
        if (res1 + res2 > 0){
            return 1;
        } else return null;
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
}
