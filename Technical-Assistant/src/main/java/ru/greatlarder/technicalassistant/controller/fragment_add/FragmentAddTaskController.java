package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetAffairs;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.SetAffairsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


public class FragmentAddTaskController implements Initializable {
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
    String lang;
    Company company;
    User user;


    public void saveTask() {

        Affairs affairs = new Affairs();
        affairs.setDateOfCreation(LocalDate.parse(labelDate.getText()));
        if(labelTime.getText().equals("ASAP")){
            affairs.setTimeOfCreation(LocalTime.now());
        }else affairs.setTimeOfCreation(LocalTime.parse(labelTime.getText()));
        affairs.setCreator(labelCustomer.getText());
        affairs.setRoom(labelRoom.getText());
        affairs.setTextTask(textAreaTask.getText());
        affairs.setNameCompany(company.getNameCompany());
        affairs.setStatus(1);

        SetAffairs setAffairs = new SetAffairsSQLite();
        setAffairs.setAffairs(user, company.getNameCompany(), affairs);

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
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
       this.lang = GlobalLinkMainController.getMainController().getLang();
       setLanguage(lang);
    }
}