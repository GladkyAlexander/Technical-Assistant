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
import ru.greatlarder.technicalassistant.controller.InfoPageController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CompanyRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.CompanyRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
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
import java.util.ArrayList;
import java.util.List;
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
    HandlerCompanyListener handlerCompanyListener = GlobalLinkMainController.getMainController().getHandlerCompanyListener();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().getHandlerUserListener();
    CompanyRepository companyRepository = new CompanyRepositoryImpl();
    User user;
    private Company company;
    String lang;
    private List<Company> companies = new ArrayList<>();
    
    public Company getCompany() {
        return company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    public void updateTheCompany() {
        setCompany(companyRepository.getCompanyName(getCompany().getNameCompany()));
        updateCompany(new DataCompany(getCompany()));
    }
    
    public void clickHomePage(MouseEvent mouseEvent) {
        loadPageHome(this.user, getCompany());
    }
    
    public void clickDocumentation(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/documentationEngineerPage.fxml"));
        try {
            if (company == null) {
                borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            } else {
                fileManager.folderCompanyDocumentation(company.getNameCompany());
                borderPaneEngineerPage.setCenter(loader.load());
                handlerUserListener.registerObserverUser(loader.getController());
                handlerLang.registerObserverLang(loader.getController());
                handlerCompanyListener.registerObserverCompany(loader.getController());
                DocumentationEngineerController documentationFragmentController = loader.getController();
                documentationFragmentController.updateLang(new DataLang(this.lang));
                documentationFragmentController.updateUser(new DataUser(this.user));
                documentationFragmentController.updateCompany(new DataCompany(this.company));
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
            handlerCompanyListener.registerObserverCompany(loader.getController());
            SettingsEngineerController settingsPageController = loader.getController();
            settingsPageController.updateLang(new DataLang(this.lang));
            settingsPageController.updateUser(new DataUser(this.user));
            settingsPageController.updateCompany(new DataCompany(this.company));
            settingsPageController.loadPage();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void openPageInfo(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/info_page.fxml"));
        try {
            borderPaneEngineerPage.setCenter(loader.load());
            handlerLang.registerObserverLang(loader.getController());
            InfoPageController controller = loader.getController();
            controller.updateLang(new DataLang(this.lang));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    EventHandler<ActionEvent> eventEventHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {
            btnSelectCompanies.setText(((MenuItem) event.getSource()).getText());
            setCompany(companyRepository.getCompanyName(((MenuItem) event.getSource()).getText()));

            handlerCompanyListener.onNewDataCompany(new DataCompany(getCompany()));
         /*   for (Company comp : user.getCompanyList()) {
                if (comp.getNameCompany().equals(((MenuItem) event.getSource()).getText())) {
                    setCompany(comp);
                    equalToOne(getCompany());
                }
            }*/
        }
    };
    
    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }
    
    @Override
    public void updateUser(DataUser dataUser) {
        if (dataUser == null) {
            this.user = null;
        } else this.user = dataUser.getUser();
    }
    
    public void setLanguage(String lang) {
        labelHomePage.setText(language.HOME_PAGE(lang));
        labelManuals.setText(language.DOCUMENTATION(lang));
        labelSettings.setText(language.SETTINGS(lang));
        labelInfo.setText(language.INFORMATION(lang));
        if (btnSelectCompanies.getText().equals(language.CHOOSE_A_COMPANY("Русский")) ||
                btnSelectCompanies.getText().equals(language.CHOOSE_A_COMPANY("English"))) {
            btnSelectCompanies.setText(language.CHOOSE_A_COMPANY(lang));
        }
        labelWeAreGlad.setText(language.WE_ARE_GLAD_OF_YOUR_CHOICE(lang));
        labelStartByRegistering.setText(language.START_BY_REGISTERING_ON_THE_SETTINGS_PAGE(lang));
    }
    
    @Override
    public void updateCompany(DataCompany dataCompany) {
        if (dataCompany == null) {
            setCompany(null);
        } else {
            setCompany(dataCompany.getCompany());
        }
        startPage();
    }
    
    private void clean() {
        vBoxNameCompany.setVisible(false);
        vBoxNameCompany.setManaged(false);
        imageViewLogo.setFitHeight(131);
        imageViewLogo.setFitWidth(220);
        imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
    }
    
    private void loadPageHome(User userIn, Company companyIn) {
        FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/homeEngineerPage.fxml"));
        try {
            borderPaneEngineerPage.setCenter(loaderHomePage.load());
            handlerLang.registerObserverLang(loaderHomePage.getController());
            handlerUserListener.registerObserverUser(loaderHomePage.getController());
            handlerCompanyListener.registerObserverCompany(loaderHomePage.getController());
            HomeEngineerController controller = loaderHomePage.getController();
            controller.updateLang(new DataLang(this.lang));
            controller.updateUser(new DataUser(userIn));
            controller.updateCompany(new DataCompany(companyIn));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startPage() {
        GlobalLinkStartEngineerController.setStartEngineerController(this);
        if (user != null) {
            selectionCompany();
        } else clean();
    }
    
    public void selectionCompany() {
        if (getCompany() == null) {
            companies = companyRepository.getListCompanyByUserId(user.getId());
            if (companies.size() == 1) {
               equalToOne(companies.get(0));
            } else if (companies.size() > 1) {
                moreThanOne(companies);
            } else clean();
        } else {
            btnSelectCompanies.setText(getCompany().getNameCompany());
            vBoxNameCompany.setVisible(true);
            vBoxNameCompany.setManaged(true);
            ptAdr.prefColumnCountProperty().bind(ptAdr.textProperty().length());
            ptAdr.setText(getCompany().getAddress());
            ptCur.setText(getCompany().getCuratorLastName() + " " + getCompany().getCuratorFirstName());
            ptPhone.setText(getCompany().getPhoneCurator());
            ptEmail.setText(getCompany().getMailCurator());
            imageViewLogo.setFitHeight(50);
            imageViewLogo.setFitWidth(50);
            imageViewLogo.setImage(new Image(fileManager.folderImage() + "\\" + getCompany().getLogoCompany()));
    
            loadPageHome(this.user, getCompany());
        }
        
    }
    
    private void moreThanOne(List<Company> companyList) {
        btnSelectCompanies.setText(language.CHOOSE_A_COMPANY(lang));
        btnSelectCompanies.getItems().clear();
        for (Company compIn : companyList) {
                MenuItem menuItem = new MenuItem(compIn.getNameCompany());
                menuItem.setOnAction(eventEventHandler);
                btnSelectCompanies.getItems().addAll(menuItem);
        }
        labelStartByRegistering.setText(language.CHOOSE_A_COMPANY(lang));
        vBoxNameCompany.setVisible(true);
        vBoxNameCompany.setManaged(true);
        imageViewLogo.setFitHeight(50);
        imageViewLogo.setFitWidth(50);
        imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
    }
    
    private void equalToOne(Company comIn) {
        setCompany(comIn);
        vBoxNameCompany.setVisible(true);
        vBoxNameCompany.setManaged(true);
        ptAdr.prefColumnCountProperty().bind(ptAdr.textProperty().length());
        ptAdr.setText(getCompany().getAddress());
        ptCur.setText(getCompany().getCuratorLastName() + " " + getCompany().getCuratorFirstName());
        ptPhone.setText(getCompany().getPhoneCurator());
        ptEmail.setText(getCompany().getMailCurator());
        imageViewLogo.setFitHeight(50);
        imageViewLogo.setFitWidth(50);
        imageViewLogo.setImage(new Image(fileManager.folderImage() + "\\" + getCompany().getLogoCompany()));
        
        handlerCompanyListener.onNewDataCompany(new DataCompany(getCompany()));
    }
    
    public void choiceCompanyMouseClicked(MouseEvent mouseEvent) {
        btnSelectCompanies.getItems().clear();
            for (Company compIn : companies) {
                MenuItem menuItem = new MenuItem(compIn.getNameCompany());
                menuItem.setOnAction(eventEventHandler);
                btnSelectCompanies.getItems().addAll(menuItem);
            }
    }
}
