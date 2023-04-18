package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetSeatingArrangements {
    Integer setSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements);
}
