package ru.greatlarder.technicalassistant.services.room_listener;

public interface SubjectRoom {
    void registerObserverRoom(ObserverRoom observerRoom);
    void unregisterObserverRoom(ObserverRoom observerRoom);
    void notifyObserversRoom();
}
