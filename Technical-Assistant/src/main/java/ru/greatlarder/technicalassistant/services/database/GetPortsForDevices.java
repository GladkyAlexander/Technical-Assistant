package ru.greatlarder.technicalassistant.services.database;

import ru.greatlarder.technicalassistant.domain.PortsForDevices;
import ru.greatlarder.technicalassistant.domain.user.User;

public interface GetPortsForDevices {
    PortsForDevices getPortsForDevices(User user, String nameCompany, Integer idNetworkSwitch);
}
