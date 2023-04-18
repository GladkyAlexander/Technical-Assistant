package ru.greatlarder.technicalassistant.controller.fragment_item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetDefect;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.UpdateDefect;
import ru.greatlarder.technicalassistant.services.database.UpdateEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.GetDefectByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.UpdateDefectSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.UpdateEquipmentSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class ItemDefectController implements Initializable {
    @FXML public Label labelDateOfCreation;
    @FXML public Label labelTimeOfCreation;
    @FXML public Label labelEquipment;
    @FXML public Label labelSerialNumber;
    @FXML public Label labelRoot;
    @FXML public TextArea textAreaCauseMalfunction;
    @FXML public TextArea textAreaHowFixed;
    @FXML public TextArea textAreaNote;
    @FXML public CheckBox checkBoxStatus;
    @FXML public Button btnSaveDefectOne;
     Defect defect;
    int idEquipment;
    @FXML public ImageView imgLogoEquipment;
    @FXML public HBox hBoxItemDefect;
    Label labelSpecifyTheReason;
    Label labelHowFixed;
    Label labelNote;
    Label labelFixed;
    Language language = new LanguageImpl();
    String lang;
    Company company;
    User user;

    public void save(ActionEvent actionEvent) {
        Defect defect1 = new Defect();
        defect1.setId(defect.getId());
        defect1.setDefect(defect.getDefect());
        defect1.setRoom(defect.getRoom());
        defect1.setDate_defect(defect.getDate_defect());
        defect1.setTime_defect(defect.getTime_defect());
        defect1.setInitiatorName(defect.getInitiatorName());
        if(checkBoxStatus.isSelected()){
            defect1.setCondition(language.SATISFACTORY(lang));
            defect1.setDateOfCompletion(LocalDate.now());
            defect1.setTimeOfCompletion(LocalTime.now());
            defect1.setExecutorName(defect.getExecutorName());
        } else if(!checkBoxStatus.isSelected()){
            defect1.setCondition(language.FAULTY("ru"));
            defect1.setDateOfCompletion(defect.getDateOfCompletion());
            defect1.setTimeOfCompletion(defect.getTime_defect());
            defect1.setExecutorName(defect.getExecutorName());
        }
        defect1.setSerial_number_equipment(defect.getSerial_number_equipment());
        defect1.setIdEquipment(idEquipment);
        defect1.setCauseOfTheMalfunction(textAreaCauseMalfunction.getText());
        defect1.setHowFixed(textAreaHowFixed.getText());
        defect1.setNoteExecutor(textAreaNote.getText());
        defect1.setName_Company(defect.getName_Company());

        GetEquipment getEquipment = new EquipmentByIdSQLite();
        Equipment equipment = getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(idEquipment));
        equipment.setCondition(language.SATISFACTORY(lang));
        UpdateEquipment updateEquipment = new UpdateEquipmentSQLite();
        updateEquipment.updateEquipment(user, company, equipment);

        UpdateDefect updateDefect = new UpdateDefectSQLite();
        updateDefect.updateDefect(user, company.getNameCompany(), defect1, defect1.getId());

        GetDefect getDefect = new GetDefectByIdSQLite();

        if(getEquipment.getEquipment(user, company.getNameCompany(), String.valueOf(idEquipment)).getCondition().equals(language.SATISFACTORY(lang))
        && getDefect.getDefect(user, company.getNameCompany(), String.valueOf(defect1.getId())).getCondition().equals(language.SATISFACTORY(lang))){
            hBoxItemDefect.setStyle(new HBox().getStyle());
            hBoxItemDefect.getChildren().clear();
        } else hBoxItemDefect.setStyle(StyleSRC.STYLE_DANGER);

    }

    public void setDefect(Defect defect){
        this.defect = defect;
    }

    public void setLabelDateOfCreation(String labelDateOfCreation) {
        this.labelDateOfCreation.setText(labelDateOfCreation);
    }

    public void setLabelTimeOfCreation(String labelTimeOfCreation) {
        this.labelTimeOfCreation.setText(labelTimeOfCreation);
    }

    public void setLabelEquipment(String labelEquipment) {
        this.labelEquipment.setText(labelEquipment);
    }

    public void setLabelSerialNumber(String labelSerialNumber) {
        this.labelSerialNumber.setText(labelSerialNumber);
    }

    public void setLabelRoot(String labelRoot) {
        this.labelRoot.setText(labelRoot);
    }

    public void setTextAreaCauseMalfunction(String textAreaCauseMalfunction) {
        this.textAreaCauseMalfunction.setText(textAreaCauseMalfunction);
    }

    public void setTextAreaHowFixed(String textAreaHowFixed) {
        this.textAreaHowFixed.setText(textAreaHowFixed);
    }

    public void setTextAreaNote(String textAreaNote) {
        this.textAreaNote.setText(textAreaNote);
    }

    public void setCheckBoxStatus(String status) {
        if(status.equals(language.SATISFACTORY(lang))){
            this.checkBoxStatus.setSelected(true);
        } else this.checkBoxStatus.setSelected(false);
    }

    public void setImgLogoEquipment(String imgLogoEquipment) {
        this.imgLogoEquipment.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream(imgLogoEquipment))));
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    private void setLanguage(String lang1) {
        labelSpecifyTheReason.setText(language.SPECIFY_THE_REASON(lang1));
        labelHowFixed.setText(language.HOW_FIXED(lang1));
        labelNote.setText(language.NOTE(lang1));
        labelFixed.setText(language.FIXED(lang1));
        btnSaveDefectOne.setText(language.SAVE(lang1));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        setLanguage(lang);

    }
}
