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
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesArrangement;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.SetSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetListSeatingArrangementsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.SetSeatingArrangementsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.*;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageArrangementsImpl;
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

public class FragmentAddSeatingArrangements implements Initializable {
    @FXML public VBox vBox;
    @FXML public ImageView closeAddSeating;
    @FXML public ImageView imgBanquet;
    @FXML public ImageView imgBoardRoom;
    @FXML public ImageView imgChevron;
    @FXML public ImageView imgCircle;
    @FXML public ImageView imgClassroom;
    @FXML public ImageView imgColumns;
    @FXML public ImageView imgCresecentRound;
    @FXML public ImageView imgEShape;
    @FXML public ImageView imgHollow;
    @FXML public ImageView imgHorseshoe;
    @FXML public ImageView imgOctagon;
    @FXML public ImageView imgOvalBoardroom;
    @FXML public ImageView imgSeminar;
    @FXML public ImageView imgTShape;
    @FXML public ImageView imgTheatre;
    @FXML public ImageView imgUShape;
    @FXML public ImageView imgVShape;
    @FXML public Label labelNameTip;
    @FXML public TextField textFieldNewNameTip;
    @FXML public Label labelPhotoTip;
    @FXML public ImageView imgNewImageSeating;
    @FXML public Button btnAddNewSeating;
    @FXML public ListView<SeatingArrangements> listViewSeatingArrangements;
    @FXML public Label labelArrangement;
    @FXML public Label labelNameCompany;
    Language language = new LanguageImpl();
    LanguageArrangements languageArrangements = new LanguageArrangementsImpl();
    FileManager fileManager = new FileManagerImpl();
    User user;
    Company company;
    String lang;
    Image img;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadLang(lang);
    }
    
    private void loadLang(String lang) {
        labelNameTip.setText(language.TYPE_NAME(lang));
        labelPhotoTip.setText(language.IMAGE(lang));
        btnAddNewSeating.setText(language.ADD(lang));
    }
    public void loadFragment(Company company){
        this.company = company;
        loadTooltip();
        if(company != null){
            img = new Image(imagePicker(imgNewImageSeating.getImage()));
            labelNameCompany.setText(company.getNameCompany());
            loadListArrangement(company);
        } 
    }
    private void loadTooltip(){
        Tooltip banquet = new Tooltip(languageArrangements.getBanquet(lang));
        Tooltip.install(imgBanquet, banquet);
        Tooltip boardroom = new Tooltip(languageArrangements.getBoardRoom(lang));
        Tooltip.install(imgBoardRoom, boardroom);
        Tooltip chevron = new Tooltip(languageArrangements.getChevron(lang));
        Tooltip.install(imgChevron, chevron);
        Tooltip circle = new Tooltip(languageArrangements.getCircle(lang));
        Tooltip.install(imgCircle, circle );
        Tooltip classRoom = new Tooltip(languageArrangements.getClassRoom(lang));
        Tooltip.install(imgClassroom, classRoom);
        Tooltip columns = new Tooltip(languageArrangements.getColumns(lang));
        Tooltip.install(imgColumns, columns);
        Tooltip crescentRoundsCabaret = new Tooltip(languageArrangements.getCrescentRoundsCabaret(lang));
        Tooltip.install(imgCresecentRound, crescentRoundsCabaret);
        Tooltip eShape = new Tooltip(languageArrangements.getEShape(lang));
        Tooltip.install(imgEShape, eShape);
        Tooltip hollowRectangle = new Tooltip(languageArrangements.getHollowRectangle(lang));
        Tooltip.install(imgHollow, hollowRectangle);
        Tooltip horseshoe = new Tooltip(languageArrangements.getHorseshoe(lang));
        Tooltip.install(imgHorseshoe, horseshoe);
        Tooltip octagonMeeting = new Tooltip(languageArrangements.getOctagonMeeting(lang));
        Tooltip.install(imgOctagon, octagonMeeting);
        Tooltip ovalBoardRoom = new Tooltip(languageArrangements.getOvalBoardRoom(lang));
        Tooltip.install(imgOvalBoardroom, ovalBoardRoom);
        Tooltip seminar = new Tooltip(languageArrangements.getSeminar(lang));
        Tooltip.install(imgSeminar, seminar);
        Tooltip tShape = new Tooltip(languageArrangements.getTShape(lang));
        Tooltip.install(imgTShape, tShape);
        Tooltip theatreAuditorium = new Tooltip(languageArrangements.getTheatreAuditorium(lang));
        Tooltip.install(imgTheatre, theatreAuditorium);
        Tooltip uShape = new Tooltip(languageArrangements.getUShape(lang));
        Tooltip.install(imgUShape, uShape);
        Tooltip vShape = new Tooltip(languageArrangements.getVShape(lang));
        Tooltip.install(imgVShape, vShape);
    }
    private void loadListArrangement(Company comp){
        listViewSeatingArrangements.getItems().clear();
        
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        vBox.getChildren().add(6, progressBar);
        Task<ObservableList<SeatingArrangements>> task = new Task<>() {
            @Override
            protected ObservableList<SeatingArrangements> call() {
                GetListSeatingArrangements getListSeatingArrangements = new GetListSeatingArrangementsSQLite();
                return FXCollections.observableArrayList(getListSeatingArrangements.getListSeatingArrangements(user, comp.getNameCompany(), null));
            }
        };
        task.setOnSucceeded((s)->{
            listViewSeatingArrangements.setItems(task.getValue());
            listViewSeatingArrangements.setCellFactory(param-> new ListCell<>(){
                final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_arrangement.fxml"));
                Node node;
                ItemTypesArrangement controller;
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
                protected void updateItem(SeatingArrangements seatingArrangements, boolean b) {
                    super.updateItem(seatingArrangements, b);
                    if(b){
                        setGraphic(null);
                    } else {
                        controller.setSeatingArrangements(seatingArrangements);
                        controller.setLabelSeating(seatingArrangements.getNameSeatingArrangements());
                        controller.setImgLogo(new Image(seatingArrangements.getUrlImageSeatingArrangements()));
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
    
    public void addBanquet() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getBanquet(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgBanquet.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addBoardRoom() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getBoardRoom(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgBoardRoom.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addChevron() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getChevron(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgChevron.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addCircle() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getCircle(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgCircle.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addClassroom() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getClassRoom(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgClassroom.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addColumns() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getColumns(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgColumns.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addCresecent() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getCrescentRoundsCabaret(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgCresecentRound.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addEShape() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getEShape(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgEShape.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addHollow() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getHollowRectangle(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgHollow.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addHorseshoe() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getHorseshoe(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgHorseshoe.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addOctagon() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getOctagonMeeting(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgOctagon.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addOvalBoardroom() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getOvalBoardRoom(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgOvalBoardroom.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addSeminar() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getSeminar(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgSeminar.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addTShape() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getTShape(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgTShape.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addTheatre() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getTheatreAuditorium(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgTheatre.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addUShape() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getUShape(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgUShape.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addVShape() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameSeatingArrangements(languageArrangements.getVShape(lang));
            seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgVShape.getImage()));
            seatingArrangements.setNameCompany(company.getNameCompany());
            saveSeatingArrangements(seatingArrangements);
            loadListArrangement(company);
        }
    }
    
    public void addImageSeating() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG, extensionFilter);
        
        File chooserFile = fileChooser.showOpenDialog(imgNewImageSeating.getScene().getWindow());
        
        if (chooserFile != null && chooserFile.length() < 494592) {
            Image image = new Image(chooserFile.toURI().toString());
            imgNewImageSeating.setImage(new Image(imagePicker(image)));
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
    
    public void addNewSeating() {
        if(company != null){
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            if(!textFieldNewNameTip.getText().isEmpty()) {
                seatingArrangements.setNameSeatingArrangements(textFieldNewNameTip.getText());
                if(imgNewImageSeating.getImage().getUrl().substring(imgNewImageSeating.getImage().getUrl().lastIndexOf('/') + 1).equals("upphoto.png")){
                    seatingArrangements.setUrlImageSeatingArrangements(imagePicker(imgNewImageSeating.getImage()));
                } else seatingArrangements.setUrlImageSeatingArrangements(imgNewImageSeating.getImage().getUrl());
                seatingArrangements.setNameCompany(company.getNameCompany());
                if (saveSeatingArrangements(seatingArrangements) != null) {
                    textFieldNewNameTip.clear();
                    textFieldNewNameTip.setStyle(new TextField().getStyle());
                    imgNewImageSeating.setImage(img);
                    loadListArrangement(company);
                } else {
                    vBox.setStyle(StyleSRC.STYLE_DANGER);
                }
            }else textFieldNewNameTip.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            btnAddNewSeating.setDisable(false);
        }
    }
    
    private Integer saveSeatingArrangements(SeatingArrangements seatingArrangements) {
        SetSeatingArrangements setSeatingArrangements = new SetSeatingArrangementsSQLite();
        return setSeatingArrangements.setSeatingArrangements(user, company.getNameCompany(), seatingArrangements);
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
