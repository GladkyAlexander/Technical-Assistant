package ru.greatlarder.technicalassistant.controller;

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
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentRegistrationUserController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.ProblemMonitor;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;

import java.io.IOException;
import java.util.Objects;

public class MainController implements ObserverLang {
    @FXML
    public BorderPane borderPaneMainPage;
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
    public HBox hBoxTopToolbar;
    @FXML
    public ImageView imgEquipment;
    @FXML
    public ImageView imgTool;
    @FXML
    public ImageView imgIpAddress;
    @FXML
    public ImageView imgAllDefect;
    @FXML
    public ImageView imgAddEquipment;
    @FXML
    public ImageView imgAddTool;
    @FXML
    public ImageView imgAddDefect;
    @FXML
    public ImageView imgWatchWorkProg;
    @FXML
    public ImageView imgEngine;
    @FXML
    public ImageView imgAddTaskReception;
    @FXML
    public TextField tfSerNum;
    @FXML
    public MenuButton mbtLang;
    @FXML
    public MenuItem menuItemRu;
    @FXML
    public MenuItem menuItemEn;
    @FXML
    public ImageView imgLangMenuButton;
    @FXML
    public VBox vBoxStartLayout;
    @FXML
    public Label labelWeAreGlad;
    @FXML
    public Label labelStartByRegistering;
    @FXML
    public ImageView imgSearch;
    public User user;
    public static Company company;
    HandlerUserListener handlerUserListener = new HandlerUserListener();
    HandlerLang handlerLang = new HandlerLang();
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        setLanguage(dataLang.getLanguage());
    }

    public void loadPage() {

        mbtLang.setText(menuItemRu.getText());
        imgLangMenuButton.setImage(new Image(Objects.requireNonNull(getClass()
                .getResourceAsStream("/ru/greatlarder/technicalassistant/images/ru.png"))));

        GlobalLinkMainController.setMainController(this);

        setToolBar();

    }

    private void setToolBar(){
        borderPaneMainPage.getChildren().remove(borderPaneMainPage.getCenter());
        if(user == null){
            tfSerNum.setVisible(false);
            imgSearch.setVisible(false);
            vBoxNameCompany.setVisible(false);
            vBoxNameCompany.setManaged(false);
            imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            imageViewLogo.setFitHeight(131);
            imageViewLogo.setFitWidth(220);
            imgEquipment.setVisible(false);
            imgAddEquipment.setVisible(false);
            imgTool.setVisible(false);
            imgAddTool.setVisible(false);
            imgIpAddress.setVisible(false);
            imgAllDefect.setVisible(false);
            imgAddDefect.setVisible(false);
            imgWatchWorkProg.setVisible(false);
            imgAddTaskReception.setVisible(false);
            handlerUserListener.onNewDataUser(new DataUser(user));
            btnSelectCompanies.setDisable(true);
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
        if(user != null && (user.getPost().equals("Инженер") || user.getPost().equals("Engineer"))){
            tfSerNum.setVisible(true);
            imgSearch.setVisible(true);
            imgEquipment.setVisible(true);
            imgAddEquipment.setVisible(true);
            imgTool.setVisible(true);
            imgAddTool.setVisible(true);
            imgIpAddress.setVisible(true);
            imgAllDefect.setVisible(true);
            imgAddDefect.setVisible(true);
            imgWatchWorkProg.setVisible(true);
            imgSearch.setVisible(true);
            imgSearch.setManaged(true);
            imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            imageViewLogo.setFitHeight(131);
            imageViewLogo.setFitWidth(220);

            if(user.getCompanyList() != null && user.getCompanyList().size() > 0){
                imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment.png"))));
                imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment.png"))));
                imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button.png"))));
                imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add.png"))));
                imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip.png"))));
                imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect.png"))));
                imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add.png"))));
                imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time.png"))));
                if (company != null) {

                    if (new ProblemMonitor().searchProblemMonitor(company.getNameCompany())) {
                        imgEngine.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/brakes_warning_light.png"))));
                    } else
                        imgEngine.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/brakes_ok_light.png"))));
                }
            } else {
                imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment_un_active.png"))));
                imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment_un_active.png"))));
                imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button_in_active.png"))));
                imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add_in_active.png"))));
                imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip_un_active.png"))));
                imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect_un_active.png"))));
                imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add_un_active.png"))));
                imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time_un_active.png"))));
            }

        }
        if(user != null && (user.getPost().equals("Reception Secretary") || user.getPost().equals("Секретарь приемной"))){
            imgAddTaskReception.setVisible(true);
            vBoxNameCompany.setVisible(false);
            vBoxNameCompany.setManaged(false);
            imageViewLogo.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            imageViewLogo.setFitWidth(50);
            imageViewLogo.setFitHeight(50);
            vBoxNameCompany.setVisible(true);
            vBoxNameCompany.setManaged(true);
        }
    }
    public void setLanguage(String lang) {
        labelHomePage.setText(language.HOME_PAGE(lang));
        labelManuals.setText(language.DOCUMENTATION(lang));
        labelSettings.setText(language.SETTINGS(lang));
        labelInfo.setText(language.INFORMATION(lang));
        btnSelectCompanies.setText(language.CHOOSE_A_COMPANY(lang));
        labelWeAreGlad.setText(language.WE_ARE_GLAD_OF_YOUR_CHOICE(lang));
        labelStartByRegistering.setText(language.START_BY_REGISTERING_ON_THE_SETTINGS_PAGE(lang));
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

    public void clickHomePage(MouseEvent mouseEvent) {
    }

    public void clickDocumentation(MouseEvent mouseEvent) {
    }

    public void clickPageSettings(MouseEvent mouseEvent) {

    }

    public void openPageInfo(MouseEvent mouseEvent) {
    }

    public void allEquipment(MouseEvent mouseEvent) {
    }

    public void allListTool(MouseEvent mouseEvent) {
    }

    public void allIpAddress(MouseEvent mouseEvent) {
    }

    public void allDefect(MouseEvent mouseEvent) {
    }

    public void addEquipment(MouseEvent mouseEvent) {
    }

    public void addTool(MouseEvent mouseEvent) {
    }

    public void addDefect(MouseEvent mouseEvent) {
    }

    public void watchWorkProjectors(MouseEvent mouseEvent) {
    }

    public void imgEnginSearch(MouseEvent mouseEvent) {
    }

    public void addTask(MouseEvent mouseEvent) {
    }

    public void addPhoneBook(MouseEvent mouseEvent) {
    }

    public void searchSerialNumber(MouseEvent mouseEvent) {
    }

    public void startMenuButton(MouseEvent mouseEvent) {
        btnSelectCompanies.getItems().clear();
        menuButtonStart();
    }

    public void menuButtonStart() {
        if(user.getCompanyList().size() == 1){
            for (Company company1 : user.getCompanyList()) {
                company = company1;
                btnSelectCompanies.setText(company.getNameCompany());
            }
        }
        if(user.getCompanyList().size() > 1) {
            for (Company company : user.getCompanyList()) {
                MenuItem menuItem = new MenuItem(company.getNameCompany());
                menuItem.setOnAction(eventEventHandler);
                btnSelectCompanies.getItems().addAll(menuItem);
            }
        }
    }

    EventHandler<ActionEvent> eventEventHandler = new EventHandler<>() {
        @Override
        public void handle(ActionEvent event) {

            btnSelectCompanies.setText(((MenuItem) event.getSource()).getText());

            for (Company company1 : user.getCompanyList()){
                if(company1.getNameCompany().equals(((MenuItem) event.getSource()).getText())){
                    company = company1;
                }
            }

            ptAdr.prefColumnCountProperty().bind(ptAdr.textProperty().length());
            ptAdr.setText(company.getAddress());
            ptCur.setText(company.getCurator());
            ptPhone.setText(company.getPhoneCurator());
            ptEmail.setText(company.getMailCurator());
            imageViewLogo.setImage(new Image( fileManager.folderImage() +"\\" + company.getLogoCompany()));
        }

    };
}