package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListMailSettings {
    List<MailSettings> getListMailSettings(User user, String value);
}
