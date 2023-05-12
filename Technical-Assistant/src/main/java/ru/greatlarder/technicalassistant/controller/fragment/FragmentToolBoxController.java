package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ru.greatlarder.technicalassistant.controller.fragment_add.*;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.Engineer;
import ru.greatlarder.technicalassistant.domain.user.Reception;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.ProblemMonitor;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentBySerialNumberSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartReceptionController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.LanguageToolBox;
import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageToolBoxImpl;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageWarningsImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentToolBoxController implements ObserverCompany, Initializable {
    @FXML
    public ImageView imgEquipment;
    @FXML
    public ImageView imgTool;
    @FXML
    public ImageView imgIpAddress;
    @FXML
    public ImageView imgAllDefect;
    @FXML
    public ImageView imgAddEquipment;
    @FXML
    public ImageView imgAddTool;
    @FXML
    public ImageView imgAddDefect;
    @FXML
    public ImageView imgWatchWorkProg;
    @FXML
    public ImageView imgEngine;
    @FXML
    public TextField tfSerNum;
    @FXML
    public ImageView imgSearch;
    @FXML
    public ImageView imgRooms;
    @FXML
    public ImageView imgAllEvent;
    @FXML
    public ImageView imgAddRoom;
    @FXML
    public ImageView imgAddEvent;
    @FXML
    public ImageView imgPhoneBook;
    @FXML
    public ImageView imgSearchIp;
    @FXML
    public ImageView imgNotes;
    @FXML public ImageView imgAddSeatingArrangement;
    @FXML public ImageView imgSeatingArrangements;
    User user;
    Company company;
    String lang;
    HandlerCompanyListener handlerCompanyListener = GlobalLinkMainController.mainController.getHandlerCompanyListener();
    Language language = new LanguageImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadPage();
        loadTooltip();
    }
    
    private void loadTooltip() {
        LanguageToolBox languageToolBox = new LanguageToolBoxImpl();
        Tooltip equipment = new Tooltip(languageToolBox.Equipment_list(lang));
        Tooltip.install(imgEquipment, equipment);
        Tooltip tool = new Tooltip(languageToolBox.List_of_tools(lang));
        Tooltip.install(imgTool, tool);
        Tooltip ipAddress = new Tooltip(languageToolBox.IP_ADDRESS_POOL(lang));
        Tooltip.install(imgIpAddress, ipAddress);
        Tooltip defect = new Tooltip(languageToolBox.LIST_OF_DEFECT(lang));
        Tooltip.install(imgAllDefect, defect);
        Tooltip rooms = new Tooltip(languageToolBox.List_of_rooms(lang));
        Tooltip.install(imgRooms, rooms);
        Tooltip events = new Tooltip(languageToolBox.List_of_events(lang));
        Tooltip.install(imgAllEvent, events);
        Tooltip notes = new Tooltip(languageToolBox.Notes(lang));
        Tooltip.install(imgNotes, notes);
        Tooltip phonebook = new Tooltip(languageToolBox.Phonebook(lang));
        Tooltip.install(imgNotes, phonebook);
        Tooltip addEquipment = new Tooltip(languageToolBox.add_equipment(lang));
        Tooltip.install(imgAddEquipment, addEquipment);
        Tooltip addTool = new Tooltip(languageToolBox.add_tools(lang));
        Tooltip.install(imgAddTool, addTool);
        Tooltip addDefect = new Tooltip(languageToolBox.add_defects(lang));
        Tooltip.install(imgAddDefect, addDefect);
        Tooltip addWatchWorkProjectors = new Tooltip(languageToolBox.Change_the_operating_time_of_the_projector_lamps(lang));
        Tooltip.install(imgWatchWorkProg, addWatchWorkProjectors);
        Tooltip addRooms = new Tooltip(languageToolBox.Add_a_room(lang));
        Tooltip.install(imgAddRoom, addRooms);
        Tooltip addEvents = new Tooltip(languageToolBox.Add_a_events(lang));
        Tooltip.install(imgAddEvent, addEvents);
        Tooltip addSeatingArrangement = new Tooltip(languageToolBox.Add_seating_arrangement(lang));
        Tooltip.install(imgAddSeatingArrangement, addSeatingArrangement);
        Tooltip ipScanner = new Tooltip(languageToolBox.ipScanner(lang));
        Tooltip.install(imgSearchIp, ipScanner);
        Tooltip seatingArrangement = new Tooltip(languageToolBox.seating_arrangement(lang));
        Tooltip.install(imgSeatingArrangements, seatingArrangement);
    }
    
    private void loadPage() {
        handlerCompanyListener.registerObserverCompany(this);
        setStartVisible(false);
        if (user == null) {
            handlerCompanyListener.unregisterObserverCompany(this);
            GlobalLinkMainController.getMainController().hBoxTopToolbar.getChildren().clear();
        } else {
            if (user instanceof Engineer) {
                if (company == null) {
                    loadUiUnActive(true);
                } else loadUiActive(false);
                loadUiEngineer(true);
            }
            if (user instanceof Reception) {
                loadUiActive(false);
                loadUiReception(true);
            }
        }

    }

    private void setStartVisible(boolean a) {
        imgEquipment.setVisible(a);
        imgEquipment.setManaged(a);
        imgTool.setVisible(a);
        imgTool.setManaged(a);
        imgIpAddress.setVisible(a);
        imgIpAddress.setManaged(a);
        imgAllDefect.setVisible(a);
        imgAllDefect.setManaged(a);
        imgAddEquipment.setVisible(a);
        imgAddEquipment.setManaged(a);
        imgAddTool.setVisible(a);
        imgAddTool.setManaged(a);
        imgAddDefect.setVisible(a);
        imgAddDefect.setManaged(a);
        imgWatchWorkProg.setVisible(a);
        imgWatchWorkProg.setManaged(a);
        imgEngine.setVisible(a);
        imgEngine.setManaged(a);
        tfSerNum.setVisible(a);
        tfSerNum.setManaged(a);
        imgSearch.setVisible(a);
        imgSearch.setManaged(a);
        imgRooms.setVisible(a);
        imgRooms.setManaged(a);
        imgAllEvent.setVisible(a);
        imgAllEvent.setManaged(a);
        imgAddRoom.setVisible(a);
        imgAddRoom.setManaged(a);
        imgAddEvent.setVisible(a);
        imgAddEvent.setManaged(a);
        imgPhoneBook.setVisible(a);
        imgPhoneBook.setManaged(a);
        imgSearchIp.setVisible(a);
        imgSearchIp.setManaged(a);
        imgNotes.setVisible(a);
        imgNotes.setManaged(a);
        imgAddSeatingArrangement.setVisible(a);
        imgAddSeatingArrangement.setManaged(a);
    }
    
    private void loadUiReception(boolean b) {
        imgSeatingArrangements.setVisible(b);
        imgSeatingArrangements.setManaged(b);
        imgRooms.setVisible(b);
        imgRooms.setManaged(b);
        imgAllEvent.setVisible(b);
        imgAllEvent.setManaged(b);
        imgPhoneBook.setVisible(b);
        imgPhoneBook.setManaged(b);
        imgNotes.setVisible(b);
        imgNotes.setManaged(b);
    }

    private void loadUiEngineer(boolean c) {
        setStartVisible(c);
    }

    private void loadUiActive(boolean d) {
        imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment.png"))));
        imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment.png"))));
        imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button.png"))));
        imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add.png"))));
        imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip.png"))));
        imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect.png"))));
        imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add.png"))));
        imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time.png"))));
        imgRooms.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_room_active.png"))));
        imgAllEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_event_active.png"))));
        imgAddRoom.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_rooms_active.png"))));
        imgAddEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_events_active.png"))));
        imgPhoneBook.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/phone_book.png"))));
        imgNotes.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_task_active.png"))));
        imgAddSeatingArrangement.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_seating_arrangement.png"))));
        imgSeatingArrangements.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/activ_seating_arrangement.png"))));

        setActive(d);
    }

    private void loadUiUnActive(boolean e) {

        imgEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_equipment_un_active.png"))));
        imgAddEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_equipment_un_active.png"))));
        imgTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_button_in_active.png"))));
        imgAddTool.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_add_in_active.png"))));
        imgIpAddress.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_ip_un_active.png"))));
        imgAllDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/tool_box_defect_un_active.png"))));
        imgAddDefect.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/defect_add_un_active.png"))));
        imgWatchWorkProg.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_time_un_active.png"))));
        imgRooms.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_room_unactive.png"))));
        imgAllEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_events_un_active.png"))));
        imgAddRoom.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_rooms_un_active.png"))));
        imgAddEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/add_events_un_active.png"))));
        imgPhoneBook.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/phone_book_unactive.png"))));
        imgNotes.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/all_task_unactive.png"))));
        imgAddSeatingArrangement.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/un_activ_seating_arrangement.png"))));
        imgSeatingArrangements.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/unactiv_seating_arrangement.png"))));
        
        imgSearch.setDisable(true);
        imgSearchIp.setDisable(true);

        setActive(e);
    }

    private void setActive(boolean w) {
        imgEquipment.setDisable(w);
        imgAddEquipment.setDisable(w);
        imgTool.setDisable(w);
        imgAddTool.setDisable(w);
        imgIpAddress.setDisable(w);
        imgAllDefect.setDisable(w);
        imgAddDefect.setDisable(w);
        imgWatchWorkProg.setDisable(w);
        imgRooms.setDisable(w);
        imgAllEvent.setDisable(w);
        imgAddRoom.setDisable(w);
        imgAddEvent.setDisable(w);
        imgPhoneBook.setDisable(w);
        imgNotes.setDisable(w);
        imgSearch.setDisable(w);
        imgSearchIp.setDisable(w);
        tfSerNum.setDisable(w);
        imgSearchIp.setDisable(w);
        imgAddSeatingArrangement.setDisable(w);
        imgSeatingArrangements.setDisable(w);
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        loadPage();
    }

    public void allEquipment() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipment.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allTool() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentTool.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allIpAddress() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentIpAddress.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void allDefect() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentDefect.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEquipment() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_equipment.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage
                        .setCenter(new Label(language.FILL_IN_THE_DB(GlobalLinkMainController.getMainController().getLang())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FragmentAddEquipmentController controller = loader.getController();
        controller.loadFragment();
    }

    public void addTool() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_tool.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController()
                        .borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(GlobalLinkMainController.getMainController().getLang())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDefect() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_defect.fxml"));
        try {
            if (company != null) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController()
                        .borderPaneEngineerPage.setCenter(new Label(language.FILL_IN_THE_DB(GlobalLinkMainController.getMainController().getLang())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void watchWorkProjectors() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_watch_work_projectors.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void imgEnginSearch() {
        if (new ProblemMonitor().searchProblemMonitor(company.getNameCompany())) {
            allDefect();
        }
    }

    public void searchSerialNumber() {
        int length = tfSerNum.getLength();

        FragmentEquipmentOneController equipmentItemController;

        if (length > 0) {
            GetEquipment getEquipment = new EquipmentBySerialNumberSQLite();
            Equipment equipment = getEquipment.getEquipment(user, company.getNameCompany(), tfSerNum.getText());

            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));

            if (equipment != null) {
                try {

                    Scene scene = new Scene(loader.load());
                    Stage stage = new Stage();

                    equipmentItemController = loader.getController();
                    equipmentItemController.setEquip(equipment);

                    stage.setTitle(equipment.getModel() + " : " + equipment.getSerialNumber());
                    stage.setScene(scene);
                    stage.show();
                    tfSerNum.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage
                        .setCenter(new Label(language.NO_DEVICE_WITH_THIS_SERIAL_NUMBER(GlobalLinkMainController.getMainController().getLang())));
                tfSerNum.clear();
            }
        } else {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage
                    .setCenter(new Label(language.ENTER_THE_SERIAL_NUMBER(GlobalLinkMainController.getMainController().getLang())));
            tfSerNum.clear();
        }
    }

    public void allRooms() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentRoom.fxml"));
        try {
            if(user instanceof Engineer) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            }
            if (user instanceof Reception){
                GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAllEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEvents.fxml"));
        try {
            if (user instanceof Engineer) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            }
            if (user instanceof Reception) {
                GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addRoom() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_room.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            if (company != null) {
                FragmentAddRoom fragmentAddRoom = loader.getController();
                fragmentAddRoom.loadFragment(this.company);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_name_event.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            if (company != null) {
                FragmentAddNameEvent fragmentAddNameEvent = loader.getController();
                fragmentAddNameEvent.loadFragment(this.company);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                LanguageWarnings languageWarnings = new LanguageWarningsImpl();
                alert.setTitle(languageWarnings.CHOOSE_A_COMPANY(lang));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
                alert.setContentText(languageWarnings.CHOOSE_A_COMPANY(lang));
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openPhoneBook() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentPhoneBook.fxml"));
        try {
            if (user.getPost().equals("Engineer") || user.getPost().equals("Инженер")) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            }
            if (user.getPost().equals("Reception Secretary") || user.getPost().equals("Секретарь приемной")) {
                GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());
            }
            FragmentPhoneBook controller1 = loader.getController();
            controller1.loadFragment();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startSearchIp() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_ip_address.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setRight(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public void openNotes() {
    }
    public void addSeatingArrangement() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_add/add_seating_arrangements.fxml"));
        try {
            GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            if (company != null) {
                FragmentAddSeatingArrangements fragmentAddSeatingArrangements = loader.getController();
                fragmentAddSeatingArrangements.loadFragment(this.company);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                LanguageWarnings languageWarnings = new LanguageWarningsImpl();
                alert.setTitle(languageWarnings.CHOOSE_A_COMPANY(lang));
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/logo.png"))));
                alert.setContentText(languageWarnings.CHOOSE_A_COMPANY(lang));
                alert.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void allSeatingArrangement() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentSeatingArrangement.fxml"));
        try {
            if(user instanceof Engineer) {
                GlobalLinkStartEngineerController.getStartEngineerController().borderPaneEngineerPage.setCenter(loader.load());
            }
            if(user instanceof Reception){
                GlobalLinkStartReceptionController.getStartReceptionController().borderPaneStartReception.setCenter(loader.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
