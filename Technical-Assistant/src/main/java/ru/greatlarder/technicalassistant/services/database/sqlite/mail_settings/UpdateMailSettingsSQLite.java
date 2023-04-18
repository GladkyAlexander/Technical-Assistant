package ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateMailSettings;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteMailSettings;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdateMailSettingsSQLite implements UpdateMailSettings {
    @Override
    public void updateMailSettings(User user, MailSettings mailSettings, Integer id) {
        createMailSettingsTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteMailSettings.UPDATE_MAIL_SETTINGS);

            cf.setString(1, mailSettings.getMailMonitoring());
            cf.setString(2, mailSettings.getPasswordMailMonitoring());
            cf.setString(3, mailSettings.getHostMailMonitoring());
            cf.setString(4, mailSettings.getSubjectOfTheLetter());
            cf.setInt(5, mailSettings.getIdUser());

            cf.setInt(6, mailSettings.getId());

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
