package ru.greatlarder.technicalassistant.domain.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;

public class TvPanel extends Equipment {
    String diagonal;
    public TvPanel() {
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }
    
    @Override
    public String toString() {
        return "TvPanel{" +
            "diagonal='" + diagonal + '\'' +
            ", id=" + id +
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
