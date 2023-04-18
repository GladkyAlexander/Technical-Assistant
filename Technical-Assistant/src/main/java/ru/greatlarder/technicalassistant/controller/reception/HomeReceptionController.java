package ru.greatlarder.technicalassistant.controller.reception;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.domain.Email;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.GetListMailSettings;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.ListEquipmentByRoomMySQL;
import ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings.GetListMailSettingsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewMail;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewEmailMin;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class HomeReceptionController implements Initializable {
    @FXML
    public BorderPane borderPaneHomePage;
    String lang;
    User user;

    public void loadFragment() {
        GetListMailSettings getListMailSettings = new GetListMailSettingsSQLite();
        
        MailSettings ms = null;
        for (MailSettings mailSettings : getListMailSettings.getListMailSettings(user, String.valueOf(user.getId()))){
            ms = mailSettings;
        }
        if (ms != null) {
            loadMail(ms);
        }
    }
    
    private void setLanguage(String lang1) {
        //labelRoomsHRC.setText(language.ROOMS(lang1));
    }
    private ListView<Equipment> createTableEquipment(Room room) {

        GetListEquipment getListEquipment = new ListEquipmentByRoomMySQL();

        ListView<Equipment> equipmentListView = new ListView<>(FXCollections.observableArrayList(getListEquipment.getListEquipment(
                user, user.getCompanyAffiliation(), room.getNameRoom())));
        equipmentListView.setCellFactory(param -> new ListCell<>() {
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_equipment.fxml"));
            Node node;
            ItemEquipmentController controller;

            {
                try {
                    node = loader.load();
                    controller = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Equipment item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    controller.setEquipment(item);
                    setGraphic(node);
                }
            }
        });
        return equipmentListView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
    }
    private void loadMail(MailSettings mailSettings) {
        Task<ListView<Email>> task = new Task<>() {
            @Override
            protected ListView<Email> call() throws Exception {
                GetListViewMail getListViewMail = new ListViewEmailMin();
                return getListViewMail.getListViewEmail(user, mailSettings);
            }
        };
        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        borderPaneHomePage.setTop(progressBar);
        task.setOnSucceeded((succeededEvent) -> {
            borderPaneHomePage.setRight(task.getValue());
            borderPaneHomePage.getChildren().remove(progressBar);
        });
        Platform.runLater(()->{
            progressBar.setProgress(task.getProgress());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });
    }
}
