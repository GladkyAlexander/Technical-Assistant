package ru.greatlarder.technicalassistant.controller.fragment_add;

import jakarta.mail.MessagingException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemButtonEquipmentForAddEvent;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.check.CheckForANumber;
import ru.greatlarder.technicalassistant.services.check.CheckingForANumberImpl;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.mysql.day.UpdateDayMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.ListEquipmentByRoomVisibleTrueMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.event_format.SetEventFormatMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.event_format.UpdateEventFormatMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.ListNamesForEventFormatMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.ListNamesForSeatingArrangementMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements.SeatingArrangementsByIdMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements.SeatingArrangementsByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements.SetSeatingArrangementsMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.phone_book.GetListPhoneBookByUserSQLite;
import ru.greatlarder.technicalassistant.services.get.GetDayUpdate;
import ru.greatlarder.technicalassistant.services.get.GetListTimeToString;
import ru.greatlarder.technicalassistant.services.get.GetLocDatTime;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.impl.SendApplicationLetter;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentAddEvent implements Initializable {
    @FXML public GridPane gridPaneAddEvent;
    @FXML public Label nameEvent;
    @FXML public Label labelStartEvent;
    @FXML public Label labelEndEvent;
    @FXML public Label labelSeatingArrangements;
    @FXML public Label labelLastNameCustomer;
    @FXML public Label labelFirstNameCustomer;
    @FXML public Label labelNumberOfParticipants;
    @FXML public Label labelEquipment;
    @FXML public Label labelNote;
    @FXML public TextArea textAreaNote;
    @FXML public TextField textFileFirstNameCustomer;
    @FXML public TextField textFiledLastNameCustomer;
    @FXML public Label startEvent;
    @FXML public ComboBox<String> comboBoxEndEvent;
    @FXML public ComboBox<String> comboBoxSeatingArrangements;
    @FXML public TextField textFiledNumberOfParticipants;
    @FXML public Label endEvent;
    @FXML public ImageView imgNameEvent;
    @FXML public ComboBox<String> comboBoxNameEvent;
    @FXML public Label labelTo;
    @FXML public Label labelTopic;
    @FXML public TextField textFiledTo;
    @FXML public TextField textFiledTopic;
    @FXML public Button btnSaveAndSend;
    @FXML public ComboBox<String> comboBoxChoiceTo;
    @FXML public Button btnToChange;
    @FXML public BorderPane borderPane;
    @FXML public HBox hBoxListEquipment;

    EventFormat events;
    String time;
    User user;
    String lang;
    Company company;
    Language language = new LanguageImpl();
    Day day;
    List<PhoneBook> contactCarts;
    HashMap<String, String> hashMap = new HashMap<>();
    int flag = 0;

    public void loadFragment(EventFormat events, String time, Day day){
        textAreaNote.setWrapText(true);
        this.day = day;
        this.time = time;
        this.events = events;
        if(this.events == null){
            loadNewData();
        } else loadingIncomingData();
    }

    private void loadNewData() {
        loadVisibleButton(true);
        comboBoxEndEvent.setVisible(true);
        comboBoxEndEvent.setManaged(true);
        comboBoxNameEvent.setVisible(true);
        comboBoxNameEvent.setManaged(true);
        comboBoxSeatingArrangements.setDisable(false);
        startEvent.setText(time);
        loadCombo();
        GetListTimeToString getListTimeToString = new GetListTimeToString();
        GetLocDatTime getLocDatTime = new GetLocDatTime();

        comboBoxEndEvent.setItems(FXCollections.observableArrayList(getListTimeToString.getListTimeToString(this.day, getLocDatTime.getLocDatTime(time))));

        comboBoxChoiceTo.setItems(getTo());
    }

    private void loadCombo() {
        GetListNames getListSeating = new ListNamesForSeatingArrangementMySQL();
        Task<List<String>> task = new Task<>() {
            @Override
            protected List<String> call() throws Exception {
                List<String> stringsS = new ArrayList<>();
                for(Names names: getListSeating.getListNames(user, company.getNameCompany(), null)){
                    stringsS.add(names.getNames());
                }
                return stringsS;
            }
        };
        task.setOnSucceeded((succeededEvent) -> {
            comboBoxSeatingArrangements.setItems(FXCollections.observableList(task.getValue()));
        });
        Platform.runLater(()->{
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });

        Task<List<Names>> taskListEvent = new Task<>() {
            @Override
            protected List<Names> call() throws Exception {
                GetListNames getListEventName = new ListNamesForEventFormatMySQL();
                return getListEventName.getListNames(user, company.getNameCompany(), null);
            }
        };
        taskListEvent.setOnSucceeded((succeededEvent) -> {
            List<String> stringsE = new ArrayList<>();
            List<Names> namesList = taskListEvent.getValue();
            for(Names names: namesList){
                stringsE.add(names.getNames());
            }
            comboBoxNameEvent.setItems(FXCollections.observableArrayList(stringsE));
            comboBoxNameEvent.setOnAction(event -> {
                nameEvent.setText(comboBoxNameEvent.getValue());
                for (Names names : namesList){
                    if(names.getNames().equals(comboBoxNameEvent.getValue())){
                        imgNameEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(
                                "/ru/greatlarder/technicalassistant/images/events_img" + "/" + names.getUrl()))));
                    };
                }
            });
        });
        Platform.runLater(()->{
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(taskListEvent);
            executorService.shutdown();
        });
        openVisibleDevicesOfRoom(day.getRoom());
    }
    private void loadingIncomingData(){
        
        loadVisibleButton(false);
        
        nameEvent.setText(events.getNameEventFormat());
        startEvent.setText(events.getEventStartTime());
        endEvent.setText(events.getEndTimeOfTheEvent());
        imgNameEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/" + events.getUrlImageEvent()))));
        comboBoxEndEvent.setVisible(false);
        comboBoxEndEvent.setManaged(false);
        comboBoxNameEvent.setVisible(false);
        comboBoxNameEvent.setManaged(false);
        comboBoxSeatingArrangements.setDisable(true);
        if(events.getIdSeatingArrangements() != 0) {
            GetSeatingArrangements getSeatingArrangements = new SeatingArrangementsByIdMySQL();
            SeatingArrangements seatingArrangements = getSeatingArrangements.getSeatingArrangements(user, company.getNameCompany()
                    , String.valueOf(events.getIdSeatingArrangements()));
            comboBoxSeatingArrangements.setPromptText(seatingArrangements.getNameSeatingArrangements());
            textFiledNumberOfParticipants.setText(seatingArrangements.getNumberOfParticipants().toString());
        }
        textFiledLastNameCustomer.setText(events.getLastNameCustomer());
        textFileFirstNameCustomer.setText(events.getFirstNameCustomer());
        textAreaNote.setText(events.getNote());

    }
    
    private void loadVisibleButton(boolean b) {
        btnSaveAndSend.setVisible(b);
        btnSaveAndSend.setManaged(b);
        btnToChange.setVisible(!b);
        btnToChange.setManaged(!b);
    }

    private void setLanguage(String lang) {
        labelStartEvent.setText(language.START_EVENT(lang));
        labelEndEvent.setText(language.END_EVENT(lang));
        labelSeatingArrangements.setText(language.SEATING_ARRANGEMENTS(lang));
        labelNumberOfParticipants.setText(language.NUMBER_OF_PARTICIPANTS(lang));
        labelLastNameCustomer.setText(language.LAST_NAME_CUSTOMER(lang));
        labelFirstNameCustomer.setText(language.FIRST_NAME_CUSTOMER(lang));
        labelEquipment.setText(language.EQUIPMENT(lang));
        labelNote.setText(language.NOTE(lang));
        labelTo.setText(language.TO(lang));
        labelTopic.setText(language.TOPIC(lang));
        nameEvent.setText(language.EVENT(lang));
        btnSaveAndSend.setText(language.SAVE_AND_SEND(lang));
        btnToChange.setText(language.CHANGE_DATA(lang));
    }
    private String getNameImg(String nameEvent){
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Zoom", "zoom.png");
        hashMap.put("Skype", "skype.png");
        hashMap.put("VCS", "vcs.png");
        hashMap.put("Встреча", "meeting.png");
        hashMap.put("Презентация", "present.png");
        return hashMap.get(nameEvent);
    }

    public void saveEndSendLetter() {
        if(flag == 0) {
            save();
        }
        if (flag == 1){
            change();
        }
    }
    
    private void save(){
        
        UpdateDay updateDay = new UpdateDayMySQL();
        GetDayUpdate getDayUpdate = new GetDayUpdate();
        
        updateDay.updateDay(user, getDayUpdate.getUpdateDay(getEventFormat(), day, time));
        SendApplicationLetter htmlLetter = new SendApplicationLetter();
        
        String[] address = textFiledTo.getText().split(",");
        List<String> list = new ArrayList<>(Arrays.asList(address));
        List<String> listAddress = new ArrayList<>();
        
        for (String s : list){
            listAddress.add(hashMap.get(s));
        }
        
        try {
            htmlLetter.sendEmail(user, getDocument(), listAddress, textFiledTopic.getText(), null );
        } catch (MessagingException | IOException | URISyntaxException e) {
            gridPaneAddEvent.setStyle(StyleSRC.STYLE_DANGER);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentConnectionError.fxml"));
            try {
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png")))));
            stage.setTitle("Error");
            stage.setScene(scene);
            stage.show();
            e.printStackTrace();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) gridPaneAddEvent.getScene().getWindow();
        stage.close();
    }
    private void change(){
        
        events.setNameEventFormat(comboBoxNameEvent.getValue());
        events.setUrlImageEvent(getNameImg(comboBoxNameEvent.getValue()));
        events.setEventStartTime(startEvent.getText());
        events.setEndTimeOfTheEvent(comboBoxEndEvent.getValue());
        if (comboBoxSeatingArrangements.getValue() != null) {
            GetSeatingArrangements getSeatingArrangements = new SeatingArrangementsByNameMySQL();
            events.setIdSeatingArrangements(getSeatingArrangements.getSeatingArrangements(user, company.getNameCompany(), comboBoxSeatingArrangements.getValue()).getId());
        }
        events.setLastNameCustomer(textFiledLastNameCustomer.getText());
        events.setFirstNameCustomer(textFileFirstNameCustomer.getText());
        events.setIdDay(day.getId());
        events.setNameRoom(day.getRoom());
        events.setNote(textAreaNote.getText());
        
        UpdateEventFormat updateEventFormat = new UpdateEventFormatMySQL();
        updateEventFormat.updateEvent(user, events);
        
        UpdateDay updateDay = new UpdateDayMySQL();
        GetDayUpdate getDayUpdate = new GetDayUpdate();
        updateDay.updateDay(user, getDayUpdate.getUpdateDay(events, day, time));
        
        SendApplicationLetter htmlLetter = new SendApplicationLetter();
        String[] address = textFiledTo.getText().split(",");
        try {
            htmlLetter.sendEmail(user, getDocument(), new ArrayList<>(Arrays.asList(address)), textFiledTopic.getText(), null );
        } catch (MessagingException | IOException | URISyntaxException e) {
            gridPaneAddEvent.setStyle(StyleSRC.STYLE_DANGER);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentConnectionError.fxml"));
            try {
                Stage stage = new Stage();
                Scene scene = new Scene(loader.load());
                stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png")))));
                stage.setTitle("Error");
                stage.setScene(scene);
                stage.show();
                e.printStackTrace();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
        Stage stage = (Stage) gridPaneAddEvent.getScene().getWindow();
        stage.close();
    }
    public void valueInput() {
        textFiledTo.setText(comboBoxChoiceTo.getValue());
    }
    private ObservableList<String> getTo(){
        GetListPhoneBook getListPhoneBook = new GetListPhoneBookByUserSQLite();
        this.contactCarts = getListPhoneBook.getListPhoneBook(user, company.getNameCompany(), String.valueOf(user.getId()));
        List<String> list = new ArrayList<>();
        for (PhoneBook c : this.contactCarts){
            list.add(c.getLastName() + " " + c.getFirstName());
            hashMap.put(c.getLastName() + " " + c.getFirstName(), c.getMail());
        }
        return FXCollections.observableList(list);
    }
    
    public void openChange() {
        this.flag = 1;
        loadNewData();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
    }
    public void onKeyRealisedNumberOfParticipants(KeyEvent keyEvent) {
        CheckForANumber check = new CheckingForANumberImpl();
        if(!check.checkingForANumber(textFiledNumberOfParticipants.getText())){
            textFiledNumberOfParticipants.setStyle(StyleSRC.STYLE_DANGER);
        } else {
            textFiledNumberOfParticipants.setStyle(new TextField().getStyle());
        }
    }
    public void openVisibleDevicesOfRoom(String room) {
        GetListEquipment getListEquipment = new ListEquipmentByRoomVisibleTrueMySQL();

        Task<List<Equipment>> taskEquipments = new Task<>() {
            @Override
            protected List<Equipment> call() {
                return getListEquipment.getListEquipment(user, user.getCompanyAffiliation(), room);
            }
        };
        ProgressBar progressBar = new ProgressBar();
        hBoxListEquipment.getChildren().add(progressBar);

        taskEquipments.setOnSucceeded((s)->{

           for (Equipment e : taskEquipments.getValue()){
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_button_equipment_for_add_event.fxml"));
               try {
                   hBoxListEquipment.getChildren().add(loader.load());
                   ItemButtonEquipmentForAddEvent controller = loader.getController();
                   controller.loadFragment(e);
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
           }
           hBoxListEquipment.getChildren().remove(progressBar);
        });
        Platform.runLater(() -> {
            progressBar.progressProperty().bind(taskEquipments.progressProperty());
            progressBar.visibleProperty().bind(taskEquipments.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(taskEquipments);
            executorService.shutdown();
        });
    }
    private List<Equipment> getListNameEquipment(){
        ObservableList<Node> list23 = hBoxListEquipment.getChildren();
        List<Equipment> listNameEquipment = new ArrayList<>();
        Object controller = null;
        for (Node node: list23){
            do {
                controller = node.getUserData();
                node = node.getParent();
            } while (controller == null && node != null);
            if (controller instanceof ItemButtonEquipmentForAddEvent){
                listNameEquipment.add(((ItemButtonEquipmentForAddEvent) controller).getEquipment());
            }
        }
        return listNameEquipment;
    }

    private EventFormat getEventFormat() {
        EventFormat eventNew = new EventFormat();
        eventNew.setNameEventFormat(comboBoxNameEvent.getValue());
        eventNew.setUrlImageEvent(getNameImg(comboBoxNameEvent.getValue()));
        eventNew.setEventStartTime(startEvent.getText());
        eventNew.setEndTimeOfTheEvent(comboBoxEndEvent.getValue());
        if(comboBoxSeatingArrangements.getValue() != null) {
            SeatingArrangements seatingArrangements = new SeatingArrangements(comboBoxSeatingArrangements.getValue()
                    , company.getNameCompany(), null, Integer.valueOf(textFiledNumberOfParticipants.getText()));
            SetSeatingArrangements setSeatingArrangements = new SetSeatingArrangementsMySQL();
            eventNew.setIdSeatingArrangements(setSeatingArrangements.setSeatingArrangements(user, company.getNameCompany(), seatingArrangements));
        }
        eventNew.setLastNameCustomer(textFiledLastNameCustomer.getText());
        eventNew.setFirstNameCustomer(textFileFirstNameCustomer.getText());
        eventNew.setIdDay(day.getId());
        eventNew.setNameRoom(day.getRoom());
        eventNew.setNote(textAreaNote.getText());

        SetEventFormat setEventFormat = new SetEventFormatMySQL();
        Integer idEvent = setEventFormat.setEventFormat(user, eventNew);

        eventNew.setId(idEvent);
        return eventNew;
    }

    private String getDocument() throws URISyntaxException, IOException {
        
        File file = null;
        Document document = null;
        if (lang.equals("Русский")){
            file = new File(Objects.requireNonNull(getClass()
                .getResource("/ru/greatlarder/technicalassistant/html/letter_application_ru.html")).toURI());
            document = Jsoup.parse(file);
        }
        if(lang.equals("English")) {
            file = new File(Objects.requireNonNull(getClass()
                .getResource("/ru/greatlarder/technicalassistant/html/letter_application_en.html")).toURI());
            document = Jsoup.parse(file);
        }
        
        if(document != null) {
            Element date_event = document.getElementById("p_date_event");
            Element time_start = document.getElementById("p_time_start");
            Element room = document.getElementById("p_room");
            Element event = document.getElementById("p_event");
            Element customer = document.getElementById("p_customer");
            Element seating_arrangements = document.getElementById("p_seating_arrangements");
            Element number_of_participants = document.getElementById("p_number_of_participants");
            Element equipment = document.getElementById("p_equipment");
            Element note = document.getElementById("p_note");
            
            assert date_event != null;
            date_event.text(day.getDate().toString());
            assert time_start != null;
            time_start.text(startEvent.getText());
            assert room != null;
            room.text(day.getRoom());
            assert event != null;
            event.text(comboBoxNameEvent.getValue());
            assert customer != null;
            customer.text(textFiledLastNameCustomer.getText() + " " + textFileFirstNameCustomer.getText());
            assert seating_arrangements != null;
            seating_arrangements.text(comboBoxSeatingArrangements.getValue());
            assert number_of_participants != null;
            number_of_participants.text(textFiledNumberOfParticipants.getText());
            assert equipment != null;
            for (Equipment equipment1 : getListNameEquipment()){
                equipment.text(equipment1.getName());
            }
            assert note != null;
            note.text(textAreaNote.getText());
        }
        assert document != null;
        return document.html();
    }
}
