package ru.great_larder.technical_assistant_android.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Defect {
    private int id;
    private String defect;
    private String room;
    private LocalDate date_defect;
    private LocalTime time_defect;
    private String initiatorName;
    private String condition;
    private LocalDate dateOfCompletion;
    private LocalTime timeOfCompletion;
    private String executorName;
    private String serial_number_equipment;
    private int idEquipment;
    private String causeOfTheMalfunction;
    private String howFixed;
    private String noteExecutor;
    private String name_Company;

    public Defect(){
    }

    public Defect(String defect, String room, LocalDate date_defect, LocalTime time_defect, String initiatorName
            , String condition, LocalDate dateOfCompletion, LocalTime timeOfCompletion, String executorName
            , String serial_number_equipment, int idEquipment, String causeOfTheMalfunction, String howFixed, String noteExecutor, String name_Company) {
        this.defect = defect;
        this.room = room;
        this.date_defect = date_defect;
        this.time_defect = time_defect;
        this.initiatorName = initiatorName;
        this.condition = condition;
        this.dateOfCompletion = dateOfCompletion;
        this.timeOfCompletion = timeOfCompletion;
        this.executorName = executorName;
        this.serial_number_equipment = serial_number_equipment;
        this.idEquipment = idEquipment;
        this.causeOfTheMalfunction = causeOfTheMalfunction;
        this.howFixed = howFixed;
        this.noteExecutor = noteExecutor;
        this.name_Company = name_Company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public LocalDate getDate_defect() {
        return date_defect;
    }

    public void setDate_defect(LocalDate date_defect) {
        this.date_defect = date_defect;
    }

    public LocalTime getTime_defect() {
        return time_defect;
    }

    public void setTime_defect(LocalTime time_defect) {
        this.time_defect = time_defect;
    }

    public String getInitiatorName() {
        return initiatorName;
    }

    public void setInitiatorName(String initiatorName) {
        this.initiatorName = initiatorName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public LocalDate getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(LocalDate dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public LocalTime getTimeOfCompletion() {
        return timeOfCompletion;
    }

    public void setTimeOfCompletion(LocalTime timeOfCompletion) {
        this.timeOfCompletion = timeOfCompletion;
    }

    public String getExecutorName() {
        return executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getSerial_number_equipment() {
        return serial_number_equipment;
    }

    public void setSerial_number_equipment(String serial_number_equipment) {
        this.serial_number_equipment = serial_number_equipment;
    }

    public String getCauseOfTheMalfunction() {
        return causeOfTheMalfunction;
    }

    public void setCauseOfTheMalfunction(String causeOfTheMalfunction) {
        this.causeOfTheMalfunction = causeOfTheMalfunction;
    }

    public String getHowFixed() {
        return howFixed;
    }

    public void setHowFixed(String howFixed) {
        this.howFixed = howFixed;
    }

    public String getNoteExecutor() {
        return noteExecutor;
    }

    public void setNoteExecutor(String noteExecutor) {
        this.noteExecutor = noteExecutor;
    }

    public String getName_Company() {
        return name_Company;
    }

    public void setName_Company(String name_Company) {
        this.name_Company = name_Company;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    @Override
    public String toString() {
        return "Defect{" +
                "id=" + id +
                ", defect='" + defect + '\'' +
                ", room='" + room + '\'' +
                ", date_defect=" + date_defect +
                ", time_defect=" + time_defect +
                ", initiatorName='" + initiatorName + '\'' +
                ", condition='" + condition + '\'' +
                ", dateOfCompletion=" + dateOfCompletion +
                ", timeOfCompletion=" + timeOfCompletion +
                ", executorName='" + executorName + '\'' +
                ", serial_number_equipment='" + serial_number_equipment + '\'' +
                ", idEquipment=" + idEquipment +
                ", causeOfTheMalfunction='" + causeOfTheMalfunction + '\'' +
                ", howFixed='" + howFixed + '\'' +
                ", noteExecutor='" + noteExecutor + '\'' +
                ", name_Company='" + name_Company + '\'' +
                '}';
    }
}
