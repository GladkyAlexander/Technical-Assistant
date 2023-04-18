package ru.greatlarder.technicalassistant.services.user_listener;

import ru.greatlarder.technicalassistant.domain.user.User;

public class DataUser {
    private final User user;

    public DataUser(User user) {
        this.user = user;
    }

    public User getUser(){
        return user;
    }

}
