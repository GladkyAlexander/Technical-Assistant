package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesEvent;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEventFormat;
import ru.greatlarder.technicalassistant.services.database.SetEventFormat;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.ListEventFormatByCompanyNameSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.SetEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageEvents;
import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageEventsImpl;
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

public class FragmentAddNameEvent implements Initializable {
    @FXML public VBox vBox;
    @FXML public ImageView closeAddEvent;
    @FXML public HBox hBoxEvent;
    @FXML public ImageView imgBriefing;
    @FXML public ImageView imgConference;
    @FXML public ImageView imgCongress;
    @FXML public ImageView imgExhibition;
    @FXML public ImageView imgLunch;
    @FXML public ImageView imgMeeting;
    @FXML public ImageView imgPresentation;
    @FXML public ImageView imgReception;
    @FXML public ImageView imgRoundTable;
    @FXML public ImageView imgSeminar;
    @FXML public ImageView imgTraining;
    @FXML public Label labelNameEvent;
    @FXML public TextField tfNameEvent;
    @FXML public Label labelLogoEvent;
    @FXML public ImageView imgLogoEvent;
    @FXML public ListView<EventFormat> listViewEventForCompany;
    @FXML public Button btnAddEvent;
    @FXML public HBox hBoxLabel;
    @FXML public Label labelCondition;
    @FXML public Label labelNameCompany;
    Language language = new LanguageImpl();
    LanguageEvents languageEvents = new LanguageEventsImpl();
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
        labelNameEvent.setText(language.EVENT_NAME(lang));
        labelLogoEvent.setText(language.EVENT_LOGO(lang));
        labelCondition.setText(language.EVENTS(lang));
        btnAddEvent.setText(language.ADD(lang));
    }
    public void loadFragment(Company company){
        this.company = company;
        loadTooltip();
        if(company != null){
            img = new Image(imagePicker(imgLogoEvent.getImage()));
            labelNameCompany.setText(company.getNameCompany());
            loadListEvent(company);
        }
    }
    
    private void loadTooltip(){
        Tooltip briefing = new Tooltip(languageEvents.getBriefing(lang));
        Tooltip.install(imgBriefing, briefing);
        Tooltip conference = new Tooltip(languageEvents.getThe_Conference(lang));
        Tooltip.install(imgConference, conference);
        Tooltip congress = new Tooltip(languageEvents.getCongress(lang));
        Tooltip.install(imgCongress, congress);
        Tooltip exhibition = new Tooltip(languageEvents.getExhibition(lang));
        Tooltip.install(imgExhibition, exhibition);
        Tooltip lunch = new Tooltip(languageEvents.getLunch(lang));
        Tooltip.install(imgLunch, lunch);
        Tooltip meeting = new Tooltip(languageEvents.getMeeting(lang));
        Tooltip.install(imgMeeting, meeting);
        Tooltip presentation = new Tooltip(languageEvents.getPresentation(lang));
        Tooltip.install(imgPresentation, presentation);
        Tooltip reception = new Tooltip(languageEvents.getReception(lang));
        Tooltip.install(imgReception, reception);
        Tooltip round_table = new Tooltip(languageEvents.getRoundTable(lang));
        Tooltip.install(imgRoundTable, round_table);
        Tooltip seminar = new Tooltip(languageEvents.getSeminar(lang));
        Tooltip.install(imgSeminar, seminar);
        Tooltip training = new Tooltip(languageEvents.getTraining(lang));
        Tooltip.install(imgTraining, training);
    }
    private void loadListEvent(Company comp) {
        listViewEventForCompany.getItems().clear();
        
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        vBox.getChildren().add(6, progressBar);
        Task<ObservableList<EventFormat>> task = new Task<>() {
            @Override
            protected ObservableList<EventFormat> call() {
                GetListEventFormat getListEventFormat = new ListEventFormatByCompanyNameSQLite();
                return FXCollections.observableArrayList(getListEventFormat.getListEventFormat(user, comp.getNameCompany(), null));
            }
        };
        task.setOnSucceeded((s)->{
            listViewEventForCompany.setItems(task.getValue());
            listViewEventForCompany.setCellFactory(param-> new ListCell<>(){
                final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_events.fxml"));
                Node node;
                ItemTypesEvent controller;
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
                protected void updateItem(EventFormat events, boolean b) {
                    super.updateItem(events, b);
                    if(b){
                        setGraphic(null);
                    } else {
                        controller.setEvents(events);
                        controller.setLabelEvent(events.getNameEventFormat());
                        controller.setImg_event(new Image(events.getUrlImageEvent()));
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
    
    public void addBriefing() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getBriefing(lang));
            events.setUrlImageEvent(imagePicker(imgBriefing.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addConference() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getThe_Conference(lang));
            events.setUrlImageEvent(imagePicker(imgConference.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addCongress() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getCongress(lang));
            events.setUrlImageEvent(imagePicker(imgCongress.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addExhibition() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getExhibition(lang));
            events.setUrlImageEvent(imagePicker(imgExhibition.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addLunch() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getLunch(lang));
            events.setUrlImageEvent(imagePicker(imgLunch.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addMeeting() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getMeeting(lang));
            events.setUrlImageEvent(imagePicker(imgMeeting.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addPresentation() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getPresentation(lang));
            events.setUrlImageEvent(imagePicker(imgPresentation.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addReception() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getReception(lang));
            events.setUrlImageEvent(imagePicker(imgReception.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addRoundTable() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getRoundTable(lang));
            events.setUrlImageEvent(imagePicker(imgRoundTable.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addSeminar() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getSeminar(lang));
            events.setUrlImageEvent(imagePicker(imgSeminar.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void addTraining() {
        if(company != null){
            EventFormat events = new EventFormat();
            events.setNameEventFormat(languageEvents.getTraining(lang));
            events.setUrlImageEvent(imagePicker(imgTraining.getImage()));
            events.setNameCompany(company.getNameCompany());
            saveEvent(events);
            loadListEvent(company);
        }
    }
    
    public void loadLogoEvent() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG, extensionFilter);
        
        File chooserFile = fileChooser.showOpenDialog(imgLogoEvent.getScene().getWindow());
        
        if (chooserFile != null && chooserFile.length() < 494592) {
            Image image = new Image(chooserFile.toURI().toString());
            imgLogoEvent.setImage(new Image(imagePicker(image)));
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
    
    public void addNewEvent() {
        if(company != null){
            EventFormat events = new EventFormat();
            if(!tfNameEvent.getText().isEmpty()) {
                events.setNameEventFormat(tfNameEvent.getText());
                if(imgLogoEvent.getImage().getUrl().substring(imgLogoEvent.getImage().getUrl().lastIndexOf('/') + 1).equals("upphoto.png")){
                    events.setUrlImageEvent(imagePicker(imgLogoEvent.getImage()));
                } else events.setUrlImageEvent(imgLogoEvent.getImage().getUrl());
                events.setNameCompany(company.getNameCompany());
                
                if (saveEvent(events) != null) {
                    tfNameEvent.clear();
                    tfNameEvent.setStyle(new TextField().getStyle());
                    imgLogoEvent.setImage(img);
                    loadListEvent(company);
                } else {
                    vBox.setStyle(StyleSRC.STYLE_DANGER);
                }
            }else tfNameEvent.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            btnAddEvent.setDisable(false);
        }
    }
    private Integer saveEvent(EventFormat eventFormat){
        SetEventFormat setEventFormat = new SetEventFormatSQLite();
        return setEventFormat.setEventFormat(user, eventFormat);
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
