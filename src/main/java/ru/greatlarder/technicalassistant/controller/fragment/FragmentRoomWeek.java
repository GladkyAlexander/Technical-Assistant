package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemDay;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
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
import java.util.HashMap;

public class FragmentRoomWeek implements ObserverLang, ObserverUser {
    @FXML
    public TabPane tabPaneWeek;
    private Room room;
    LocalDate date = LocalDate.now();
    private User user;

    HashMap<Tab, LocalDate> hashMap = new HashMap<>();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    Language language = new LanguageImpl();
    String lang;

    public void loadWeek(User user, Room room) {
        this.room = room;
        this.user = user;
        for (int i = 0; i < 7; i++) {
            LocalDate d = date.plusDays(i);
            Tab t = new Tab(d + " : " + language.getDayWeek(d.getDayOfWeek().toString(),lang));
            t.setContent(getCont(t, d));
            tabPaneWeek.getTabs().add(t);
        }
    }
    

    private Node getCont(Tab tab, LocalDate localDate){
        Node node = null;
        if (localDate != null) {
            hashMap.put(tab, localDate);
            tab.setOnSelectionChanged(new TabEventHandler());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_day.fxml"));
            try {
                node = loader.load();
                handlerLang.registerObserverLang(loader.getController());
                ItemDay controller = loader.getController();
                controller.updateLang(new DataLang(lang));
                controller.loadFragment(user, localDate, room.getNameRoom());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return node;
    }

    private class TabEventHandler implements EventHandler<Event>{
        @Override
        public void handle(Event event) {
            ((Tab)event.getSource()).setContent(getCont((Tab)event.getSource(), hashMap.get((Tab)event.getSource())));
        }
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }
    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
