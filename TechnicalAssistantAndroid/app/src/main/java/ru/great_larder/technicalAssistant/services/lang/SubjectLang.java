package ru.great_larder.technicalAssistant.services.lang;

import java.util.Set;

public interface SubjectLang {
    void registerObserverLang(ObserverLang observerLang); //регистрация наблюдателей
    void unregisterObserverLang(ObserverLang observerLang); // удаление наблюдателей
    void notifyObserversLang(); // уведомить наблюдателей
    Set<ObserverLang> getObserverLangList(); //

    void clear();
}
