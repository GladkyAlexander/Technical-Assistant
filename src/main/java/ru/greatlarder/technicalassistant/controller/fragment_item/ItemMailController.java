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
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ItemMailController implements ObserverLang, ObserverCompany, ObserverUser {


    @FXML public Label labelDate;
    @FXML public Label labelTime;
    @FXML public Label labelRoom;
    @FXML public Label labelCustomer;
    @FXML public HBox hBoxRequests;
    @FXML public GridPane gridPaneTaskItem;
    @FXML public Button btnAdd;
    private String taskHTML;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    HandlerCompanyListener handlerCompanyListener = GlobalLinkMainController.mainController.getHandlerCompanyListener();
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().getHandlerUserListener();
    private Company company;
    private String lang;
    private User user;
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

            addTaskController = loader.getController();
            handlerLang.registerObserverLang(loader.getController());
            handlerCompanyListener.registerObserverCompany(loader.getController());
            handlerUserListener.registerObserverUser(loader.getController());

            addTaskController.updateLang(new DataLang(lang));
            addTaskController.updateUser(new DataUser(user));
            addTaskController.updateCompany(new DataCompany(company));

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
    }
    private void setLang(String lang1){
        btnAdd.setText(language.ADD(lang1));
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else this.company = dataCompany.getCompany();
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
