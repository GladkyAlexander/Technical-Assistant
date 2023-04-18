package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetMailSettings {
    MailSettings getMailSettings(User user, String value);
}
