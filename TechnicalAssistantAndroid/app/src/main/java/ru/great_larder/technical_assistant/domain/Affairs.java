package ru.great_larder.technical_assistant.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Affairs {
    private int id;
    private LocalDate dateOfCreation;
    private LocalTime timeOfCreation;
    private LocalDate closingDate;
    private LocalTime closingTime;
    private String creator;
    private String room;
    private String executor;
    private String textTask;
    private int status;
    private String nameCompany;

    public Affairs(LocalDate dateOfCreation, LocalTime timeOfCreation, LocalDate closingDate, LocalTime closingTime
            , String creator, String room, String executor, String textTask, int status, String nameCompany) {
        this.dateOfCreation = dateOfCreation;
        this.timeOfCreation = timeOfCreation;
        this.closingDate = closingDate;
        this.closingTime = closingTime;
        this.creator = creator;
        this.room = room;
        this.executor = executor;
        this.textTask = textTask;
        this.status = status;
        this.nameCompany = nameCompany;
    }

    public Affairs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(LocalDate dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public LocalTime getTimeOfCreation() {
        return timeOfCreation;
    }

    public void setTimeOfCreation(LocalTime timeOfCreation) {
        this.timeOfCreation = timeOfCreation;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getTextTask() {
        return textTask;
    }

    public void setTextTask(String textTask) {
        this.textTask = textTask;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", dateOfCreation=" + dateOfCreation +
                ", timeOfCreation=" + timeOfCreation +
                ", closingDate=" + closingDate +
                ", closingTime=" + closingTime +
                ", creator='" + creator + '\'' +
                ", room='" + room + '\'' +
                ", executor='" + executor + '\'' +
                ", textTask='" + textTask + '\'' +
                ", status=" + status +
                ", nameCompany='" + nameCompany + '\'' +
                '}';
    }
}