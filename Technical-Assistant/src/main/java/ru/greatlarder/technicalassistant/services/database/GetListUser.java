package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListUser {
    List<User> getListUser(String value1, String value2);
}
