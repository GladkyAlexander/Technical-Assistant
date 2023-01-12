package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.*;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CartContactRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.CartContactRepositoryImpl;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.HTMLLetter;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.util.*;

public class FragmentAddEvent implements ObserverLang {
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
    @FXML public Button btnSaveEvent;
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
    @FXML public Label listEquipment;
    @FXML public ComboBox<String> comboBoxChoiceTo;
    @FXML public Button btnToChange;
    
    Events events;
    private String time;
    SeatingArrangementsRepositoryMySQL seatingArrangementsRepositoryMySQL = new SeatingArrangementsRepositoryMySQLImpl();
    ListSeatingArrangementsNameRepositoryMySQL listSeatingArrangementsNameRepositoryMySQL = new ListSeatingArrangementsNameRepositoryMySQLImpl();
    private User user;
    private String lang;
    Language language = new LanguageImpl();
    List<String> timeEnd;
    private Day day;
    private List<ContactCart> contactCarts;
    private HashMap<String, String> hashMap = new HashMap<>();
    private int flag = 0;

    public void loadFragment(User user, Events events, String time, Day day){
        textAreaNote.setWrapText(true);
        this.day = day;
        this.user = user;
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
        
        ListEventNameRepositoryMySQL listEventNameRepositoryMySQL = new ListEventNameRepositoryMySQLImpl();
        comboBoxNameEvent.setItems(FXCollections.observableArrayList(listEventNameRepositoryMySQL.getEventsNameList(user, user.getCompanyAffiliation())));
        startEvent.setText(time);
        comboBoxSeatingArrangements.setItems(FXCollections.observableList(listSeatingArrangementsNameRepositoryMySQL
                .getSeatingArrangementsNameList(user, user.getCompanyAffiliation())));
        comboBoxEndEvent.setItems(FXCollections.observableArrayList(getListTimeEnd()));
        comboBoxChoiceTo.setItems(getTo());
    }

    private List<String> getListTimeEnd() {
        timeEnd = new ArrayList<>();
        
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("8:00", day.getA800());
        treeMap.put("8:15", day.getA815());
        treeMap.put("8:30", day.getA830());
        treeMap.put("8:45", day.getA845());
        treeMap.put("9:00", day.getA900());
        treeMap.put("9:15", day.getA915());
        treeMap.put("9:30", day.getA930());
        treeMap.put("9:45", day.getA945());
        treeMap.put("10:00", day.getA1000());
        treeMap.put("10:15", day.getA1015());
        treeMap.put("10:30", day.getA1030());
        treeMap.put("10:45", day.getA1045());
        treeMap.put("11:00", day.getA1100());
        treeMap.put("11:15", day.getA1115());
        treeMap.put("11:30", day.getA1130());
        treeMap.put("11:45", day.getA1145());
        treeMap.put("12:00", day.getA1200());
        treeMap.put("12:15", day.getA1215());
        treeMap.put("12:30", day.getA1230());
        treeMap.put("12:45", day.getA1245());
        treeMap.put("13:00", day.getA1300());
        treeMap.put("13:15", day.getA1315());
        treeMap.put("13:30", day.getA1330());
        treeMap.put("13:45", day.getA1345());
        treeMap.put("14:00", day.getA1400());
        treeMap.put("14:15", day.getA1415());
        treeMap.put("14:30", day.getA1430());
        treeMap.put("14:45", day.getA1445());
        treeMap.put("15:00", day.getA1500());
        treeMap.put("15:15", day.getA1515());
        treeMap.put("15:30", day.getA1530());
        treeMap.put("15:45", day.getA1545());
        treeMap.put("16:00", day.getA1600());
        treeMap.put("16:15", day.getA1615());
        treeMap.put("16:30", day.getA1630());
        treeMap.put("16:45", day.getA1645());
        treeMap.put("17:00", day.getA1700());
        treeMap.put("17:15", day.getA1715());
        treeMap.put("17:30", day.getA1730());
        treeMap.put("17:45", day.getA1745());
        treeMap.put("18:00", day.getA1800());
        treeMap.put("18:15", day.getA1815());
        treeMap.put("18:30", day.getA1830());
        treeMap.put("18:45", day.getA1845());
        treeMap.put("19:00", day.getA1900());
        treeMap.put("19:15", day.getA1915());
        treeMap.put("19:30", day.getA1930());
        treeMap.put("19:45", day.getA1945());
        treeMap.put("20:00", day.getA2000());
      
        String fl = null;
     
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()){
            if (entry.getKey().equals(time)){
                fl = entry.getKey();
                timeEnd.add(fl);
            }
            if (fl != null){
                if (entry.getValue() == 0) {
                    timeEnd.add(entry.getKey());
                } else break;
            }
        }
        
