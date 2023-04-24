package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.Day;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetDay {
    Integer setDay(User user, Company company, Day day);
}
