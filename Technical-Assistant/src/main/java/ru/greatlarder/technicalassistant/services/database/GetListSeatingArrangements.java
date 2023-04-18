package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;

import java.util.List;

public interface GetListSeatingArrangements {
    List<SeatingArrangements> getListSeatingArrangements(User user, String nameCompany, String value);
}
