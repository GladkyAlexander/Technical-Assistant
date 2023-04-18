package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

public class ItemTypesEvent implements ObserverLang {

    @FXML public ImageView img_event;
    @FXML public Label labelEvent;
    @FXML public HBox hBoxChoose;

    public EventFormat events;
    private String lang;
    Language language = new LanguageImpl();

    public void setEvents(EventFormat events) {
        this.events = events;
    }

    public void setImg_event(Image img_event) {
        this.img_event.setImage(img_event);
    }

    public void setLabelEvent(String labelEvent) {
        this.labelEvent.setText(labelEvent);
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang) {

    }

}
