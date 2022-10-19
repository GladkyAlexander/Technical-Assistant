package ru.greatlarder.technicalassistant.domain;

import java.util.List;

public class Room {
    String nameRoom;
    List<Equipment> equipmentList;

    public Room(String nameRoom, List<Equipment> equipmentList) {
        this.nameRoom = nameRoom;
        this.equipmentList = equipmentList;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    @Override
    public String toString() {
        return "Room{" +
                "nameRoom='" + nameRoom + '\'' +
                ", equipmentList=" + equipmentList +
                '}';
    }
}
