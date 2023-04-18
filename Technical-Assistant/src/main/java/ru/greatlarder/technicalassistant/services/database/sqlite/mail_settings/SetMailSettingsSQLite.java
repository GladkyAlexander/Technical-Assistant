package ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetMailSettings;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteMailSettings;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetMailSettingsSQLite implements SetMailSettings {
    @Override
    public Integer setMailSettings(User user, MailSettings mailSettings) {
        Integer idMailSettings = null;
        createMailSettingsTable();

        try (PreparedStatement cf = connection.prepareStatement(SQLiteMailSettings.INSERT_TABLE_MAIL_SETTINGS, Statement.RETURN_GENERATED_KEYS)) {
            cf.setString(1, mailSettings.getMailMonitoring());
            cf.setString(2, mailSettings.getPasswordMailMonitoring());
            cf.setString(3, mailSettings.getHostMailMonitoring());
            cf.setString(4, mailSettings.getSubjectOfTheLetter());
            cf.setInt(5, mailSettings.getIdUser());

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idMailSettings = rs.getInt(1);
                }
            }

            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return idMailSettings;
    }
}
