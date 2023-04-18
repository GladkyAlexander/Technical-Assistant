package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetAffairs;
import ru.greatlarder.technicalassistant.services.database.UpdateAffairs;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.GetAffairsByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.affaris.UpdateAffairsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemTaskController implements Initializable {
    @FXML
    public GridPane gridPane;
    @FXML public Label labelDate;
    @FXML public Label labelTime;
    @FXML public TextArea textAreaTask;
    @FXML public Label labelCreator;
    @FXML public ImageView imgCompletion;
    @FXML public Label labelRoom;
    Affairs affairs;
    User user;
    Company company;
    String lang;

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
    public void setAffairs(Affairs affairs) {
        this.affairs = affairs;
    }
    public void changeStatus(MouseEvent mouseEvent) {

        UpdateAffairs updateAffairs = new UpdateAffairsSQLite();
        affairs.setStatus(0);
        updateAffairs.updateAffairs(user, company.getNameCompany(), affairs, affairs.getId());
        GetAffairs getAffairs = new GetAffairsByIdSQLite();
        if(getAffairs.getAffairs(user, company.getNameCompany(), String.valueOf(affairs.getId())).getStatus() == 0){

            gridPane.getChildren().clear();
            gridPane.setManaged(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }
}
