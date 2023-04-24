package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.Defect;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateDefect {
    void updateDefect(User user, String nameCompany, Defect defect, Integer idDefect);
}
