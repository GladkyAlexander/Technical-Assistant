package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.CompanyRepository;
import ru.greatlarder.technicalassistant.repository.impl.CompanyRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.Objects;

public class StartEngineerController implements ObserverLang, ObserverUser, ObserverCompany {
    @FXML
    public ImageView imageViewLogo;
    @FXML
    public VBox vBoxNameCompany;
    @FXML
    public MenuButton btnSelectCompanies;
    @FXML
    public TextField ptAdr;
    @FXML
    public TextField ptCur;
    @FXML
    public TextField ptPhone;
    @FXML
    public TextField ptEmail;
    @FXML
    public VBox vBoxLeftButton;
    @FXML
    public Label labelHomePage;
    @FXML
    public Label labelManuals;
    @FXML
    public Label labelSettings;
    @FXML
    public HBox hBoxInfo;
    @FXML
    public Label labelInfo;
    @FXML
    public VBox vBoxStartLayout;
    @FXML
    public Label labelWeAreGlad;
    @FXML
    public Label labelStartByRegistering;
    @FXML
    public BorderPane borderPaneEngineerPage;
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    HandlerLang handlerLang = new HandlerLang();
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    CompanyRepository companyRepository = new CompanyRepositoryImpl();
    User user;
    public Company company;
    String lang;

    public void updateTheCompany() {
        this.company = companyRepository.getCompanyName(company.getNameCompany());
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
    }

