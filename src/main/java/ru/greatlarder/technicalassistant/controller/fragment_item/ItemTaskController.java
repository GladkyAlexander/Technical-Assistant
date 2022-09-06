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
import ru.greatlarder.technicalassistant.repository.TaskRepository;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.repository.impl.TaskRepositoryImpl;
import ru.greatlarder.technicalassistant.repository.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.util.Objects;

public class ItemTaskController {
    @FXML
    public GridPane gridPane;
    @FXML public Label labelDate;
    @FXML public Label labelTime;
    @FXML public TextArea textAreaTask;
    @FXML public Label labelCreator;
    @FXML public ImageView imgCompletion;
    @FXML public Label labelRoom;
    Company company;
    Task task;
    TaskRepository taskRepository = new TaskRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();

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
}
