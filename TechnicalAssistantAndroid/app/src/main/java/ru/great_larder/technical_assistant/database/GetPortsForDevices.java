package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.PortsForDevices;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetPortsForDevices {
    PortsForDevices getPortsForDevices(User user, String nameCompany, Integer idNetworkSwitch);
}
