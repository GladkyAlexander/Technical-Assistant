package ru.greatlarder.technicalassistant.services.company_listener;

import java.util.Set;

public interface SubjectCompany {
    void registerObserverCompany(ObserverCompany observerCompany);
    void unregisterObserverCompany(ObserverCompany observerCompany);
    void notifyObserversCompany();
    void clear();
    Set<ObserverCompany> getOU();
}
