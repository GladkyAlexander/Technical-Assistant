package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemDay implements ObserverLang {

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
    HashMap<Pane, Integer> hashMap = new HashMap<Pane, Integer>();
   HashMap<Pane, String> paneHashMap = new HashMap<Pane, String>();
   List<Pane> panes = new ArrayList<>();
    private Day day;
    private User user;
    HandlerLang handlerLang = new HandlerLang();
    String lang;

    public void loadFragment(User user, Day day){
        this.user = user;
        this.day = day;
        setActionPane();
        loadPane();
    }

    private void loadPane() {
        for (Pane pane : panes){
            pane.getChildren().add(getGgidPaneEvent(user, hashMap.get(pane), paneHashMap.get(pane)));
        }
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }
    private void setActionPane(){
        hashMap.put(p800, day.getA800());
        panes.add(p800);
        paneHashMap.put(p800, "8:00");

        hashMap.put(p815, day.getA815());
        panes.add(p815);
        paneHashMap.put(p815, "8:15");

        hashMap.put(p830, day.getA830());
        panes.add(p830);
        paneHashMap.put(p830 ,"8:30");

        hashMap.put(p845, day.getA845());
        panes.add(p845);
        paneHashMap.put(p845,"8:45");

        hashMap.put(p900, day.getA900());
        panes.add(p900);
        paneHashMap.put(p900, "9:00");

        hashMap.put(p915, day.getA915());
        panes.add(p915);
        paneHashMap.put(p915, "9:15");

        hashMap.put(p930, day.getA930());
        panes.add(p930);
        paneHashMap.put(p930, "9:30");

        hashMap.put(p945, day.getA945());
        panes.add(p945);
        paneHashMap.put(p945, "9:45");

        hashMap.put(p1000, day.getA1000());
        panes.add(p1000);
        paneHashMap.put(p1000, "10:00");

        hashMap.put(p1015, day.getA1015());
        panes.add(p1015);
        paneHashMap.put(p1015, "10:15");

        hashMap.put(p1030, day.getA1030());
        panes.add(p1030);
        paneHashMap.put(p1030, "10:30");

        hashMap.put(p1045, day.getA1045());
        panes.add(p1045);
        paneHashMap.put(p1045, "10:45");

        hashMap.put(p1100, day.getA1100());
        panes.add(p1100);
        paneHashMap.put(p1100, "11:00");

        hashMap.put(p1115, day.getA1115());
        panes.add(p1115);
        paneHashMap.put(p1115, "11:15");

        hashMap.put(p1130, day.getA1130());
        panes.add(p1130);
        paneHashMap.put(p1130, "11:30");

        hashMap.put(p1145, day.getA1145());
        panes.add(p1145);
        paneHashMap.put(p1145, "11:45");

        hashMap.put(p1200, day.getA1200());
        panes.add(p1200);
        paneHashMap.put(p1200, "12:00");

        hashMap.put(p1215, day.getA1215());
        panes.add(p1215);
        paneHashMap.put(p1215, "12:15");

        hashMap.put(p1230, day.getA1230());
        panes.add(p1230);
        paneHashMap.put(p1230, "12:30");

        hashMap.put(p1245, day.getA1245());
        panes.add(p1245);
        paneHashMap.put(p1245, "12:45");

        hashMap.put(p1300, day.getA1300());
        panes.add(p1300);
        paneHashMap.put(p1300, "13:00");

        hashMap.put(p1315, day.getA1315());
        panes.add(p1315);
        paneHashMap.put(p1315, "13:15");

        hashMap.put(p1330, day.getA1330());
        panes.add(p1330);
        paneHashMap.put(p1330, "13:30");

        hashMap.put(p1345, day.getA1345());
        panes.add(p1345);
        paneHashMap.put(p1345, "13:45");

        hashMap.put(p1400, day.getA1400());
        panes.add(p1400);
        paneHashMap.put(p1400, "14:00");

        hashMap.put(p1415, day.getA1415());
        panes.add(p1415);
        paneHashMap.put(p1415, "14:15");

        hashMap.put(p1430, day.getA1430());
        panes.add(p1430);
        paneHashMap.put(p1430, "14:30");

        hashMap.put(p1445, day.getA1445());
        panes.add(p1445);
        paneHashMap.put(p1445, "14:45");

        hashMap.put(p1500, day.getA1500());
        panes.add(p1500);
        paneHashMap.put(p1500, "15:00");

        hashMap.put(p1515, day.getA1515());
        panes.add(p1515);
        paneHashMap.put(p1515, "15:15");

        hashMap.put(p1530, day.getA1530());
        panes.add(p1530);
        paneHashMap.put(p1530, "15:30");

        hashMap.put(p1545, day.getA1545());
        panes.add(p1545);
        paneHashMap.put(p1545, "15:45");

        hashMap.put(p1600, day.getA1600());
        panes.add(p1600);
        paneHashMap.put(p1600, "16:00");

        hashMap.put(p1615, day.getA1615());
        panes.add(p1615);
        paneHashMap.put(p1615, "16:15");

        hashMap.put(p1630, day.getA1630());
        panes.add(p1630);
        paneHashMap.put(p1630, "16:30");

        hashMap.put(p1645, day.getA1645());
        panes.add(p1645);
        paneHashMap.put(p1645, "16:45");

        hashMap.put(p1700, day.getA1700());
        panes.add(p1700);
        paneHashMap.put(p1700, "17:00");

        hashMap.put(p1715, day.getA1715());
        panes.add(p1715);
        paneHashMap.put(p1715, "17:15");

        hashMap.put(p1730, day.getA1730());
        panes.add(p1730);
        paneHashMap.put(p1730, "17:30");

        hashMap.put(p1745, day.getA1745());
        panes.add(p1745);
        paneHashMap.put(p1745, "17:45");

        hashMap.put(p1800, day.getA1800());
        panes.add(p1800);
        paneHashMap.put(p1800, "18:00");

        hashMap.put(p1815, day.getA1815());
        panes.add(p1815);
        paneHashMap.put(p1815, "18:15");

        hashMap.put(p1830, day.getA1830());
        panes.add(p1830);
        paneHashMap.put(p1830, "18:30");

        hashMap.put(p1845, day.getA1845());
        panes.add(p1845);
        paneHashMap.put(p1845, "18:45");

        hashMap.put(p1900, day.getA1900());
        panes.add(p1900);
        paneHashMap.put(p1900, "19:00");

        hashMap.put(p1915, day.getA1915());
        panes.add(p1915);
        paneHashMap.put(p1915, "19:15");

        hashMap.put(p1930, day.getA1930());
        panes.add(p1930);
        paneHashMap.put(p1930, "19:30");

        hashMap.put(p1945, day.getA1945());
        panes.add(p1945);
        paneHashMap.put(p1945, "19:45");

        hashMap.put(p2000, day.getA2000());
        panes.add(p2000);
        paneHashMap.put(p2000, "20:00");

    }

    private GridPane getGgidPaneEvent(User user, Integer idEvent, String time){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_event_min.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
            handlerLang.registerObserverLang(loader.getController());

            ItemEventMin controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.loadFragment(user, idEvent, time, day);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gridPane;
    }
}
