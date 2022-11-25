package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Task;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.TaskRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl.TaskRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.time.LocalDate;
import java.time.LocalTime;


public class FragmentAddTaskController implements ObserverLang, ObserverCompany, ObserverUser {
    @FXML public GridPane gridPaneAddTask;
    @FXML public Label labelDate;
    @FXML public Label labelTime;
    @FXML public Label labelRoom;
    @FXML public Label labelCustomer;
    @FXML public TextArea textAreaTask;
    @FXML public Button btnSaveTask;
    @FXML public Label labelInfoDateTime;
    @FXML public Label labelInfoRoom;
    @FXML public Label labelInfoCustomer;
    @FXML public Label labelInfoTArea;
    Language line = new LanguageImpl();
    TaskRepository taskRepository = new TaskRepositoryImpl();
    private String lang;
    private Company company;
    private User user;


    public void saveTask(MouseEvent mouseEvent) {

        Task task = new Task();
        task.setDateOfCreation(LocalDate.parse(labelDate.getText()));
        if(labelTime.getText().equals("ASAP")){
            task.setTimeOfCreation(LocalTime.now());
        }else task.setTimeOfCreation(LocalTime.parse(labelTime.getText()));
        task.setCreator(labelCustomer.getText());
        task.setRoom(labelRoom.getText());
        task.setTextTask(textAreaTask.getText());
        task.setNameCompany(company.getNameCompany());
        task.setStatus(1);

        taskRepository.setTask(task);
        GlobalLinkMainController.getMainController().updateUser();

        Window stage = labelInfoRoom.getScene().getWindow();
        stage.hide();
    }

    private void setLanguage(String language){
        labelInfoDateTime.setText(line.DATE(language) + " , " + line.TIME(language));
        labelInfoRoom.setText(line.ROOM(language));
        labelInfoCustomer.setText(line.CUSTOMER(language));
        labelInfoTArea.setText(line.TASK_TEXT(language));
        btnSaveTask.setText(line.SAVE(language));
    }

    public void setLabelDate(String labelDate) {
        this.labelDate.setText(labelDate);
    }

    public void setLabelTime(String labelTime) {
        this.labelTime.setText(labelTime);
    }

    public void setLabelRoom(String labelRoom) {
        this.labelRoom.setText(labelRoom);
    }

    public void setLabelCustomer(String labelCustomer) {
        this.labelCustomer.setText(labelCustomer);
    }

    public void setTextAreaTask(String textAreaTask) {
        this.textAreaTask.setText(textAreaTask);
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
}
