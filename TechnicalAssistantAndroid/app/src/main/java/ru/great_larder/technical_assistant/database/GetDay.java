package ru.great_larder.technical_assistant.database;


import java.time.LocalDate;

import ru.great_larder.technical_assistant.domain.Day;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetDay {
    Day getDay(User user, LocalDate localDate, String value);
}
