package ru.greatlarder.technicalassistant.services.lang;

public interface SubjectLang {
    void registerObserverLang(ObserverLang observerLang); //регистрация наблюдателей
    void unregisterObserverLang(ObserverLang observerLang); // удаление наблюдателей
    void notifyObserversLang(); // уведомить наблюдателей
}
