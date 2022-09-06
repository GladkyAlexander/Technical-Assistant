package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Task;
import ru.greatlarder.technicalassistant.repository.TaskRepository;
import ru.greatlarder.technicalassistant.repository.impl.TaskRepositoryImpl;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.time.LocalDate;
import java.time.LocalTime;


public class FragmentAddTaskController implements ObserverLang, ObserverCompany {
    public GridPane gridPaneAddTask;
    public Label labelDate;
    public Label labelTime;
    public Label labelRoom;
    public Label labelCustomer;
    public TextArea textAreaTask;
    public Button btnSaveTask;
    public Label labelInfoDateTime;
    public Label labelInfoRoom;
    public Label labelInfoCustomer;
    public Label labelInfoTArea;
    Language line = new LanguageImpl();
    TaskRepository taskRepository = new TaskRepositoryImpl();
    String lang;
    Company company;

    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();

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
        /*
        company.setTaskList(taskRepository.getListTask(company.getNameCompany()));
        handlerCompanyListener.registerObserverCompany(GlobalLinkStartEngineerController.getStartEngineerController());
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));

         */

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
}
