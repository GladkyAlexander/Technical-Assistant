package ru.greatlarder.technicalassistant.services.database.sqlite.repository;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;

import java.util.List;

public interface SeatingRepository {
    List<SeatingArrangements> getListSeatingArrangementsForCompany(String nameCompany);
    void setSeatingArrangements(SeatingArrangements SeatingArrangements);
    SeatingArrangements getSeatingArrangementsForName(String nameCompany, String nameSeatingArrangements);
    void changeSeatingArrangementsName(SeatingArrangements SeatingArrangements, String value, String column);
}
