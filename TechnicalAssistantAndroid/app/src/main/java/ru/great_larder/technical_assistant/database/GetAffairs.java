package ru.great_larder.technical_assistant.database;


import ru.great_larder.technical_assistant.domain.Affairs;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetAffairs {
    Affairs getAffairs(User user, String nameCompany, String value);
}
