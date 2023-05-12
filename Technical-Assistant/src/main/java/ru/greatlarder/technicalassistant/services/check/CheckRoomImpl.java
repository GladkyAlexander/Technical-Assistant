package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.Room;

public class CheckRoomImpl implements CheckRoom{
    @Override
    public boolean check(Room room, Room room1) {
        
        return (room.getNameRoom().equals(room1.getNameRoom()) && room.getNameCompanyForRoom().equals(room1.getNameCompanyForRoom()));
        
        //return false;
    }
}
