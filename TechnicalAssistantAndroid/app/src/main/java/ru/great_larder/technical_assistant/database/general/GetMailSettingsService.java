package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;

import ru.great_larder.technical_assistant.domain.MailSettings;

public interface GetMailSettingsService {
    MailSettings getMailSettings(ResultSet resultSet);
}
