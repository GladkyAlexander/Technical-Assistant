package ru.greatlarder.technicalassistant.domain;

public class EventFormat {
    Integer id;
    String nameEventFormat;
    String urlImageEvent;
    Integer idSeatingArrangements;
    String lastNameCustomer;
    String firstNameCustomer;
    Integer idDay;
    String eventStartTime;
    String endTimeOfTheEvent;
    String nameRoom;
    String note;
    String nameCompany;

    public EventFormat() {
    }

    public EventFormat(String nameEvent, String urlImageEvent, Integer idSeatingArrangements, String lastNameCustomer, String firstNameCustomer, Integer idDay, String eventStartTime, String endTimeOfTheEvent, String nameRoom, String note, String nameCompany) {
        this.nameEventFormat = nameEvent;
        this.urlImageEvent = urlImageEvent;
        this.idSeatingArrangements = idSeatingArrangements;
        this.lastNameCustomer = lastNameCustomer;
        this.firstNameCustomer = firstNameCustomer;
        this.idDay = idDay;
        this.eventStartTime = eventStartTime;
        this.endTimeOfTheEvent = endTimeOfTheEvent;
        this.nameRoom = nameRoom;
        this.note = note;
        this.nameCompany = nameCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEventFormat() {
        return nameEventFormat;
    }

    public void setNameEventFormat(String nameEventFormat) {
        this.nameEventFormat = nameEventFormat;
    }

    public String getUrlImageEvent() {
        return urlImageEvent;
    }

    public void setUrlImageEvent(String urlImageEvent) {
        this.urlImageEvent = urlImageEvent;
    }

    public Integer getIdSeatingArrangements() {
        return idSeatingArrangements;
    }

    public void setIdSeatingArrangements(Integer idSeatingArrangements) {
        this.idSeatingArrangements = idSeatingArrangements;
    }

    public String getLastNameCustomer() {
        return lastNameCustomer;
    }

    public void setLastNameCustomer(String lastNameCustomer) {
        this.lastNameCustomer = lastNameCustomer;
    }

    public String getFirstNameCustomer() {
        return firstNameCustomer;
    }

    public void setFirstNameCustomer(String firstNameCustomer) {
        this.firstNameCustomer = firstNameCustomer;
    }

    public Integer getIdDay() {
        return idDay;
    }

    public void setIdDay(Integer idDay) {
        this.idDay = idDay;
    }

    public String getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String getEndTimeOfTheEvent() {
        return endTimeOfTheEvent;
    }

    public void setEndTimeOfTheEvent(String endTimeOfTheEvent) {
        this.endTimeOfTheEvent = endTimeOfTheEvent;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", nameEvent='" + nameEventFormat + '\'' +
                ", urlImageEvent='" + urlImageEvent + '\'' +
                ", idSeatingArrangements=" + idSeatingArrangements +
                ", lastNameCustomer='" + lastNameCustomer + '\'' +
                ", firstNameCustomer='" + firstNameCustomer + '\'' +
                ", idDay=" + idDay +
                ", eventStartTime='" + eventStartTime + '\'' +
                ", endTimeOfTheEvent='" + endTimeOfTheEvent + '\'' +
                ", nameRoom='" + nameRoom + '\'' +
                ", note='" + note + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                '}';
    }
}
