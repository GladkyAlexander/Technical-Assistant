package ru.greatlarder.technicalassistant.controller.fragment_item;

import jakarta.mail.Message;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemEmail implements Initializable {

    public GridPane gridPane;
    public Label labelFrom;
    public Label labelTopic;
    public Label labelData;
    User user;
    Company company;
    Message message;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
    }

    public Label getLabelFrom() {
        return labelFrom;
    }

    public void setLabelFrom(String labelFrom) {
        this.labelFrom.setText(labelFrom);
    }

    public Label getLabelTopic() {
        return labelTopic;
    }

    public void setLabelTopic(String labelTopic) {
        this.labelTopic.setText(labelTopic);
    }

    public Label getLabelData() {
        return labelData;
    }

    public void setLabelData(String labelData) {
        this.labelData.setText(labelData);
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
