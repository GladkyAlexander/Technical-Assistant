package ru.greatlarder.technicalassistant.domain.equipment;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.Equipment;

import java.time.LocalDate;
import java.util.List;

public class Projector extends Equipment {
    protected Integer timeWorkLampProjector;
    protected Integer maximumLampOperatingTimeProjector;
    
    public Projector() {
    }
    
    public Projector(Integer timeWorkLampProjector, Integer maximumLampOperatingTimeProjector) {
        this.timeWorkLampProjector = timeWorkLampProjector;
        this.maximumLampOperatingTimeProjector = maximumLampOperatingTimeProjector;
    }
    
    public Projector(int id, String name, String model, String manufacturer, String serialNumber, String macAddress, String macAddress1, String macAddress2, String macAddress3, String login, String password, String image, String room, String location, LocalDate dateWork, String condition, String company, String manual, String ipAddress, String masc, String gateway, String danteIpAddress, String danteMasc, String danteGateway, String outletNumber, Integer portNumberInTheSwitch, Integer idNetworkSwitcher, String type, boolean visible, List<Defect> defectList, Class<? extends Equipment> cl, Integer timeWorkLampProjector, Integer maximumLampOperatingTimeProjector) {
        super(id, name, model, manufacturer, serialNumber, macAddress, macAddress1, macAddress2, macAddress3, login, password, image, room, location, dateWork, condition, company, manual, ipAddress, masc, gateway, danteIpAddress, danteMasc, danteGateway, outletNumber, portNumberInTheSwitch, idNetworkSwitcher, type, visible, defectList, cl);
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
            "timeWorkLampProjector=" + timeWorkLampProjector +
            ", maximumLampOperatingTimeProjector=" + maximumLampOperatingTimeProjector +
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
