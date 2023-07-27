package ru.greatlarder.technicalassistant.controller.engineer;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentIdenticalData;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentAddCompanyController;
import ru.greatlarder.technicalassistant.controller.fragment_add.FragmentRegistrationUserController;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.DataVerification;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.mysql.CompanyByNameMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.SetCompanyMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.UpdateCompanyMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.EquipmentByNameCompanyMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.ListEquipmentByNameCompanyMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.SetEquipmentMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.UpdateEquipmentMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.names.*;
import ru.greatlarder.technicalassistant.services.database.mysql.room.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByNameCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.event_format.GetListEventFormatSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.ListRoomByCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements.GetListSeatingArrangementsSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageWarningsImpl;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import javax.sound.midi.Patch;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingsEngineerController implements Initializable {
    @FXML
    public BorderPane borderPaneSettings;
    @FXML
    public Label labelReferenceDirectory;
    @FXML
    public Label labelReferenceDataBase;
    @FXML
    public Label tfRefDirApp;
    @FXML
    public TextField tfRefDirDB;
    @FXML
    public Button btnCompanyChoice;
    @FXML
    public Button btnRegistry;
    @FXML
    public Label labelDSL;
   /* @FXML
    public Button btnSettingsMail;*/
    @FXML
    public Button btnSendToAnExternalDatabase;
    @FXML
    public Button btnGetDataFromAnExternalDatabase;
    @FXML
    public Button btnSettingsUser;
    @FXML
    public Button buttonCompanyDataSettings;
    @FXML
    public GridPane gridPaneUpdateExternalDB;
    @FXML
    public Button buttonListOfRooms;
    @FXML
    public Button buttonSeatingList;
    @FXML
    public Button buttonEquipment;
    @FXML
    public Button buttonListOfEvents;
    @FXML
    public Button buttonCompanyData;
    @FXML public Button btnShow;
    Language language = new LanguageImpl();
    User user;
    Company company;
    FragmentRegistrationUserController fragmentRegistrationUserController;
    DataVerification dataVerification = new DataVerification();
    String lang;
    HashMap<Object, Object> objectHashMap = new HashMap<>();

    public void openLayoutSettingsUser(ActionEvent actionEvent) {
        borderPaneSettings.getChildren().remove(borderPaneSettings.getCenter());
        FXMLLoader loaderRegistration = new FXMLLoader(getClass().
                getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/fragmentRegisrationUser.fxml"));
        try {
            borderPaneSettings.setRight(loaderRegistration.load());

            fragmentRegistrationUserController = loaderRegistration.getController();
            fragmentRegistrationUserController.updateLang(new DataLang(lang));
            fragmentRegistrationUserController.loadPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*    public void openSettingsMail() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_mail_settings.fxml"));
        try {
            borderPaneSettings.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public void actionBtnCompany() {
        GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(GlobalLinkStartEngineerController
            .getStartEngineerController().borderPaneEngineerPage.getCenter());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_company.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            FragmentAddCompanyController fragmentAddCompanyController = loader.getController();
            fragmentAddCompanyController.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLanguage(String lang) {
        btnRegistry.setText(language.REGISTRY(lang));
        btnCompanyChoice.setText(language.ADD_A_COMPANY(lang));
        labelDSL.setText(language.DATE_STORAGE_LOCATION(lang));
        labelReferenceDirectory.setText(language.APPLICATION_FOLDER(lang));
        labelReferenceDataBase.setText(language.DATABASE_HOSTING_FOLDER(lang));
//        btnSettingsMail.setText(language.MAIL_SETTINGS(lang));
        btnSendToAnExternalDatabase.setText(language.UPDATE_DATA_IN_AN_EXTERNAL_DATABASE(lang));
        btnGetDataFromAnExternalDatabase.setText(language.GET_DATA_FROM_AN_EXTERNAL_DATABASE(lang));

        btnSettingsUser.setText(language.PROFILE_SETTINGS(lang));
        buttonCompanyDataSettings.setText(language.COMPANY_DATA_SETTINGS(lang));
        buttonCompanyData.setText(language.COMPANY_DATA(lang));
        buttonListOfRooms.setText(language.LIST_OF_ROOMS(lang));
        buttonSeatingList.setText(language.SEATING_LIST(lang));
        buttonEquipment.setText(language.EQUIPMENT(lang));
        buttonListOfEvents.setText(language.LIST_OF_EVENTS(lang));
    }

    public void loadPage() {
        FileManager fileManager = new FileManagerImpl();
        tfRefDirApp.setText(fileManager.folderProject());
        tfRefDirDB.setText(fileManager.folderDB());
        tfRefDirDB.setPrefColumnCount(tfRefDirDB.getText().length());
        if (user == null) {
            btnCompanyChoice.setDisable(true);
        } else {
            btnCompanyChoice.setDisable(false);
            btnRegistry.setDisable(true);
            borderPaneSettings.getChildren().remove(borderPaneSettings.getRight());
        }
        btnSendToAnExternalDatabase.setDisable(user.getNameDB().isEmpty()
            && user.getPasswordDB().isEmpty() && user.getUserDB().isEmpty());
        
        btnGetDataFromAnExternalDatabase.setDisable(company == null || (user.getNameDB().isEmpty()
            && user.getPasswordDB().isEmpty() && user.getUserDB().isEmpty()));
        visibleGridUpdateExternalDB(false);
        btnShow.setVisible(false);
        btnShow.setManaged(false);
    }

    private void visibleGridUpdateExternalDB(boolean b) {
        gridPaneUpdateExternalDB.setVisible(b);
        gridPaneUpdateExternalDB.setManaged(b);
    }

    public void sendToAnExternalDatabase() {
        visibleGridUpdateExternalDB(!gridPaneUpdateExternalDB.isVisible());
        GetCompany getCompanyByName = new CompanyByNameMySQL();
        btnDisable(true);

        ProgressBar progressIndicator = new ProgressBar();
        gridPaneUpdateExternalDB.add(progressIndicator, 1, 0);

        Task<Boolean> task = new Task<>() {
            @Override
            protected Boolean call() {
                return getCompanyByName.getCompany(user, company.getNameCompany()) == null;
            }
        };

        task.setOnSucceeded((succeededEvent) -> {
            progressIndicator.progressProperty().bind(task.progressProperty());
            progressIndicator.visibleProperty().bind(task.runningProperty());
            btnDisable(task.getValue());
        });
        Platform.runLater(() -> {
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });

    }

    public void getDataFromAnExternalDatabase() {
        GetListEquipment listEquipmentSqlite = new ListEquipmentByNameCompanySQLite();
        GetListEquipment listEquipmentMysql = new ListEquipmentByNameCompanyMySQL();

        List<Equipment> equipmentsSqlite = listEquipmentSqlite.getListEquipment(user, company.getNameCompany(), null);
        List<Equipment> equipmentListMySQL = listEquipmentMysql.getListEquipment(user, company.getNameCompany(), null);

        GetListRoom listRoomSQLite = new ListRoomByCompanySQLite();
        GetListRoom listRoomMySQL = new ListRoomByCompanyMySQL();

        HashMap<Equipment, Equipment> equipmentHashMap = dataVerification.getEquipmentList(equipmentsSqlite, equipmentListMySQL);
        if(!equipmentHashMap.isEmpty()){
            objectHashMap.putAll(equipmentHashMap);
        }

        if(!objectHashMap.isEmpty()){
                btnShow.setManaged(true);
                btnShow.setVisible(true);
                btnShow.setText(language.SHOW(lang) + " " + language.DIFFERENCES(lang) + " " + objectHashMap.size());
        } else borderPaneSettings.setCenter(new Label(language.THE_DATA_IS_UP_TO_DATE(lang)));

    }

    public void openSettingsUser() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragment_change_settings_user.fxml"));
        try {
            borderPaneSettings.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCompanyDataSettings() {
        if(company != null) {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.getChildren().remove(GlobalLinkStartEngineerController
                .getStartEngineerController().borderPaneEngineerPage.getCenter());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_company.fxml"));
            try {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
                FragmentAddCompanyController fragmentAddCompanyController = loader.getController();
                fragmentAddCompanyController.loadChangeCompanyFragment(company);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            LanguageWarnings languageWarnings = new LanguageWarningsImpl();
            alert.setTitle(languageWarnings.CHOOSE_A_COMPANY(lang));
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
            alert.setContentText(languageWarnings.CHOOSE_A_COMPANY(lang));
            alert.showAndWait();
        }
    }

    public void uploadCompanyData() {
        btnDisable(true);
        GetCompany getCompany = new CompanyByNameMySQL();
        SetCompany setCompanyMySQL = new SetCompanyMySQL();
        UpdateCompany updateCompany = new UpdateCompanyMySQL();

        if (getCompany.getCompany(user, company.getNameCompany()) == null) {
            Task<String> task = new Task<>() {
                @Override
                protected String call(){
                    setCompanyMySQL.setCompany(user, company);
                    if (getCompany.getCompany(user, company.getNameCompany()) == null) {
                        return language.NOT_ADDED(lang);
                    } else {
                        return language.ADDED(lang);
                    }
                }
            };
            ProgressBar progressIndicator = new ProgressBar();
            progressIndicator.progressProperty().bind(task.progressProperty());
            progressIndicator.visibleProperty().bind(task.runningProperty());
            gridPaneUpdateExternalDB.add(progressIndicator, 1, 0);
            task.setOnSucceeded((succeededEvent) -> {
                progressIndicator.progressProperty().bind(task.progressProperty());
                progressIndicator.visibleProperty().bind(task.runningProperty());
                gridPaneUpdateExternalDB.add(new Label(task.getValue()), 1, 0);
            });

            Platform.runLater(() -> {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(task);
                executorService.shutdown();
            });

        } else {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    updateCompany.updateCompany(user, company);
                    return null;
                }
            };
            ProgressBar progressIndicator = new ProgressBar();
            progressIndicator.progressProperty().bind(task.progressProperty());
            gridPaneUpdateExternalDB.add(progressIndicator, 1, 0);
            new Thread(task).start();

            task.setOnSucceeded((succeededEvent) -> {
                progressIndicator.progressProperty().bind(task.progressProperty());
                progressIndicator.visibleProperty().bind(task.runningProperty());
                gridPaneUpdateExternalDB.add(new Label(language.DATA_UPDATED(lang)), 1, 0);
            });

            Platform.runLater(() -> {
                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(task);
                executorService.shutdown();
            });

        }
        btnDisable(false);
    }

    public void updateListOfRooms() {
        btnDisable(true);
        gridPaneUpdateExternalDB.getChildren().removeIf(node -> node instanceof ProgressIndicator);
        GetListRoom getListRoom = new ListRoomByCompanySQLite();
        GetRoom getRoomMySQL = new RoomByNameMySQL();
        UpdateRoom updateRoom = new UpdateRoomMySQL();
        SetRoom setRoom = new SetRoomMySQL();

        ProgressIndicator progressIndicator = new ProgressIndicator();
        gridPaneUpdateExternalDB.add(progressIndicator, 1, 1);
        Thread thread = new Thread(() -> {
            double progress = 0;
            List<Room> list = getListRoom.getListRoom(user, company.getNameCompany(), null);
            for (Room room : list) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += (double) 1 / list.size();
                final double reportedProgress = progress;
                Platform.runLater(() -> {
                    progressIndicator.setProgress(reportedProgress);
                });
                Room room1 = getRoomMySQL.getRoom(user, company.getNameCompany(), room.getNameRoom());
                if (room1 == null) {
                    setRoom.setRoom(user, company.getNameCompany(), room);
                } else {
                    updateRoom.updateRoom(user, room, room.getId());
                }
            }
            btnDisable(false);
        });
        thread.start();

    }

    public void updateSeatingList() {

        btnDisable(true);

        gridPaneUpdateExternalDB.getChildren().removeIf(node -> node instanceof ProgressIndicator);

        GetListSeatingArrangements getListSeatingArrangements = new GetListSeatingArrangementsSQLite();
        GetNames getNames = new GetNamesSeatingArrangementsByNameMySQL();
        SetNames setNames = new SetNamesSeatingArrangementsMySQL();
        UpdateNames updateIds = new UpdateNamesByNameMySQL();

        ProgressIndicator progressIndicator = new ProgressIndicator();
        gridPaneUpdateExternalDB.add(progressIndicator, 1, 2);
        Thread thread = new Thread(() -> {
            double progress = 0;
            List<SeatingArrangements> list = getListSeatingArrangements.getListSeatingArrangements(user, company.getNameCompany(), null);
            for (SeatingArrangements seatingArrangements : list) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += (double) 1 / list.size();
                final double reportedProgress = progress;
                Platform.runLater(() -> {
                    progressIndicator.setProgress(reportedProgress);
                });
                
                Names names = getNames.getNames(user, company.getNameCompany(), seatingArrangements.getNameSeatingArrangements());
                
                Path path = Paths.get(seatingArrangements.getUrlImageSeatingArrangements());
                File file = path.toFile();
                
                if (names == null) {
                    setNames.setNames(user, company.getNameCompany(), new Names(seatingArrangements.getNameSeatingArrangements()
                    , seatingArrangements.getNameCompany(), file, ""));
                } else {
                    if (seatingArrangements.getNameSeatingArrangements().equals(names.getNames())) {
                        Names n = new Names(seatingArrangements.getNameSeatingArrangements()
                            , seatingArrangements.getNameCompany(), file
                            , names.getDomain());
                        updateIds.updateNames(user, n, n.getNames());
                    } else {
                        setNames.setNames(user, company.getNameCompany(), new Names(seatingArrangements.getNameSeatingArrangements()
                                , seatingArrangements.getNameCompany(), file, ""));
                    }
                }
            }
            btnDisable(false);
        });
     thread.start();
    }

    public void updateEquipment() {
        btnDisable(true);
        gridPaneUpdateExternalDB.getChildren().removeIf(node -> node instanceof ProgressIndicator);
        GetListEquipment getListEquipment = new ListEquipmentByNameCompanySQLite();
        GetEquipment getEquipmentMySQL = new EquipmentByNameCompanyMySQL();
        UpdateEquipment updateEquipment = new UpdateEquipmentMySQL();
        SetEquipment setEquipment = new SetEquipmentMySQL();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        gridPaneUpdateExternalDB.add(progressIndicator, 1, 3);
        Thread thread = new Thread(() -> {
            double progress = 0;
            List<Equipment> list = getListEquipment.getListEquipment(user, company.getNameCompany(), null);
            for (Equipment equipment : list) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress += (double) 1 / list.size();
                final double reportedProgress = progress;
                Platform.runLater(() -> {
                    progressIndicator.setProgress(reportedProgress);
                });
                Equipment idEquip = getEquipmentMySQL.getEquipment(user, company.getNameCompany(), String.valueOf(equipment.getId()));
                if (idEquip == null) {
                    setEquipment.setEquipment(user, company.getNameCompany(), equipment);
                } else {
                    if (Objects.equals(idEquip.getId(), equipment.getId())) {
                        updateEquipment.updateEquipment(user, company, equipment);
                    } else setEquipment.setEquipment(user, company.getNameCompany(), equipment);
                }
            }
            btnDisable(false);
        });
        thread.start();
    }

    public void updateListOfEvents() {
        btnDisable(true);
        gridPaneUpdateExternalDB.getChildren().removeIf(node -> node instanceof ProgressIndicator);
        GetListEventFormat getList = new GetListEventFormatSQLite();

        GetNames getNames = new GetNamesEventFormatByNameMySQL();
        SetNames setNames = new SetNamesEventFormatMySQL();
        UpdateNames updateNames = new UpdateNamesByNameMySQL();

        ProgressIndicator progressIndicator = new ProgressIndicator();
        gridPaneUpdateExternalDB.add(progressIndicator, 1, 4);

        Thread thread = new Thread(() -> {

            double progress = 0;
            List<EventFormat> list = getList.getListEventFormat(user, company.getNameCompany(), null);
            if (list.size() > 0) {
                for (EventFormat eventFormat : list) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Names names = getNames.getNames(user, eventFormat.getNameCompany(), eventFormat.getNameEventFormat());
                    Path path = Paths.get(eventFormat.getUrlImageEvent());
                    File file = path.toFile();
                   
                    if (names == null) {
                        setNames.setNames(user, company.getNameCompany(), new Names(eventFormat.getNameEventFormat()
                                , eventFormat.getNameCompany(), file, ""));
                    } else{
                        if(eventFormat.getNameEventFormat().equals(names.getNames())){
                            Names n = new Names(eventFormat.getNameEventFormat(), eventFormat.getNameCompany()
                                , file, names.getDomain());
                            updateNames.updateNames(user, n, n.getNames());
                        } else {
                            setNames.setNames(user, company.getNameCompany(), new Names(eventFormat.getNameEventFormat()
                                    , eventFormat.getNameCompany(), file, ""));
                        }
                    }

                    progress += (double) 1 / list.size();
                    final double reportedProgress = progress;
                    Platform.runLater(() -> {
                        progressIndicator.setProgress(reportedProgress);
                    });
                }
            }
            btnDisable(false);
        });
        thread.start();
    }

    private void btnDisable(Boolean dis) {
        buttonCompanyData.setDisable(!dis);
        buttonListOfRooms.setDisable(dis);
        buttonSeatingList.setDisable(dis);
        buttonEquipment.setDisable(dis);
        buttonListOfEvents.setDisable(dis);
        btnGetDataFromAnExternalDatabase.setDisable(dis);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        loadPage();
    }

    public void openDetails() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentIdenticalData.fxml"));
        try {
            borderPaneSettings.setCenter(loader.load());
            FragmentIdenticalData fragmentIdenticalData = loader.getController();
            fragmentIdenticalData.loadFragment(objectHashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  /*  public void saveFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(" .Pdf", "*.*");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File file = fileChooser.showOpenDialog(btnSaveFile.getScene().getWindow());

        SaveFileFTPServer sf = new SaveFileFTPServer();
        try {
            sf.putFileToPath(file,"/www/download_company/");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sf.close();
        }
    }

    public void openFileByWebsait(ActionEvent actionEvent) {

        SaveFileFTPServer sf = new SaveFileFTPServer();
        String f ="00 Titularni list ??.pdf";
        try {
            sf.downloadFile("/www/download_company/"+f,"C:/Users/ASGla/Documents/Technical_Assistant/Company/В1/"+ f);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sf.close();
        }

    }*/
}
