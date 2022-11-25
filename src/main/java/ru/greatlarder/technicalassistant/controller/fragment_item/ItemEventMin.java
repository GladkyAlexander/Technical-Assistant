package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddEvent;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.Events;
import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.EventRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.SeatingArrangementsRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.EventRepositoryMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.SeatingArrangementsRepositoryMySQLImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemEventMin implements ObserverLang {
    @FXML public GridPane gridPaneItemEventMin;
    @FXML public Label startEventMin;
    @FXML public Label endEventMin;
    @FXML public Label lastNameMin;
    @FXML public Label firstNameMin;
    @FXML public Label seatingArrangements;
    @FXML public Label numberOfParticipantsMin;
    @FXML public Label labelNameEvent;
    @FXML public ImageView imgLogoNameEvent;
    private String lang;
    private User user;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    private Integer idEvents;
    private Events events;
    private String time;
    private Day day;

    public void loadFragment(User user, Integer idEvents, String time, Day day){
        this.day = day;
        this.time = time;
        this.user = user;
        this.idEvents = idEvents;
        if(idEvents != 0) {
            EventRepositoryMySQL eventRepositoryMySQL = new EventRepositoryMySQLImpl();
            this.events = eventRepositoryMySQL.getEventsForId(user, this.idEvents);
            gridPaneItemEventMin.setStyle(StyleSRC.STYLE_TRUE);
            loadEvents();
        } else {
            gridPaneItemEventMin.setStyle(StyleSRC.STYLE_FALSE);
        }
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }
    private void loadEvents(){
        startEventMin.setText(events.getEventStartTime());
        endEventMin.setText(events.getEndTimeOfTheEvent());
        lastNameMin.setText(events.getLastNameCustomer());
        firstNameMin.setText(events.getFirstNameCustomer());
        labelNameEvent.setText(events.getNameEvent());
        if(events.getUrlImageEvent() != null){
            imgLogoNameEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/" + events.getUrlImageEvent()))));
        }
        if (events.getIdSeatingArrangements() != 0) {
            getSeating();
        } else {
            seatingArrangements.setText(events.getIdSeatingArrangements().toString());
            numberOfParticipantsMin.setText("0");
        }
    }

    private void getSeating() {
       Task<SeatingArrangements> task = new Task<SeatingArrangements>() {
           @Override
           protected SeatingArrangements call() throws Exception {
               SeatingArrangementsRepositoryMySQL seatingArrangementsRepositoryMySQL = new SeatingArrangementsRepositoryMySQLImpl();
               return seatingArrangementsRepositoryMySQL.getSeatingArrangementsByName(user, events.getIdSeatingArrangements());
           }
       };
       task.setOnSucceeded((succeededEvent)->{
               seatingArrangements.setText(task.getValue().getNameSeatingArrangements());
               numberOfParticipantsMin.setText(String.valueOf(task.getValue().getNumberOfParticipants()));
       });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

    public void openEvent(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_event.fxml"));
        Scene scene = null;
        try {
            Stage stage = new Stage();
            scene = new Scene(loader.load());
            handlerLang.registerObserverLang(loader.getController());

            FragmentAddEvent controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.loadFragment(user, events, time, day);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
