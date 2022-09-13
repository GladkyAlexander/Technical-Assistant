package ru.greatlarder.technicalassistant.controller.fragment_add;

import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class FragmentAddDatabaseSettings implements ObserverLang, ObserverUser {

    private String lang;
    private User user;

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        setLanguage(lang);
    }

    private void setLanguage(String lang) {
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
}
