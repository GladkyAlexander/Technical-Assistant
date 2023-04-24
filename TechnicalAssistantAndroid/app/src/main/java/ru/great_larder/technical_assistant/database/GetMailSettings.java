package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.MailSettings;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetMailSettings {
    MailSettings getMailSettings(User user, String value);
}
