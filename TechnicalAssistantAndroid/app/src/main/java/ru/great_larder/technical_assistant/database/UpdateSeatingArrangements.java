package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdateSeatingArrangements {
    void updateSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements, Integer id);
}
