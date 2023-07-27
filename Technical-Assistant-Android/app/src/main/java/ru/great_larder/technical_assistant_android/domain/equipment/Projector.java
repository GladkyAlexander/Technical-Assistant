package ru.great_larder.technical_assistant_android.domain.equipment;

import ru.great_larder.technical_assistant_android.domain.Equipment;

public class Projector extends Equipment {
    protected Integer timeWorkLampProjector;
    protected Integer maximumLampOperatingTimeProjector;

    public Projector() {
    }

    public Projector(Integer timeWorkLampProjector, Integer maximumLampOperatingTimeProjector) {
        this.timeWorkLampProjector = timeWorkLampProjector;
        this.maximumLampOperatingTimeProjector = maximumLampOperatingTimeProjector;
    }

    public Integer getTimeWorkLampProjector() {
        return timeWorkLampProjector;
    }

    public void setTimeWorkLampProjector(Integer timeWorkLampProjector) {
        this.timeWorkLampProjector = timeWorkLampProjector;
    }

    public Integer getMaximumLampOperatingTimeProjector() {
        return maximumLampOperatingTimeProjector;
    }

    public void setMaximumLampOperatingTimeProjector(Integer maximumLampOperatingTimeProjector) {
        this.maximumLampOperatingTimeProjector = maximumLampOperatingTimeProjector;
    }

    @Override
    public String toString() {
        return "Projector{" +
                "id=" + id +
                ", timeWorkLampProjector=" + timeWorkLampProjector +
                ", maximumLampOperatingTimeProjector=" + maximumLampOperatingTimeProjector +
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
