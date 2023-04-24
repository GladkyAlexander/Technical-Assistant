package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.MailSettings;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateMailSettings {
    void updateMailSettings(User user, MailSettings mailSettings, Integer id);
}
