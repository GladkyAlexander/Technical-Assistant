package ru.greatlarder.technicalassistant.services.user_listener;

import java.util.HashSet;
import java.util.Set;

public class HandlerUserListener implements SubjectUser {

    private DataUser dataUser;
    private final Set<ObserverUser> observerUsers = new HashSet<>();

    public DataUser getDataUser(){
        return dataUser;
    }
    public void onNewDataUser(DataUser newDataUser){
        this.dataUser = newDataUser;
        notifyObserversUser();
    }

    @Override
    public void registerObserverUser(ObserverUser observerUser) {
        observerUsers.add(observerUser);
    }

    @Override
    public void unregisterObserverUser(ObserverUser observerUser) {
        observerUsers.remove(observerUser);
    }

    @Override
    public void notifyObserversUser() {
        for(ObserverUser observerUser : observerUsers){
            observerUser.updateUser(dataUser);
        }
    }

    @Override
    public void clear() {
        this.observerUsers.clear();
    }

    @Override
    public Set<ObserverUser> getOU() {
        return observerUsers;
    }
}
