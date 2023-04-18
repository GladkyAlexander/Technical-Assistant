package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetSeatingArrangements {
    SeatingArrangements getSeatingArrangements(User user,String nameCompany, String value);
}
