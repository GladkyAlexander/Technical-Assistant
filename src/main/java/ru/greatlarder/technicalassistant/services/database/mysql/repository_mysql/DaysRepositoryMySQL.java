package ru.greatlarder.technicalassistant.services.database.mysql.repository_mysql;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.User;

import java.util.List;

public interface DaysRepositoryMySQL {

    List<Day> getDaysForRoom(User user, String nameRoom);
    void setDaysForRoom(User user, Day day);
    Day getDayForName(User user, Day day);
    boolean checkDayByName(User user, Day day);
    boolean checkDayById(User user, Integer idDay);
    void updateDay(User user, Day day);
}
