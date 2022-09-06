package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentWebTaskController;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddTaskController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ItemMailController implements ObserverLang, ObserverCompany {


    @FXML public Label labelDate;
    @FXML public Label labelTime;
    @FXML public Label labelRoom;
    @FXML public Label labelCustomer;
    @FXML public HBox hBoxRequests;
    @FXML public GridPane gridPaneTaskItem;
    @FXML public Button btnAdd;
    private String taskHTML;
    HandlerLang handlerLang = new HandlerLang();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    Company company;
    String lang;
    Language language = new LanguageImpl();

    public void setLabelDate(LocalDate localDate) {
        this.labelDate.setText(localDate.toString());
        gridPaneTaskItem.setStyle(StyleSRC.STYLE_EXCELLENT);
    }

    public void setLabelTime(LocalTime localTime) {
        if(localTime.toString().equals("00:00")){
            this.labelTime.setText("ASAP");
        } else this.labelTime.setText(localTime.toString());
    }

    public void setLabelRoom(String room) {
        this.labelRoom.setText(room);
    }

    public void setLabelCustomer(String customer) {
        this.labelCustomer.setText(customer);
    }

    public void sethBoxRequests(Label requests) {
        this.hBoxRequests.getChildren().add(requests);
    }

    public void setTaskHTML(String html){
        this.taskHTML = html;
    }

    public void clickGridPane(MouseEvent mouseEvent) {
        FragmentWebTaskController webTaskController = new FragmentWebTaskController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentWebTask.fxml"));

        try {

            Scene scene = new Scene(loader.load(), 500, 300);
            Stage stage = new Stage();

            webTaskController = loader.getController();
            webTaskController.load(taskHTML);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickedTaskAdd(MouseEvent mouseEvent) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_task.fxml"));
        FragmentAddTaskController addTaskController;

        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();

            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            handlerCompanyListener.onNewDataCompany(new DataCompany(company));

            addTaskController = loader.getController();
            addTaskController.setLabelDate(labelDate.getText());
            addTaskController.setLabelTime(labelTime.getText());
            addTaskController.setLabelRoom(labelRoom.getText());
            addTaskController.setLabelCustomer(labelCustomer.getText());

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLang(lang);
        handlerLang.onNewDataLang(new DataLang(lang));
    }
    private void setLang(String lang1){
        btnAdd.setText(language.ADD(lang1));
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
    }
}
