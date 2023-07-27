package ru.greatlarder.technicalassistant.domain.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;

public class TvTuner extends Equipment {
    public TvTuner() {
    }
    
    @Override
    public String toString() {
        return "TvTuner{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", model='" + model + '\'' +
            ", manufacturer='" + manufacturer + '\'' +
            ", serialNumber='" + serialNumber + '\'' +
            ", macAddress='" + macAddress + '\'' +
            ", macAddress1='" + macAddress1 + '\'' +
            ", macAddress2='" + macAddress2 + '\'' +
            ", macAddress3='" + macAddress3 + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", image='" + image + '\'' +
            ", room='" + room + '\'' +
            ", location='" + location + '\'' +
            ", dateWork=" + dateWork +
            ", condition='" + condition + '\'' +
            ", company='" + company + '\'' +
            ", manual='" + manual + '\'' +
            ", ipAddress='" + ipAddress + '\'' +
            ", masc='" + masc + '\'' +
            ", gateway='" + gateway + '\'' +
            ", danteIpAddress='" + danteIpAddress + '\'' +
            ", danteMasc='" + danteMasc + '\'' +
            ", danteGateway='" + danteGateway + '\'' +
            ", outletNumber='" + outletNumber + '\'' +
            ", portNumberInTheSwitch=" + portNumberInTheSwitch +
            ", idNetworkSwitcher=" + idNetworkSwitcher +
            ", type='" + type + '\'' +
            ", userVisibility=" + userVisibility +
            '}';
    }
}
