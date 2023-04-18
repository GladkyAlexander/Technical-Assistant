package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.MailSettings;

import java.sql.ResultSet;
import java.sql.SQLException;

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
