package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.controller.MainController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Task;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.repository.TaskRepository;
import ru.greatlarder.technicalassistant.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.repository.impl.TaskRepositoryImpl;
import ru.greatlarder.technicalassistant.services.TheEntireCatalogList;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.work_doc.ExelTask;
import ru.greatlarder.technicalassistant.services.work_doc.ExelWorker;
import ru.greatlarder.technicalassistant.services.work_doc.ExelZip;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    String dateFormat = localDate.format(formatter);
    TaskRepository taskRepository = new TaskRepositoryImpl();
    TheEntireCatalogList listOfDirectory = new TheEntireCatalogList();

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
        loadFoldersList();
        splitPaneDocumentation.setDividerPositions(0.85f, 0.15f);
        splitPaneDocumentation.getItems().setAll(vBoxLeft);
    }

    public void zip(MouseEvent mouseEvent) {
        new ExelZip(equipmentRepository.getListEquipmentForCompanyCondition(company.getNameCompany(), language.SPARE_PARTS_INCLUDED(lang))
                , "zip" + dateFormat + "."+ "xls", company.getNameCompany());

    }

    public void createDocLamp(ActionEvent actionEvent) {
        new ExelWorker(equipmentRepository.getListEquipmentByName(language.PROJECTOR(lang), company.getNameCompany())
                , "lamp_time_" + dateFormat + "."+ "xls", company.getNameCompany());
        loadFoldersList();
    }

    public void createTimeShite(MouseEvent mouseEvent) {
        List<Task> taskList = new ArrayList<>();
        for(Task task : taskRepository.getListTask(MainController.company.getNameCompany())){
            if (localDate.getYear() == task.getDateOfCreation().getYear() && localDate.getMonth() == task.getDateOfCreation().getMonth()){
                taskList.add(task);
            }
        }

        new ExelTask(taskList, "timeShite" + dateFormat + "_" + LocalTime.now().getMinute() + "." + "xls", company.getNameCompany());
    }
    private void loadFoldersList(){
        Platform.runLater(() ->{
            splitPaneDocumentation.getItems().add(listOfDirectory.upVbox(company.getNameCompany()));
        });
    }
}
