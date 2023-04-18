package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.MailSettings;

import java.sql.ResultSet;

public interface GetMailSettingsService {
    MailSettings getMailSettings(ResultSet resultSet);
}
