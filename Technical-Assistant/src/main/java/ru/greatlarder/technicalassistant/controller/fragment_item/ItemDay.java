package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentRoomWeek;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetDay;
import ru.greatlarder.technicalassistant.services.database.SetDay;
import ru.greatlarder.technicalassistant.services.database.mysql.day.DayByLocalDateAndRoomMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.day.SetDayMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartReceptionController;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomWorkloadMySQL;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class ItemDay implements Initializable {

    @FXML public GridPane gridPaneItemDay;
    @FXML public Pane p800;
    @FXML public Pane p815;
    @FXML public Pane p830;
    @FXML public Pane p845;
    @FXML public Pane p900;
    @FXML public Pane p915;
    @FXML public Pane p930;
    @FXML public Pane p945;
    @FXML public Pane p1000;
    @FXML public Pane p1015;
    @FXML public Pane p1030;
    @FXML public Pane p1045;
    @FXML public Pane p1100;
    @FXML public Pane p1115;
    @FXML public Pane p1130;
    @FXML public Pane p1145;
    @FXML public Pane p1200;
    @FXML public Pane p1215;
    @FXML public Pane p1230;
    @FXML public Pane p1245;
    @FXML public Pane p1300;
    @FXML public Pane p1315;
    @FXML public Pane p1330;
    @FXML public Pane p1345;
    @FXML public Pane p1400;
    @FXML public Pane p1415;
    @FXML public Pane p1430;
    @FXML public Pane p1445;
    @FXML public Pane p1500;
    @FXML public Pane p1515;
    @FXML public Pane p1530;
    @FXML public Pane p1545;
    @FXML public Pane p1600;
    @FXML public Pane p1615;
    @FXML public Pane p1630;
    @FXML public Pane p1645;
    @FXML public Pane p1700;
    @FXML public Pane p1715;
    @FXML public Pane p1730;
    @FXML public Pane p1745;
    @FXML public Pane p1800;
    @FXML public Pane p1815;
    @FXML public Pane p1830;
    @FXML public Pane p1845;
    @FXML public Pane p1900;
    @FXML public Pane p1915;
    @FXML public Pane p1930;
    @FXML public Pane p1945;
    @FXML public Pane p2000;
    @FXML public BorderPane borderPane;
    HashMap<Pane, Integer> hashMap = new HashMap<Pane, Integer>();
    HashMap<Pane, String> paneHashMap = new HashMap<Pane, String>();
    List<Pane> panes = new ArrayList<>();
    Day day;
    String roomName;
    LocalDate date;
    User user;
    String lang;
    Company company;


    public void loadFragment(LocalDate localDate, String roomName){
        this.date = localDate;
        this.roomName = roomName;
        setActionPane();
        loadDop(localDate);
    }
    private void setActionPane(){

        Task<Day> task = new Task<>() {
            @Override
            protected Day call() {
                GetDay getDay = new DayByLocalDateAndRoomMySQL();
                return getDay.getDay(user, date, roomName);
            }
        };

        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setTop(progressBar);

        task.setOnSucceeded((succeededEvent) -> {
            Day team = task.getValue();
            if (team == null) {
                this.day = getDay();
            } else this.day = team;
            GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.getChildren().remove(progressBar);
            loadPage(day);
        });
        Platform.runLater(()->{
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });

    }
    private void loadPage(Day day1){
        p800.getChildren().add(getGridPaneEvent(day1.getA800(), "8:00"));
        p815.getChildren().add(getGridPaneEvent(day1.getA815(), "8:15"));
        p830.getChildren().add(getGridPaneEvent(day1.getA830(), "8:30"));
        p845.getChildren().add(getGridPaneEvent(day1.getA845(), "8:45"));
        p900.getChildren().add(getGridPaneEvent(day1.getA900(), "9:00"));
        p915.getChildren().add(getGridPaneEvent(day1.getA915(), "9:15"));
        p930.getChildren().add(getGridPaneEvent(day1.getA930(), "9:30"));
        p945.getChildren().add(getGridPaneEvent(day1.getA945(), "9:45"));
        p1000.getChildren().add(getGridPaneEvent(day1.getA1000(), "10:00"));
        p1015.getChildren().add(getGridPaneEvent(day1.getA1015(), "10:15"));
        p1030.getChildren().add(getGridPaneEvent(day1.getA1030(), "10:30"));
        p1045.getChildren().add(getGridPaneEvent(day1.getA1045(), "10:45"));
        p1100.getChildren().add(getGridPaneEvent(day1.getA1100(), "11:00"));
        p1115.getChildren().add(getGridPaneEvent(day1.getA1115(), "11:15"));
        p1130.getChildren().add(getGridPaneEvent(day1.getA1130(), "11:30"));
        p1145.getChildren().add(getGridPaneEvent(day1.getA1145(), "11:45"));
        p1200.getChildren().add(getGridPaneEvent(day1.getA1200(), "12:00"));
        p1215.getChildren().add(getGridPaneEvent(day1.getA1215(), "12:15"));
        p1230.getChildren().add(getGridPaneEvent(day1.getA1230(), "12:30"));
        p1245.getChildren().add(getGridPaneEvent(day1.getA1245(), "12:45"));
        p1300.getChildren().add(getGridPaneEvent(day1.getA1300(), "13:00"));
        p1315.getChildren().add(getGridPaneEvent(day1.getA1315(), "13:15"));
        p1330.getChildren().add(getGridPaneEvent(day1.getA1330(), "13:30"));
        p1345.getChildren().add(getGridPaneEvent(day1.getA1345(), "13:45"));
        p1400.getChildren().add(getGridPaneEvent(day1.getA1400(), "14:00"));
        p1415.getChildren().add(getGridPaneEvent(day1.getA1415(), "14:15"));
        p1430.getChildren().add(getGridPaneEvent(day1.getA1430(), "14:30"));
        p1445.getChildren().add(getGridPaneEvent(day1.getA1445(), "14:45"));
        p1500.getChildren().add(getGridPaneEvent(day1.getA1500(), "15:00"));
        p1515.getChildren().add(getGridPaneEvent(day1.getA1515(), "15:15"));
        p1530.getChildren().add(getGridPaneEvent(day1.getA1530(), "15:30"));
        p1545.getChildren().add(getGridPaneEvent(day1.getA1545(), "15:45"));
        p1600.getChildren().add(getGridPaneEvent(day1.getA1600(), "16:00"));
        p1615.getChildren().add(getGridPaneEvent(day1.getA1615(), "16:15"));
        p1630.getChildren().add(getGridPaneEvent(day1.getA1630(), "16:30"));
        p1645.getChildren().add(getGridPaneEvent(day1.getA1645(), "16:45"));
        p1700.getChildren().add(getGridPaneEvent(day1.getA1700(), "17:00"));
        p1715.getChildren().add(getGridPaneEvent(day1.getA1715(), "17:15"));
        p1730.getChildren().add(getGridPaneEvent(day1.getA1730(), "17:30"));
        p1745.getChildren().add(getGridPaneEvent(day1.getA1745(), "17:45"));
        p1800.getChildren().add(getGridPaneEvent(day1.getA1800(), "18:00"));
        p1815.getChildren().add(getGridPaneEvent(day1.getA1815(), "18:15"));
        p1830.getChildren().add(getGridPaneEvent(day1.getA1830(), "18:30"));
        p1845.getChildren().add(getGridPaneEvent(day1.getA1845(), "18:45"));
        p1900.getChildren().add(getGridPaneEvent(day1.getA1900(), "19:00"));
        p1915.getChildren().add(getGridPaneEvent(day1.getA1915(), "19:15"));
        p1930.getChildren().add(getGridPaneEvent(day1.getA1930(), "19:30"));
        p1945.getChildren().add(getGridPaneEvent(day1.getA1945(), "19:45"));
        p2000.getChildren().add(getGridPaneEvent(day1.getA2000(), "20:00"));
    }

    private Day getDay() {
        Day day = new Day();
        day.setRoom(roomName);
        day.setDate(date);
        day.setA800(0);
        day.setA815(0);
        day.setA830(0);
        day.setA845(0);
        day.setA900(0);
        day.setA915(0);
        day.setA930(0);
        day.setA945(0);
        day.setA1000(0);
        day.setA1015(0);
        day.setA1030(0);
        day.setA1045(0);
        day.setA1100(0);
        day.setA1115(0);
        day.setA1130(0);
        day.setA1145(0);
        day.setA1200(0);
        day.setA1215(0);
        day.setA1230(0);
        day.setA1245(0);
        day.setA1300(0);
        day.setA1315(0);
        day.setA1330(0);
        day.setA1345(0);
        day.setA1400(0);
        day.setA1415(0);
        day.setA1430(0);
        day.setA1445(0);
        day.setA1500(0);
        day.setA1515(0);
        day.setA1530(0);
        day.setA1545(0);
        day.setA1600(0);
        day.setA1615(0);
        day.setA1630(0);
        day.setA1645(0);
        day.setA1700(0);
        day.setA1715(0);
        day.setA1730(0);
        day.setA1745(0);
        day.setA1800(0);
        day.setA1815(0);
        day.setA1830(0);
        day.setA1845(0);
        day.setA1900(0);
        day.setA1915(0);
        day.setA1930(0);
        day.setA1945(0);
        day.setA2000(0);

        SetDay setDay = new SetDayMySQL();

        day.setId(setDay.setDay(user, company, day));

        return day;
    }

    private VBox getGridPaneEvent(Integer idEvent, String time){
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_event_min.fxml"));
        VBox gridPane = null;
        try {
            gridPane = loader.load();
            ItemEventMin controller = loader.getController();
            controller.loadFragment(idEvent, time, day);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert gridPane != null;
        gridPane.prefHeightProperty().bind(p800.heightProperty());
        gridPane.prefWidthProperty().bind(p800.widthProperty());
        return gridPane;
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();

    }
    private void loadDop(LocalDate localDate){
        Task<ListView<Room>> task = new Task<>() {
            @Override
            protected ListView<Room> call() {
                GetListViewRoom listView = new ListViewRoomWorkloadMySQL();
                return listView.getListView(user, company.getNameCompany(), String.valueOf(localDate));
            }
        };

      task.setOnSucceeded((t)->{
            ListView<Room> listView = task.getValue();
            listView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                try {
                    final FXMLLoader loader = new FXMLLoader(getClass()
                            .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentRoomWeek.fxml"));
                    GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());
                    FragmentRoomWeek fragmentRoomWeek = loader.getController();
                    fragmentRoomWeek.loadWeek(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            borderPane.setRight(listView);
         });
        Platform.runLater(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }

}
