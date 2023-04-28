package ru.greatlarder.technicalassistant.services.list_view.impl;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentWebTaskController;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEmail;
import ru.greatlarder.technicalassistant.domain.Email;
import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.GetLanguageDataName;
import ru.greatlarder.technicalassistant.services.lang.impl.GetLanguageDataNameImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewMail;
import ru.greatlarder.technicalassistant.services.mail.GetMulti;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class ListViewEmailMin implements GetListViewMail {
    GetLanguageDataName getLanguageDataName = new GetLanguageDataNameImpl();
    @Override
    public ListView<Email> getListViewEmail(User user, MailSettings mailSettings) {

        ObservableList<Email> observableList = FXCollections.observableArrayList(getListMessageList(mailSettings));
        ListView<Email> list = new ListView<>(observableList);
        list.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_email.fxml"));
            Node node;
            ItemEmail controller;
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
            protected void updateItem(Email item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    try {
                        LocalDate date = item.getLetter().getSentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        String lang = GlobalLinkMainController.getMainController().getLang();
                        controller.setLabelData(getLanguageDataName.getDayWeek(date, lang) +
                                " " + date.getDayOfMonth() + " " + getLanguageDataName.getMonthWeek(date, lang));
                        controller.setLabelTopic(item.getLetter().getSubject());
                        Address[] format = item.getLetter().getFrom();
                        controller.setLabelFrom(format == null ? null: ((InternetAddress)format[0]).getAddress());
                        controller.setMessage(item.getLetter());
                        setGraphic(node);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentWebTask.fxml"));

                try {

                    Scene scene = new Scene(loader.load(), 600, 400);
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image((Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png")))));
                    stage.setTitle(newValue.getLetter().getSubject());
                    
                    FragmentWebTaskController webTaskController = loader.getController();
                    webTaskController.load(newValue);

                    stage.setScene(scene);
                    stage.show();
                } catch (IOException | MessagingException e) {
                    e.printStackTrace();
                }
            
        });

        return list;
    }
    private List<Email> getListMessageList(MailSettings settings){

        String mail = settings.getMailMonitoring();
        String password = settings.getPasswordMailMonitoring();
        String host = settings.getHostMailMonitoring();

        List<Email> list = new ArrayList<>();

        Properties prop = new Properties();
        prop.put("mail.store.protocol", "imaps");
        Session.getDefaultInstance(prop);
        Store store = null;
        Folder inbox = null;

        try {
            store = Session.getInstance(prop).getStore();
            store.connect(host, mail, password);

            inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            int count = inbox.getMessageCount();
            Message[] messages = inbox.getMessages(1, count);

            for (Message message : messages) {
                if (message.getSubject() != null) {

                    Email email = new Email();
                    email.setLetter(message);
                    email.setTextHTML(new GetMulti().getText(message));
                    list.add(email);
                }
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        } finally {
            if (inbox != null) {
                try {
                    inbox.close(false);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            if (store != null) {
                try {
                    store.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

}
