package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CompanyRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.ToolsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.CompanyRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.ToolsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class FragmentAddToolController implements ObserverLang, ObserverCompany, ObserverUser {
    @FXML public TextField tfNameTool1;
    @FXML public TextField tfBrandTool1;
    @FXML public DatePicker dpStartOfOperation1;
    @FXML public MenuButton mbCondition1;
    @FXML public TextField tfSerialNumberTool1;
    @FXML public GridPane gridPaneToolAdd;
    @FXML public Button btnSave;
    @FXML public Label labelNameTool;
    @FXML public Label labelManufacturer;
    @FXML public Label labelSerialNumber;
    @FXML public Label labelDateWork;
    @FXML public Label labelOk1;
    @FXML public ImageView imgOk1;
    @FXML public MenuItem menuItemFaulty;
    @FXML public MenuItem menuItemRequiresRepair;
    @FXML public MenuItem menuItemSatisfactory;
    @FXML public ImageView close1;
    @FXML public ComboBox<String> comboBoxChooseACompany;

    private Company company;
    ToolsRepository toolsRepository = new ToolsRepositoryImpl();
    private String lang;
    Language language = new LanguageImpl();
    private User user;
    UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    public void sendTool(ActionEvent actionEvent) {
        Tool tool = new Tool();
        tool.setToolName(tfNameTool1.getText());
        tool.setToolBrand(tfBrandTool1.getText());
        tool.setToolSerialNumber(tfSerialNumberTool1.getText());
        tool.setNameCompanyToolLocation(comboBoxChooseACompany.getValue());
        if(dpStartOfOperation1.getValue() ==null){
            tool.setStartOfOperation(LocalDate.now());
        }else tool.setStartOfOperation(dpStartOfOperation1.getValue());
        tool.setToolCondition(mbCondition1.getText());

        if(toolsRepository.getToolBySerialNumber(tool.getToolSerialNumber()) == null ){
            if(comboBoxChooseACompany.getValue() != null) {
                toolsRepository.setTool(tool);
                if (toolsRepository.getToolBySerialNumber(tool.getToolSerialNumber()).getToolSerialNumber().equals(tool.getToolSerialNumber())) {

                    imgOk1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
                    labelOk1.setText(language.TOOL_WITH_SN(lang) + tool.getToolSerialNumber() + language.ADDED(lang));

                    GlobalLinkMainController.getMainController().updateUser(new DataUser(userRepository.getUserLoginPassword(this.user.getLogin(), this.user.getPassword())));
                    GlobalLinkStartEngineerController.getStartEngineerController().updateTheCompany();

                    Clean();

                } else {
                    imgOk1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
                    labelOk1.setText(language.NOT_ADDED(lang));
                }
            }else {
                comboBoxChooseACompany.setStyle("-fx-border-color: #e10909; -fx-border-radius: 5; -fx-border-width: 3;");
            }
        } else {
            tfSerialNumberTool1.setStyle("-fx-border-color: #e10909; -fx-border-radius: 5; -fx-border-width: 3;");
            tfSerialNumberTool1.setPromptText(language.A_TOOL_WITH_THIS_SERIAL_NUMBER_HAS_ALREADY_BEEN_ADDED(lang));
        }
    }

    public void setLanguage(String lang){
        labelNameTool.setText(language.NAME_TOOL(lang));
        labelManufacturer.setText(language.MANUFACTURER(lang));
        labelSerialNumber.setText(language.SERIAL_NUMBER(lang));
        labelDateWork.setText(language.START_DATE_OF_OPERATION(lang));
        btnSave.setText(language.SAVE(lang));
        mbCondition1.setText(language.SATISFACTORY(lang));
        menuItemFaulty.setText(language.FAULTY(lang));
        menuItemRequiresRepair.setText(language.REPAIR(lang));
        menuItemSatisfactory.setText(language.SATISFACTORY(lang));
    }
    public void Clean(){
        TextField cleanStyle = new TextField();
        tfNameTool1.setStyle(cleanStyle.getStyle());
        tfNameTool1.clear();
        tfBrandTool1.setStyle(cleanStyle.getStyle());
        tfBrandTool1.clear();
        tfSerialNumberTool1.setStyle(cleanStyle.getStyle());
        tfSerialNumberTool1.clear();
        dpStartOfOperation1.getEditor().clear();
        mbCondition1.getItems().clear();
    }

    public void faultyOnAction(ActionEvent actionEvent) {
        mbCondition1.setText(menuItemFaulty.getText());
    }

    public void repairOnAction(ActionEvent actionEvent) {
        mbCondition1.setText(menuItemRequiresRepair.getText());
    }

    public void satisfactoryOnAction(ActionEvent actionEvent) {
        mbCondition1.setText(menuItemSatisfactory.getText());
    }
    public void closeAddToolController(MouseEvent mouseEvent) {
        gridPaneToolAdd.getChildren().clear();
        gridPaneToolAdd.setStyle(new GridPane().getStyle());
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else this.company = dataCompany.getCompany();
        loadFragment();
    }
    public void loadFragment(){
        setLanguage(lang);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        if(user != null) {
            CompanyRepository companyRepository = new CompanyRepositoryImpl();
            List<Company> companies = companyRepository.getListCompanyByUserId(user.getId());
            for (Company company1 : companies) {
                if (companies.size() == 1) {
                    comboBoxChooseACompany.setValue(company1.getNameCompany());
                } else comboBoxChooseACompany.setPromptText(language.CHOOSE_A_COMPANY(lang));
                observableList.add(company1.getNameCompany());
            }
        }
        comboBoxChooseACompany.setItems(observableList);
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
