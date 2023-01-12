package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemTypesEvent;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.*;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentAddCompanyController implements ObserverLang, ObserverCompany, ObserverUser {
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
    @FXML public VBox vBoxRooms;
    @FXML public Button btnAddRoom;
    @FXML public TextField tfNewRoom;
    @FXML public Label labelTypesOfEvents;
    @FXML public ComboBox<String> comboBoxRooms;
    @FXML public ComboBox<String> comboBoxEvent;
    @FXML public ImageView imgAddEvent;
    @FXML public ImageView imgMeeting;
    @FXML public ImageView imgPresent;
    @FXML public ImageView imgSkype;
    @FXML public ImageView imgVCS;
    @FXML public ImageView imgZoom;
    @FXML public ListView<Events> listViewEventForCompany;
    @FXML public Label labelNameEvent;
    @FXML public TextField tfNameEvent;
    @FXML public ImageView imgLogoEvent;
    @FXML public Label labelLogoEvent;
    @FXML public Button btnAddEvent;
    @FXML public HBox hBoxEvent;
    @FXML public ScrollPane scrolPaneAC;
    @FXML public Button btnSaveChange;
    @FXML public Label labelSeatingArangements;
    @FXML public HBox hBoxArangement;
    @FXML public ComboBox<String> comboBoxSeatingArrangement;
    @FXML public ImageView imgAddPaneSeating;
    @FXML public Label labelNameTip;
    @FXML public Label labelPhotoTip;
    @FXML public ImageView imgNewImageSeating;
    @FXML public Button btnAddNewSeating;
    @FXML public ListView listViewSeatingArangements;
    @FXML public TextField textFieldNewNameTip;
    Language language = new LanguageImpl();
    String fileName;
    private String lang;
    CompanyRepository companyRepository = new CompanyRepositoryImpl();
    EventsRepository eventsRepository = new EventsRepositoryImpl();
    RoomsRepository roomsRepository = new RoomsRepositoryImpl();
    SeatingRepository seatingRepository = new SeatingRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();
    FileManager fileManager = new FileManagerImpl();
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().getHandlerUserListener();
    HandlerCompanyListener handlerCompanyListener = GlobalLinkMainController.mainController.getHandlerCompanyListener();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    private Company company;
    private User user;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(dataLang.getLanguage());
    }

    public void loadFragment(){
        btnSaveChange.setVisible(false);
        btnSaveChange.setManaged(false);
        loadUINewRooms(false);
        loadUINewEvents(false);
        loadUINewArrangement(false);
    }

    public void loadChangeCompanyFragment(Company company){
        this.company = company;
        btnSendCompany.setVisible(false);
        btnSendCompany.setManaged(false);
        loadUINewRooms(false);
        loadUINewEvents(false);
        loadUINewArrangement(false);
        loadDataFragment();
    }
    private void loadUINewArrangement(Boolean enabled){
        labelNameTip.setVisible(enabled);
        labelNameTip.setManaged(enabled);
        textFieldNewNameTip.setVisible(enabled);
        textFieldNewNameTip.setManaged(enabled);
        labelPhotoTip.setVisible(enabled);
        labelPhotoTip.setManaged(enabled);
        imgNewImageSeating.setVisible(enabled);
        imgNewImageSeating.setManaged(enabled);
        btnAddNewSeating.setVisible(enabled);
        btnAddNewSeating.setManaged(enabled);
        listViewSeatingArangements.setVisible(enabled);
        listViewSeatingArangements.setManaged(enabled);
    }

    private void loadUINewRooms(Boolean enabled) {
        tfNewRoom.setVisible(enabled);
        tfNewRoom.setManaged(enabled);
        btnAddRoom.setVisible(enabled);
        btnAddRoom.setManaged(enabled);
        vBoxRooms.setVisible(enabled);
        vBoxRooms.setManaged(enabled);
        scrolPaneAC.setVisible(enabled);
        scrolPaneAC.setManaged(enabled);

    }
    private void loadUINewEvents(Boolean enabled){
        hBoxEvent.setVisible(enabled);
        hBoxEvent.setManaged(enabled);
        labelNameEvent.setVisible(enabled);
        labelNameEvent.setManaged(enabled);
        labelLogoEvent.setVisible(enabled);
        labelLogoEvent.setManaged(enabled);
        tfNameEvent.setVisible(enabled);
        tfNameEvent.setManaged(enabled);
        imgLogoEvent.setVisible(enabled);
        imgLogoEvent.setManaged(enabled);
        btnAddEvent.setVisible(enabled);
        btnAddEvent.setManaged(enabled);
        listViewEventForCompany.setVisible(enabled);
        listViewEventForCompany.setManaged(enabled);
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
        for(Room room : roomsRepository.getListRoomForCompany(company.getNameCompany())){
            roomsList.add(room.getNameRoom());
        }
        comboBoxRooms.setItems(FXCollections.observableArrayList(roomsList));

        List<String> eventsList = new ArrayList<>();
        for (Events event : eventsRepository.getListEventsForCompany(company.getNameCompany())) {
           eventsList.add(event.getNameEvent());
        }
        comboBoxEvent.setItems(FXCollections.observableArrayList(eventsList));

        List<String> arrangements = new ArrayList<>();
        for (SeatingArrangements arrangements1 : seatingRepository.getListSeatingArrangementsForCompany(company.getNameCompany())){
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
        btnAddRoom.setText(language.ADD(lang));
        labelTypesOfEvents.setText(language.TYPES_OF_EVENTS(lang));
        comboBoxRooms.setPromptText(language.ALL_ROOMS(lang));
        comboBoxEvent.setPromptText(language.ALL_EVENTS(lang));
        labelNameEvent.setText(language.EVENT_NAME(lang));
        labelLogoEvent.setText(language.EVENT_LOGO(lang));
        btnAddEvent.setText(language.ADD(lang));
        labelNameTip.setText(language.TYPE_NAME(lang));
        labelPhotoTip.setText(language.IMAGE(lang));
        btnAddNewSeating.setText(language.ADD(lang));
        labelSeatingArangements.setText(language.TYPES_OF_FURNITURE_ARRANGEMENT(lang));
    }

    public void closeAddCompanyController() {
        handlerLang.unregisterObserverLang(this);
        handlerCompanyListener.unregisterObserverCompany(this);
        handlerUserListener.unregisterObserverUser(this);
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
        Company companyS = new Company();
        companyS.setNameCompany(tfNameCompany.getText());
        companyS.setAddress(tfAddress.getText());
        companyS.setCuratorLastName(tfCuratorLastName.getText());
        companyS.setCuratorFirstName(tfCuratorFirstName.getText());
        companyS.setPhoneCurator(tfNumberPhone.getText());
        companyS.setMailCurator(tfEmail.getText());
        companyS.setWebsiteCompany(tfWeb.getText());
        companyS.setLogoCompany(fileName);
        companyS.setManagerLastName(tfLastNameManager.getText());
        companyS.setManagerFirstName(tfFirstNameManager.getText());
        companyS.setPhoneManager(tfManagerPhone.getText());
        companyS.setMailManager(tfManagerMail.getText());
        companyS.setEngineerLastName(tfLastNameEngineer.getText());
        companyS.setEngineerFirstName(tfFirstNameEngineer.getText());
        companyS.setPhoneEngineer(tfEngineerPhone.getText());
        companyS.setMailEngineer(tfEngineerMail.getText());

        Company company1 = checkCompany(companyS);

        if (company1 != null) {
            fileManager.createDirectoryCompany(company1.getNameCompany());
            
            if(companyRepository.setCompany(user.getId(), company1) != null){
                labelOk.setText(tfNameCompany.getText() + language.ADDED(lang));
                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
    
                fileManager.createDirectoryCompany(company1.getNameCompany());
    
                GlobalLinkMainController.getMainController().updateUser(new DataUser(userRepository.getUserLoginPassword(this.user.getLogin(), this.user.getPassword())));
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

    public void addNewRoom(MouseEvent mouseEvent) {
        if(company != null) {
            if(!tfNewRoom.getText().isEmpty()) {
                RoomsRepository repository = new RoomsRepositoryImpl();
                Room room = new Room();
                room.setNameRoom(tfNewRoom.getText());
                room.setNameCompanyForRoom(company.getNameCompany());
                repository.setRoom(room);
                tfNewRoom.clear();
            } else tfNewRoom.setStyle(StyleSRC.STYLE_WARNING);
        }
        vBoxRooms.getChildren().clear();
        for (Room name : getRoom()){
            vBoxRooms.getChildren().add(new Label(name.getNameRoom()));
        }
    }

    public void imgOpenAddRooms(MouseEvent mouseEvent) {
        loadUINewRooms(true);
        vBoxRooms.getChildren().clear();
        for (Room name : getRoom()){
            vBoxRooms.getChildren().add(new Label(name.getNameRoom()));
        }
    }

    private List<Room> getRoom() {
        List<Room> result = new ArrayList<Room>();
        if(company != null) {
            RoomsRepository repository = new RoomsRepositoryImpl();
            result = repository.getListRoomForCompany(company.getNameCompany());
        }
        return result;
    }

    public void openAddEvent(MouseEvent mouseEvent) {
        loadUINewEvents(true);
        loadEvents();
    }

    public void addMeeting(MouseEvent mouseEvent) {
        tfNameEvent.setText(language.MEETING(lang));
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/meeting.png")));
        saveLogoEvent(img, "meeting.png");
        imgLogoEvent.setImage(new Image(fileManager.folderImage() + '/' + "meeting.png"));
    }

    public void addPresent(MouseEvent mouseEvent) {
        tfNameEvent.setText(language.PRESENTATION(lang));
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/present.png")));
        saveLogoEvent(img, "present.png");
        imgLogoEvent.setImage(new Image(fileManager.folderImage() + '/' + "present.png"));
    }

    public void addSkype(MouseEvent mouseEvent) {
        tfNameEvent.setText("Skype");
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/skype.png")));
        saveLogoEvent(img, "skype.png");
        imgLogoEvent.setImage(new Image(fileManager.folderImage() + '/' + "skype.png"));
    }

    public void addVCS(MouseEvent mouseEvent) {
        tfNameEvent.setText("VCS");
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/vcs.png")));
        saveLogoEvent(img, "vcs.png");
        imgLogoEvent.setImage(new Image(fileManager.folderImage() + '/' + "vcs.png"));
    }

    public void addZoom(MouseEvent mouseEvent) {
        tfNameEvent.setText("Zoom");
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/zoom.png")));
        saveLogoEvent(img, "zoom.png");
        imgLogoEvent.setImage(new Image(fileManager.folderImage() + '/' + "zoom.png"));
    }

    public void loadLogoEvent(MouseEvent mouseEvent) {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterPNG, extensionFilter);

            File chooserFile = fileChooser.showOpenDialog(imgLogoCompany.getScene().getWindow());

            if (chooserFile != null) {
                Image image = new Image(chooserFile.toURI().toString());

                String nameLogoFile = image.getUrl().substring(image.getUrl().lastIndexOf('/') + 1);

                saveLogoEvent(image, nameLogoFile);

                imgLogoEvent.setImage(new Image(fileManager.folderImage() + '/' + nameLogoFile));
            }
    }

    public void addNewEvent(MouseEvent mouseEvent) {
        Events events = new Events();
        events.setNameEvent(tfNameEvent.getText());
        events.setUrlImageEvent(imgLogoEvent.getImage().getUrl().substring(imgLogoEvent.getImage().getUrl().lastIndexOf('/') + 1));
        events.setNameCompany(company.getNameCompany());

        eventsRepository.setEvent(events);
        loadEvents();

    }

    private void loadEvents(){

        listViewEventForCompany.getItems().clear();

        EventsRepository repository = new EventsRepositoryImpl();
        ObservableList<Events> eventsObservableList = FXCollections.observableArrayList(repository.getListEventsForCompany(company.getNameCompany()));
        listViewEventForCompany.setItems(eventsObservableList);
        listViewEventForCompany.setCellFactory(param-> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_types_events.fxml"));
            Node node;
            ItemTypesEvent controller;
            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected void updateItem(Events events, boolean b) {
                super.updateItem(events, b);
                if(b){
                    setGraphic(null);
                } else {

                    controller.updateLang(new DataLang(lang));

                    controller.setEvents(events);
                    controller.setLabelEvent(events.getNameEvent());
                    controller.setImg_event(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/events_img/" + events.getUrlImageEvent()))));
                    setGraphic(node);
                }
            }
        });
    }

    private void saveLogoEvent(Image file, String name){
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(file, null), "png",
                        new FileOutputStream(fileManager.folderImage() + '/' + name));
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public void saveChange(MouseEvent mouseEvent) {

        convert(imgLogoCompany.getImage(), fileName);
        Company companyN = new Company();
        companyN.setNameCompany(tfNameCompany.getText());
        companyN.setAddress(tfAddress.getText());
        companyN.setCuratorLastName(tfCuratorLastName.getText());
        companyN.setCuratorFirstName(tfCuratorFirstName.getText());
        companyN.setPhoneCurator(tfNumberPhone.getText());
        companyN.setMailCurator(tfEmail.getText());
        companyN.setWebsiteCompany(tfWeb.getText());
        companyN.setLogoCompany(fileName);
        companyN.setManagerLastName(tfLastNameManager.getText());
        companyN.setManagerFirstName(tfFirstNameManager.getText());
        companyN.setPhoneManager(tfManagerPhone.getText());
        companyN.setMailManager(tfManagerMail.getText());
        companyN.setEngineerLastName(tfLastNameEngineer.getText());
        companyN.setEngineerFirstName(tfFirstNameEngineer.getText());
        companyN.setPhoneEngineer(tfEngineerPhone.getText());
        companyN.setMailEngineer(tfEngineerMail.getText());

        Company company1 = checkCompany(companyN);

        if (company1 != null) {
            fileManager.createDirectoryCompany(company1.getNameCompany());

            companyRepository.changeCompany(company1);

            if (companyRepository.getCompanyName(company1.getNameCompany()).getNameCompany().equals(company1.getNameCompany())) {
                labelOk.setText(tfNameCompany.getText() + language.ADDED(lang));
                imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));

                fileManager.createDirectoryCompany(company1.getNameCompany());
                GlobalLinkMainController.getMainController().updateUser(new DataUser(userRepository.getUserLoginPassword(this.user.getLogin(), this.user.getPassword())));
                cleanAddCompany();
            }

        } else {
            labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
        }

    }

    public void openPaneSeating(MouseEvent mouseEvent) {
        loadUINewArrangement(true);
        listViewSeatingArangements.setItems(FXCollections.observableArrayList(seatingRepository.getListSeatingArrangementsForCompany(company.getNameCompany())));
    }

    public void addImageSeating(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterPNG, extensionFilter);

        File chooserFile = fileChooser.showOpenDialog(imgNewImageSeating.getScene().getWindow());

        if (chooserFile != null) {
            Image image = new Image(chooserFile.toURI().toString());

            String nameLogoFile = image.getUrl().substring(image.getUrl().lastIndexOf('/') + 1);

            saveLogoEvent(image, nameLogoFile);

            imgNewImageSeating.setImage(new Image(fileManager.folderImage() + '/' + nameLogoFile));
        }
    }

    public void addNewSeating(MouseEvent mouseEvent) {

        SeatingArrangements arrangements = new SeatingArrangements();
        if(imgNewImageSeating.getImage() != null) {
            arrangements.setUrlImageSeatingArrangements(imgNewImageSeating.getImage().getUrl().substring(imgLogoEvent.getImage().getUrl().lastIndexOf('/') + 1));
        }
        arrangements.setNameCompany(company.getNameCompany());
        arrangements.setNameSeatingArrangements(textFieldNewNameTip.getText());

        SeatingRepository seatingRepository = new SeatingRepositoryImpl();
        seatingRepository.setSeatingArrangements(arrangements);

        listViewSeatingArangements.getItems().add(FXCollections.observableArrayList(seatingRepository
                .getSeatingArrangementsForName(arrangements.getNameCompany(), arrangements.getNameSeatingArrangements())));

    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else this.company = dataCompany.getCompany();
        loadFragment();
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
