package ru.greatlarder.technicalassistant.services.user_listener;

public interface SubjectUser {
    void registerObserverUser(ObserverUser observerUser);
    void unregisterObserverUser(ObserverUser observerUser);
    void notifyObserversUser();
}
