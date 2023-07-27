package ru.great_larder.technical_assistant_android.services;

import java.util.Set;

public interface SubjectUser {
    void registerObserverUser(ObserverUser observerUser);
    void unregisterObserverUser(ObserverUser observerUser);
    void notifyObserversUser();
    void clear();
    Set<ObserverUser> getOU();
}
