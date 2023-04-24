package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.EventFormat;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListEventFormat {
    List<EventFormat> getListEventFormat(User user, String nameCompany, String value);
}
