package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;

public interface CheckEquipment {
    boolean checkingEquipment (Equipment equipment);
    boolean checkingEquipmentBySerialNumber (String serialNumber, String nameCompany);

    String getMacAddressEquipment (String ou1, String ou2, String ou3, String ua1, String ua2, String ua3);
    boolean checkingEquipmentMacAddress(String macAddress, String nameCompany);
    boolean checkingEquipmentMac1Address(String macAddress, String nameCompany);
    boolean checkingEquipmentMac2Address(String macAddress, String nameCompany);
    boolean checkingEquipmentMac3Address(String macAddress, String nameCompany);

    String getIpAddressEquipment (String network1, String network2, String subnet, String device);
    boolean checkingEquipmentIpAddress(Equipment equipment);
    boolean checkingForAddressOccupancy (String ipAddress, String nameCompany);

    boolean checkingEquipmentIpAddressDante(Equipment equipment);
    boolean checkingForAddressOccupancyDante (String ipAddress, String nameCompany);
    String getFrequency(String frequency1, String frequency2);
    boolean checkingFrequency(String frequency, String nameCompany);
    boolean checkingTheSwitchPort(NetworkSwitch networkSwitch, int numberPort);

    boolean checkingForANumber(String value);
    boolean checkingStringWithACondition(String value);
}
