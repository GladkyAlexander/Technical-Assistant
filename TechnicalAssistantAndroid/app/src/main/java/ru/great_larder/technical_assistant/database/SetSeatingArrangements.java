package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetSeatingArrangements {
    Integer setSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements);
}
