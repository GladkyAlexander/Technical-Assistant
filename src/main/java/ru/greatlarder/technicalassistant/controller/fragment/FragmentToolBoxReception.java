package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartReceptionController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;

public class FragmentToolBoxReception implements ObserverLang, ObserverUser {
    public HBox hBoxToolBoxReception;
    public ImageView imgAllRoom;
    public ImageView imgAllEquipment;
    public ImageView imgAddTask;
    Language language = new LanguageImpl();
    private String lang;
    private User user;
    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }

    public void openAllRoom(MouseEvent mouseEvent) {
    }

    public void openAllEquipment(MouseEvent mouseEvent) {
    }

    public void openAddTask(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_task_reception.fxml"));
        try {
            GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
