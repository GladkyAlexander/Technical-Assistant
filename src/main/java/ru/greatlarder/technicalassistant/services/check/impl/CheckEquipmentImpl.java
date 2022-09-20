package ru.greatlarder.technicalassistant.services.check.impl;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.Microphone;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.repository.impl.EquipmentRepositoryImpl;
import ru.greatlarder.technicalassistant.services.check.CheckEquipment;

import java.util.HashMap;
import java.util.List;

public class CheckEquipmentImpl implements CheckEquipment {

    EquipmentRepository equipmentRepository = new EquipmentRepositoryImpl();

    @Override
    public boolean checkingEquipment(Equipment equipment) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(equipment.getCompany());
        for (Equipment equipment1 : equipmentList){
            if(equipment.equals(equipment1)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingEquipmentBySerialNumber(String serialNumber, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment1 : equipmentList){
            if(serialNumber.equals(equipment1.getSerialNumber())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getMacAddressEquipment(String oui1, String oui2, String oui3, String uaa1, String uaa2, String uaa3) {
        String macAddress = null;

        if (!oui1.trim().isEmpty() && !oui2.trim().isEmpty() && !oui3.trim().isEmpty()
                && !uaa1.trim().isEmpty() && !uaa2.trim().isEmpty() && !uaa3.trim().isEmpty()) {
            if (oui1.length() == 2 && oui2.length() == 2 && oui3.length() == 2
                    && uaa1.length() == 2 && uaa2.length() == 2 && uaa3.length() == 2) {
                macAddress = oui1 + ":" + oui2 + ":" + oui3 + ":"
                        + uaa1 + ":" + uaa2 + ":" + uaa3;
            }
        }
        return macAddress;
    }

    @Override
    public boolean checkingEquipmentMacAddress(String macAddress, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment : equipmentList){
            if(equipment.getMacAddress().equals(macAddress)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingEquipmentMac1Address(String macAddress, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment : equipmentList){
            if(equipment.getMacAddress1().equals(macAddress)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingEquipmentMac2Address(String macAddress, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment : equipmentList){
            if(equipment.getMacAddress2().equals(macAddress)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingEquipmentMac3Address(String macAddress, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment : equipmentList){
            if(equipment.getMacAddress3().equals(macAddress)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getIpAddressEquipment(String network1, String network2, String subnet, String device) {
        String ipAddress = null;
        if (!network1.trim().isEmpty() && !network2.trim().isEmpty()
                && !subnet.trim().isEmpty() && !device.trim().isEmpty()) {
            ipAddress = network1 + "." + network2 + "." + subnet + "." + device;
        }
        return ipAddress;
    }

    @Override
    public boolean checkingEquipmentIpAddress(Equipment equipment) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(equipment.getCompany());
        for (Equipment equipment1 : equipmentList){
            if(equipment.getIpAddress().equals(equipment1.getIpAddress())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingForAddressOccupancy(String ipAddress, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment : equipmentList){
            if(ipAddress.equals(equipment.getIpAddress())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingEquipmentIpAddressDante(Equipment equipment) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(equipment.getCompany());
        for (Equipment equipment1 : equipmentList){
            if(equipment.getDanteIpAddress().equals(equipment1.getDanteIpAddress())){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkingForAddressOccupancyDante(String ipAddress, String nameCompany) {
        List<Equipment> equipmentList = equipmentRepository.getListEquipmentForCompany(nameCompany);
        for (Equipment equipment : equipmentList){
            if(ipAddress.equals(equipment.getDanteIpAddress())){
                return true;
            }
        }
        return false;
    }

    @Override
    public String getFrequency(String frequency1, String frequency2) {
        String frequency = null;
        if(frequency1 != null && !frequency1.isEmpty() && frequency2 != null && !frequency2.isEmpty()){
            frequency = frequency1 + "." + frequency2;
        }
        return frequency;
    }

    @Override
    public boolean checkingFrequency(String frequency, String nameCompany) {
            for (Equipment equipment : equipmentRepository.getListEquipmentForCompany(nameCompany)) {
                if (equipment instanceof Microphone microphone
                        && (frequency.equals(((Microphone) equipment).getFrequency()))) {
                    return true;
                }
            }
            return false;
    }

    @Override
    public boolean checkingTheSwitchPort(NetworkSwitch networkSwitch, int numberPort) {
            HashMap<Integer, Equipment> hashMap = new HashMap<>();
            hashMap.put(1, networkSwitch.getPort1());
            hashMap.put(2, networkSwitch.getPort2());
            hashMap.put(3, networkSwitch.getPort3());
            hashMap.put(4, networkSwitch.getPort4());
            hashMap.put(5, networkSwitch.getPort5());
            hashMap.put(6, networkSwitch.getPort6());
            hashMap.put(7, networkSwitch.getPort7());
            hashMap.put(8, networkSwitch.getPort8());
            hashMap.put(9, networkSwitch.getPort9());
            hashMap.put(10, networkSwitch.getPort10());
            hashMap.put(11, networkSwitch.getPort11());
            hashMap.put(12, networkSwitch.getPort12());
            hashMap.put(13, networkSwitch.getPort13());
            hashMap.put(14, networkSwitch.getPort14());
            hashMap.put(15, networkSwitch.getPort15());
            hashMap.put(16, networkSwitch.getPort16());
            hashMap.put(17, networkSwitch.getPort17());
            hashMap.put(18, networkSwitch.getPort18());
            hashMap.put(19, networkSwitch.getPort19());
            hashMap.put(20, networkSwitch.getPort20());
            hashMap.put(21, networkSwitch.getPort21());
            hashMap.put(22, networkSwitch.getPort22());
            hashMap.put(23, networkSwitch.getPort23());
            hashMap.put(24, networkSwitch.getPort24());
            hashMap.put(25, networkSwitch.getPort25());
            hashMap.put(26, networkSwitch.getPort26());
            hashMap.put(27, networkSwitch.getPort27());
            hashMap.put(28, networkSwitch.getPort28());
            hashMap.put(29, networkSwitch.getPort29());
            hashMap.put(30, networkSwitch.getPort30());
            hashMap.put(31, networkSwitch.getPort31());
            hashMap.put(32, networkSwitch.getPort32());
            hashMap.put(33, networkSwitch.getPort33());
            hashMap.put(34, networkSwitch.getPort34());
            hashMap.put(35, networkSwitch.getPort35());
            hashMap.put(36, networkSwitch.getPort36());
            hashMap.put(37, networkSwitch.getPort37());
            hashMap.put(38, networkSwitch.getPort38());
            hashMap.put(39, networkSwitch.getPort39());
            hashMap.put(40, networkSwitch.getPort40());
            hashMap.put(41, networkSwitch.getPort41());
            hashMap.put(42, networkSwitch.getPort42());
            hashMap.put(43, networkSwitch.getPort43());
            hashMap.put(44, networkSwitch.getPort44());
            hashMap.put(45, networkSwitch.getPort45());
            hashMap.put(46, networkSwitch.getPort46());
            hashMap.put(47, networkSwitch.getPort47());
            hashMap.put(48, networkSwitch.getPort48());

            Equipment value = hashMap.get(numberPort);

            if (value == null) {
                return true;
            } else return false;
    }

    @Override
    public boolean checkingForANumber(String value) {
        return value.chars().allMatch(Character::isDigit);
    }
    public boolean checkingStringWithACondition(String value) {
        return value.matches("^\\w+$");
    }
}
