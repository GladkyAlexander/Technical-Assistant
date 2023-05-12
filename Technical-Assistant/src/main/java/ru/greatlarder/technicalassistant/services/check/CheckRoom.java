package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.Room;

public interface CheckRoom {
    boolean check(Room room, Room room1);
}
