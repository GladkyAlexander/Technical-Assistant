package ru.greatlarder.technicalassistant.domain;

import java.util.List;

public class Letter {
    String from;
    String to;
    String topic;
    String nameEvent;
    String seatingArrangements;
    String numberOfParticipants;
    String dateStart;
    String timeStart;
    String office;
    String customer;
    List<Equipment> equipmentList;
    String note;

    public Letter() {
    }

    public Letter(String from, String to, String topic, String nameEvent, String seatingArrangements, String numberOfParticipants, String dateStart, String timeStart, String office, String customer, List<Equipment> equipmentList, String note) {
        this.from = from;
        this.to = to;
        this.topic = topic;
        this.nameEvent = nameEvent;
        this.seatingArrangements = seatingArrangements;
        this.numberOfParticipants = numberOfParticipants;
        this.dateStart = dateStart;
        this.timeStart = timeStart;
        this.office = office;
        this.customer = customer;
        this.equipmentList = equipmentList;
        this.note = note;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getSeatingArrangements() {
        return seatingArrangements;
    }

    public void setSeatingArrangements(String seatingArrangements) {
        this.seatingArrangements = seatingArrangements;
    }

    public String getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(String numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", topic='" + topic + '\'' +
                ", nameEvent='" + nameEvent + '\'' +
                ", seatingArrangements='" + seatingArrangements + '\'' +
                ", numberOfParticipants='" + numberOfParticipants + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", office='" + office + '\'' +
                ", customer='" + customer + '\'' +
                ", equipmentList=" + equipmentList +
                ", note='" + note + '\'' +
                '}';
    }
}
