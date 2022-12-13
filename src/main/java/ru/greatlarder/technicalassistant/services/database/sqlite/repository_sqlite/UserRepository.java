package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface UserRepository {
    void setUser(User user);
    List<User> getListUser();
    User getUserLoginPassword(String login, String password);
    User change(User user, String valueWhatToChange, String valueOnWhat);
}
