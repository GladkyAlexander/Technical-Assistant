package ru.greatlarder.technicalassistant.repository.impl;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.repository.MailSettingsRepository;
import ru.greatlarder.technicalassistant.services.db.SQLiteMailSettings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.db.DBconnect.*;

public class MailSettingsRepositoryImpl implements MailSettingsRepository {
    private MailSettings getMailSettings(ResultSet resultSet) {
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

    @Override
    public MailSettings getMailSettingsById(int idUser, int idMailSettings) {
        for (MailSettings mailSettings : getListMailSettings()){
            if(mailSettings.getIdUser() == idUser && mailSettings.getId() == idMailSettings) return mailSettings;
        }
        return null;
    }

    @Override
    public MailSettings setMailSettings(MailSettings mailSettings) {
        createMailSettingsTable();

        try (PreparedStatement cf = connection.prepareStatement(SQLiteMailSettings.INSERT_TABLE_MAIL_SETTINGS)) {
            cf.setString(1, mailSettings.getMailMonitoring());
            cf.setString(2, mailSettings.getPasswordMailMonitoring());
            cf.setString(3, mailSettings.getHostMailMonitoring());
            cf.setString(4, mailSettings.getSubjectOfTheLetter());
            cf.setInt(5, mailSettings.getIdUser());
            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return null;
    }

    @Override
    public List<MailSettings> getListMailSettingsByUser(int idUser) {
        List<MailSettings> mailSettingsList = new ArrayList<>();
       for (MailSettings mailSettings : getListMailSettings()){
           if(mailSettings.getIdUser() == idUser){
               mailSettingsList.add(mailSettings);
           }
       }
       return mailSettingsList;
    }
    private List<MailSettings> getListMailSettings(){
        List<MailSettings> mailSettings = new ArrayList<>();
        createMailSettingsTable();

        try {
            resultSet = statement.executeQuery(SQLiteMailSettings.READ_TABLE_MAIL_SETTINGS);

            while (resultSet.next()){
                mailSettings.add(getMailSettings(resultSet));
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return mailSettings;
    }
}
