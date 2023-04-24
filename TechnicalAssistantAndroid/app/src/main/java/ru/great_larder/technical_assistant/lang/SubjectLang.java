package ru.great_larder.technical_assistant.lang;

import java.util.Set;

public interface SubjectLang {
    void registerObserverLang(ObserverLang observerLang); //регистрация наблюдателей
    void unregisterObserverLang(ObserverLang observerLang); // удаление наблюдателей
    void notifyObserversLang(); // уведомить наблюдателей
    Set<ObserverLang> getObserverLangList(); //

    void clear();
}
