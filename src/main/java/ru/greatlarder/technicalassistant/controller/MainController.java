package ru.greatlarder.technicalassistant.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.controller.engineer.StartEngineerController;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentRegistrationUserController;
import ru.greatlarder.technicalassistant.controller.reception.StartReceptionController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;

import java.io.IOException;
import java.util.Objects;

public class MainController implements ObserverLang {
    @FXML
    public BorderPane borderPaneMainPage;
    @FXML
    public HBox hBoxTopToolbar;
    @FXML
    public MenuButton mbtLang;
    @FXML
    public MenuItem menuItemRu;
    @FXML
    public MenuItem menuItemEn;
    @FXML
    public ImageView imgLangMenuButton;
    public User user;
    public static Company company;
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    HandlerLang handlerLang = new HandlerLang();

    @Override
    public void updateLang(DataLang dataLang) {
        setLanguage(dataLang.getLanguage());
    }

    public void loadPage() {

        mbtLang.setText(menuItemRu.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));

        GlobalLinkMainController.setMainController(this);

        if(user != null){
            if(user.getPost().equals("Engineer") || user.getPost().equals("Инженер")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/startEngineerPage.fxml"));
                try {
                        borderPaneMainPage.setCenter(loader.load());
                        handlerUserListener.registerObserverUser(loader.getController());
                        handlerLang.registerObserverLang(loader.getController());
                        handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
                        handlerUserListener.onNewDataUser(new DataUser(user));
                    StartEngineerController controller = loader.getController();
                    controller.loadPage();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(user.getPost().equals("Reception Secretary") || user.getPost().equals("Секретарь приемной")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/startReceptionPage.fxml"));
                try {
                    borderPaneMainPage.setCenter(loader.load());
                    handlerUserListener.registerObserverUser(loader.getController());
                    handlerLang.registerObserverLang(loader.getController());
                    handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
                    handlerUserListener.onNewDataUser(new DataUser(user));
                    StartReceptionController controller = loader.getController();
                    controller.loadPage();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                    getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
            try {
                borderPaneMainPage.setCenter(loaderRegistration.load());
                handlerLang.registerObserverLang(loaderRegistration.getController());
                handlerLang.onNewDataLang(new DataLang(mbtLang.getText()));
                FragmentRegistrationUserController fragmentRegistrationUserController = loaderRegistration.getController();
                fragmentRegistrationUserController.loadPage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void setLanguage(String lang) {

    }
    public void mBru(ActionEvent actionEvent) {
        mbtLang.setText(menuItemRu.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));
        handlerLang.registerObserverLang(this);
        handlerLang.onNewDataLang(new DataLang("Русский"));
    }

    public void mBen(ActionEvent actionEvent) {
        mbtLang.setText(menuItemEn.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ico-en.png"))));
        handlerLang.registerObserverLang(this);
        handlerLang.onNewDataLang(new DataLang("English"));
    }

    public void updateUser(User user){
        this.user = user;
        handlerUserListener.onNewDataUser(new DataUser(user));
        loadPage();
    }
    public void addPhoneBook(MouseEvent mouseEvent) {
    }
}