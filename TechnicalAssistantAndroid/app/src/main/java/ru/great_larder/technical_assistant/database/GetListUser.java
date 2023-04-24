package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListUser {
    List<User> getListUser(String value1, String value2);
}
