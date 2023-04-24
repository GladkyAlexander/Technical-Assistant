package ru.great_larder.technical_assistant.database;


import ru.great_larder.technical_assistant.domain.Defect;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetDefect {
    Defect getDefect(User user, String nameCompany, String value);
}
