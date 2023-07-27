package ru.great_larder.technical_assistant_android.services;

import ru.great_larder.technical_assistant_android.domain.user.User;

public class DataUser {
    private final User user;

    public DataUser(User user) {
        this.user = user;
    }

    public User getUser(){
        return user;
    }

}
