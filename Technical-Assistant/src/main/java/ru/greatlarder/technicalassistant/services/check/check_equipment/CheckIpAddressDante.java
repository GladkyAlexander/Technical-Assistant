package ru.greatlarder.technicalassistant.services.check.check_equipment;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface CheckIpAddressDante {
    boolean checkIpAddress(User user, Company company, String ipAddress);
}
