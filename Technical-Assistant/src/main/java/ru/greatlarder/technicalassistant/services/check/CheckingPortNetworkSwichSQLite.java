package ru.greatlarder.technicalassistant.services.check;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;

import java.util.HashMap;

public class CheckingPortNetworkSwichSQLite implements CheckPortNetworkSwich{

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
}
