package ru.greatlarder.technicalassistant.controller.reception;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
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

public class StartReceptionController implements ObserverLang, ObserverUser, ObserverCompany {

    @FXML public BorderPane borderPaneStartReception;
    @FXML public SplitPane splitPaneStartReception;
    @FXML public Label labelLastName;
    @FXML public Label labelFirstName;
    @FXML public Label labelHomePage;
    @FXML public Label labelInstructions;
    @FXML public ImageView imgLabelCompany;
    @FXML public Label labelInfo;
    @FXML public Label labelSettings;
    HandlerLang handlerLang = new HandlerLang();
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    Language language = new LanguageImpl();
    private String lang;
    private User user;
    private Company company;

    public void loadPage() {
        GlobalLinkStartReceptionController.setStartReceptionController(this);
        setLanguage(lang);
        if(user != null) {
            labelLastName.setText(user.getLastName());
            labelFirstName.setText(user.getFirstName());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentToolBoxReception.fxml"));
            try {
                GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().clear();
                GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().add(loader.load());

            } catch (IOException e) {
                e.printStackTrace();
            }

            FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/homeReceptionPage.fxml"));
            try {
                borderPaneStartReception.setCenter(loaderHomePage.load());
                handlerUserListener.registerObserverUser(loaderHomePage.getController());
                handlerLang.registerObserverLang(loaderHomePage.getController());

                if(user != null){
                    handlerLang.onNewDataLang(new DataLang(lang));
                }
                handlerUserListener.onNewDataUser(new DataUser(user));

                HomeReceptionController controller = loaderHomePage.getController();
                controller.loadFragment();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            labelLastName.setText("");
            labelFirstName.setText("");
        }
    }

    private void setLanguage(String l){
        labelHomePage.setText(language.HOME_PAGE(l));
        labelInstructions.setText(language.INSTRUCTION(l));
        labelSettings.setText(language.SETTINGS(l));
        labelInfo.setText(language.INFORMATION(l));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
        handlerLang.onNewDataLang(new DataLang(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        loadPage();
        handlerUserListener.onNewDataUser(new DataUser(user));
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
    }


    public void openPageSettings(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/settingsReceptionController.fxml"));
        try {
            borderPaneStartReception.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            handlerUserListener.onNewDataUser(new DataUser(user));

            SettingsReceptionController settingsReceptionController = loader.getController();
            settingsReceptionController.loadFragment();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openHomePageReception(MouseEvent mouseEvent) {
        FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/homeReceptionPage.fxml"));
        try {
            borderPaneStartReception.setCenter(loaderHomePage.load());
            handlerUserListener.registerObserverUser(loaderHomePage.getController());
            handlerLang.registerObserverLang(loaderHomePage.getController());

            if(user != null){
                handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
            }
            handlerUserListener.onNewDataUser(new DataUser(user));

            HomeReceptionController controller = loaderHomePage.getController();
            controller.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openInstructionPageReception(MouseEvent mouseEvent) {
        FXMLLoader loaderInstructionPage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/instructionReceptionPage.fxml"));
        try {
            borderPaneStartReception.setCenter(loaderInstructionPage.load());
            handlerUserListener.registerObserverUser(loaderInstructionPage.getController());
            handlerLang.registerObserverLang(loaderInstructionPage.getController());

            if(user != null){
                handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
            }
            handlerUserListener.onNewDataUser(new DataUser(user));

            InstructionReceptionPage controller = loaderInstructionPage.getController();
            controller.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openInfoPage(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/info_page.fxml"));
        try {
            borderPaneStartReception.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
