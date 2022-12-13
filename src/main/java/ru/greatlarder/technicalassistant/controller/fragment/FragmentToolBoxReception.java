package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartReceptionController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;

public class FragmentToolBoxReception implements ObserverLang, ObserverUser {
    @FXML public HBox hBoxToolBoxReception;
    @FXML public ImageView imgAllRoom;
    @FXML public ImageView imgAllEquipment;
    @FXML public ImageView imgAddTask;
    @FXML public ImageView imgPhoneBook;
    private String lang;
    Language language = new LanguageImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().handlerUserListener;
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
    
    public void openPhoneBook(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentPhoneBook.fxml"));
        try {
            GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            FragmentPhoneBook controller1 = loader.getController();
            controller1.updateLang(new DataLang(this.lang));
            controller1.updateUser(new DataUser(this.user));
        
            controller1.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
