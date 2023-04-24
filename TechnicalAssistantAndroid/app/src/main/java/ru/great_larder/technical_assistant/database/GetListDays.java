package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.Day;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListDays {

    List<Day> getListDays(User user, Company company, String value);
}
