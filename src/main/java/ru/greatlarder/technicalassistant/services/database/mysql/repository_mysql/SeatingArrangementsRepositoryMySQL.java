package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface SeatingArrangementsRepositoryMySQL {
    List<SeatingArrangements> getSeatingArrangementsList(User user, String nameCompany);
    SeatingArrangements getSeatingArrangementsById(User user, Integer id);
    SeatingArrangements getSeatingArrangementsByName(User user, String name);
    Integer setSeatingArrangements(User user, SeatingArrangements seatingArrangements);
    void changeSeatingArrangements(SeatingArrangements seatingArrangements);
}
