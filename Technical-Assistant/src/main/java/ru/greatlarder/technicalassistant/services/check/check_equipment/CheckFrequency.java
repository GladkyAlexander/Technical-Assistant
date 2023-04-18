package ru.greatlarder.technicalassistant.services.check.check_equipment;

import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckFrequency {
    boolean checkFrequency (User user, String nameCompany, String frequency);
}
