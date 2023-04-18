package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddEvent;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetEventFormat;
import ru.greatlarder.technicalassistant.services.database.mysql.event_format.GetEventFormatByIdMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ItemEventMin implements Initializable {
    @FXML
    public Label lastNameMin;
    @FXML public Label labelNameEvent;
    @FXML public Label firstNameMin;
    @FXML public VBox vBox;
    User user;
    Integer idEvents;
    EventFormat events;
    String time;
    Day day;
    Company company;
    String lang;
    Language language = new LanguageImpl();

    public void loadFragment(Integer idEvents, String time, Day day) {
        this.day = day;
        this.time = time;
        this.idEvents = idEvents;
        if (idEvents != 0) {
            Task<EventFormat> task = new Task<>() {
                @Override
                protected EventFormat call() {
                    GetEventFormat getEventFormat = new GetEventFormatByIdMySQL();
                    return getEventFormat.getEventFormat(user, company.getNameCompany(), String.valueOf(idEvents));
                }
            };
            ProgressIndicator progressIndicator = new ProgressIndicator(task.getProgress());
            progressIndicator.visibleProperty();
            task.setOnSucceeded((succeededEvent) -> {
                progressIndicator.visibleProperty().bind(task.runningProperty());
                this.events = task.getValue();
                vBox.setStyle(StyleSRC.STYLE_TRUE);
                lastNameMin.setText(events.getLastNameCustomer());
                firstNameMin.setText(events.getFirstNameCustomer());
                labelNameEvent.setText(events.getNameEventFormat());
            });
            Platform.runLater(() -> {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(task);
                executorService.shutdown();
            });
        } else {
            vBox.setStyle(StyleSRC.STYLE_FALSE);
        }
    }

    public void openEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_event.fxml"));
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            FragmentAddEvent controller = loader.getController();
            controller.loadFragment(events, time, day);
            stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png")))));
            if (events == null) {
                stage.setTitle(language.ADD_AN_EVENT(lang));
            } else {
                stage.setTitle(events.getNameEventFormat());
            }
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }
}
