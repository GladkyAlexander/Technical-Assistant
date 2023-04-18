package ru.greatlarder.technicalassistant.services.database.sqlite.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentService;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEquipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class ListEquipmentByNameCompanySQLite implements GetListEquipment {
    @Override
    public List<Equipment> getListEquipment(User user, String companyName, String value) {
        List<Equipment> equipmentListToCompany = new ArrayList<>();
        List<NetworkSwitch> networkSwitchList = new ArrayList<>();
        List<Equipment> equipmentList = new ArrayList<>();
        GetEquipmentService getEquipment = new GetEquipmentServiceImpl();
        createEquipmentTable();
        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()) {
                if(resultSet.getString("company").equals(companyName)){
                    equipmentListToCompany.add(getEquipment.getEquipment(resultSet));
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        for (Equipment equipment : equipmentListToCompany) {
            if (equipment instanceof NetworkSwitch) {
                networkSwitchList.add((NetworkSwitch) equipment);
            } else equipmentList.add(equipment);
        }

        for (NetworkSwitch networkSwitch : networkSwitchList) {
            if (networkSwitch != null) {
                equipmentList.add(getLoadSwitch(networkSwitch, equipmentList));
            }
        }

        return equipmentList;
    }

    private NetworkSwitch getLoadSwitch(NetworkSwitch networkSwitch, List<Equipment> equipmentList) {

        HashMap<Integer, Equipment> equipmentHashMap = new HashMap<>();

        for (Equipment equipment : equipmentList){
            if(equipment.getIdNetworkSwitcher() == networkSwitch.getId()
                    && equipment.getPortNumberInTheSwitch() != null ){
                equipmentHashMap.put(equipment.getPortNumberInTheSwitch(), equipment);
            }
        }

        if (equipmentHashMap.get(1) != null) {
            networkSwitch.setPort1(equipmentHashMap.get(1));
        }
        if (equipmentHashMap.get(2) != null) {
            networkSwitch.setPort2(equipmentHashMap.get(2));
        }
        if (equipmentHashMap.get(3) != null) {
            networkSwitch.setPort3(equipmentHashMap.get(3));
        }
        if (equipmentHashMap.get(4) != null) {
            networkSwitch.setPort4(equipmentHashMap.get(4));
        }
        if (equipmentHashMap.get(5) != null) {
            networkSwitch.setPort5(equipmentHashMap.get(5));
        }
        if (equipmentHashMap.get(6) != null) {
            networkSwitch.setPort6(equipmentHashMap.get(6));
        }
        if (equipmentHashMap.get(7) != null) {
            networkSwitch.setPort7(equipmentHashMap.get(7));
        }
        if (equipmentHashMap.get(8) != null) {
            networkSwitch.setPort8(equipmentHashMap.get(8));
        }
        if (equipmentHashMap.get(9) != null) {
            networkSwitch.setPort9(equipmentHashMap.get(9));
        }
        if (equipmentHashMap.get(10) != null) {
            networkSwitch.setPort10(equipmentHashMap.get(10));
        }
        if (equipmentHashMap.get(11) != null) {
            networkSwitch.setPort11(equipmentHashMap.get(11));
        }
        if (equipmentHashMap.get(12) != null) {
            networkSwitch.setPort12(equipmentHashMap.get(12));
        }
        if (equipmentHashMap.get(13) != null) {
            networkSwitch.setPort13(equipmentHashMap.get(13));
        }
        if (equipmentHashMap.get(14) != null) {
            networkSwitch.setPort14(equipmentHashMap.get(14));
        }
        if (equipmentHashMap.get(15) != null) {
            networkSwitch.setPort15(equipmentHashMap.get(15));
        }
        if (equipmentHashMap.get(16) != null) {
            networkSwitch.setPort16(equipmentHashMap.get(16));
        }
        if (equipmentHashMap.get(17) != null) {
            networkSwitch.setPort17(equipmentHashMap.get(17));
        }
        if (equipmentHashMap.get(18) != null) {
            networkSwitch.setPort18(equipmentHashMap.get(18));
        }
        if (equipmentHashMap.get(19) != null) {
            networkSwitch.setPort19(equipmentHashMap.get(19));
        }
        if (equipmentHashMap.get(20) != null) {
            networkSwitch.setPort20(equipmentHashMap.get(20));
        }
        if (equipmentHashMap.get(21) != null) {
            networkSwitch.setPort21(equipmentHashMap.get(21));
        }
        if (equipmentHashMap.get(22) != null) {
            networkSwitch.setPort22(equipmentHashMap.get(22));
        }
        if (equipmentHashMap.get(23) != null) {
            networkSwitch.setPort23(equipmentHashMap.get(23));
        }
        if (equipmentHashMap.get(24) != null) {
            networkSwitch.setPort24(equipmentHashMap.get(24));
        }
        if (equipmentHashMap.get(25) != null) {
            networkSwitch.setPort25(equipmentHashMap.get(25));
        }
        if (equipmentHashMap.get(26) != null) {
            networkSwitch.setPort26(equipmentHashMap.get(26));
        }
        if (equipmentHashMap.get(27) != null) {
            networkSwitch.setPort27(equipmentHashMap.get(27));
        }
        if (equipmentHashMap.get(28) != null) {
            networkSwitch.setPort28(equipmentHashMap.get(28));
        }
        if (equipmentHashMap.get(29) != null) {
            networkSwitch.setPort29(equipmentHashMap.get(29));
        }
        if (equipmentHashMap.get(30) != null) {
            networkSwitch.setPort30(equipmentHashMap.get(30));
        }
        if (equipmentHashMap.get(31) != null) {
            networkSwitch.setPort31(equipmentHashMap.get(31));
        }
        if (equipmentHashMap.get(32) != null) {
            networkSwitch.setPort32(equipmentHashMap.get(32));
        }
        if (equipmentHashMap.get(33) != null) {
            networkSwitch.setPort33(equipmentHashMap.get(33));
        }
        if (equipmentHashMap.get(34) != null) {
            networkSwitch.setPort34(equipmentHashMap.get(34));
        }
        if (equipmentHashMap.get(35) != null) {
            networkSwitch.setPort35(equipmentHashMap.get(35));
        }
        if (equipmentHashMap.get(36) != null) {
            networkSwitch.setPort36(equipmentHashMap.get(36));
        }
        if (equipmentHashMap.get(37) != null) {
            networkSwitch.setPort37(equipmentHashMap.get(37));
        }
        if (equipmentHashMap.get(38) != null) {
            networkSwitch.setPort38(equipmentHashMap.get(38));
        }
        if (equipmentHashMap.get(39) != null) {
            networkSwitch.setPort39(equipmentHashMap.get(39));
        }
        if (equipmentHashMap.get(40) != null) {
            networkSwitch.setPort40(equipmentHashMap.get(40));
        }
        if (equipmentHashMap.get(41) != null) {
            networkSwitch.setPort41(equipmentHashMap.get(41));
        }
        if (equipmentHashMap.get(42) != null) {
            networkSwitch.setPort42(equipmentHashMap.get(42));
        }
        if (equipmentHashMap.get(43) != null) {
            networkSwitch.setPort43(equipmentHashMap.get(43));
        }
        if (equipmentHashMap.get(44) != null) {
            networkSwitch.setPort44(equipmentHashMap.get(44));
        }
        if (equipmentHashMap.get(45) != null) {
            networkSwitch.setPort45(equipmentHashMap.get(45));
        }
        if (equipmentHashMap.get(46) != null) {
            networkSwitch.setPort46(equipmentHashMap.get(46));
        }
        if (equipmentHashMap.get(47) != null) {
            networkSwitch.setPort47(equipmentHashMap.get(47));
        }
        if (equipmentHashMap.get(48) != null) {
            networkSwitch.setPort48(equipmentHashMap.get(48));
        }

        return networkSwitch;
    }

}
