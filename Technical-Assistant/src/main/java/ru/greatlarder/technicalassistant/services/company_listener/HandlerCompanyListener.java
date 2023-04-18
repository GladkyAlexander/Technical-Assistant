package ru.greatlarder.technicalassistant.services.company_listener;

import java.util.HashSet;
import java.util.Set;

public class HandlerCompanyListener implements SubjectCompany {

    private DataCompany dataCompany;
    private final Set<ObserverCompany> observerCompanies = new HashSet<>();

    public DataCompany getDataCompany(){
        return dataCompany;
    }
    public void onNewDataCompany(DataCompany newDataCompany){
        this.dataCompany = newDataCompany;
        notifyObserversCompany();
    }

    @Override
    public void registerObserverCompany(ObserverCompany observerCompany) {
        observerCompanies.add(observerCompany);
    }

    @Override
    public void unregisterObserverCompany(ObserverCompany observerCompany) {
        observerCompanies.remove(observerCompany);
    }

    @Override
    public void notifyObserversCompany() {
        for(ObserverCompany observerCompany : observerCompanies){
            observerCompany.updateCompany(dataCompany);
        }
    }

    @Override
    public void clear() {
        observerCompanies.clear();
    }

    @Override
    public Set<ObserverCompany> getOU() {
        return observerCompanies;
    }
}
