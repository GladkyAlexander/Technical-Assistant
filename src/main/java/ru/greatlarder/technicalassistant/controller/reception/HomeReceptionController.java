package ru.greatlarder.technicalassistant.controller.reception;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentRoomWeek;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemEquipmentController;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemRoomMin;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.EquipmentRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.ListRoomNameRepositoryMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.EquipmentRepositoryMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql.impl.ListRoomNameRepositoryMySQLImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkHomeFragmentController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.HandlerUserListener;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class HomeReceptionController implements ObserverLang, ObserverUser {
    @FXML
    public BorderPane borderPaneHomePage;
    private User user;
    private String lang;
    Language language = new LanguageImpl();
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    HandlerUserListener handlerUserListener = GlobalLinkMainController.getMainController().getHandlerUserListener();
    FileManager fileManager = new FileManagerImpl();

    public void loadFragment() {
        GlobalLinkHomeFragmentController.setHomeReceptionController(this);
        loadRooms();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang1) {
        //labelRoomsHRC.setText(language.ROOMS(lang1));
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }

    private void loadRooms() {

        Task<ListView<Room>> task = new Task<ListView<Room>>() {
            @Override
            protected ListView<Room> call() throws Exception {
                ListRoomNameRepositoryMySQL listRoomNameRepositoryMySQL = new ListRoomNameRepositoryMySQLImpl();
                ListView<Room> listView = new ListView<>(FXCollections.observableArrayList(listRoomNameRepositoryMySQL
                        .getRoomsNameList(user, user.getCompanyAffiliation())));
                listView.setCellFactory(p -> new ListCell<>() {
                    final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_room_min.fxml"));
                    Node node;
                    ItemRoomMin controller;

                    {
                        try {
                            node = loader.load();
                            controller = loader.getController();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void updateItem(Room room, boolean b) {
                        super.updateItem(room, b);
                        if (b) {
                            setGraphic(null);
                        } else {
                            controller.setUser(user);
                            controller.labelNameRoomIRM.setText(room.getNameRoom());
                            setGraphic(node);
                        }
                    }
                });

                listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Room>() {
                    @Override
                    public void changed(ObservableValue<? extends Room> observable, Room oldValue, Room newValue) {
                        try {
                            getRoomWeek(newValue);
                            borderPaneHomePage.setRight(createTableEquipment(newValue));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

                return listView;
            }
        };
        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        progressBar.setMaxHeight(MAX_VALUE);
        GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().add(progressBar);
        task.setOnSucceeded((t) -> {
            progressBar.visibleProperty().bind(task.runningProperty());
            borderPaneHomePage.setLeft(task.getValue());
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();

    }

    private void getRoomWeek(Room room) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentRoomWeek.fxml"));
        borderPaneHomePage.setCenter(loader.load());
        handlerLang.registerObserverLang(loader.getController());
        handlerUserListener.registerObserverUser(loader.getController());
        FragmentRoomWeek fragmentRoomWeek = loader.getController();
        fragmentRoomWeek.updateLang(new DataLang(lang));
        fragmentRoomWeek.updateUser(new DataUser(user));

        fragmentRoomWeek.loadWeek(user, room);
       
    }

    private ListView<Equipment> createTableEquipment(Room room) {
        EquipmentRepositoryMySQL equipmentRepositoryMySQL = new EquipmentRepositoryMySQLImpl();
        ListView<Equipment> equipmentListView = new ListView<>(FXCollections.observableArrayList(equipmentRepositoryMySQL
                .getEquipmentByRoomName(user, user.getCompanyAffiliation(), room.getNameRoom())));
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
                    handlerLang.registerObserverLang(loader.getController());
                    controller.updateLang(new DataLang(lang));
                    if (item.getImage() != null) {
                        if (fileManager.getUrlFileImage(item.getImage()) != null) {
                            controller.setIvPhoto(new Image(fileManager.getUrlFileImage(item.getImage())));
                        } else {
                            controller.setIvPhoto(new Image(Objects.requireNonNull(getClass()
                                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/equipment_img/" + item.getImage()))));
                        }
                    }
                    controller.setModel(item.getModel());
                    controller.setSerialNumber(item.getSerialNumber());
                    controller.setDate(item.getDateWork());
                    controller.setEquipment(item);
                    setGraphic(node);
                }
            }
        });
        return equipmentListView;
    }

}
