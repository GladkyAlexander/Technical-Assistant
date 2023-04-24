package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Affairs;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListAffairs {
    List<Affairs> getListAffairs(User user, String nameCompany, String value);
}
