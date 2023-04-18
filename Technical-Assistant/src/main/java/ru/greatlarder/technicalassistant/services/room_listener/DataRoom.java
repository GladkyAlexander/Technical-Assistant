package ru.greatlarder.technicalassistant.services.room_listener;

import ru.greatlarder.technicalassistant.domain.Room;

public class DataRoom {
    private final Room room;

    public DataRoom(Room room) {
        this.room = room;
    }

    public Room getRoom(){
        return room;
    }
}
