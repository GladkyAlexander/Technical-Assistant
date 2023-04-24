package ru.great_larder.technical_assistant.domain;

import java.time.LocalDate;

public class BookingEquipment {
    int id;
    int idEquipment;
    int conditionBooking;
    LocalDate date;

    public BookingEquipment() {
    }

    public BookingEquipment(int id, int idEquipment, int condition_booking, LocalDate date) {
        this.id = id;
        this.idEquipment = idEquipment;
        this.conditionBooking = condition_booking;
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEquipment() {
        return idEquipment;
    }

    public void setIdEquipment(int idEquipment) {
        this.idEquipment = idEquipment;
    }

    public int getConditionBooking() {
        return conditionBooking;
    }

    public void setConditionBooking(int conditionBooking) {
        this.conditionBooking = conditionBooking;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "BookingEquipment{" +
                "id=" + id +
                ", idEquipment=" + idEquipment +
                ", condition_booking=" + conditionBooking +
                ", date=" + date +
                '}';
    }
}