        return timeEnd;
    }

    public void saveEvent(MouseEvent mouseEvent) {
        Events events = new Events();
    
        events.setNameEvent(comboBoxNameEvent.getValue());
        events.setUrlImageEvent(getNameImg(comboBoxNameEvent.getValue()));
        events.setEventStartTime(startEvent.getText());
        events.setEndTimeOfTheEvent(comboBoxEndEvent.getValue());
        if(comboBoxSeatingArrangements.getValue() != null) {
        
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameCompany(user.getCompanyAffiliation());
            seatingArrangements.setNameSeatingArrangements(comboBoxSeatingArrangements.getValue());
            seatingArrangements.setNumberOfParticipants(Integer.valueOf(textFiledNumberOfParticipants.getText()));
        
            Integer id = seatingArrangementsRepositoryMySQL.setSeatingArrangements(user, seatingArrangements);
            if(id != null) {
                events.setIdSeatingArrangements(id);
            }
        }
        events.setLastNameCustomer(textFiledLastNameCustomer.getText());
        events.setFirstNameCustomer(textFileFirstNameCustomer.getText());
        events.setIdDay(day.getId());
        events.setNameRoom(day.getRoom());
        events.setNote(textAreaNote.getText());
    
        EventRepositoryMySQL eventRepositoryMySQL = new EventRepositoryMySQLImpl();
    
        Integer idEvent = eventRepositoryMySQL.setEvent(user, events);
        events.setId(idEvent);
    
        DaysRepositoryMySQL daysRepositoryMySQL = new DaysRepositoryMySQLImpl();
    
        daysRepositoryMySQL.updateDay(user, getUpdateDay(events, this.day));
    
        gridPaneAddEvent.setStyle(StyleSRC.STYLE_EXCELLENT);
        Stage stage = (Stage) gridPaneAddEvent.getScene().getWindow();
        stage.close();
    }
    private void loadingIncomingData(){
        
        loadVisibleButton(false);
        
        nameEvent.setText(events.getNameEvent());
        startEvent.setText(events.getEventStartTime());
        endEvent.setText(events.getEndTimeOfTheEvent());
        imgNameEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/" + events.getUrlImageEvent()))));
        comboBoxEndEvent.setVisible(false);
        comboBoxEndEvent.setManaged(false);
        comboBoxNameEvent.setVisible(false);
        comboBoxNameEvent.setManaged(false);
        comboBoxSeatingArrangements.setDisable(true);
        btnSaveEvent.setVisible(false);
        btnSaveEvent.setManaged(false);
        if(events.getIdSeatingArrangements() != 0) {
            SeatingArrangements seatingArrangements = seatingArrangementsRepositoryMySQL.getSeatingArrangementsById(user
                    , events.getIdSeatingArrangements());
            comboBoxSeatingArrangements.setPromptText(seatingArrangements.getNameSeatingArrangements());
            textFiledNumberOfParticipants.setText(seatingArrangements.getNumberOfParticipants().toString());
        }
        textFiledLastNameCustomer.setText(events.getLastNameCustomer());
        textFileFirstNameCustomer.setText(events.getFirstNameCustomer());
        textAreaNote.setText(events.getNote());

    }
    
    private void loadVisibleButton(boolean b) {
        btnSaveEvent.setVisible(b);
        btnSaveEvent.setManaged(b);
        btnSaveAndSend.setVisible(b);
        btnSaveAndSend.setManaged(b);
    
            btnToChange.setVisible(!b);
            btnToChange.setManaged(!b);
       
    }
    
    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
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
        btnSaveEvent.setText(language.SAVE(lang));
        labelTo.setText(language.TO(lang));
        labelTopic.setText(language.TOPIC(lang));
        nameEvent.setText(language.EVENT(lang));
        btnSaveAndSend.setText(language.SAVE_AND_SEND(lang));
        btnToChange.setText(language.CHANGE_DATA(lang));
    }

    private Day getUpdateDay(Events events, Day current){
        Day nDey = current;
        List<String> gapSheet = new ArrayList<>();
        for (String s : timeEnd){
            if(s.equals(events.getEndTimeOfTheEvent())){
                gapSheet.add(s);
                break;
            }
            gapSheet.add(s);
        }
        for (String s : gapSheet){
            getDayUpdate(nDey, s, events);
        }
        return nDey;
    }
    private void getDayUpdate(Day d, String s, Events e){
        
        if(s.equals("8:00")){d.setA800(e.getId());}
        if(s.equals("8:15")){d.setA815(e.getId());}
        if(s.equals("8:30")){d.setA830(e.getId());}
        if(s.equals("8:45")){d.setA845(e.getId());}
        if(s.equals("9:00")){d.setA900(e.getId());}
        if(s.equals("9:15")){d.setA915(e.getId());}
        if(s.equals("9:30")){d.setA930(e.getId());}
        if(s.equals("9:45")){d.setA945(e.getId());}
        if(s.equals("10:00")){d.setA1000(e.getId());}
        if(s.equals("10:15")){d.setA1015(e.getId());}
        if(s.equals("10:30")){d.setA1030(e.getId());}
        if(s.equals("10:45")){d.setA1045(e.getId());}
        if(s.equals("11:00")){d.setA1100(e.getId());}
        if(s.equals("11:15")){d.setA1115(e.getId());}
        if(s.equals("11:30")){d.setA1130(e.getId());}
        if(s.equals("11:45")){d.setA1145(e.getId());}
        if(s.equals("12:00")){d.setA1200(e.getId());}
        if(s.equals("12:15")){d.setA1215(e.getId());}
        if(s.equals("12:30")){d.setA1230(e.getId());}
        if(s.equals("12:45")){d.setA1245(e.getId());}
        if(s.equals("13:00")){d.setA1300(e.getId());}
        if(s.equals("13:15")){d.setA1315(e.getId());}
        if(s.equals("13:30")){d.setA1330(e.getId());}
        if(s.equals("13:45")){d.setA1345(e.getId());}
        if(s.equals("14:00")){d.setA1400(e.getId());}
        if(s.equals("14:15")){d.setA1415(e.getId());}
        if(s.equals("14:30")){d.setA1430(e.getId());}
        if(s.equals("14:45")){d.setA1445(e.getId());}
        if(s.equals("15:00")){d.setA1500(e.getId());}
        if(s.equals("15:15")){d.setA1515(e.getId());}
        if(s.equals("15:30")){d.setA1530(e.getId());}
        if(s.equals("15:45")){d.setA1545(e.getId());}
        if(s.equals("16:00")){d.setA1600(e.getId());}
        if(s.equals("16:15")){d.setA1615(e.getId());}
        if(s.equals("16:30")){d.setA1630(e.getId());}
        if(s.equals("16:45")){d.setA1645(e.getId());}
        if(s.equals("17:00")){d.setA1700(e.getId());}
        if(s.equals("17:15")){d.setA1715(e.getId());}
        if(s.equals("17:30")){d.setA1730(e.getId());}
        if(s.equals("17:45")){d.setA1745(e.getId());}
        if(s.equals("18:00")){d.setA1800(e.getId());}
        if(s.equals("18:15")){d.setA1815(e.getId());}
        if(s.equals("18:30")){d.setA1830(e.getId());}
        if(s.equals("18:45")){d.setA1845(e.getId());}
        if(s.equals("19:00")){d.setA1900(e.getId());}
        if(s.equals("19:15")){d.setA1915(e.getId());}
        if(s.equals("19:30")){d.setA1930(e.getId());}
        if(s.equals("19:45")){d.setA1945(e.getId());}
        if(s.equals("20:00")){d.setA2000(e.getId());}
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

    public void saveEndSendLetter(MouseEvent mouseEvent) {
        if(flag == 0) {
            save();
        }
        if (flag == 1){
            change();
        }
    }
    
    private void save(){
        Events eventNew = new Events();
    
        eventNew.setNameEvent(comboBoxNameEvent.getValue());
        eventNew.setUrlImageEvent(getNameImg(comboBoxNameEvent.getValue()));
        eventNew.setEventStartTime(startEvent.getText());
        eventNew.setEndTimeOfTheEvent(comboBoxEndEvent.getValue());
        if(comboBoxSeatingArrangements.getValue() != null) {
        
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameCompany(user.getCompanyAffiliation());
            seatingArrangements.setNameSeatingArrangements(comboBoxSeatingArrangements.getValue());
            seatingArrangements.setNumberOfParticipants(Integer.valueOf(textFiledNumberOfParticipants.getText()));
        
            Integer id = seatingArrangementsRepositoryMySQL.setSeatingArrangements(user, seatingArrangements);
            if(id != null) {
                eventNew.setIdSeatingArrangements(id);
            }
        }
        eventNew.setLastNameCustomer(textFiledLastNameCustomer.getText());
        eventNew.setFirstNameCustomer(textFileFirstNameCustomer.getText());
        eventNew.setIdDay(day.getId());
        eventNew.setNameRoom(day.getRoom());
        eventNew.setNote(textAreaNote.getText());
    
        EventRepositoryMySQL eventRepositoryMySQL = new EventRepositoryMySQLImpl();
    
        Integer idEvent = eventRepositoryMySQL.setEvent(user, eventNew);
        eventNew.setId(idEvent);
    
        DaysRepositoryMySQL daysRepositoryMySQL = new DaysRepositoryMySQLImpl();
    
        daysRepositoryMySQL.updateDay(user, getUpdateDay(eventNew, this.day));
    
        gridPaneAddEvent.setStyle(StyleSRC.STYLE_EXCELLENT);
    
        Letter letter = new Letter();
        letter.setNameEvent(comboBoxNameEvent.getValue());
        letter.setTo(hashMap.get(textFiledTo.getText()));
        letter.setTopic(textFiledTopic.getText());
        letter.setDateStart(day.getDate().toString());
        letter.setTimeStart(startEvent.getText());
        letter.setSeatingArrangements(comboBoxSeatingArrangements.getValue());
        letter.setNumberOfParticipants(textFiledNumberOfParticipants.getText());
        letter.setCustomer(textFiledLastNameCustomer.getText() + " " + textFileFirstNameCustomer.getText());
        letter.setNote(textAreaNote.getText());
        letter.setRoom(day.getRoom());
        letter.setEquipmentList(new ArrayList<>());
        HTMLLetter htmlLetter = new HTMLLetter(user);
        if (htmlLetter.sendLetter(letter) != null) {
            Stage stage = (Stage) gridPaneAddEvent.getScene().getWindow();
            stage.close();
        } else gridPaneAddEvent.setStyle(StyleSRC.STYLE_DANGER);
    }
    private void change(){
        events.setNameEvent(comboBoxNameEvent.getValue());
        events.setUrlImageEvent(getNameImg(comboBoxNameEvent.getValue()));
        events.setEventStartTime(startEvent.getText());
        events.setEndTimeOfTheEvent(comboBoxEndEvent.getValue());
        if(comboBoxSeatingArrangements.getValue() != null) {
        
            SeatingArrangements seatingArrangements = new SeatingArrangements();
            seatingArrangements.setNameCompany(user.getCompanyAffiliation());
            seatingArrangements.setNameSeatingArrangements(comboBoxSeatingArrangements.getValue());
            seatingArrangements.setNumberOfParticipants(Integer.valueOf(textFiledNumberOfParticipants.getText()));
        
            Integer id = seatingArrangementsRepositoryMySQL.setSeatingArrangements(user, seatingArrangements);
            if(id != null) {
                events.setIdSeatingArrangements(id);
            }
        }
        events.setLastNameCustomer(textFiledLastNameCustomer.getText());
        events.setFirstNameCustomer(textFileFirstNameCustomer.getText());
        events.setIdDay(day.getId());
        events.setNameRoom(day.getRoom());
        events.setNote(textAreaNote.getText());
    
        EventRepositoryMySQL eventRepositoryMySQL = new EventRepositoryMySQLImpl();
    
        Integer idEvent = eventRepositoryMySQL.setEvent(user, events);
        events.setId(idEvent);
    
        DaysRepositoryMySQL daysRepositoryMySQL = new DaysRepositoryMySQLImpl();
    
        daysRepositoryMySQL.updateDay(user, getUpdateDay(events, this.day));
    
        gridPaneAddEvent.setStyle(StyleSRC.STYLE_EXCELLENT);
    
        Letter letterChange = new Letter();
        letterChange.setNameEvent(comboBoxNameEvent.getValue());
        letterChange.setTo(hashMap.get(textFiledTo.getText()));
        letterChange.setTopic(textFiledTopic.getText());
        letterChange.setDateStart(day.getDate().toString());
        letterChange.setTimeStart(startEvent.getText());
        letterChange.setSeatingArrangements(comboBoxSeatingArrangements.getValue());
        letterChange.setNumberOfParticipants(textFiledNumberOfParticipants.getText());
        letterChange.setCustomer(textFiledLastNameCustomer.getText() + " " + textFileFirstNameCustomer.getText());
        letterChange.setNote(textAreaNote.getText());
        letterChange.setRoom(day.getRoom());
        letterChange.setEquipmentList(new ArrayList<>());
        HTMLLetter htmlLetter = new HTMLLetter(user);
        if (htmlLetter.sendLetter(letterChange) != null) {
            Stage stage = (Stage) gridPaneAddEvent.getScene().getWindow();
            stage.close();
        } else gridPaneAddEvent.setStyle(StyleSRC.STYLE_DANGER);
    }
    
    public void valueInput(ActionEvent actionEvent) {
        textFiledTo.setText(comboBoxChoiceTo.getValue());
    }
    private ObservableList<String> getTo(){
        CartContactRepository contactRepository = new CartContactRepositoryImpl();
        this.contactCarts = contactRepository.getContactCardByUser(user.getId());
        List<String> list = new ArrayList<>();
        for (ContactCart c : this.contactCarts){
            list.add(c.getLastName() + " " + c.getFirstName());
            hashMap.put(c.getLastName() + " " + c.getFirstName(), c.getMail());
        }
        return FXCollections.observableList(list);
    }
    
    public void openChange(MouseEvent mouseEvent) {
        this.flag = 1;
        loadNewData();
    }
}
