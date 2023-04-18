package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.PortsForDevices;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface SetPortsForDevices {
    Integer setPortsForDevices(User user, String nameCompany, PortsForDevices portsForDevices);
}
