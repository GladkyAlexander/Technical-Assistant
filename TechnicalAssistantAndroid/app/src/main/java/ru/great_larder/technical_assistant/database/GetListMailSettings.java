package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.MailSettings;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListMailSettings {
    List<MailSettings> getListMailSettings(User user, String value);
}
