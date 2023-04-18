package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemCompanySelection;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.company.ListAllCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartEngineerController implements ObserverLang,Initializable {
    @FXML
    public ImageView imageViewLogo;
    @FXML
    public VBox vBoxNameCompany;
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
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    User user;
    Company company;
    String lang;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void clickHomePage(MouseEvent mouseEvent) {
        loadPageHome();
    }

    public void clickDocumentation(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/documentationEngineerPage.fxml"));
        try {
            if (company == null) {
                borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(lang)));
            } else {
                fileManager.folderCompanyDocumentation(company.getNameCompany());
                borderPaneEngineerPage.setCenter(loader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickPageSettings(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/settingsEngineerPage.fxml"));
        try {
            borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPageInfo(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/info_page.fxml"));
        try {
            borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }

    public void setLanguage(String lang) {
        labelHomePage.setText(language.HOME_PAGE(lang));
        labelManuals.setText(language.DOCUMENTATION(lang));
        labelSettings.setText(language.SETTINGS(lang));
        labelInfo.setText(language.INFORMATION(lang));

        labelWeAreGlad.setText(language.WE_ARE_GLAD_OF_YOUR_CHOICE(lang));
        labelStartByRegistering.setText(language.START_BY_REGISTERING_ON_THE_SETTINGS_PAGE(lang));
    }

    private void clean() {
        vBoxNameCompany.setVisible(false);
        vBoxNameCompany.setManaged(false);
        imageViewLogo.setFitHeight(131);
        imageViewLogo.setFitWidth(220);
        imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
    }

    private void loadPageHome() {
        FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/engineer/homeEngineerPage.fxml"));
        try {
            if(company == null){
                selectionCompany();
            } else borderPaneEngineerPage.setCenter(loaderHomePage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startPage() {
        GlobalLinkStartEngineerController.setStartEngineerController(this);
        handlerLang.registerObserverLang(this);
        selectionCompany();
    }

    public void selectionCompany() {
        GetListCompany listAllCompany = new ListAllCompanySQLite();
        if (getCompany() == null) {
            List<Company> companies = listAllCompany.getAllCompany(user, String.valueOf(user.getId()));
            if (companies.size() == 1) {
                equalToOne(companies.get(0));
            } else if (companies.size() > 1) {
                moreThanOne(companies);
            } else clean();
        } else {

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

            loadPageHome();
        }

    }

    private void moreThanOne(List<Company> companyList) {

        HBox vBox = new HBox();
        for (Company company1 : companyList){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_company_selection.fxml"));
                vBox.setPadding(new Insets(10, 10, 10, 10));
                vBox.setSpacing(20);
                vBox.setAlignment(Pos.CENTER);
                vBox.setMaxHeight(Region.USE_PREF_SIZE);
                vBox.getChildren().add(loader.load());
                ItemCompanySelection itemCompanySelection = loader.getController();
                itemCompanySelection.setCompany(company1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        borderPaneEngineerPage.setCenter(vBox);
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

        loadPageHome();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        startPage();
    }

}
