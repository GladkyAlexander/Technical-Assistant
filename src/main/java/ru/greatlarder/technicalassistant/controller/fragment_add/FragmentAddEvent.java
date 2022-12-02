package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
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
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.mail.HTMLLetter;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    Events events;
    private String time;
    SeatingArrangementsRepositoryMySQL seatingArrangementsRepositoryMySQL = new SeatingArrangementsRepositoryMySQLImpl();
    ListSeatingArrangementsNameRepositoryMySQL listSeatingArrangementsNameRepositoryMySQL = new ListSeatingArrangementsNameRepositoryMySQLImpl();
    private User user;
    private String lang;
    Language language = new LanguageImpl();
    List<String> timeEnd;
    private Day day;

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
        ListEventNameRepositoryMySQL listEventNameRepositoryMySQL = new ListEventNameRepositoryMySQLImpl();
        comboBoxNameEvent.setItems(FXCollections.observableArrayList(listEventNameRepositoryMySQL.getEventsNameList(user, user.getCompanyAffiliation())));
        startEvent.setText(time);
        comboBoxSeatingArrangements.setItems(FXCollections.observableList(listSeatingArrangementsNameRepositoryMySQL
                .getSeatingArrangementsNameList(user, user.getCompanyAffiliation())));
        comboBoxEndEvent.setItems(FXCollections.observableArrayList(getListTimeEnd()));
    }

    private List<String> getListTimeEnd() {
        timeEnd = new ArrayList<String>();
        List<String> timeDay = new ArrayList<String>();
        timeDay.add("8:00");
        timeDay.add("8:15");
        timeDay.add("8:30");
        timeDay.add("8:45");
        timeDay.add("9:00");
        timeDay.add("9:15");
        timeDay.add("9:30");
        timeDay.add("9:45");
        timeDay.add("10:00");
        timeDay.add("10:15");
        timeDay.add("10:30");
        timeDay.add("10:45");
        timeDay.add("11:00");
        timeDay.add("11:15");
        timeDay.add("11:30");
        timeDay.add("11:45");
        timeDay.add("12:00");
        timeDay.add("12:15");
        timeDay.add("12:30");
        timeDay.add("12:45");
        timeDay.add("13:00");
        timeDay.add("13:15");
        timeDay.add("13:30");
        timeDay.add("13:45");
        timeDay.add("14:00");
        timeDay.add("14:15");
        timeDay.add("14:30");
        timeDay.add("14:45");
        timeDay.add("15:00");
        timeDay.add("15:15");
        timeDay.add("15:30");
        timeDay.add("15:45");
        timeDay.add("16:00");
        timeDay.add("16:15");
        timeDay.add("16:30");
        timeDay.add("16:45");
        timeDay.add("17:00");
        timeDay.add("17:15");
        timeDay.add("17:30");
        timeDay.add("17:45");
        timeDay.add("18:00");
        timeDay.add("18:15");
        timeDay.add("18:30");
        timeDay.add("18:45");
        timeDay.add("19:00");
        timeDay.add("19:15");
        timeDay.add("19:30");
        timeDay.add("19:45");
        timeDay.add("20:00");

        for (String s : timeDay){
            if(s.equals(time)){
                timeEnd = timeDay.subList(timeDay.indexOf(s), timeDay.size());
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

            SeatingArrangementsRepositoryMySQL seatingArrangementsRepositoryMySQL = new SeatingArrangementsRepositoryMySQLImpl();

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
        btnSaveAndSend.setText(language.SAVE_AND_SEND(lang));
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
        Letter events = new Letter();
        events.setNameEvent(comboBoxNameEvent.getValue());
        events.setTo(textFiledTo.getText());
        events.setTopic(textFiledTopic.getText());
        events.setDateStart(day.getDate().toString());
        events.setTimeStart(startEvent.getText());
        events.setSeatingArrangements(comboBoxSeatingArrangements.getValue());
        events.setNumberOfParticipants(textFiledNumberOfParticipants.getText());
        events.setCustomer(textFiledLastNameCustomer.getText() + " " + textFileFirstNameCustomer.getText());
        events.setNote(textAreaNote.getText());
        HTMLLetter htmlLetter = new HTMLLetter(user);
        htmlLetter.sendLetter(events);
    }
}
