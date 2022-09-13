package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.repository.CompanyRepository;
import ru.greatlarder.technicalassistant.repository.impl.CompanyRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class FragmentAddCompanyController implements ObserverLang {
    @FXML
    public ImageView imgOk;
    @FXML
    public Label labelOk;
    @FXML
    public ImageView close1;
    @FXML
    public TextField tfNameCompany;
    @FXML
    public TextField tfAddress;
    @FXML
    public TextField tfNumberPhone;
    @FXML
    public TextField tfEmail;
    @FXML
    public TextField tfWeb;
    @FXML
    public ImageView imgLogoCompany;
    @FXML
    public Button btnSendCompany;
    @FXML
    public TextField tfEngineerPhone;
    @FXML
    public TextField tfEngineerMail;
    @FXML
    public TextField tfManagerPhone;
    @FXML
    public TextField tfManagerMail;
    @FXML
    public GridPane gridPaneAddCompany;
    @FXML
    public Label labelNameCompany;
    @FXML
    public Label labelAddressCompany;
    @FXML
    public Label labelContactPerson;
    @FXML
    public Label labelRepresentativePhoneNumber;
    @FXML
    public Label mailRepresentative;
    @FXML
    public Label labelWebsite;
    @FXML
    public Label labelLogoCompany;
    @FXML
    public Label labelNameManager;
    @FXML
    public Label labelEngineer;
    @FXML
    public Label labelPhoneManager;
    @FXML
    public Label labelMailManager;
    @FXML
    public Label labelPhoneEngineer;
    @FXML
    public Label labelMailEngineer;
    @FXML
    public TextField tfCuratorLastName;
    @FXML
    public TextField tfCuratorFirstName;
    @FXML
    public Label labelLastNameCurator;
    @FXML
    public Label labelFirstNameCurator;
    @FXML
    public Label labelLastNameManager;
    @FXML
    public Label labelFirstNameManager;
    @FXML
    public TextField tfLastNameManager;
    @FXML
    public TextField tfFirstNameManager;
    @FXML
    public TextField tfLastNameEngineer;
    @FXML
    public TextField tfFirstNameEngineer;
    @FXML
    public Label labelLastNameEngineer;
    @FXML
    public Label labelFirstNameEngineer;
    Language language = new LanguageImpl();
    String fileName;
    String lang;
    CompanyRepository companyRepository = new CompanyRepositoryImpl();
    FileManager fileManager = new FileManagerImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }

    private void setLanguage(String lang) {
        labelNameCompany.setText(language.NAME_COMPANY(lang));
        labelAddressCompany.setText(language.COMPANY_ADDRESS(lang));
        labelContactPerson.setText(language.CONTACT_PERSON(lang));
        labelRepresentativePhoneNumber.setText(language.REPRESENTATIVES_PHONE_NUMBER(lang));
        mailRepresentative.setText(language.REPRESENTATIVES_EMAIL(lang));
        labelWebsite.setText(language.WEBSITE(lang));
        labelLogoCompany.setText(language.COMPANY_LOGO(lang));
        labelNameManager.setText(language.MANAGER(lang));
        labelEngineer.setText(language.ENGINEER(lang));
        labelPhoneManager.setText(language.MANAGER_PHONE(lang));
        labelMailManager.setText(language.MANAGER_MAIL(lang));
        labelPhoneEngineer.setText(language.ENGINEER_PHONE(lang));
        labelMailEngineer.setText(language.ENGINEER_MAIL(lang));
        tfNameCompany.setPromptText(language.NAME_COMPANY(lang));
        tfAddress.setPromptText(language.COMPANY_ADDRESS(lang));
        tfNumberPhone.setPromptText("+*(***)***-**-**");
        tfEmail.setPromptText("***@**.**");
        tfWeb.setPromptText("www.********.***");
        labelLastNameCurator.setText(language.LAST_NAME(lang));
        labelFirstNameCurator.setText(language.FIRST_NAME(lang));
        labelFirstNameManager.setText(language.FIRST_NAME(lang));
        labelLastNameManager.setText(language.LAST_NAME(lang));
        labelLastNameEngineer.setText(language.LAST_NAME(lang));
        labelFirstNameEngineer.setText(language.FIRST_NAME(lang));
        btnSendCompany.setText(language.SAVE(lang));
    }

    public void closeAddCompanyController() {
        gridPaneAddCompany.getChildren().clear();
        gridPaneAddCompany.setStyle(new GridPane().getStyle());
    }

    public void imagePicker() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG, extensionFilter);

        File chooserFile = fileChooser.showOpenDialog(imgLogoCompany.getScene().getWindow());

        if (chooserFile != null) {
            Image image = new Image(chooserFile.toURI().toString());

            fileName = image.getUrl().substring(image.getUrl().lastIndexOf('/') + 1);

            imgLogoCompany.setImage(image);
        }
    }

    public void sendCompany(MouseEvent mouseEvent) {

        convert(imgLogoCompany.getImage(), fileName);
        Company company = new Company();
        company.setNameCompany(tfNameCompany.getText());
        company.setAddress(tfAddress.getText());
        company.setCuratorLastName(tfCuratorLastName.getText());
        company.setCuratorFirstName(tfCuratorFirstName.getText());
        company.setPhoneCurator(tfNumberPhone.getText());
        company.setMailCurator(tfEmail.getText());
        company.setWebsiteCompany(tfWeb.getText());
        company.setLogoCompany(fileName);
        company.setManagerLastName(tfLastNameManager.getText());
        company.setManagerFirstName(tfFirstNameManager.getText());
        company.setPhoneManager(tfManagerPhone.getText());
        company.setMailManager(tfManagerMail.getText());
        company.setEngineerLastName(tfLastNameEngineer.getText());
        company.setEngineerFirstName(tfFirstNameEngineer.getText());
        company.setPhoneEngineer(tfEngineerPhone.getText());
        company.setMailEngineer(tfEngineerMail.getText());

        Company company1 = checkCompany(company);

        if (company1 != null) {
            fileManager.createDirectoryCompany(company1.getNameCompany());

            companyRepository.setCompany(company1);

            if (companyRepository.getCompanyName(company1.getNameCompany()).getNameCompany().equals(company1.getNameCompany())) {
                labelOk.setText(tfNameCompany.getText() + language.ADDED(lang));
                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));

                fileManager.createDirectoryCompany(company1.getNameCompany());
                GlobalLinkMainController.getMainController().updateUser();
                cleanAddCompany();
            }

        } else {
            labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
        }

    }

    public void convert(Image wim, String fileName) {

        try {
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png",
                    new FileOutputStream(fileManager.folderImage() + "\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cleanAddCompany() {
        tfNameCompany.clear();
        tfNameCompany.setStyle(new TextField().getStyle());
        tfAddress.clear();
        tfAddress.setStyle(new TextField().getStyle());
        tfCuratorLastName.clear();
        tfCuratorLastName.setStyle(new TextField().getStyle());
        tfCuratorFirstName.clear();
        tfCuratorFirstName.setStyle(new TextField().getStyle());
        tfNumberPhone.clear();
        tfNumberPhone.setStyle(new TextField().getStyle());
        tfEmail.clear();
        tfEmail.setStyle(new TextField().getStyle());
        tfWeb.clear();
        tfWeb.setStyle(new TextField().getStyle());
        imgLogoCompany.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/upphoto.png"))));
        tfLastNameManager.clear();
        tfLastNameManager.setStyle(new TextField().getStyle());
        tfFirstNameManager.clear();
        tfFirstNameManager.setStyle(new TextField().getStyle());
        tfManagerPhone.clear();
        tfManagerPhone.setStyle(new TextField().getStyle());
        tfManagerMail.clear();
        tfManagerMail.setStyle(new TextField().getStyle());
        tfLastNameEngineer.clear();
        tfLastNameEngineer.setStyle(new TextField().getStyle());
        tfFirstNameEngineer.clear();
        tfFirstNameEngineer.setStyle(new TextField().getStyle());
        tfEngineerPhone.clear();
        tfEngineerPhone.setStyle(new TextField().getStyle());
        tfEngineerMail.clear();
        tfEngineerMail.setStyle(new TextField().getStyle());
    }

    public Company checkCompany(Company company) {
        Company returnCompany = new Company();

        if (company.getNameCompany() == null) {
            returnCompany.setNameCompany(null);
            tfNameCompany.setStyle(StyleSRC.STYLE_DANGER);
        } else if (company.getNameCompany() != null
                && companyRepository.getCompanyName(company.getNameCompany()) != null) {
            returnCompany.setNameCompany(null);
            tfNameCompany.setStyle(StyleSRC.STYLE_DANGER);
            labelOk.setText(language.ALREADY_HAVE(lang));
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
        } else if (company.getNameCompany() != null
                && companyRepository.getCompanyName(company.getNameCompany()) == null) {
            returnCompany.setNameCompany(company.getNameCompany());
            tfNameCompany.setStyle(new TextField().getStyle());
            labelOk.setText("");
        }

        if (company.getAddress() == null || company.getAddress().isEmpty()) {
            tfAddress.setStyle(StyleSRC.STYLE_WARNING);
            tfAddress.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setAddress(null);
        } else {
            tfAddress.setStyle(new TextField().getStyle());
            returnCompany.setAddress(company.getAddress());
        }

        if (company.getCuratorLastName() == null || company.getCuratorLastName().isEmpty()) {
            tfCuratorLastName.setStyle(StyleSRC.STYLE_WARNING);
            tfCuratorLastName.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setCuratorLastName(null);
        } else {
            tfCuratorLastName.setStyle(new TextField().getStyle());
            returnCompany.setCuratorLastName(company.getCuratorLastName());
        }

        if (company.getPhoneCurator() == null || company.getPhoneCurator().isEmpty()) {
            tfNumberPhone.setStyle(StyleSRC.STYLE_WARNING);
            tfNumberPhone.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setPhoneCurator(null);
        } else {
            tfNumberPhone.setStyle(new TextField().getStyle());
            returnCompany.setPhoneCurator(company.getPhoneCurator());
        }

        if (company.getMailCurator() == null || company.getMailCurator().isEmpty()) {
            tfEmail.setStyle(StyleSRC.STYLE_WARNING);
            tfEmail.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setMailCurator(null);
        } else {
            tfEmail.setStyle(new TextField().getStyle());
            returnCompany.setMailCurator(company.getMailCurator());
        }

        if (company.getWebsiteCompany() == null || company.getWebsiteCompany().isEmpty()) {
            tfWeb.setStyle(StyleSRC.STYLE_WARNING);
            tfWeb.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setWebsiteCompany(company.getWebsiteCompany());
        } else {
            tfWeb.setStyle(new TextField().getStyle());
            returnCompany.setWebsiteCompany(company.getWebsiteCompany());
        }

        if (company.getLogoCompany() == null || company.getLogoCompany().isEmpty()) {
            imgLogoCompany.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/upphoto.png"))));
            returnCompany.setLogoCompany(null);
        } else {
            returnCompany.setLogoCompany(company.getLogoCompany());
        }

        if (company.getManagerLastName() == null || company.getManagerLastName().isEmpty()) {
            tfLastNameManager.setStyle(StyleSRC.STYLE_WARNING);
            tfLastNameManager.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setManagerLastName(null);
        } else {
            tfLastNameManager.setStyle(new TextField().getStyle());
            returnCompany.setManagerLastName(company.getManagerLastName());
        }

        if (company.getMailManager() == null || company.getMailManager().isEmpty()) {
            tfManagerMail.setStyle(StyleSRC.STYLE_WARNING);
            tfManagerMail.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setMailManager(null);
        } else {
            tfManagerMail.setStyle(new TextField().getStyle());
            returnCompany.setMailManager(company.getMailManager());
        }

        if (company.getPhoneManager() == null || company.getPhoneManager().isEmpty()) {
            tfManagerPhone.setStyle(StyleSRC.STYLE_WARNING);
            tfManagerPhone.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setPhoneManager(null);
        } else {
            tfManagerPhone.setStyle(new TextField().getStyle());
            returnCompany.setPhoneManager(company.getPhoneManager());
        }

        if (company.getEngineerLastName() == null || company.getEngineerLastName().isEmpty()) {
            tfLastNameEngineer.setStyle(StyleSRC.STYLE_WARNING);
            tfLastNameEngineer.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setEngineerLastName(null);
        } else {
            tfLastNameEngineer.setStyle(new TextField().getStyle());
            returnCompany.setEngineerLastName(company.getEngineerLastName());
        }

        if (company.getPhoneEngineer() == null || company.getPhoneEngineer().isEmpty()) {
            tfEngineerPhone.setStyle(StyleSRC.STYLE_WARNING);
            tfEngineerPhone.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setPhoneEngineer(null);
        } else {
            tfEngineerPhone.setStyle(new TextField().getStyle());
            returnCompany.setPhoneEngineer(company.getPhoneEngineer());
        }

        if (company.getMailEngineer() == null || company.getMailEngineer().isEmpty()) {
            tfEngineerMail.setStyle(StyleSRC.STYLE_WARNING);
            tfEngineerMail.setPromptText(language.WILL_NOT_BE_ADDED(lang));
            returnCompany.setMailEngineer(null);
        } else {
            tfEngineerMail.setStyle(new TextField().getStyle());
            returnCompany.setMailEngineer(company.getMailEngineer());
        }

        if (returnCompany.getNameCompany() != null) {
            return returnCompany;
        } else return null;
    }
}
