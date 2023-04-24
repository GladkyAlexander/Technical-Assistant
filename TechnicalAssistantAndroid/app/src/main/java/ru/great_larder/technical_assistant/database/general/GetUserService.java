package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;

import ru.great_larder.technical_assistant.domain.user.User;

public interface GetUserService {
    User getUser(ResultSet resultSet);
}
