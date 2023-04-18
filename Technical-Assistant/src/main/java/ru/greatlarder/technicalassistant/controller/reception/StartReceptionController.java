package ru.greatlarder.technicalassistant.controller.reception;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentFormAssignment;
import ru.greatlarder.technicalassistant.controller.fragment.FragmentRoomWeek;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartReceptionController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewEquipment;
import ru.greatlarder.technicalassistant.services.list_view.GetListViewRoom;
import ru.greatlarder.technicalassistant.services.list_view.impl.ListViewRoomMySQL;
import ru.greatlarder.technicalassistant.services.list_view.impl.equipment.ListViewEquipmentPortableDatabaseMySQL;
import ru.greatlarder.technicalassistant.services.manager.FileManager;
import ru.greatlarder.technicalassistant.services.manager.impl.FileManagerImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Long.MAX_VALUE;

public class StartReceptionController implements ObserverLang, Initializable {

    @FXML
    public BorderPane borderPaneStartReception;
    @FXML
    public SplitPane splitPaneStartReception;
    @FXML
    public Label labelLastName;
    @FXML
    public Label labelFirstName;
    @FXML
    public Label labelHomePage;
    @FXML
    public Label labelInstructions;
    @FXML
    public ImageView imgLabelCompany;
    @FXML
    public Label labelInfo;
    @FXML
    public Label labelSettings;
    @FXML public Label labelPortableDevices;
    @FXML public ImageView imageLogoCompany;
    @FXML public VBox vBoxStartRecep;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().getHandlerLang();
    Language language = new LanguageImpl();
    String lang;
    User user;
    ListView<Equipment> listViewEquipments;
    Company company;
    FileManager fileManager = new FileManagerImpl();
    public SplitPane splitPane = new SplitPane();

    public void loadPage() {
        handlerLang.registerObserverLang(this);
        labelLastName.setText(user.getLastName());
        labelFirstName.setText(user.getFirstName());
        if(company.getLogoCompany() != null){
            if(fileManager.checkingPresenceFileDirectoryImage(company.getLogoCompany())) {
                imageLogoCompany.setImage(new Image(fileManager.getUrlFileImage(company.getLogoCompany())));
            } else imageLogoCompany.setImage(new Image(Objects.requireNonNull(getClass()
                    .getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
        }
        GetListViewRoom getListView = new ListViewRoomMySQL();
        Task<ListView<Room>> task = new Task<>() {
            @Override
            protected ListView<Room> call() {
                return getListView.getListView(user, user.getCompanyAffiliation(), null);
            }
        };
        ProgressBar progressBar = new ProgressBar(task.getProgress());
        progressBar.setMaxWidth(MAX_VALUE);
        vBoxStartRecep.getChildren().add(progressBar);
        task.setOnSucceeded((t) -> {
            ListView<Room> listView = task.getValue();
            splitPaneStartReception.getItems().add(listView);
            vBoxStartRecep.setPrefHeight(Region.USE_COMPUTED_SIZE);
            clickListViewItemRoom(listView);
            splitPaneStartReception.setDividerPosition(1, 0);
        });
        Platform.runLater(() -> {
            progressBar.progressProperty().bind(task.progressProperty());
            progressBar.visibleProperty().bind(task.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(task);
            executorService.shutdown();
        });

    }

    private void setLanguage(String l) {
        labelHomePage.setText(language.HOME_PAGE(l));
        labelInstructions.setText(language.INSTRUCTION(l));
        labelSettings.setText(language.SETTINGS(l));
        labelInfo.setText(language.INFORMATION(l));
        labelPortableDevices.setText(language.PORTABLE_DEVICES(l));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    public void openPageSettings() {
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getCenter());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/settingsReceptionController.fxml"));
        try {
            borderPaneStartReception.setCenter(loader.load());
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openHomePageReception() {
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getCenter());
        loadHomePage();
    }

    public void openInstructionPageReception() {
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getCenter());
        FXMLLoader loaderInstructionPage = new FXMLLoader(getClass()
                .getResource("/ru/greatlarder/technicalassistant/layout/page/reception/instructionReceptionPage.fxml"));
        try {
            borderPaneStartReception.setCenter(loaderInstructionPage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openInfoPage() {
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getCenter());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/info_page.fxml"));
        try {
            borderPaneStartReception.setCenter(loader.load());
         } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadHomePage() {
        FXMLLoader loaderHomePage = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/page/reception/homeReceptionPage.fxml"));
        try {
            borderPaneStartReception.setCenter(loaderHomePage.load());
            HomeReceptionController controller = loaderHomePage.getController();
            controller.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GlobalLinkStartReceptionController.setStartReceptionController(this);
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        updateLang(new DataLang(GlobalLinkMainController.getMainController().getLang()));
        loadPage();
    }
    public void openPortableDevices() {
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
        borderPaneStartReception.getChildren().remove(borderPaneStartReception.getCenter());
        GetListViewEquipment getListViewEquipment = new ListViewEquipmentPortableDatabaseMySQL();
        ProgressIndicator progressIndicator = new ProgressIndicator();
        borderPaneStartReception.setCenter(progressIndicator);
        Task<ListView<Equipment>> taskEquipments = new Task<>() {
            @Override
            protected ListView<Equipment> call() {
                return getListViewEquipment.getListViewEquipment(user, user.getCompanyAffiliation(), null);
            }
        };
        progressIndicator.progressProperty().bind(taskEquipments.progressProperty());
        taskEquipments.setOnSucceeded((s)->{
            listViewEquipments = taskEquipments.getValue();
            borderPaneStartReception.setRight(listViewEquipments);
            listViewEquipments.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
                try {
                    final FXMLLoader loaderForm = new FXMLLoader(getClass()
                            .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentFormAssignment.fxml"));
                    borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
                    borderPaneStartReception.setCenter(loaderForm.load());
                    FragmentFormAssignment controller = loaderForm.getController();
                    controller.loadFragment(newValue);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        Platform.runLater(() -> {
            progressIndicator.visibleProperty().bind(taskEquipments.runningProperty());
            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(taskEquipments);
            executorService.shutdown();
        });
    }
    private void clickListViewItemRoom(ListView<Room> listView){
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                splitPane.getItems().clear();
                borderPaneStartReception.getChildren().remove(borderPaneStartReception.getRight());
                borderPaneStartReception.getChildren().remove(borderPaneStartReception.getCenter());
                final FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentRoomWeek.fxml"));
                borderPaneStartReception.setCenter(loader.load());
                FragmentRoomWeek fragmentRoomWeek = loader.getController();
                fragmentRoomWeek.loadWeek(newValue);
            } catch (IOException e) {
                e.printStackTrace();
            }
            splitPane.setOrientation(Orientation.VERTICAL);
            splitPane.setDividerPositions(0.3f, 0.7f);

            borderPaneStartReception.setRight(splitPane);
        });
    }

}
