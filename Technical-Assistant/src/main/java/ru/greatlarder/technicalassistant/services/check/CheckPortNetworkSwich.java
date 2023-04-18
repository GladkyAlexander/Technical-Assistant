package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;

public interface CheckPortNetworkSwich {
    boolean checkingTheSwitchPort(NetworkSwitch networkSwitch, int numberPort);
}
