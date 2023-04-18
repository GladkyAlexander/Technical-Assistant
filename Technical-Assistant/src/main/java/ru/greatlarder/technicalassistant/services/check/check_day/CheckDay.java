package ru.greatlarder.technicalassistant.services.check.check_day;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckDay {
    boolean checkDay(User user, Day day);
}
