package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.Events;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface EventRepositoryMySQL {
    Events getEventsForId(User user, Integer id);
    List<Events> getEventsForCompany(User user, String companyName);
    Events getEventsForNameIdDayEventStart(User user, String nameEvent, Integer idDay, String eventStartTime);
    void setEvent(User user, Events events);
    void updateEvent(User user,Events events);
}
