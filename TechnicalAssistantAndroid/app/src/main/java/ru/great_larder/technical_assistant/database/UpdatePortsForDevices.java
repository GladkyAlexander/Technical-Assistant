package ru.great_larder.technical_assistant.database;

import ru.great_larder.technical_assistant.domain.PortsForDevices;
import ru.great_larder.technical_assistant.domain.user.User;

public interface UpdatePortsForDevices {
    void updatePortsForDevices(User user, String nameCompany, PortsForDevices portsForDevices, Integer idPortsForDevices);
}
