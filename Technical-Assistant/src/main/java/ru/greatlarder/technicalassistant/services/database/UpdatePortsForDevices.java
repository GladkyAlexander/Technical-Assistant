package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.PortsForDevices;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface UpdatePortsForDevices {
    void updatePortsForDevices(User user, String nameCompany, PortsForDevices portsForDevices, Integer idPortsForDevices);
}
