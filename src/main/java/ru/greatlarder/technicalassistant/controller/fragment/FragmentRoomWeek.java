package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemDay;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.DaysRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.DaysRepositoryMySQLImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static javafx.geometry.Pos.CENTER;

public class FragmentRoomWeek implements ObserverLang, ObserverUser {
    @FXML
    public TabPane tabPaneWeek;
    private Room room;
    LocalDate date = LocalDate.now();
    private User user;
    DaysRepositoryMySQL daysRepositoryMySql = new DaysRepositoryMySQLImpl();
    HashMap<Tab, Day> hashMap = new HashMap<>();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    Language language = new LanguageImpl();
    String lang;

    public void loadWeek(User user, Room room) {
        this.room = room;
        this.user = user;
        getSheetWeek(daysRepositoryMySql.getDaysForRoom(user, room.getNameRoom()));
    }

    private void getSheetWeek(List<Day> days) {
        List<Day> result = new ArrayList<>();
        HashMap<LocalDate, Day> hashMap = new HashMap<>();
        hashMap.put(date, null);
        hashMap.put(date.plusDays(1), null);
        hashMap.put(date.plusDays(2), null);
        hashMap.put(date.plusDays(3), null);
        hashMap.put(date.plusDays(4), null);
        hashMap.put(date.plusDays(5), null);
        hashMap.put(date.plusDays(6), null);

        if (days.size() != 0) {
            for (Day day : days){
                if (hashMap.containsKey(day.getDate())){
                    hashMap.replace(day.getDate(), day);
                }
            }

            for (Map.Entry<LocalDate, Day> set : hashMap.entrySet()){
                if (set.getValue() == null){
                    hashMap.replace(set.getKey(), getDay(set.getKey()));
                }
            }

            for (Map.Entry<LocalDate, Day> set : hashMap.entrySet()){
                result.add(set.getValue());
            }

        } else {
            for (int i = 0; i < 7; i++) {
                result.add(getDay(date.plusDays(i)));
            }
        }
        createTableDay(result);
    }

    private void createTableDay(List<Day> days) {

        days.sort(Comparator.comparing(Day::getDate));

        for (Day day : days) {
            Tab tab = new Tab();
            tab.setText(day.getDate() + " : " + language.getDayWeek(day.getDate().getDayOfWeek().toString(), lang));
            tab.setOnSelectionChanged(new TabEventHandler());
            if(day.getDate().equals(LocalDate.now())) {
                tab.setContent(getTab(day));
            }
            hashMap.put(tab, day);
            tabPaneWeek.getTabs().add(tab);

        }
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }

    private class TabEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event event) {
            Tab tab = (Tab) event.getSource();
            Task<Node> task = new Task<Node>(){
                @Override
                protected Node call() throws Exception {
                    Day day = hashMap.get(tab);
                    return getTab(day);
                }
            };
            ProgressIndicator progressBar = new ProgressIndicator(task.getProgress());
            HBox hBox = new HBox();
            hBox.setAlignment(CENTER);
            hBox.getChildren().add(progressBar);
            tab.setContent(hBox);
            task.setOnSucceeded((succeededEvent)->{
                progressBar.visibleProperty().bind(task.runningProperty());
                tab.setContent(task.getValue());
            });
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        }
    }

    private Node getTab(Day day) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_day.fxml"));
        Node node = null;
        try {
            node = loader.load();
            handlerLang.registerObserverLang(loader.getController());
            ItemDay controller = loader.getController();
            controller.updateLang(new DataLang(lang));
            controller.loadFragment(user, day);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return node;
    }

    private Day getDay(LocalDate date1) {
        Day day = new Day();
        day.setRoom(room.getNameRoom());
        day.setDate(date1);
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
        DaysRepositoryMySQL daysRepositoryMySql = new DaysRepositoryMySQLImpl();
        daysRepositoryMySql.setDaysForRoom(user, day);

        day.setId(daysRepositoryMySql.getDayForName(user, day).getId());

        return day;
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }
}
