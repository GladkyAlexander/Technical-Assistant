package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateCompany {
    void updateCompany(User user, Company company);
}