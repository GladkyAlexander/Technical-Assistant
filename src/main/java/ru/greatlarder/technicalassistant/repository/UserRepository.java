package ru.greatlarder.technicalassistant.repository;

import ru.greatlarder.technicalassistant.domain.User;

public interface UserRepository {
    User getUser();
    void setUser(User user);
}
