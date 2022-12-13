package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.MailSettings;

import java.util.List;

public interface MailSettingsRepository {
    MailSettings getMailSettingsByIdUser(int idUser);
    MailSettings setMailSettings(MailSettings mailSettings);
    List<MailSettings> getListMailSettingsByUser(int idUser);
}
