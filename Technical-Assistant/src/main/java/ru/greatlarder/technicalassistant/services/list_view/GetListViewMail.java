package ru.greatlarder.technicalassistant.services.list_view;

import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.domain.Email;
import ru.greatlarder.technicalassistant.domain.MailSettings;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetListViewMail {
    ListView<Email> getListViewEmail(User user, MailSettings mailSettings);
}
