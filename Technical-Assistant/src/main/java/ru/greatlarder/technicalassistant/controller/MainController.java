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
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.GetCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.company.CompanyByNameSQLite;
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

public class MainController implements ObserverLang, ObserverUser, ObserverCompany {
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
    @FXML
    public MenuButton menuButtonUserInOut;
    @FXML
    public MenuItem menuItemOut;
    @FXML
    public ImageView imgUserAct;
    @FXML
    public MenuItem menuItemChangeCompany;
    @FXML public MenuItem menuItemInput;
    Language language = new LanguageImpl();
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    HandlerLang handlerLang = new HandlerLang();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    String lang;
    User user;
    Company company;

    public HandlerCompanyListener getHandlerCompanyListener() {
        return handlerCompanyListener;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HandlerLang getHandlerLang() {
        return handlerLang;
    }

    public HandlerUserListener getHandlerUserListener() {
        return handlerUserListener;
    }

    @Override
    public void updateLang(DataLang dataLang) {
        setLang(dataLang.getLanguage());
        setLanguage(lang);
        if(dataLang.getLanguage().equals("Русский")){
            mbtLang.setText(menuItemRu.getText());
            imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));
        }
        if(dataLang.getLanguage().equals("English")) {
            mbtLang.setText(menuItemEn.getText());
            imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ico-en.png"))));
            
        }
    }

    public void setLanguage(String lan) {
        menuItemOut.setText(language.EXIT(lan));
        menuItemChangeCompany.setText(language.CHANGE_COMPANY(lan));
        menuItemInput.setText(language.ENTER(lan));
    }

    public void mBru() {
        mbtLang.setText(menuItemRu.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));
        handlerLang.onNewDataLang(new DataLang("Русский"));
    }

    public void mBen() {
        mbtLang.setText(menuItemEn.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass()
            .getResourceAsStream("/ru/greatlarder/technicalassistant/images/ico-en.png"))));
        handlerLang.onNewDataLang(new DataLang("English"));
    }

    public void onActionMenuItemOut() {
        setCompany(null);
        handlerUserListener.onNewDataUser(new DataUser(null));
        menuButtonUserInOut.setText("");
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void startAccount(User userIn) {
        setUser(userIn);
        clear();
        GlobalLinkMainController.setMainController(this);

        handlerUserListener.registerObserverUser(this);
        handlerLang.registerObserverLang(this);
        handlerCompanyListener.registerObserverCompany(this);

        mbtLang.setText(menuItemRu.getText());
        
        updateLang(new DataLang(menuItemRu.getText()));
        
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));

        if (this.user == null) {
            //menuButtonUserInOut.setVisible(false);
            imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_unactive.png"))));
            FXMLLoader loaderUserLogin = new FXMLLoader(getClass().
                    getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentUserLogin.fxml"));
            try {
                borderPaneMainPage.setCenter(loaderUserLogin.load());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //menuButtonUserInOut.setVisible(true);
            updateLang(new DataLang(user.getLanguage()));
            if (this.user.getPost().equals("Engineer") || this.user.getPost().equals("Инженер")) {
                menuItemChangeCompany.setVisible(true);
                setLang(user.getLanguage());
                handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
                loadEngineer(this.user);
            }
            if (this.user.getPost().equals("Reception Secretary") || this.user.getPost().equals("Секретарь приемной")) {
                menuItemChangeCompany.setVisible(false);
                setLang(user.getLanguage());
                handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
                loadReception(this.user);
            }
        }

    }

    private void loadEngineer(User user) {

        imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_active.png"))));
        menuButtonUserInOut.setText(user.getLastName() + "   " + user.getFirstName());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/startEngineerPage.fxml"));
        FXMLLoader loaderToolBox = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentToolBox.fxml"));
        try {
            borderPaneMainPage.setCenter(loader.load());
            hBoxTopToolbar.getChildren().add(loaderToolBox.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadReception(User user) {

        imgUserAct.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/login_active.png"))));
        menuButtonUserInOut.setText(user.getLastName() + "   " + user.getFirstName());

        GetCompany company = new CompanyByNameSQLite();
        setCompany(company.getCompany(user, user.getCompanyAffiliation()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/startReceptionPage.fxml"));
        FXMLLoader loaderToolBox = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentToolBox.fxml"));
        try {
            borderPaneMainPage.setCenter(loader.load());
            hBoxTopToolbar.getChildren().add(loaderToolBox.load());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(DataUser dataUser) {
        startAccount(dataUser.getUser());
    }

    private void clear() {
        handlerUserListener.clear();
        handlerLang.clear();
        handlerCompanyListener.clear();
        hBoxTopToolbar.getChildren().clear();
        borderPaneMainPage.getChildren().remove(borderPaneMainPage.getCenter());
    }

    public void onActionMenuItemChangeCompany() {
        updateCompany(new DataCompany(null));
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        setCompany(dataCompany.getCompany());
        startAccount(getUser());
    }
    
    public void onAcMenuButtonUserInOut(MouseEvent actionEvent) {
        if(getUser() == null){
            menuItemOut.setVisible(false);
            menuItemChangeCompany.setVisible(false);
            menuItemInput.setVisible(true);
        } else {
            menuItemOut.setVisible(true);
            menuItemChangeCompany.setVisible(true);
            menuItemInput.setVisible(false);
        }
    }
    
    public void onActionMenuItemInput(ActionEvent actionEvent) {
        setCompany(null);
        handlerUserListener.onNewDataUser(new DataUser(null));
        menuButtonUserInOut.setText("");
    }
}