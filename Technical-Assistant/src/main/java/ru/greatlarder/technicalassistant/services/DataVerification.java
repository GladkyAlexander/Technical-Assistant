package ru.greatlarder.technicalassistant.services;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.equipment.*;

import java.util.HashMap;
import java.util.List;

public class DataVerification {

    private HashMap<String, Boolean> verifyEquipment(Equipment equipment, Equipment equipment_external) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        if (equipment != null && equipment_external != null) {
            if (equipment.getId() != 0 && equipment_external.getId() != 0) {
                hashMap.put("id", String.valueOf(equipment.getId()).equals(String.valueOf(equipment_external.getId())));
            }
            if (equipment.getName() != null && equipment_external.getName() != null) {
                hashMap.put("name", equipment.getName().equals(equipment_external.getName()));
            }
            if (equipment.getModel() != null && equipment_external.getModel() != null) {
                hashMap.put("model", equipment.getModel().equals(equipment_external.getModel()));
            }
            if (equipment.getManufacturer() != null && equipment_external.getManufacturer() != null) {
                hashMap.put("manufacturer", equipment.getManufacturer().equals(equipment_external.getManufacturer()));
            }
            if (equipment.getSerialNumber() != null && equipment_external.getSerialNumber() != null) {
                hashMap.put("serialNumber", equipment.getSerialNumber().equals(equipment_external.getSerialNumber()));
            }
            if (equipment.getMacAddress() != null && equipment_external.getMacAddress() != null) {
                hashMap.put("macAddress", equipment.getMacAddress().equals(equipment_external.getMacAddress()));
            }
            if (equipment.getMacAddress1() != null && equipment_external.getMacAddress1() != null) {
                hashMap.put("macAddress1", equipment.getMacAddress1().equals(equipment_external.getMacAddress1()));
            }
            if (equipment.getMacAddress2() != null && equipment_external.getMacAddress2() != null) {
                hashMap.put("macAddress2", equipment.getMacAddress2().equals(equipment_external.getMacAddress2()));
            }
            if (equipment.getMacAddress3() != null && equipment_external.getMacAddress3() != null) {
                hashMap.put("macAddress3", equipment.getMacAddress3().equals(equipment_external.getMacAddress3()));
            }
            if (equipment.getLogin() != null && equipment_external.getLogin() != null) {
                hashMap.put("login", equipment.getLogin().equals(equipment_external.getLogin()));
            }
            if (equipment.getPassword() != null && equipment_external.getPassword() != null) {
                hashMap.put("password", equipment.getPassword().equals(equipment_external.getPassword()));
            }
            if (equipment.getImage() != null && equipment_external.getImage() != null) {
                hashMap.put("image", equipment.getImage().equals(equipment_external.getImage()));
            }
            if (equipment.getRoom() != null && equipment_external.getRoom() != null) {
                hashMap.put("room", equipment.getRoom().equals(equipment_external.getRoom()));
            }
            if (equipment.getLocation() != null && equipment_external.getLocation() != null) {
                hashMap.put("location", equipment.getLocation().equals(equipment_external.getLocation()));
            }
            if (equipment.getDateWork() != null && equipment_external.getDateWork() != null) {
                hashMap.put("dateWork", equipment.getDateWork().equals(equipment_external.getDateWork()));
            }
            if (equipment.getCondition() != null && equipment_external.getCondition() != null) {
                hashMap.put("condition", equipment.getCondition().equals(equipment_external.getCondition()));
            }
            if (equipment.getCompany() != null && equipment_external.getCompany() != null) {
                hashMap.put("company", equipment.getCompany().equals(equipment_external.getCompany()));
            }
            if (equipment.getManual() != null && equipment_external.getManual() != null) {
                hashMap.put("manual", equipment.getManual().equals(equipment_external.getManual()));
            }
            if (equipment.getIpAddress() != null && equipment_external.getIpAddress() != null) {
                hashMap.put("ipAddress", equipment.getIpAddress().equals(equipment_external.getIpAddress()));
            }
            if (equipment.getMasc() != null && equipment_external.getMasc() != null) {
                hashMap.put("masc", equipment.getMasc().equals(equipment_external.getMasc()));
            }
            if (equipment.getGateway() != null && equipment_external.getGateway() != null) {
                hashMap.put("gateway", equipment.getGateway().equals(equipment_external.getGateway()));
            }
            if (equipment.getDanteIpAddress() != null && equipment_external.getDanteIpAddress() != null) {
                hashMap.put("danteIpAddress", equipment.getDanteIpAddress().equals(equipment_external.getDanteIpAddress()));
            }
            if (equipment.getDanteMasc() != null && equipment_external.getDanteMasc() != null) {
                hashMap.put("danteMasc", equipment.getDanteMasc().equals(equipment_external.getDanteMasc()));
            }
            if (equipment.getDanteGateway() != null && equipment_external.getDanteGateway() != null) {
                hashMap.put("danteGateway", equipment.getDanteGateway().equals(equipment_external.getDanteGateway()));
            }
            if (equipment.getOutletNumber() != null && equipment_external.getOutletNumber() != null) {
                hashMap.put("outletNumber", equipment.getOutletNumber().equals(equipment_external.getOutletNumber()));
            }
            if (equipment.getPortNumberInTheSwitch() != null && equipment_external.getPortNumberInTheSwitch() != null) {
                hashMap.put("portNumberInTheSwitch", equipment.getPortNumberInTheSwitch().equals(equipment_external.getPortNumberInTheSwitch()));
            }
            if (equipment.getIdNetworkSwitcher() != null && equipment_external.getIdNetworkSwitcher() != null) {
                hashMap.put("idNetworkSwitcher", equipment.getIdNetworkSwitcher().equals(equipment_external.getIdNetworkSwitcher()));
            }
            if (equipment instanceof Laptop && equipment_external instanceof Laptop) {
                if (((Laptop) equipment).getOs() != null && ((Laptop) equipment_external).getOs() != null) {
                    hashMap.put("os", ((Laptop) equipment).getOs().equals(((Laptop) equipment_external).getOs()));
                }
            }
            if (equipment instanceof Microphone && equipment_external instanceof Microphone) {
                if (((Microphone) equipment).getFrequency() != null && ((Microphone) equipment_external).getFrequency() != null) {
                    hashMap.put("frequency", ((Microphone) equipment).getFrequency().equals(((Microphone) equipment_external).getFrequency()));
                }
            }
            if (equipment instanceof NetworkSwitch && equipment_external instanceof NetworkSwitch) {

                if (((NetworkSwitch) equipment).getPort1() != null && ((NetworkSwitch) equipment_external).getPort1() != null) {
                    hashMap.put("port1", ((NetworkSwitch) equipment).getPort1().equals(((NetworkSwitch) equipment_external).getPort1()));
                }
                if (((NetworkSwitch) equipment).getPort2() != null && ((NetworkSwitch) equipment_external).getPort2() != null) {
                    hashMap.put("port2", ((NetworkSwitch) equipment).getPort2().equals(((NetworkSwitch) equipment_external).getPort2()));
                }
                if (((NetworkSwitch) equipment).getPort3() != null && ((NetworkSwitch) equipment_external).getPort3() != null) {
                    hashMap.put("port3", ((NetworkSwitch) equipment).getPort3().equals(((NetworkSwitch) equipment_external).getPort3()));
                }
                if (((NetworkSwitch) equipment).getPort4() != null && ((NetworkSwitch) equipment_external).getPort4() != null) {
                    hashMap.put("port4", ((NetworkSwitch) equipment).getPort4().equals(((NetworkSwitch) equipment_external).getPort4()));
                }
                if (((NetworkSwitch) equipment).getPort5() != null && ((NetworkSwitch) equipment_external).getPort5() != null) {
                    hashMap.put("port5", ((NetworkSwitch) equipment).getPort5().equals(((NetworkSwitch) equipment_external).getPort5()));
                }
                if (((NetworkSwitch) equipment).getPort6() != null && ((NetworkSwitch) equipment_external).getPort6() != null) {
                    hashMap.put("port6", ((NetworkSwitch) equipment).getPort6().equals(((NetworkSwitch) equipment_external).getPort6()));
                }
                if (((NetworkSwitch) equipment).getPort7() != null && ((NetworkSwitch) equipment_external).getPort7() != null) {
                    hashMap.put("port7", ((NetworkSwitch) equipment).getPort7().equals(((NetworkSwitch) equipment_external).getPort7()));
                }
                if (((NetworkSwitch) equipment).getPort8() != null && ((NetworkSwitch) equipment_external).getPort8() != null) {
                    hashMap.put("port8", ((NetworkSwitch) equipment).getPort8().equals(((NetworkSwitch) equipment_external).getPort8()));
                }
                if (((NetworkSwitch) equipment).getPort9() != null && ((NetworkSwitch) equipment_external).getPort9() != null) {
                    hashMap.put("port9", ((NetworkSwitch) equipment).getPort9().equals(((NetworkSwitch) equipment_external).getPort9()));
                }
                if (((NetworkSwitch) equipment).getPort10() != null && ((NetworkSwitch) equipment_external).getPort10() != null) {
                    hashMap.put("port10", ((NetworkSwitch) equipment).getPort10().equals(((NetworkSwitch) equipment_external).getPort10()));
                }
                if (((NetworkSwitch) equipment).getPort11() != null && ((NetworkSwitch) equipment_external).getPort11() != null) {
                    hashMap.put("port11", ((NetworkSwitch) equipment).getPort11().equals(((NetworkSwitch) equipment_external).getPort11()));
                }
                if (((NetworkSwitch) equipment).getPort12() != null && ((NetworkSwitch) equipment_external).getPort12() != null) {
                    hashMap.put("port12", ((NetworkSwitch) equipment).getPort12().equals(((NetworkSwitch) equipment_external).getPort12()));
                }
                if (((NetworkSwitch) equipment).getPort13() != null && ((NetworkSwitch) equipment_external).getPort13() != null) {
                    hashMap.put("port13", ((NetworkSwitch) equipment).getPort13().equals(((NetworkSwitch) equipment_external).getPort13()));
                }
                if (((NetworkSwitch) equipment).getPort14() != null && ((NetworkSwitch) equipment_external).getPort14() != null) {
                    hashMap.put("port14", ((NetworkSwitch) equipment).getPort14().equals(((NetworkSwitch) equipment_external).getPort14()));
                }
                if (((NetworkSwitch) equipment).getPort15() != null && ((NetworkSwitch) equipment_external).getPort15() != null) {
                    hashMap.put("port15", ((NetworkSwitch) equipment).getPort15().equals(((NetworkSwitch) equipment_external).getPort15()));
                }
                if (((NetworkSwitch) equipment).getPort16() != null && ((NetworkSwitch) equipment_external).getPort16() != null) {
                    hashMap.put("port16", ((NetworkSwitch) equipment).getPort16().equals(((NetworkSwitch) equipment_external).getPort16()));
                }
                if (((NetworkSwitch) equipment).getPort17() != null && ((NetworkSwitch) equipment_external).getPort17() != null) {
                    hashMap.put("port17", ((NetworkSwitch) equipment).getPort17().equals(((NetworkSwitch) equipment_external).getPort17()));
                }
                if (((NetworkSwitch) equipment).getPort18() != null && ((NetworkSwitch) equipment_external).getPort18() != null) {
                    hashMap.put("port18", ((NetworkSwitch) equipment).getPort18().equals(((NetworkSwitch) equipment_external).getPort18()));
                }
                if (((NetworkSwitch) equipment).getPort19() != null && ((NetworkSwitch) equipment_external).getPort19() != null) {
                    hashMap.put("port19", ((NetworkSwitch) equipment).getPort19().equals(((NetworkSwitch) equipment_external).getPort19()));
                }
                if (((NetworkSwitch) equipment).getPort20() != null && ((NetworkSwitch) equipment_external).getPort20() != null) {
                    hashMap.put("port20", ((NetworkSwitch) equipment).getPort20().equals(((NetworkSwitch) equipment_external).getPort20()));
                }
                if (((NetworkSwitch) equipment).getPort21() != null && ((NetworkSwitch) equipment_external).getPort21() != null) {
                    hashMap.put("port21", ((NetworkSwitch) equipment).getPort21().equals(((NetworkSwitch) equipment_external).getPort21()));
                }
                if (((NetworkSwitch) equipment).getPort22() != null && ((NetworkSwitch) equipment_external).getPort22() != null) {
                    hashMap.put("port22", ((NetworkSwitch) equipment).getPort22().equals(((NetworkSwitch) equipment_external).getPort22()));
                }
                if (((NetworkSwitch) equipment).getPort23() != null && ((NetworkSwitch) equipment_external).getPort23() != null) {
                    hashMap.put("port23", ((NetworkSwitch) equipment).getPort23().equals(((NetworkSwitch) equipment_external).getPort23()));
                }
                if (((NetworkSwitch) equipment).getPort24() != null && ((NetworkSwitch) equipment_external).getPort24() != null) {
                    hashMap.put("port24", ((NetworkSwitch) equipment).getPort24().equals(((NetworkSwitch) equipment_external).getPort24()));
                }
                if (((NetworkSwitch) equipment).getPort25() != null && ((NetworkSwitch) equipment_external).getPort25() != null) {
                    hashMap.put("port25", ((NetworkSwitch) equipment).getPort25().equals(((NetworkSwitch) equipment_external).getPort25()));
                }
                if (((NetworkSwitch) equipment).getPort26() != null && ((NetworkSwitch) equipment_external).getPort26() != null) {
                    hashMap.put("port26", ((NetworkSwitch) equipment).getPort26().equals(((NetworkSwitch) equipment_external).getPort26()));
                }
                if (((NetworkSwitch) equipment).getPort27() != null && ((NetworkSwitch) equipment_external).getPort27() != null) {
                    hashMap.put("port27", ((NetworkSwitch) equipment).getPort27().equals(((NetworkSwitch) equipment_external).getPort27()));
                }
                if (((NetworkSwitch) equipment).getPort28() != null && ((NetworkSwitch) equipment_external).getPort28() != null) {
                    hashMap.put("port28", ((NetworkSwitch) equipment).getPort28().equals(((NetworkSwitch) equipment_external).getPort28()));
                }
                if (((NetworkSwitch) equipment).getPort29() != null && ((NetworkSwitch) equipment_external).getPort29() != null) {
                    hashMap.put("port29", ((NetworkSwitch) equipment).getPort29().equals(((NetworkSwitch) equipment_external).getPort29()));
                }
                if (((NetworkSwitch) equipment).getPort30() != null && ((NetworkSwitch) equipment_external).getPort30() != null) {
                    hashMap.put("port30", ((NetworkSwitch) equipment).getPort30().equals(((NetworkSwitch) equipment_external).getPort30()));
                }
                if (((NetworkSwitch) equipment).getPort31() != null && ((NetworkSwitch) equipment_external).getPort31() != null) {
                    hashMap.put("port31", ((NetworkSwitch) equipment).getPort31().equals(((NetworkSwitch) equipment_external).getPort31()));
                }
                if (((NetworkSwitch) equipment).getPort32() != null && ((NetworkSwitch) equipment_external).getPort32() != null) {
                    hashMap.put("port32", ((NetworkSwitch) equipment).getPort32().equals(((NetworkSwitch) equipment_external).getPort32()));
                }
                if (((NetworkSwitch) equipment).getPort33() != null && ((NetworkSwitch) equipment_external).getPort33() != null) {
                    hashMap.put("port33", ((NetworkSwitch) equipment).getPort33().equals(((NetworkSwitch) equipment_external).getPort33()));
                }
                if (((NetworkSwitch) equipment).getPort34() != null && ((NetworkSwitch) equipment_external).getPort34() != null) {
                    hashMap.put("port34", ((NetworkSwitch) equipment).getPort34().equals(((NetworkSwitch) equipment_external).getPort34()));
                }
                if (((NetworkSwitch) equipment).getPort35() != null && ((NetworkSwitch) equipment_external).getPort35() != null) {
                    hashMap.put("port35", ((NetworkSwitch) equipment).getPort35().equals(((NetworkSwitch) equipment_external).getPort35()));
                }
                if (((NetworkSwitch) equipment).getPort36() != null && ((NetworkSwitch) equipment_external).getPort36() != null) {
                    hashMap.put("port36", ((NetworkSwitch) equipment).getPort36().equals(((NetworkSwitch) equipment_external).getPort36()));
                }
                if (((NetworkSwitch) equipment).getPort37() != null && ((NetworkSwitch) equipment_external).getPort37() != null) {
                    hashMap.put("port37", ((NetworkSwitch) equipment).getPort37().equals(((NetworkSwitch) equipment_external).getPort37()));
                }
                if (((NetworkSwitch) equipment).getPort38() != null && ((NetworkSwitch) equipment_external).getPort38() != null) {
                    hashMap.put("port38", ((NetworkSwitch) equipment).getPort38().equals(((NetworkSwitch) equipment_external).getPort38()));
                }
                if (((NetworkSwitch) equipment).getPort39() != null && ((NetworkSwitch) equipment_external).getPort39() != null) {
                    hashMap.put("port39", ((NetworkSwitch) equipment).getPort39().equals(((NetworkSwitch) equipment_external).getPort39()));
                }
                if (((NetworkSwitch) equipment).getPort40() != null && ((NetworkSwitch) equipment_external).getPort40() != null) {
                    hashMap.put("port40", ((NetworkSwitch) equipment).getPort40().equals(((NetworkSwitch) equipment_external).getPort40()));
                }
                if (((NetworkSwitch) equipment).getPort41() != null && ((NetworkSwitch) equipment_external).getPort41() != null) {
                    hashMap.put("port41", ((NetworkSwitch) equipment).getPort41().equals(((NetworkSwitch) equipment_external).getPort41()));
                }
                if (((NetworkSwitch) equipment).getPort42() != null && ((NetworkSwitch) equipment_external).getPort42() != null) {
                    hashMap.put("port42", ((NetworkSwitch) equipment).getPort42().equals(((NetworkSwitch) equipment_external).getPort42()));
                }
                if (((NetworkSwitch) equipment).getPort43() != null && ((NetworkSwitch) equipment_external).getPort43() != null) {
                    hashMap.put("port43", ((NetworkSwitch) equipment).getPort43().equals(((NetworkSwitch) equipment_external).getPort43()));
                }
                if (((NetworkSwitch) equipment).getPort44() != null && ((NetworkSwitch) equipment_external).getPort44() != null) {
                    hashMap.put("port44", ((NetworkSwitch) equipment).getPort44().equals(((NetworkSwitch) equipment_external).getPort44()));
                }
                if (((NetworkSwitch) equipment).getPort45() != null && ((NetworkSwitch) equipment_external).getPort45() != null) {
                    hashMap.put("port45", ((NetworkSwitch) equipment).getPort45().equals(((NetworkSwitch) equipment_external).getPort45()));
                }
                if (((NetworkSwitch) equipment).getPort46() != null && ((NetworkSwitch) equipment_external).getPort46() != null) {
                    hashMap.put("port46", ((NetworkSwitch) equipment).getPort46().equals(((NetworkSwitch) equipment_external).getPort46()));
                }
                if (((NetworkSwitch) equipment).getPort47() != null && ((NetworkSwitch) equipment_external).getPort47() != null) {
                    hashMap.put("port47", ((NetworkSwitch) equipment).getPort47().equals(((NetworkSwitch) equipment_external).getPort47()));
                }
                if (((NetworkSwitch) equipment).getPort48() != null && ((NetworkSwitch) equipment_external).getPort48() != null) {
                    hashMap.put("port48", ((NetworkSwitch) equipment).getPort48().equals(((NetworkSwitch) equipment_external).getPort48()));
                }
            }
            if (equipment instanceof Projector && equipment_external instanceof Projector) {
                if (((Projector) equipment).getTimeWorkLampProjector() != null && ((Projector) equipment_external).getTimeWorkLampProjector() != null) {
                    hashMap.put("timeWorkLampProjector", ((Projector) equipment).getTimeWorkLampProjector().equals(((Projector) equipment_external).getTimeWorkLampProjector()));
                }
                if (((Projector) equipment).getMaximumLampOperatingTimeProjector() != null && ((Projector) equipment_external).getMaximumLampOperatingTimeProjector() != null) {
                    hashMap.put("maximumLampOperatingTimeProjector", ((Projector) equipment).getMaximumLampOperatingTimeProjector().equals(((Projector) equipment_external).getMaximumLampOperatingTimeProjector()));
                }
            }
            if (equipment instanceof TouchControlPanel && equipment_external instanceof TouchControlPanel) {
                if (((TouchControlPanel) equipment).getDiagonal() != null && ((TouchControlPanel) equipment_external).getDiagonal() != null) {
                    hashMap.put("diagonal", ((TouchControlPanel) equipment).getDiagonal().equals(((TouchControlPanel) equipment_external).getDiagonal()));
                }
            }
            if (equipment instanceof TvPanel && equipment_external instanceof TvPanel) {
                if (((TvPanel) equipment).getDiagonal() != null && ((TvPanel) equipment_external).getDiagonal() != null) {
                    hashMap.put("diagonal", ((TvPanel) equipment).getDiagonal().equals(((TvPanel) equipment_external).getDiagonal()));
                }
            }
            if (equipment instanceof TouchControlPanel && equipment_external instanceof TouchControlPanel) {
                if (((TouchControlPanel) equipment).getDiagonal() != null && ((TouchControlPanel) equipment_external).getDiagonal() != null) {
                    hashMap.put("diagonal", ((TouchControlPanel) equipment).getDiagonal().equals(((TouchControlPanel) equipment_external).getDiagonal()));
                }
            }
        }
        return hashMap;
    }

    public HashMap<String, Boolean> verifyCompany(Company company, Company company_external) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        if (company != null && company_external != null) {
            if (company.getId() != 0 && company_external.getId() != 0) {
                hashMap.put("id", String.valueOf(company.getId()).equals(String.valueOf(company_external.getId())));
            }
            if (company.getNameCompany() != null && company_external.getNameCompany() != null) {
                hashMap.put("nameCompany", company.getNameCompany().equals(company_external.getNameCompany()));
            }
            if (company.getAddress() != null && company_external.getAddress() != null) {
                hashMap.put("address", company.getAddress().equals(company_external.getAddress()));
            }
            if (company.getCuratorLastName() != null && company_external.getCuratorLastName() != null) {
                hashMap.put("curatorLastName", company.getCuratorLastName().equals(company_external.getCuratorLastName()));
            }
            if (company.getCuratorFirstName() != null && company_external.getCuratorFirstName() != null) {
                hashMap.put("curatorFirstName", company.getCuratorFirstName().equals(company_external.getCuratorFirstName()));
            }
            if (company.getPhoneCurator() != null && company_external.getPhoneCurator() != null) {
                hashMap.put("phoneCurator", company.getPhoneCurator().equals(company_external.getPhoneCurator()));
            }
            if (company.getMailCurator() != null && company_external.getMailCurator() != null) {
                hashMap.put("mailCurator", company.getMailCurator().equals(company_external.getMailCurator()));
            }
            if (company.getWebsiteCompany() != null && company_external.getWebsiteCompany() != null) {
                hashMap.put("websiteCompany", company.getWebsiteCompany().equals(company_external.getWebsiteCompany()));
            }
            if (company.getLogoCompany() != null && company_external.getLogoCompany() != null) {
                hashMap.put("logoCompany", company.getLogoCompany().equals(company_external.getLogoCompany()));
            }
            if (company.getManagerLastName() != null && company_external.getManagerLastName() != null) {
                hashMap.put("managerLastName", company.getManagerLastName().equals(company_external.getManagerLastName()));
            }
            if (company.getManagerFirstName() != null && company_external.getManagerFirstName() != null) {
                hashMap.put("managerFirstName", company.getManagerFirstName().equals(company_external.getManagerFirstName()));
            }
            if (company.getPhoneManager() != null && company_external.getPhoneManager() != null) {
                hashMap.put("phoneManager", company.getPhoneManager().equals(company_external.getPhoneManager()));
            }
            if (company.getMailManager() != null && company_external.getMailManager() != null) {
                hashMap.put("mailManager", company.getMailManager().equals(company_external.getMailManager()));
            }
            if (company.getEngineerLastName() != null && company_external.getEngineerLastName() != null) {
                hashMap.put("engineerLastName", company.getEngineerLastName().equals(company_external.getEngineerLastName()));
            }
            if (company.getEngineerFirstName() != null && company_external.getEngineerFirstName() != null) {
                hashMap.put("engineerFirstName", company.getEngineerFirstName().equals(company_external.getEngineerFirstName()));
            }
            if (company.getPhoneEngineer() != null && company_external.getPhoneEngineer() != null) {
                hashMap.put("phoneEngineer", company.getPhoneEngineer().equals(company_external.getPhoneEngineer()));
            }
            if (company.getMailEngineer() != null && company_external.getMailEngineer() != null) {
                hashMap.put("mailEngineer", company.getMailEngineer().equals(company_external.getMailEngineer()));
            }
        }
        return hashMap;
    }


    public HashMap<Equipment, Equipment> getEquipmentList(List<Equipment> equipment, List<Equipment> equipment_external) {
        HashMap<Equipment, Equipment> returnList = new HashMap<>();

        for (Equipment equipmentLocal : equipment ){
            Equipment equipmentL = null;
            Equipment equipmentE = null;
            for (Equipment equipmentExternal : equipment_external){
                if(equipmentLocal.getId() == equipmentExternal.getId()){
                    equipmentL = equipmentLocal;
                    equipmentE = equipmentExternal;
                }
            }
            if(verifyEquipment(equipmentL, equipmentE).containsValue(Boolean.FALSE)){
                returnList.put(equipmentL, equipmentE);
            }
        }

        return returnList;
    }

    public HashMap<Room, Room> getRoomList(List<Room> roomList, List<Room> externalRoomList){
        HashMap<Room, Room> returnList = new HashMap<>();

        for (Room room : roomList){
            Room equipmentL = null;
            Room equipmentE = null;
            for (Room equipmentExternal : externalRoomList){
                if(room.getId() == equipmentExternal.getId()){
                    equipmentL = room;
                    equipmentE = equipmentExternal;
                }
            }
            if(verifyRoom(equipmentL, equipmentE).containsValue(Boolean.FALSE)){
                returnList.put(equipmentL, equipmentE);
            }
        }

        return returnList;
    }
    private HashMap<String, Boolean> verifyRoom(Room room, Room room_external) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        if (room != null && room_external != null) {
            if (room.getId() != 0 && room_external.getId() != 0) {
                hashMap.put("id", String.valueOf(room.getId()).equals(String.valueOf(room_external.getId())));
            }
            if (room.getNameRoom() != null && room_external.getNameRoom() != null) {
                hashMap.put("name", room.getNameRoom().equals(room_external.getNameRoom()));
            }
        }
        return hashMap;
    }
}
