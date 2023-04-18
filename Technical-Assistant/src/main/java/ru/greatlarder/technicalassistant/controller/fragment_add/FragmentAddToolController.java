package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListCompany;
import ru.greatlarder.technicalassistant.services.database.GetTools;
import ru.greatlarder.technicalassistant.services.database.SetTools;
import ru.greatlarder.technicalassistant.services.database.sqlite.company.ListAllCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.tool.GetToolByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.tool.SetToolSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentAddToolController implements Initializable {
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

    Company company;
    String lang;
    Language language = new LanguageImpl();
    User user;

    public void sendTool() {
        Tool tool = new Tool();
        tool.setToolName(tfNameTool1.getText());
        tool.setToolBrand(tfBrandTool1.getText());
        tool.setToolSerialNumber(tfSerialNumberTool1.getText());
        tool.setNameCompanyToolLocation(comboBoxChooseACompany.getValue());
        if(dpStartOfOperation1.getValue() ==null){
            tool.setStartOfOperation(LocalDate.now());
        }else tool.setStartOfOperation(dpStartOfOperation1.getValue());
        tool.setToolCondition(mbCondition1.getText());

        GetTools getTools = new GetToolByIdSQLite();
        if(getTools.getTool(user, company.getNameCompany(), String.valueOf(tool.getId())) == null ){
            if(comboBoxChooseACompany.getValue() != null) {
                SetTools setTools = new SetToolSQLite();
                setTools.setTool(user, company.getNameCompany(), tool);
                if (Objects.equals(getTools.getTool(user, company.getNameCompany(), String.valueOf(tool.getId())).getId(), (tool.getId()))) {

                    imgOk1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
                    labelOk1.setText(language.TOOL_WITH_SN(lang) + tool.getToolSerialNumber() + language.ADDED(lang));

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

    public void faultyOnAction() {
        mbCondition1.setText(menuItemFaulty.getText());
    }

    public void repairOnAction() {
        mbCondition1.setText(menuItemRequiresRepair.getText());
    }

    public void satisfactoryOnAction() {
        mbCondition1.setText(menuItemSatisfactory.getText());
    }
    public void closeAddToolController() {
        ((BorderPane) gridPaneToolAdd.getParent()).getChildren().remove(gridPaneToolAdd);
    }

    public void loadFragment(){
        setLanguage(lang);
        ObservableList<String> observableList = FXCollections.observableArrayList();
        if(user != null) {
            GetListCompany getListCompany = new ListAllCompanySQLite();
            List<Company> companies = getListCompany.getAllCompany(user, String.valueOf(user.getId()));
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
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        loadFragment();
    }
}
