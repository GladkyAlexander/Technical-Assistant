package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface ListSeatingArrangementsNameRepositoryMySQL{
    void setSeatingArrangementsName(SeatingArrangements seatingArrangementsName);
    void setSeatingArrangementsNameList(User user, List<SeatingArrangements> seatingArrangements);
    List<String> getSeatingArrangementsNameList(User user, String companyName);
    SeatingArrangements getSeatingArrangementsName(String companyName, String SeatingArrangements);
    void updateSeatingArrangementsName(SeatingArrangements seatingArrangements);
    void updateSeatingArrangementsNameList(List<SeatingArrangements> seatingArrangements);
}
