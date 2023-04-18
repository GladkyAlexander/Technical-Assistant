package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdateMailSettings {
    void updateMailSettings(User user, MailSettings mailSettings, Integer id);
}
