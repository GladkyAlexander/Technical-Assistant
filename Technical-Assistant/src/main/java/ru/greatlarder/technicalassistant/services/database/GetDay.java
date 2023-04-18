package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.time.LocalDate;

public interface GetDay {
    Day getDay(User user, LocalDate localDate, String value);
}
