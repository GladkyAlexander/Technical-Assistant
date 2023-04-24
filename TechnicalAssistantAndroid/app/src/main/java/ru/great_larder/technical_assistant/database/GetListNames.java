package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Names;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListNames {
    List<Names> getListNames(User user, String nameCompany, String value);
}
