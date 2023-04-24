package ru.great_larder.technical_assistant.lang;

import java.util.HashSet;
import java.util.Set;

public class HandlerLang implements SubjectLang {

    private DataLang dataLang;
    private Set<ObserverLang> observerLangs = new HashSet<>();

    public DataLang getDataLang(){
        return dataLang;
    }
    public void onNewDataLang(DataLang newDataMain){

        this.dataLang = newDataMain;

        notifyObserversLang();
    }

    @Override
    public void registerObserverLang(ObserverLang observerLang) {
        observerLangs.add(observerLang);
    }

    @Override
    public void unregisterObserverLang(ObserverLang observerLang) {
        observerLangs.remove(observerLang);
    }

    @Override
    public void notifyObserversLang() {
        for(ObserverLang observerMain : observerLangs){
            observerMain.updateLang(dataLang);
        }
    }

    @Override
    public Set<ObserverLang> getObserverLangList() {
        return observerLangs;
    }

    @Override
    public void clear() {
        observerLangs.clear();
    }
}
