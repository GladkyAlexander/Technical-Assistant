package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetMailSettings {
    Integer setMailSettings(User user, MailSettings mailSettings);
}
