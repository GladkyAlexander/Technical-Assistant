package ru.greatlarder.technicalassistant.controller.fragment_add;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import ru.greatlarder.technicalassistant.domain.*;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.DefectRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.RoomsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.DefectRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.RoomsRepositoryImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl.UserRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentAddDefectController implements ObserverLang, ObserverCompany, ObserverUser {
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
    private String lang;
    Language language = new LanguageImpl();
    private Company company;
    private User user;
    private Equipment equipment;
    UserRepository userRepository = new UserRepositoryImpl();
    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();
    DefectRepository defectRepository = new DefectRepositoryImpl();
    List<Equipment> equipmentList = new ArrayList<>();

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }
    private void setLanguage(String lan){
        labelMalfunktion.setText(language.MALFUNCTION(lan));
        labelInitiatorsFullName.setText(language.INITIATORS_FULL_NAME(lan));
        comboBoxDefectRoom.setPromptText(language.CHOOSE_A_ROOM(lan));
        comboBoxDefectEquipment.setPromptText(language.SELECT_EQUIPMENT_BY_SERIAL_NUMBER(lan));
        btnAddDefect.setText(language.SAVE(lan));
    }
    public void loadFragment(){
        taDefect.setWrapText(true);
        RoomsRepository repository = new RoomsRepositoryImpl();
        List<String> rooms = new ArrayList<>();
        for (Room room : repository.getListRoomForCompany(company.getNameCompany())){
            rooms.add(room.getNameRoom());
        }
        comboBoxDefectRoom.setItems(FXCollections.observableArrayList(rooms));
    }

    @Override
    public void updateCompany(DataCompany dataCompany) {
        if(dataCompany == null){
            this.company = null;
        } else {
            this.company = dataCompany.getCompany();
            loadFragment();
        }
    }
    public void cbDEChoice() {
        comboBoxDefectEquipment.getItems().clear();
        ObservableList<String> equipmentRep = FXCollections.observableArrayList();
            if (!comboBoxDefectRoom.getItems().isEmpty()) {
                if(comboBoxDefectRoom.getValue() != null) {
                    equipmentList.addAll(equipmentRepository.getListEquipmentForRoom(company.getNameCompany()
                            , comboBoxDefectRoom.getValue()));
                }
            } else {
                equipmentList.addAll(company.getEquipmentList());
            }
        for(Equipment equipment : equipmentList){
            equipmentRep.add(equipment.getSerialNumber());
        }
        comboBoxDefectEquipment.setItems(equipmentRep);
    }

    public void btnActionDefectAdd(ActionEvent actionEvent) {
        Defect defect = new Defect();
        defect.setName_Company(company.getNameCompany());
        defect.setDefect(taDefect.getText());
        if(comboBoxDefectRoom.getValue() != null) {
            defect.setRoom(comboBoxDefectRoom.getValue().toString());
        } else defect.setRoom("");
        defect.setDate_defect(LocalDate.now());
        defect.setTime_defect(LocalTime.now());
        defect.setInitiatorName(tfInitiator.getText());
        defect.setCondition(language.FAULTY(lang));
        defect.setSerial_number_equipment(comboBoxDefectEquipment.getValue().toString());
        this.equipment = equipmentRepository.getEquipmentBySerialNumber(defect.getName_Company(), comboBoxDefectEquipment.getValue().toString());
        defect.setIdEquipment(equipment.getId());

        defectRepository.sendDefect(defect);
        equipmentRepository.change(equipment.getId(), "condition", language.FAULTY(lang));

        if (defectRepository.getDefectBy(defect) != null
                && equipmentRepository.getEquipmentBySerialNumber(equipment.getCompany(), equipment.getSerialNumber()).getCondition().equals(language.FAULTY(lang))){
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/ok.png"))));
            labelOk.setText(language.ADDED(lang));
            GlobalLinkMainController.getMainController().updateUser(new DataUser(userRepository.getUserLoginPassword(this.user.getLogin(), this.user.getPassword())));
        } else {
            imgOk.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/ru/greatlarder/technicalassistant/images/warning_min.png"))));
            labelOk.setText(language.WILL_NOT_BE_ADDED(lang));
        }
    }
    public void closeAddDefectController(MouseEvent mouseEvent) {
        gridPanelDefectAdd.getChildren().clear();
        gridPanelDefectAdd.setStyle(new GridPane().getStyle());
    }

    @Override
    public void updateUser(DataUser dataUser) {
        if(dataUser == null){
            this.user = null;
        } else this.user = dataUser.getUser();
    }
}
