package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Defect;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListDefect {
    List<Defect> getListDefect(User user, String nameCompany, String value);
}
