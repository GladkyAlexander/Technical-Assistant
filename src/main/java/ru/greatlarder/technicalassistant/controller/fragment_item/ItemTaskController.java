package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Task;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.TaskRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.TaskRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;

import java.util.Objects;

public class ItemTaskController implements ObserverLang, ObserverCompany {
    @FXML
    public GridPane gridPane;
    @FXML public Label labelDate;
    @FXML public Label labelTime;
    @FXML public TextArea textAreaTask;
    @FXML public Label labelCreator;
    @FXML public ImageView imgCompletion;
    @FXML public Label labelRoom;
    private Company company;
    Task task;
    TaskRepository taskRepository = new TaskRepositoryImpl();
    private String lang;

    public void setLabelDate(String labelDate) {
        this.labelDate.setText(labelDate);
    }

    public void setLabelTime(String labelTime) {
        this.labelTime.setText(labelTime);
    }

    public void setTextAreaTask(String textAreaTask) {
        this.textAreaTask.setText(textAreaTask);
    }

    public void setLabelCreator(String labelCreator) {
        this.labelCreator.setText(labelCreator);
    }

    public void setImgCompletion(int condition) {
        if(condition == 1){
            this.imgCompletion.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/brakes_warning_light.png"))));
        }
        if(condition == 0){
            this.imgCompletion.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/brakes_ok_light.png"))));
        }
    }

    public void setLabelRoom(String labelRoom) {
        this.labelRoom.setText(labelRoom);
    }
    public void setTask(Task task) {
        this.task = task;
    }
    public void setCompany(Company company){
        this.company = company;
    }
    public void changeStatus(MouseEvent mouseEvent) {
        taskRepository.changeCondition(task.getId(), "status", 0);
        if(taskRepository.getTaskById(task.getId(), task.getNameCompany()).getStatus() == 0){
            GlobalLinkMainController.getMainController().updateUser();

            gridPane.getChildren().clear();
            gridPane.setManaged(false);
        }
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }
}
