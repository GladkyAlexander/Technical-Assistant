package ru.great_larder.technical_assistant_android.domain.equipment;

import ru.great_larder.technical_assistant_android.domain.Equipment;

public class Laptop extends Equipment {
    String os;

    public Laptop(String os) {
        this.os = os;
    }

    public Laptop() {
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "os='" + os + '\'' +
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
                '}';
    }
}
