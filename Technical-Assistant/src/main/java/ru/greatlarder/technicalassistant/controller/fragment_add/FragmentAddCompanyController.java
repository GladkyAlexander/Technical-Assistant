package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.company.CompanyByNameSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.company.SetCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.company.UpdateCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.GetListEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.phone_book.SetPhoneBookSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.ListRoomByCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetListSeatingArrangementsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.*;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageWarningsImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentAddCompanyController implements ObserverLang, Initializable {
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
    @FXML public Label labelRooms;
    @FXML public ImageView imgRoomAdd;
    @FXML public Label labelTypesOfEvents;
    @FXML public ComboBox<String> comboBoxRooms;
    @FXML public ComboBox<String> comboBoxEvent;
    @FXML public ImageView imgAddEvent;
    @FXML public Button btnSaveChange;
    @FXML public Label labelSeatingArrangements;
    @FXML public HBox hBoxArrangement;
    @FXML public ComboBox<String> comboBoxSeatingArrangement;
    @FXML public ImageView imgAddPaneSeating;
    @FXML public ScrollPane scrollPane;
    @FXML public BorderPane borderPane;
    @FXML public HBox hBoxRoom;
    @FXML public HBox hBoxEvents;
    Language language = new LanguageImpl();
    String lang;
    FileManager fileManager = new FileManagerImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    Company company;
    User user;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }

    public void loadFragment(){
        btnSaveChange.setVisible(false);
        btnSaveChange.setManaged(false);
        labelRooms.setVisible(false);
        labelRooms.setManaged(false);
        labelTypesOfEvents.setVisible(false);
        labelTypesOfEvents.setManaged(false);
        labelSeatingArrangements.setVisible(false);
        labelSeatingArrangements.setManaged(false);
        hBoxRoom.setVisible(false);
        hBoxRoom.setManaged(false);
        hBoxEvents.setVisible(false);
        hBoxEvents.setManaged(false);
        hBoxArrangement.setVisible(false);
        hBoxArrangement.setManaged(false);
    }

    public void loadChangeCompanyFragment(Company company){
        btnSendCompany.setVisible(false);
        btnSendCompany.setManaged(false);
        this.company = company;
        loadDataFragment();
    }
    
    private void loadDataFragment(){
        tfNameCompany.setText(company.getNameCompany());
        tfAddress.setText(company.getAddress());
        tfNumberPhone.setText(company.getPhoneCurator());
        tfEmail.setText(company.getMailCurator());
        tfWeb.setText(company.getWebsiteCompany());
        tfEngineerPhone.setText(company.getPhoneEngineer());
        tfEngineerMail.setText(company.getMailEngineer());
        tfManagerPhone.setText(company.getPhoneManager());
        tfManagerMail.setText(company.getMailManager());
        tfCuratorLastName.setText(company.getCuratorLastName());
        tfCuratorFirstName.setText(company.getCuratorFirstName());
        tfLastNameManager.setText(company.getManagerLastName());
        tfFirstNameManager.setText(company.getManagerFirstName());
        tfLastNameEngineer.setText(company.getEngineerLastName());
        tfFirstNameEngineer.setText(company.getEngineerFirstName());
        imgLogoCompany.setImage(new Image(fileManager.folderImage() + "\\" + company.getLogoCompany()));
        List<String> roomsList = new ArrayList<>();
        GetListRoom getListRoom = new ListRoomByCompanySQLite();
        for(Room room : getListRoom.getListRoom(user, company.getNameCompany(), null)){
            roomsList.add(room.getNameRoom());
        }
        comboBoxRooms.setItems(FXCollections.observableArrayList(roomsList));
        List<String> eventsList = new ArrayList<>();
        GetListEventFormat getListEventFormat = new GetListEventFormatSQLite();
        for (EventFormat event : getListEventFormat.getListEventFormat(user, company.getNameCompany(), null)) {
           eventsList.add(event.getNameEventFormat());
        }
        comboBoxEvent.setItems(FXCollections.observableArrayList(eventsList));
        List<String> arrangements = new ArrayList<>();
        GetListSeatingArrangements getListSeatingArrangements = new GetListSeatingArrangementsSQLite();
        for (SeatingArrangements arrangements1 : getListSeatingArrangements.getListSeatingArrangements(user, company.getNameCompany(), null)){
            arrangements.add(arrangements1.getNameSeatingArrangements());
        }
        comboBoxSeatingArrangement.setItems(FXCollections.observableArrayList(arrangements));
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
        labelRooms.setText(language.ROOMS(lang));
        labelTypesOfEvents.setText(language.TYPES_OF_EVENTS(lang));
        comboBoxRooms.setPromptText(language.ALL_ROOMS(lang));
        comboBoxEvent.setPromptText(language.ALL_EVENTS(lang));
        labelSeatingArrangements.setText(language.TYPES_OF_FURNITURE_ARRANGEMENT(lang));
    }

    public void closeAddCompanyController() {
        handlerLang.unregisterObserverLang(this);
        ((BorderPane) borderPane.getParent()).getChildren().remove(borderPane);
    }

    public void imagePicker() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG, extensionFilter);

        File chooserFile = fileChooser.showOpenDialog(imgLogoCompany.getScene().getWindow());
        if (chooserFile != null && chooserFile.length() < 494592) {
            Image image = new Image(chooserFile.toURI().toString());
            
            imgLogoCompany.setImage(image);
            
            File file1 = new File(fileManager.folderImage() + "\\" + image.getUrl().substring(image.getUrl().lastIndexOf('/') + 1));
            try {
                FileUtils.copyFile(chooserFile, file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            LanguageWarnings languageWarnings = new LanguageWarningsImpl();
            alert.setTitle(languageWarnings.INCORRECT_IMAGE(lang));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            alert.setContentText(languageWarnings.THE_PICTURE_SHOULD_BE_NO_MORE_THAN_483_KB(lang));
            alert.showAndWait();
        }
    }

    public void sendCompany() {
        Company companyS = new Company();
        companyS.setNameCompany(tfNameCompany.getText());
        companyS.setAddress(tfAddress.getText());
        companyS.setCuratorLastName(tfCuratorLastName.getText());
        companyS.setCuratorFirstName(tfCuratorFirstName.getText());
        companyS.setPhoneCurator(tfNumberPhone.getText());
        companyS.setMailCurator(tfEmail.getText());
        companyS.setWebsiteCompany(tfWeb.getText());
        companyS.setLogoCompany(imgLogoCompany.getImage().getUrl());
        companyS.setManagerLastName(tfLastNameManager.getText());
        companyS.setManagerFirstName(tfFirstNameManager.getText());
        companyS.setPhoneManager(tfManagerPhone.getText());
        companyS.setMailManager(tfManagerMail.getText());
        companyS.setEngineerLastName(tfLastNameEngineer.getText());
        companyS.setEngineerFirstName(tfFirstNameEngineer.getText());
        companyS.setPhoneEngineer(tfEngineerPhone.getText());
        companyS.setMailEngineer(tfEngineerMail.getText());

        Company company1 = checkCompany(companyS);
        SetCompany setCompany = new SetCompanySQLite();
        if (company1 != null) {
            fileManager.createDirectoryCompany(company1.getNameCompany());
            
            if(setCompany.setCompany(user, company1) != null){
                
                SetPhoneBook phoneBook = new SetPhoneBookSQLite();
                
                PhoneBook phoneBook1 = new PhoneBook();
                phoneBook1.setFirstName(tfCuratorFirstName.getText());
                phoneBook1.setLastName(tfCuratorLastName.getText());
                phoneBook1.setPhone(tfNumberPhone.getText());
                phoneBook1.setMail(tfEmail.getText());
                
                phoneBook.setPhoneBook(user, company1.getNameCompany(), phoneBook1);
                
                PhoneBook phoneBook2 = new PhoneBook();
                phoneBook2.setFirstName(tfFirstNameManager.getText());
                phoneBook2.setLastName(tfLastNameManager.getText());
                phoneBook2.setPhone(tfManagerPhone.getText());
                phoneBook2.setMail(tfManagerMail.getText());
                
                phoneBook.setPhoneBook(user, company1.getNameCompany(), phoneBook2);
                
                PhoneBook phoneBook3 = new PhoneBook();
                phoneBook3.setFirstName(tfFirstNameEngineer.getText());
                phoneBook3.setLastName(tfLastNameEngineer.getText());
                phoneBook3.setPhone(tfEngineerPhone.getText());
                phoneBook3.setMail(tfEngineerMail.getText());
                
                phoneBook.setPhoneBook(user, company1.getNameCompany(), phoneBook3);
                
                labelOk.setText(tfNameCompany.getText() + language.ADDED(lang));
                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
                
                fileManager.createDirectoryCompany(company1.getNameCompany());
                cleanAddCompany();
            }
            
        } else {
            labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
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
        GetCompany getCompany = new CompanyByNameSQLite();
        if (company.getNameCompany() == null) {
            returnCompany.setNameCompany(null);
            tfNameCompany.setStyle(StyleSRC.STYLE_DANGER);
        } else if (company.getNameCompany() != null
                && getCompany.getCompany(user, company.getNameCompany()) != null) {
            returnCompany.setNameCompany(null);
            tfNameCompany.setStyle(StyleSRC.STYLE_DANGER);
            labelOk.setText(language.ALREADY_HAVE(lang));
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
        } else if (company.getNameCompany() != null
                && getCompany.getCompany(user, company.getNameCompany()) == null) {
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
    
    public void saveChange() {
        Company companyN = new Company();
        companyN.setNameCompany(tfNameCompany.getText());
        companyN.setAddress(tfAddress.getText());
        companyN.setCuratorLastName(tfCuratorLastName.getText());
        companyN.setCuratorFirstName(tfCuratorFirstName.getText());
        companyN.setPhoneCurator(tfNumberPhone.getText());
        companyN.setMailCurator(tfEmail.getText());
        companyN.setWebsiteCompany(tfWeb.getText());
        
        companyN.setLogoCompany(imgLogoCompany.getImage().getUrl());
        
        companyN.setManagerLastName(tfLastNameManager.getText());
        companyN.setManagerFirstName(tfFirstNameManager.getText());
        companyN.setPhoneManager(tfManagerPhone.getText());
        companyN.setMailManager(tfManagerMail.getText());
        companyN.setEngineerLastName(tfLastNameEngineer.getText());
        companyN.setEngineerFirstName(tfFirstNameEngineer.getText());
        companyN.setPhoneEngineer(tfEngineerPhone.getText());
        companyN.setMailEngineer(tfEngineerMail.getText());

        Company company1 = checkCompany(companyN);

        GetCompany getCompanyByName = new CompanyByNameSQLite();
        UpdateCompany updateCompany = new UpdateCompanySQLite();
        GetUser getUserLoginPassword = new GetUserSQLite();
        if (company1 != null) {
            fileManager.createDirectoryCompany(company1.getNameCompany());

            updateCompany.updateCompany(user, company1);

            if (getCompanyByName.getCompany(user, company1.getNameCompany()).getNameCompany().equals(company1.getNameCompany())) {
                labelOk.setText(tfNameCompany.getText() + language.ADDED(lang));
                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));

                fileManager.createDirectoryCompany(company1.getNameCompany());
                GlobalLinkMainController.getMainController().updateUser(new DataUser(getUserLoginPassword.getUser(this.user.getLogin(), this.user.getPassword())));
                cleanAddCompany();
            }

        } else {
            labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
        }

    }
    public void openAddRooms() {
        if(company != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_room.fxml"));
            try {
                borderPane.setRight(loader.load());
                FragmentAddRoom controller = loader.getController();
                controller.loadFragment(company);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void openAddEvent() {
        if(company != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_name_event.fxml"));
            try {
                borderPane.setRight(loader.load());
                FragmentAddNameEvent controller = loader.getController();
                controller.loadFragment(company);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void openPaneSeating() {
        if(company != null){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_seating_arrangements.fxml"));
            try {
                borderPane.setRight(loader.load());
                FragmentAddSeatingArrangements controller = loader.getController();
                controller.loadFragment(company);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }
}
