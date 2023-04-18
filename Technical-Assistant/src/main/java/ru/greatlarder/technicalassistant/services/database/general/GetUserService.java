package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.user.User;

import java.sql.ResultSet;

public interface GetUserService {
    User getUser(ResultSet resultSet);
}
