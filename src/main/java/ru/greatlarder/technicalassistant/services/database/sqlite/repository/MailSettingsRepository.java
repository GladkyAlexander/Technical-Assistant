package ru.greatlarder.technicalassistant.services.database.sqlite.repository;

import ru.greatlarder.technicalassistant.domain.MailSettings;

import java.util.List;

public interface MailSettingsRepository {
    MailSettings getMailSettingsById(int idUser, int idMailSettings);
    MailSettings setMailSettings(MailSettings mailSettings);
    List<MailSettings> getListMailSettingsByUser(int idUser);
}
