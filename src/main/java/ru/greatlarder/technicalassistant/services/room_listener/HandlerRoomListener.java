package ru.greatlarder.technicalassistant.services.room_listener;

import java.util.HashSet;
import java.util.Set;

public class HandlerRoomListener implements SubjectRoom{
    private DataRoom dataRoom;
    private Set<ObserverRoom> observerRooms = new HashSet<>();

    public DataRoom getDataRoom(){
        return dataRoom;
    }
    public void onNewDataRoom(DataRoom newDataRoom){
        this.dataRoom = newDataRoom;
        notifyObserversRoom();
    }

    @Override
    public void registerObserverRoom(ObserverRoom observerRoom) {
        observerRooms.add(observerRoom);
    }

    @Override
    public void unregisterObserverRoom(ObserverRoom observerRoom) {
        observerRooms.remove(observerRoom);
    }

    @Override
    public void notifyObserversRoom() {
        for(ObserverRoom observerRoom : observerRooms){
            observerRoom.updateRoom(dataRoom);
        }
    }
}
