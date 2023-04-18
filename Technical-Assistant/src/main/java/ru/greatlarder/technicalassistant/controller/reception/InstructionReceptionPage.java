package ru.greatlarder.technicalassistant.controller.reception;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetAListOfFilesFromTheDirectory;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewDirectoryInstruction;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class InstructionReceptionPage implements Initializable {
    @FXML public BorderPane borderPane;
    @FXML public Button btnAddInstruction;
    private User user;
    private String lang;
    Language language = new LanguageImpl();
    FileManager fileManager = new FileManagerImpl();
    Company company;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        setLang(lang);
        loadFragment();
    }

    private void setLang(String lang) {
        btnAddInstruction.setText(language.ADD(lang));
    }

    public void loadFragment() {
        GetAListOfFilesFromTheDirectory getAListOfFilesFromTheDirectory = new ListViewDirectoryInstruction();

        Task<ListView<String>> task = new Task<ListView<String>>() {
            @Override
            protected ListView<String> call() throws Exception {
                return getAListOfFilesFromTheDirectory.getListViewFile(user, company.getNameCompany());
            }
        };
        ProgressBar progressBar = new ProgressBar();
        progressBar.setMaxWidth(MAX_VALUE);
        borderPane.setRight(progressBar);
        task.setOnSucceeded((t) -> {
            borderPane.setRight(task.getValue());
        });
        Platform.runLater(() -> {
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }

    public void add() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF", "*.pdf", "*.PDF");
        fileChooser.getExtensionFilters().add(extFilter);

        File inF = fileChooser.showOpenDialog(btnAddInstruction.getScene().getWindow());
        File file = new File(fileManager.folderCompanyInstruction(company.getNameCompany()) + "\\" + inF.getName());
        try {
            FileUtils.copyFile(inF, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadFragment();
    }
}
