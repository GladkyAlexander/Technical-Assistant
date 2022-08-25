package ru.greatlarder.technicalassistant.services.company_listener;

public interface SubjectCompany {
    void registerObserverCompany(ObserverCompany observerCompany);
    void unregisterObserverCompany(ObserverCompany observerCompany);
    void notifyObserversCompany();
}
