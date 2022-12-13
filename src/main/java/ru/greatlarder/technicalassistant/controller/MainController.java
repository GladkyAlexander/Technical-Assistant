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
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentUserLogin;
import ru.greatlarder.technicalassistant.controller.reception.StartReceptionController;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.Objects;

public class MainController implements ObserverLang, ObserverUser {
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
    @FXML
    public MenuButton menuButtonUserInOut;
    @FXML
    public MenuItem menuItemOut;
    @FXML
    public MenuItem menuItemInput;
    @FXML
    public ImageView imgUserAct;
    Language language = new LanguageImpl();
    public HandlerUserListener handlerUserListener = new HandlerUserListener();
    UserRepository userRepository = new UserRepositoryImpl();
    public HandlerLang handlerLang = new HandlerLang();
    private String lang;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    public void setLanguage(String lan) {
        menuItemInput.setText(language.ENTER(lan));
        menuItemOut.setText(language.EXIT(lan));
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

    public void addPhoneBook(MouseEvent mouseEvent) {
    }

    public void updateUser() {
        this.user = userRepository.getUserLoginPassword(user.getLogin(), user.getPassword());
        handlerUserListener.onNewDataUser(new DataUser(user));
    }

    public void onActionMenuButtonInOut(ActionEvent actionEvent) {
        menuItemInput.setText(language.ENTER(lang));
        menuItemOut.setText(language.EXIT(lang));
    }

    public void onActionMenuItemOut(ActionEvent actionEvent) {
        handlerUserListener.clear();
        borderPaneMainPage.getChildren().remove(borderPaneMainPage.getCenter());
        handlerUserListener.registerObserverUser(this);
        handlerUserListener.onNewDataUser(new DataUser(null));
    }

    public void onActionMenuItemInput(ActionEvent actionEvent) {

        imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/unactive.png"))));
        FXMLLoader loaderUserLogin = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentUserLogin.fxml"));
        try {
            borderPaneMainPage.setCenter(loaderUserLogin.load());
            handlerLang.registerObserverLang(loaderUserLogin.getController());
            handlerLang.onNewDataLang(new DataLang(mbtLang.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startAccount(User user) {

        this.user = user;
        GlobalLinkMainController.setMainController(this);
        handlerUserListener.registerObserverUser(this);

        mbtLang.setText(menuItemRu.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));

        if (this.user == null) {
            imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_unactive.png"))));
            menuButtonUserInOut.setText("");

            imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_unactive.png"))));
            FXMLLoader loaderUserLogin = new FXMLLoader(getClass().
                    getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentUserLogin.fxml"));
            try {
                borderPaneMainPage.setCenter(loaderUserLogin.load());
                handlerLang.registerObserverLang(loaderUserLogin.getController());
                FragmentUserLogin userLogin = loaderUserLogin.getController();
                userLogin.updateLang(new DataLang(mbtLang.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            if(this.user.getPost().equals("Engineer") || this.user.getPost().equals("Инженер")){
                this.lang = user.getLanguage();
                loadEngineer(this.user);
            }
            if(this.user.getPost().equals("Reception Secretary") || this.user.getPost().equals("Секретарь приемной")){
                this.lang = user.getLanguage();
                loadReception(this.user);
            }
        }
    }
    private void loadEngineer(User user){
        imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_active.png"))));
        menuButtonUserInOut.setText(user.getLastName() + "   " + user.getFirstName());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/startEngineerPage.fxml"));
        try {
            borderPaneMainPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            StartEngineerController controller = loader.getController();
            controller.updateLang(new DataLang(lang));
            controller.updateUser(new DataUser(user));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void loadReception(User user){
        imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_active.png"))));
        menuButtonUserInOut.setText(user.getLastName() + "   " + user.getFirstName());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/startReceptionPage.fxml"));
        try {
            borderPaneMainPage.setCenter(loader.load());
            handlerUserListener.registerObserverUser(loader.getController());
            handlerLang.registerObserverLang(loader.getController());
            StartReceptionController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(this.user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        startAccount(this.user);
    }
}