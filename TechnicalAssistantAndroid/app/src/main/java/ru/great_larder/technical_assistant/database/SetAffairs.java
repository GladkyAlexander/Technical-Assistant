package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Affairs;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetAffairs {
    Integer setAffairs(User user, String nameCompany, Affairs affairs);
}
