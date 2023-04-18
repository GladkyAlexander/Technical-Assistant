package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetDay;
import ru.greatlarder.technicalassistant.services.database.mysql.day.DayByLocalDateAndRoomMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemRoomWorkloadController implements Initializable {
    @FXML public VBox vBox;
    @FXML public Label labelNameRoom;
    @FXML public ProgressBar progressBar;
    User user;
    Company company;
    String lang;

    public void loadFragment(Room room, LocalDate localDate){

            GetDay getDay = new DayByLocalDateAndRoomMySQL();
            labelNameRoom.setText(room.getNameRoom());

        Thread thread = new Thread(() -> {
            int g = getList(getDay.getDay(user, localDate, room.getNameRoom())).size();
            Platform.runLater(() -> {
                if(((double) g / 49) > 0 && ((double) g / 49) != 1) {
                    progressBar.setProgress((double) g / 49);
                    progressBar.setStyle(".progress-bar > .track {-fx-text-box-border: #00FF00; -fx-control-inner-background: #00FF00;}");
                } else if ( ((double) g / 49) == 1){
                    progressBar.setProgress((double) g / 49);
                    progressBar.setStyle(".progress-bar > .track {-fx-text-box-border: #FF0000; -fx-control-inner-background: #FF0000;}");
                } else {
                    progressBar.setProgress(((double) g / 49));
                    progressBar.setStyle(".progress-bar > .track {-fx-text-box-border: #00FF00; -fx-control-inner-background: #00FF00;}");
                }
            });
        });
        thread.start();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }
    private List<Integer> getList(Day day){
        List<Integer> list = new ArrayList<>();
        List<Integer> retList = new ArrayList<>();
        if(day != null) {
            list.add(day.getA800());
            list.add(day.getA815());
            list.add(day.getA830());
            list.add(day.getA845());
            list.add(day.getA900());
            list.add(day.getA915());
            list.add(day.getA930());
            list.add(day.getA945());
            list.add(day.getA1000());
            list.add(day.getA1015());
            list.add(day.getA1030());
            list.add(day.getA1045());
            list.add(day.getA1100());
            list.add(day.getA1115());
            list.add(day.getA1130());
            list.add(day.getA1145());
            list.add(day.getA1200());
            list.add(day.getA1215());
            list.add(day.getA1230());
            list.add(day.getA1245());
            list.add(day.getA1300());
            list.add(day.getA1315());
            list.add(day.getA1330());
            list.add(day.getA1345());
            list.add(day.getA1400());
            list.add(day.getA1415());
            list.add(day.getA1430());
            list.add(day.getA1445());
            list.add(day.getA1500());
            list.add(day.getA1515());
            list.add(day.getA1530());
            list.add(day.getA1545());
            list.add(day.getA1600());
            list.add(day.getA1615());
            list.add(day.getA1630());
            list.add(day.getA1645());
            list.add(day.getA1700());
            list.add(day.getA1715());
            list.add(day.getA1730());
            list.add(day.getA1745());
            list.add(day.getA1800());
            list.add(day.getA1815());
            list.add(day.getA1830());
            list.add(day.getA1845());
            list.add(day.getA1900());
            list.add(day.getA1915());
            list.add(day.getA1930());
            list.add(day.getA1945());
            list.add(day.getA2000());
            for (Integer i : list) {
                if (i > 0) {
                    retList.add(i);
                }
            }
        }
        return retList;
    }
}
