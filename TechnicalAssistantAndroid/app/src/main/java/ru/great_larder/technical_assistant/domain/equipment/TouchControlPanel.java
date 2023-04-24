package ru.great_larder.technical_assistant.domain.equipment;


import ru.great_larder.technical_assistant.domain.Equipment;

public class TouchControlPanel extends Equipment {
    String diagonal;
    public TouchControlPanel() {
    }

    public String getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(String diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return "TouchControlPanel{" +
                "diagonal='" + diagonal + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", macAddress='" + macAddress + '\'' +
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
                ", outletNumber=" + outletNumber +
                ", portNumberInTheSwitch=" + portNumberInTheSwitch +
                ", idNetworkSwitcher=" + idNetworkSwitcher +
                '}';
    }
}
