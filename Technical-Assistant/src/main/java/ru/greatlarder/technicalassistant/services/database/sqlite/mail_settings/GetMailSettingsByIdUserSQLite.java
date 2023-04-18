package ru.greatlarder.technicalassistant.services.database.sqlite.mail_settings;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetMailSettings;
import ru.greatlarder.technicalassistant.services.database.general.GetMailSettingsService;
import ru.greatlarder.technicalassistant.services.database.general.GetMailSettingsServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteMailSettings;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetMailSettingsByIdUserSQLite implements GetMailSettings {
    @Override
    public MailSettings getMailSettings(User user, String idUser) {
        createMailSettingsTable();
        try {
            resultSet = statement.executeQuery(SQLiteMailSettings.READ_TABLE_MAIL_SETTINGS);
            while (resultSet.next()){
                if(Objects.equals(resultSet.getInt("idUser"), Integer.valueOf(idUser))){
                    GetMailSettingsService getMailSettingsService = new GetMailSettingsServiceImpl();
                    return getMailSettingsService.getMailSettings(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
