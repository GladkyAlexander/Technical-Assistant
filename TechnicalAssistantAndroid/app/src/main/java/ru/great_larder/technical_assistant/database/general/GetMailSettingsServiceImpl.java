package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.MailSettings;

public class GetMailSettingsServiceImpl implements GetMailSettingsService{
    @Override
    public MailSettings getMailSettings(ResultSet resultSet) {
        MailSettings mailSettings = new MailSettings();

        try {
            mailSettings.setId(resultSet.getInt("id"));
            mailSettings.setMailMonitoring(resultSet.getString("mailMonitoring"));
            mailSettings.setPasswordMailMonitoring(resultSet.getString("passwordMailMonitoring"));
            mailSettings.setHostMailMonitoring(resultSet.getString("hostMailMonitoring"));
            mailSettings.setSubjectOfTheLetter(resultSet.getString("subjectOfTheLetter"));
            mailSettings.setIdUser(resultSet.getInt("idUser"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mailSettings;
    }
}
