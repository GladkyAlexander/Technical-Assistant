package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListSeatingArrangements {
    List<SeatingArrangements> getListSeatingArrangements(User user, String nameCompany, String value);
}
