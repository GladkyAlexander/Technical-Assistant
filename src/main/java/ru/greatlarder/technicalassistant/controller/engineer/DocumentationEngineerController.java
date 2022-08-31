package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.services.TheEntireCatalogList;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

public class DocumentationEngineerController implements ObserverLang, ObserverCompany {
    @FXML public VBox vBoxLeft;
    @FXML public Button btnCreateTimeShite;
    @FXML public Button btnZip;
    @FXML public Button btnLampProjector;
    @FXML public SplitPane splitPaneDocumentation;
    @FXML public Label labelAccounting;
    @FXML public SplitPane borderPaneDocumentation;
    String lang;
    Company company;
    Language language = new LanguageImpl();

    @Override
    public void updateCompany(DataCompany dataCompany) {
    this.company = dataCompany.getCompany();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }
    private void setLanguage(String lang){
        labelAccounting.setText(language.ACCOUNTING(lang));
        btnCreateTimeShite.setText(language.GENERATE_A_TIMESHEET(lang));
        btnZip.setText(language.GENERATE_A_ZIP_DOCUMENT(lang));
        btnLampProjector.setText(language.GENERATE_A_DOCUMENT_OF_THE_OPERATION_OF_THE_PROJECTOR_LAMP(lang));
    }

    public void startDocFragment() {
        Platform.runLater(() ->{
            TheEntireCatalogList listOfDirectory = new TheEntireCatalogList();
            splitPaneDocumentation.getItems().add(listOfDirectory.upVbox(company.getNameCompany()));
        });
        splitPaneDocumentation.setDividerPositions(0.85f, 0.15f);
        splitPaneDocumentation.getItems().setAll(vBoxLeft);
    }

    public void zip(MouseEvent mouseEvent) {
    }

    public void createDocLamp(ActionEvent actionEvent) {
    }

    public void createTimeShite(MouseEvent mouseEvent) {
    }
}
