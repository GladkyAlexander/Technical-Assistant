package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetSeatingArrangements {
    SeatingArrangements getSeatingArrangements(User user, String nameCompany, String value);
}
