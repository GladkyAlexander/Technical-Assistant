package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemDay;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.LanguageDataName;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageDataNameImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

public class FragmentRoomWeek implements Initializable {
    @FXML
    public TabPane tabPaneWeek;
    @FXML
    public Label labelNameRoom;
    @FXML
    public BorderPane borderPane;
    Room room;
    LocalDate date = LocalDate.now();
    User user;
    HashMap<Tab, LocalDate> hashMap = new HashMap<>();
    LanguageDataName languageDataName = new LanguageDataNameImpl();
    String lang;
    Company company;

    public void loadWeek(Room room) {
        this.room = room;
        labelNameRoom.setText(room.getNameRoom());

        for (int i = 0; i < 7; i++) {
            LocalDate d = date.plusDays(i);
            Tab t = new Tab(d + " : " + languageDataName.getDayWeek(d, lang));
            if (d.isEqual(LocalDate.now())) {
                setCont(t, d);
            }
            hashMap.put(t, d);
            tabPaneWeek.getTabs().add(t);
            t.setOnSelectionChanged(event -> {
                if(t.isSelected()){setCont((Tab) event.getSource(), hashMap.get((Tab) event.getSource()));}
            });
        }

    }

    private void setCont(Tab tab, LocalDate localDate) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_day.fxml"));
        try {
            tab.setContent(loader.load());
            ItemDay controller = loader.getController();
            controller.loadFragment(localDate, room.getNameRoom());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang =GlobalLinkMainController.getMainController().getLang();
    }

}
