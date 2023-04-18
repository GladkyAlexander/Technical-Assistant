package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.EventFormat;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListEventFormat {
    List<EventFormat> getListEventFormat(User user,String nameCompany, String value);
}
