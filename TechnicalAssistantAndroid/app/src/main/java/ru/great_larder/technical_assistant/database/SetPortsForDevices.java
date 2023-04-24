package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.PortsForDevices;
import ru.great_larder.technical_assistant.domain.user.User;

public interface SetPortsForDevices {
    Integer setPortsForDevices(User user, String nameCompany, PortsForDevices portsForDevices);
}
