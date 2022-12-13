package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;

import ru.greatlarder.technicalassistant.domain.Events;

import java.util.List;

public interface EventsRepository {
    List<Events> getListEventsForCompany(String nameCompany);
    Events setEvent(Events events);
    Events getEventForName(String nameCompany, String nameEvent);
    Events changeEventName(Events events, String value, String column);
}
