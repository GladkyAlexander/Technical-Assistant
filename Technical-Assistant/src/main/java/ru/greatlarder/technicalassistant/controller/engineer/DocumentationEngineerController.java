package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListAffairs;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.GetListAffairsSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByCondition;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetAListOfFilesFromTheDirectory;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewDirectoryDocumentation;
import ru.greatlarder.technicalassistant.services.work_doc.ExelTask;
import ru.greatlarder.technicalassistant.services.work_doc.ExelWorker;
import ru.greatlarder.technicalassistant.services.work_doc.ExelZip;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class DocumentationEngineerController implements Initializable {
    @FXML public Button btnCreateTimeShite;
    @FXML public Button btnZip;
    @FXML public Button btnLampProjector;
    @FXML public GridPane gridPaneDocumentation;
    @FXML public SplitPane splitPaneDocumentation;
    @FXML public Label labelAccounting;
    @FXML public BorderPane borderPaneDoc;
    String lang;
    Company company;
    Language language = new LanguageImpl();
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd_MM_yyyy");
    String dateFormat = localDate.format(formatter);
    User user;

    private void setLanguage(String lang){
        labelAccounting.setText(language.ACCOUNTING(lang));
        btnCreateTimeShite.setText(language.GENERATE_A_TIMESHEET(lang));
        btnZip.setText(language.GENERATE_A_ZIP_DOCUMENT(lang));
        btnLampProjector.setText(language.GENERATE_A_DOCUMENT_OF_THE_OPERATION_OF_THE_PROJECTOR_LAMP(lang));
    }

    public void startDocFragment() {
        if(splitPaneDocumentation.getItems().size() == 2) {
            splitPaneDocumentation.getItems().remove(1);
        }
        if(user != null){
            btnZip.setDisable(false);
            btnLampProjector.setDisable(false);
            gridPaneDocumentation.setDisable(false);
            loadFoldersList();
        } else {
            btnZip.setDisable(true);
            btnLampProjector.setDisable(true);
            gridPaneDocumentation.setDisable(true);
        }

    }

    public void zip(MouseEvent mouseEvent) {
        GetListEquipment getListEquipment = new ListEquipmentByCondition();
        new ExelZip(getListEquipment.getListEquipment(user, company.getNameCompany(), language.SPARE_PARTS_INCLUDED(lang))
                , "zip" + dateFormat + "."+ "xls", company.getNameCompany());
        if(splitPaneDocumentation.getItems().size() == 2) {
            splitPaneDocumentation.getItems().remove(1);
        }
        loadFoldersList();
    }

    public void createDocLamp(ActionEvent actionEvent) {
        GetListEquipment getListEquipment = new ListEquipmentByNameSQLite();
        new ExelWorker(getListEquipment.getListEquipment(user, company.getNameCompany(), language.PROJECTOR(lang))
                , "lamp_time_" + dateFormat + "."+ "xls", company.getNameCompany());
        if(splitPaneDocumentation.getItems().size() == 2) {
            splitPaneDocumentation.getItems().remove(1);
        }
        loadFoldersList();
    }

    public void createTimeShite(MouseEvent mouseEvent) {
        List<Affairs> taskList = new ArrayList<>();
        GetListAffairs getListAffairs = new GetListAffairsSQLite();
        for(Affairs task : getListAffairs.getListAffairs(user, company.getNameCompany(), null)){
            if (localDate.getYear() == task.getDateOfCreation().getYear() && localDate.getMonth() == task.getDateOfCreation().getMonth()){
                taskList.add(task);
            }
        }
        new ExelTask(taskList, "timeShite" + dateFormat + "_" + LocalTime.now().getMinute() + "." + "xls", company.getNameCompany());
        if(splitPaneDocumentation.getItems().size() == 2) {
            splitPaneDocumentation.getItems().remove(1);
        }
        loadFoldersList();
    }
    private void loadFoldersList(){
            Task<ListView<String>> task = new Task<ListView<String>>() {
                @Override
                protected ListView<String> call() throws Exception {
                    GetAListOfFilesFromTheDirectory getAlistView = new ListViewDirectoryDocumentation();
                    return getAlistView.getListViewFile(user, company.getNameCompany());
                }
            };

        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        progressBar.setMaxHeight(MAX_VALUE);
        borderPaneDoc.setTop(progressBar);
            task.setOnSucceeded((succeededEvent) -> {
                progressBar.visibleProperty().bind(task.runningProperty());
                splitPaneDocumentation.getItems().add(task.getValue());
            });
        Platform.runLater(()->{
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        startDocFragment();
    }
}
