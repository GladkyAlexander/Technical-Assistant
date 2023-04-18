package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.*;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.GetDefectByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.SetDefectSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentBySerialNumberSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.ListEquipmentByRoomSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.UpdateEquipmentSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.room.ListRoomByCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.user.GetUserSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class FragmentAddDefectController implements Initializable {
    @FXML
    public ImageView imgOk;
    @FXML public Label labelOk;
    @FXML public ImageView close;
    @FXML public TextArea taDefect;
    @FXML public ComboBox<String> comboBoxDefectRoom;
    @FXML public Button btnAddDefect;
    @FXML public GridPane gridPanelDefectAdd;
    @FXML public TextField tfInitiator;
    @FXML public ComboBox<String> comboBoxDefectEquipment;
    @FXML public Label labelMalfunktion;
    @FXML public Label labelInitiatorsFullName;
    String lang;
    Language language = new LanguageImpl();
    Company company;
    User user;
    Equipment equipment;

    private void setLanguage(String lan){
        labelMalfunktion.setText(language.MALFUNCTION(lan));
        labelInitiatorsFullName.setText(language.INITIATORS_FULL_NAME(lan));
        comboBoxDefectRoom.setPromptText(language.CHOOSE_A_ROOM(lan));
        comboBoxDefectEquipment.setPromptText(language.SELECT_EQUIPMENT_BY_SERIAL_NUMBER(lan));
        btnAddDefect.setText(language.SAVE(lan));
    }
    public void loadFragment(){
        taDefect.setWrapText(true);
        GetListRoom getListRoom = new ListRoomByCompanySQLite();
        List<String> rooms = new ArrayList<>();
        for (Room room : getListRoom.getListRoom(user, company.getNameCompany(), null)){
            rooms.add(room.getNameRoom());
        }
        comboBoxDefectRoom.setItems(FXCollections.observableArrayList(rooms));
    }

    public void cbDEChoice() {
        comboBoxDefectEquipment.getItems().clear();

        ObservableList<String> equipmentRep = FXCollections.observableArrayList();
            if (!comboBoxDefectRoom.getItems().isEmpty()) {
                if(comboBoxDefectRoom.getValue() != null) {
                    GetListEquipment getListEquipment = new ListEquipmentByRoomSQLite();
                    for(Equipment equipment : getListEquipment.getListEquipment(user, company.getNameCompany()
                            , comboBoxDefectRoom.getValue())){
                        equipmentRep.add(equipment.getSerialNumber());
                    }
                }
            }
            comboBoxDefectEquipment.getItems().addAll(equipmentRep);
    }

    public void btnActionDefectAdd(ActionEvent actionEvent) {
        Defect defect = new Defect();
        defect.setName_Company(company.getNameCompany());
        defect.setDefect(taDefect.getText());
        if(comboBoxDefectRoom.getValue() != null) {
            defect.setRoom(comboBoxDefectRoom.getValue());
        } else defect.setRoom("");
        defect.setDate_defect(LocalDate.now());
        defect.setTime_defect(LocalTime.now());
        defect.setInitiatorName(tfInitiator.getText());
        defect.setCondition(language.FAULTY(lang));
        defect.setSerial_number_equipment(comboBoxDefectEquipment.getValue());

        GetEquipment getEquipment = new EquipmentBySerialNumberSQLite();
        this.equipment = getEquipment.getEquipment(user, company.getNameCompany(), comboBoxDefectEquipment.getValue());

        defect.setIdEquipment(equipment.getId());
        SetDefect setDefect = new SetDefectSQLite();
        setDefect.setDefect(user, company.getNameCompany(), defect);
        equipment.setCondition(language.FAULTY(lang));
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);

        GetDefect getDefect = new GetDefectByIdSQLite();
        if (getDefect.getDefect(user, company.getNameCompany(), String.valueOf(defect.getId())) != null
                && getEquipment.getEquipment(user, equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(language.FAULTY(lang))){
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
            labelOk.setText(language.ADDED(lang));
            GetUser getUser = new GetUserSQLite();
            GlobalLinkMainController.getMainController().updateUser(new DataUser(getUser.getUser(this.user.getLogin(), this.user.getPassword())));
        } else {
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
            labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
        }
    }
    public void closeAddDefectController(MouseEvent mouseEvent) {
        ((BorderPane) gridPanelDefectAdd.getParent()).getChildren().remove(gridPanelDefectAdd);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);
        loadFragment();
    }
}