    public void clickHomePage(MouseEvent mouseEvent) {
        if(company != null){
            FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/homeEngineerPage.fxml"));
            try {
                borderPaneEngineerPage.setCenter(loaderHomePage.load());
                handlerUserListener.registerObserverUser(loaderHomePage.getController());
                handlerLang.registerObserverLang(loaderHomePage.getController());
                handlerCompanyListener.registerObserverCompany(loaderHomePage.getController());
                handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
                handlerUserListener.onNewDataUser(new DataUser(user));
                handlerCompanyListener.onNewDataCompany(new DataCompany(company));
                HomeEngineerController controller = loaderHomePage.getController();
                controller.loadFragment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clickDocumentation(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/documentationEngineerPage.fxml"));
        try {
            if (company == null) {
                borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            } else {
                fileManager.folderCompanyDocumentation(company.getNameCompany());
                borderPaneEngineerPage.setCenter(loader.load());

                handlerLang.registerObserverLang(loader.getController());
                handlerCompanyListener.registerObserverCompany(loader.getController());
                handlerLang.onNewDataLang(new DataLang(lang));
                handlerCompanyListener.onNewDataCompany(new DataCompany(company));

                DocumentationEngineerController documentationFragmentController = loader.getController();
                documentationFragmentController.startDocFragment();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickPageSettings(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/settingsEngineerPage.fxml"));
        try {
            borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            handlerUserListener.onNewDataUser(new DataUser(user));

            SettingsEngineerController settingsPageController = loader.getController();
            settingsPageController.loadPage();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPageInfo(MouseEvent mouseEvent) {
    }

    public void startMenuButton(MouseEvent mouseEvent) {
        btnSelectCompanies.getItems().clear();
        menuButtonStart();
    }

    public void menuButtonStart() {
        if (user.getCompanyList() != null) {
            if (user.getCompanyList().size() == 1) {
                for (Company company1 : user.getCompanyList()) {
                    this.company = company1;
                    btnSelectCompanies.setText(company.getNameCompany());
                    handlerCompanyListener.onNewDataCompany(new DataCompany(company));
                    vBoxNameCompany.setVisible(true);
                    vBoxNameCompany.setManaged(true);
                    ptAdr.prefColumnCountProperty().bind(ptAdr.textProperty().length());
                    ptAdr.setText(company.getAddress());
                    ptCur.setText(company.getCurator());
                    ptPhone.setText(company.getPhoneCurator());
                    ptEmail.setText(company.getMailCurator());
                    imageViewLogo.setFitHeight(50);
                    imageViewLogo.setFitWidth(50);
                    imageViewLogo.setImage(new Image(fileManager.folderImage() + "\\" + company.getLogoCompany()));
                }
            }
            if (user.getCompanyList().size() > 1) {
                vBoxNameCompany.setVisible(true);
                vBoxNameCompany.setManaged(true);
                imageViewLogo.setFitHeight(50);
                imageViewLogo.setFitWidth(50);
                imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
                for (Company company : user.getCompanyList()) {
                    MenuItem menuItem = new MenuItem(company.getNameCompany());
                    menuItem.setOnAction(eventEventHandler);
                    btnSelectCompanies.getItems().addAll(menuItem);
                }
            }
            if(user.getCompanyList().size() == 0){
                vBoxNameCompany.setVisible(false);
                vBoxNameCompany.setManaged(false);
                imageViewLogo.setFitHeight(131);
                imageViewLogo.setFitWidth(220);
                imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                        .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            }
        }
    }

    EventHandler<ActionEvent> eventEventHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {

            btnSelectCompanies.setText(((MenuItem) event.getSource()).getText());

            for (Company company1 : user.getCompanyList()) {
                if (company1.getNameCompany().equals(((MenuItem) event.getSource()).getText())) {
                    company = company1;
                }
            }
            handlerCompanyListener.onNewDataCompany(new DataCompany(company));
            vBoxNameCompany.setVisible(true);
            vBoxNameCompany.setManaged(true);
            ptAdr.prefColumnCountProperty().bind(ptAdr.textProperty().length());
            ptAdr.setText(company.getAddress());
            ptCur.setText(company.getCurator());
            ptPhone.setText(company.getPhoneCurator());
            ptEmail.setText(company.getMailCurator());
            imageViewLogo.setImage(new Image(fileManager.folderImage() + "\\" + company.getLogoCompany()));
        }

    };

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
        handlerLang.onNewDataLang(new DataLang(lang));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
        handlerUserListener.onNewDataUser(new DataUser(user));
    }

    public void setLanguage(String lang) {
        labelHomePage.setText(language.HOME_PAGE(lang));
        labelManuals.setText(language.DOCUMENTATION(lang));
        labelSettings.setText(language.SETTINGS(lang));
        labelInfo.setText(language.INFORMATION(lang));
        if(btnSelectCompanies.getText().equals(language.CHOOSE_A_COMPANY("Русский")) ||
                btnSelectCompanies.getText().equals(language.CHOOSE_A_COMPANY("English"))){
            btnSelectCompanies.setText(language.CHOOSE_A_COMPANY(lang));
        }
        labelWeAreGlad.setText(language.WE_ARE_GLAD_OF_YOUR_CHOICE(lang));
        labelStartByRegistering.setText(language.START_BY_REGISTERING_ON_THE_SETTINGS_PAGE(lang));
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        loadPage();
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
    }

    public void loadPage() {
        GlobalLinkStartEngineerController.setStartEngineerController(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentToolBox.fxml"));
        try {
            GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().clear();
            GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().add(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            handlerCompanyListener.onNewDataCompany(new DataCompany(company));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuButtonStart();

        if(company != null){
            FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/homeEngineerPage.fxml"));
            try {
                borderPaneEngineerPage.setCenter(loaderHomePage.load());
                handlerUserListener.registerObserverUser(loaderHomePage.getController());
                handlerLang.registerObserverLang(loaderHomePage.getController());
                handlerCompanyListener.registerObserverCompany(loaderHomePage.getController());
                handlerLang.onNewDataLang(new DataLang(user.getLanguage()));
                handlerUserListener.onNewDataUser(new DataUser(user));
                handlerCompanyListener.onNewDataCompany(new DataCompany(company));
                HomeEngineerController controller = loaderHomePage.getController();
                controller.loadFragment();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
