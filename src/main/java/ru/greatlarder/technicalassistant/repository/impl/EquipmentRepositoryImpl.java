package ru.greatlarder.technicalassistant.repository.impl;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.Microphone;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;
import ru.greatlarder.technicalassistant.domain.equipment.Projector;
import ru.greatlarder.technicalassistant.repository.DefectRepository;
import ru.greatlarder.technicalassistant.repository.EquipmentRepository;
import ru.greatlarder.technicalassistant.services.db.sqlite.SQLiteEquipment;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.db.DBconnect.*;

public class EquipmentRepositoryImpl implements EquipmentRepository {
    DefectRepository defectRepository = new DefectRepositoryImpl();
    Language language = new LanguageImpl();
    private Projector getProjector(ResultSet resultSet) throws SQLException {
        Projector projector = new Projector();
        projector.setId(resultSet.getInt("id"));
        projector.setName(resultSet.getString("name"));
        projector.setModel(resultSet.getString("model"));
        projector.setManufacturer(resultSet.getString("manufacturer"));
        projector.setSerialNumber(resultSet.getString("serialNumber"));
        projector.setIpAddress(resultSet.getString("ipAddress"));
        projector.setMasc(resultSet.getString("masc"));
        projector.setGateway(resultSet.getString("gateway"));
        projector.setMacAddress(resultSet.getString("macAddress"));
        projector.setLogin(resultSet.getString("login"));
        projector.setPassword(resultSet.getString("password"));
        projector.setImage(resultSet.getString("image"));
        projector.setRoom(resultSet.getString("room"));
        projector.setLocation(resultSet.getString("location"));
        projector.setCondition(resultSet.getString("condition"));
        projector.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        projector.setCompany(resultSet.getString("company"));
        projector.setManual(resultSet.getString("manual"));
        projector.setOutletNumber(resultSet.getString("outletNumber"));
        projector.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        projector.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        projector.setTimeWorkLampProjector(resultSet.getInt("timeWorkLampProjector"));
        projector.setMaximumLampOperatingTimeProjector(resultSet.getInt("maximumLampOperatingTimeProjector"));

        return projector;
    }

    private Microphone getMicrophone(ResultSet resultSet) throws SQLException {
        Microphone microphone = new Microphone();
        microphone.setId(resultSet.getInt("id"));
        microphone.setName(resultSet.getString("name"));
        microphone.setModel(resultSet.getString("model"));
        microphone.setManufacturer(resultSet.getString("manufacturer"));
        microphone.setSerialNumber(resultSet.getString("serialNumber"));
        microphone.setMacAddress(resultSet.getString("macAddress"));
        microphone.setLogin(resultSet.getString("login"));
        microphone.setPassword(resultSet.getString("password"));
        microphone.setImage(resultSet.getString("image"));
        microphone.setRoom(resultSet.getString("room"));
        microphone.setLocation(resultSet.getString("location"));
        microphone.setCondition(resultSet.getString("condition"));
        microphone.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        microphone.setCompany(resultSet.getString("company"));
        microphone.setManual(resultSet.getString("manual"));
        microphone.setOutletNumber(resultSet.getString("outletNumber"));
        microphone.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        microphone.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));
        microphone.setFrequency(resultSet.getString("frequencyMicrophone"));

        return microphone;
    }
    private Equipment getNetworkSwitch(ResultSet resultSet) throws SQLException {
        NetworkSwitch networkSwitch = new NetworkSwitch();

        networkSwitch.setId(resultSet.getInt("id"));
        networkSwitch.setName(resultSet.getString("name"));
        networkSwitch.setModel(resultSet.getString("model"));
        networkSwitch.setManufacturer(resultSet.getString("manufacturer"));
        networkSwitch.setSerialNumber(resultSet.getString("serialNumber"));
        networkSwitch.setIpAddress(resultSet.getString("ipAddress"));
        networkSwitch.setMasc(resultSet.getString("masc"));
        networkSwitch.setGateway(resultSet.getString("gateway"));
        networkSwitch.setMacAddress(resultSet.getString("macAddress"));
        networkSwitch.setLogin(resultSet.getString("login"));
        networkSwitch.setPassword(resultSet.getString("password"));
        networkSwitch.setImage(resultSet.getString("image"));
        networkSwitch.setRoom(resultSet.getString("room"));
        networkSwitch.setLocation(resultSet.getString("location"));
        networkSwitch.setCondition(resultSet.getString("condition"));
        networkSwitch.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        networkSwitch.setCompany(resultSet.getString("company"));
        networkSwitch.setManual(resultSet.getString("manual"));
        networkSwitch.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        networkSwitch.setDanteMasc(resultSet.getString("mascDante"));
        networkSwitch.setDanteGateway(resultSet.getString("gatewayDante"));
        networkSwitch.setOutletNumber(resultSet.getString("outletNumber"));

        return networkSwitch;
    }

    private Equipment getEquipment(ResultSet resultSet) throws SQLException {
        Equipment equipment = new Equipment();

        if (resultSet.getString("name").equals(language.PROJECTOR("Русский"))
        || resultSet.getString("name").equals(language.PROJECTOR("English"))) {
            equipment = getProjector(resultSet);
        }
        if (resultSet.getString("name").equals(language.MICROPHONE("Русский"))
        || resultSet.getString("name").equals(language.MICROPHONE("English"))) {
            equipment = getMicrophone(resultSet);
        }
        if (resultSet.getString("name").equals(language.NETWORK_SWITCH("Русский"))
        || resultSet.getString("name").equals(language.NETWORK_SWITCH("English"))) {
            equipment = getNetworkSwitch(resultSet);
        }
        equipment.setId(resultSet.getInt("id"));
        equipment.setName(resultSet.getString("name"));
        equipment.setModel(resultSet.getString("model"));
        equipment.setManufacturer(resultSet.getString("manufacturer"));
        equipment.setSerialNumber(resultSet.getString("serialNumber"));
        equipment.setIpAddress(resultSet.getString("ipAddress"));
        equipment.setMasc(resultSet.getString("masc"));
        equipment.setGateway(resultSet.getString("gateway"));
        equipment.setMacAddress(resultSet.getString("macAddress"));
        equipment.setMacAddress1(resultSet.getString("macAddress1"));
        equipment.setMacAddress2(resultSet.getString("macAddress2"));
        equipment.setMacAddress3(resultSet.getString("macAddress3"));
        equipment.setLogin(resultSet.getString("login"));
        equipment.setPassword(resultSet.getString("password"));
        equipment.setImage(resultSet.getString("image"));
        equipment.setRoom(resultSet.getString("room"));
        equipment.setLocation(resultSet.getString("location"));
        equipment.setCondition(resultSet.getString("condition"));
        equipment.setDateWork(resultSet.getDate("dateWork").toLocalDate());
        equipment.setCompany(resultSet.getString("company"));
        equipment.setManual(resultSet.getString("manual"));
        equipment.setDanteIpAddress(resultSet.getString("ipAddressDante"));
        equipment.setDanteMasc(resultSet.getString("mascDante"));
        equipment.setDanteGateway(resultSet.getString("gatewayDante"));
        equipment.setOutletNumber(resultSet.getString("outletNumber"));
        equipment.setPortNumberInTheSwitch(resultSet.getInt("portNumberInTheSwitch"));
        equipment.setIdNetworkSwitcher(resultSet.getInt("idNetworkSwitcher"));

        return equipment;
    }

    public List<String> getListIpAddressForCompany(String nameCompany) {
        List<String> list = new ArrayList<>();
        createEquipmentTable();

        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);

            while (resultSet.next()) {
                if (resultSet.getString("ipAddress") != null) {
                    list.add(resultSet.getString("ipAddress"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return list;
    }

    @Override
    public List<String> getListIpAddressDanteForCompany(String nameCompany) {

        List<String> list = new ArrayList<>();
        createEquipmentTable();

        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()) {
                if (resultSet.getString("ipAddressDante") != null) {
                    list.add(resultSet.getString("ipAddressDante"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return list;
    }

    @Override
    public List<String> getListNameEquipmentForCompany(String nameCompany) {

        List<String> list = new ArrayList<>();

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            for (String s : list) {
                if (!equipment.getName().equals(s)) {
                    list.add(equipment.getName());
                }
            }
        }
        return list;
    }

    @Override
    public List<Equipment> getListEquipmentForCompany(String nameCompany) {
        List<Equipment> equipmentListToCompany = new ArrayList<>();
        List<NetworkSwitch> networkSwitchList = new ArrayList<>();
        List<Equipment> equipmentList = new ArrayList<>();

        createEquipmentTable();

        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()) {
                equipmentListToCompany.add(getEquipment(resultSet));
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

        List<Equipment> returnEquipmentList = new ArrayList<>();
        for (Equipment equipment : equipmentList){
            equipment.setDefectList(defectRepository.getListAllDefectToEquipment(equipment.getSerialNumber()));
            returnEquipmentList.add(equipment);
        }
        return returnEquipmentList;
    }

    @Override
    public Equipment getEquipmentBySerialNumber(String nameCompany, String serialNumber) {
        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (serialNumber.equals(equipment.getSerialNumber())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public Equipment getEquipmentByIpAddress(String nameCompany, String ipAddress) {
        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (ipAddress.equals(equipment.getIpAddress())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public Equipment getEquipmentByIpAddressDante(String nameCompany, String ipAddress) {
        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (ipAddress.equals(equipment.getDanteIpAddress())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public Equipment getEquipmentByMacAddress(String nameCompany, String macAddress) {

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (macAddress.equals(equipment.getMacAddress())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public Equipment getEquipmentByMacAddress1(String nameCompany, String macAddress1) {

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (macAddress1.equals(equipment.getMacAddress1())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public Equipment getEquipmentByMacAddress2(String nameCompany, String macAddress2) {

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (macAddress2.equals(equipment.getMacAddress2())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public Equipment getEquipmentByMacAddress3(String nameCompany, String macAddress3) {

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (macAddress3.equals(equipment.getMacAddress3())) {
                return equipment;
            }
        }
        return null;
    }

    @Override
    public List<Equipment> getListEquipmentByName(String name, String nameCompany) {

        List<Equipment> equipmentList = new ArrayList<>();
        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (name.equals(equipment.getName())) {
                equipmentList.add(equipment);
            }
        }
        System.out.println(equipmentList.size() + "repository");
        return equipmentList;
    }

    @Override
    public Equipment setEquipment(Equipment equipment) {
        createEquipmentTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteEquipment.INSERT_TABLE_EQUIPMENT);
            cf.setString(1, equipment.getName());
            cf.setString(2, equipment.getManufacturer());
            cf.setString(3, equipment.getModel());
            cf.setString(4, equipment.getSerialNumber());
            cf.setString(5, equipment.getMacAddress());
            cf.setString(6, equipment.getMacAddress1());
            cf.setString(7, equipment.getMacAddress2());
            cf.setString(8, equipment.getMacAddress3());
            cf.setString(9, equipment.getImage());
            cf.setString(10, equipment.getRoom());
            cf.setString(11, equipment.getLocation());
            cf.setDate(12, Date.valueOf(equipment.getDateWork()));
            cf.setString(13, equipment.getCondition());
            cf.setString(14, equipment.getCompany());
            cf.setString(15, equipment.getManual());
            cf.setString(16, equipment.getLogin());
            cf.setString(17, equipment.getPassword());
            cf.setString(18, equipment.getIpAddress());
            cf.setString(19, equipment.getMasc());
            cf.setString(20, equipment.getGateway());

            if (equipment instanceof Projector) {
                cf.setInt(21, ((Projector) equipment).getTimeWorkLampProjector());
                if (((Projector) equipment).getMaximumLampOperatingTimeProjector() != null) {
                    cf.setInt(22, ((Projector) equipment).getMaximumLampOperatingTimeProjector());
                }
            }
            if (equipment instanceof Microphone) {
                cf.setString(23, ((Microphone) equipment).getFrequency());
            }
            cf.setString(24, equipment.getDanteIpAddress());
            cf.setString(25, equipment.getDanteMasc());
            cf.setString(26, equipment.getDanteGateway());
            cf.setString(27, equipment.getOutletNumber());
            if (equipment.getPortNumberInTheSwitch() != null) {
                cf.setInt(28, equipment.getPortNumberInTheSwitch());
            }
            if (equipment.getIdNetworkSwitcher() != null) {
                cf.setInt(29, equipment.getIdNetworkSwitcher());
            }
            if (equipment instanceof NetworkSwitch) {
                if (((NetworkSwitch) equipment).getPort1() != null) {
                    cf.setInt(30, ((NetworkSwitch) equipment).getPort1().getId());
                }
                if (((NetworkSwitch) equipment).getPort2() != null) {
                    cf.setInt(31, ((NetworkSwitch) equipment).getPort2().getId());
                }
                if (((NetworkSwitch) equipment).getPort3() != null) {
                    cf.setInt(32, ((NetworkSwitch) equipment).getPort3().getId());
                }
                if (((NetworkSwitch) equipment).getPort4() != null) {
                    cf.setInt(33, ((NetworkSwitch) equipment).getPort4().getId());
                }
                if (((NetworkSwitch) equipment).getPort5() != null) {
                    cf.setInt(34, ((NetworkSwitch) equipment).getPort5().getId());
                }
                if (((NetworkSwitch) equipment).getPort6() != null) {
                    cf.setInt(35, ((NetworkSwitch) equipment).getPort6().getId());
                }
                if (((NetworkSwitch) equipment).getPort7() != null) {
                    cf.setInt(36, ((NetworkSwitch) equipment).getPort7().getId());
                }
                if (((NetworkSwitch) equipment).getPort8() != null) {
                    cf.setInt(37, ((NetworkSwitch) equipment).getPort8().getId());
                }
                if (((NetworkSwitch) equipment).getPort9() != null) {
                    cf.setInt(38, ((NetworkSwitch) equipment).getPort9().getId());
                }
                if (((NetworkSwitch) equipment).getPort10() != null) {
                    cf.setInt(39, ((NetworkSwitch) equipment).getPort10().getId());
                }
                if (((NetworkSwitch) equipment).getPort11() != null) {
                    cf.setInt(40, ((NetworkSwitch) equipment).getPort11().getId());
                }
                if (((NetworkSwitch) equipment).getPort12() != null) {
                    cf.setInt(41, ((NetworkSwitch) equipment).getPort12().getId());
                }
                if (((NetworkSwitch) equipment).getPort13() != null) {
                    cf.setInt(42, ((NetworkSwitch) equipment).getPort13().getId());
                }
                if (((NetworkSwitch) equipment).getPort14() != null) {
                    cf.setInt(43, ((NetworkSwitch) equipment).getPort14().getId());
                }
                if (((NetworkSwitch) equipment).getPort15() != null) {
                    cf.setInt(44, ((NetworkSwitch) equipment).getPort15().getId());
                }
                if (((NetworkSwitch) equipment).getPort16() != null) {
                    cf.setInt(45, ((NetworkSwitch) equipment).getPort16().getId());
                }
                if (((NetworkSwitch) equipment).getPort17() != null) {
                    cf.setInt(46, ((NetworkSwitch) equipment).getPort17().getId());
                }
                if (((NetworkSwitch) equipment).getPort18() != null) {
                    cf.setInt(47, ((NetworkSwitch) equipment).getPort18().getId());
                }
                if (((NetworkSwitch) equipment).getPort19() != null) {
                    cf.setInt(48, ((NetworkSwitch) equipment).getPort19().getId());
                }
                if (((NetworkSwitch) equipment).getPort20() != null) {
                    cf.setInt(49, ((NetworkSwitch) equipment).getPort20().getId());
                }
                if (((NetworkSwitch) equipment).getPort21() != null) {
                    cf.setInt(50, ((NetworkSwitch) equipment).getPort21().getId());
                }
                if (((NetworkSwitch) equipment).getPort22() != null) {
                    cf.setInt(51, ((NetworkSwitch) equipment).getPort22().getId());
                }
                if (((NetworkSwitch) equipment).getPort23() != null) {
                    cf.setInt(52, ((NetworkSwitch) equipment).getPort23().getId());
                }
                if (((NetworkSwitch) equipment).getPort24() != null) {
                    cf.setInt(53, ((NetworkSwitch) equipment).getPort24().getId());
                }
                if (((NetworkSwitch) equipment).getPort25() != null) {
                    cf.setInt(54, ((NetworkSwitch) equipment).getPort25().getId());
                }
                if (((NetworkSwitch) equipment).getPort26() != null) {
                    cf.setInt(55, ((NetworkSwitch) equipment).getPort26().getId());
                }
                if (((NetworkSwitch) equipment).getPort27() != null) {
                    cf.setInt(56, ((NetworkSwitch) equipment).getPort27().getId());
                }
                if (((NetworkSwitch) equipment).getPort28() != null) {
                    cf.setInt(57, ((NetworkSwitch) equipment).getPort28().getId());
                }
                if (((NetworkSwitch) equipment).getPort29() != null) {
                    cf.setInt(58, ((NetworkSwitch) equipment).getPort29().getId());
                }
                if (((NetworkSwitch) equipment).getPort30() != null) {
                    cf.setInt(59, ((NetworkSwitch) equipment).getPort30().getId());
                }
                if (((NetworkSwitch) equipment).getPort31() != null) {
                    cf.setInt(60, ((NetworkSwitch) equipment).getPort31().getId());
                }
                if (((NetworkSwitch) equipment).getPort32() != null) {
                    cf.setInt(61, ((NetworkSwitch) equipment).getPort32().getId());
                }
                if (((NetworkSwitch) equipment).getPort33() != null) {
                    cf.setInt(62, ((NetworkSwitch) equipment).getPort33().getId());
                }
                if (((NetworkSwitch) equipment).getPort34() != null) {
                    cf.setInt(63, ((NetworkSwitch) equipment).getPort34().getId());
                }
                if (((NetworkSwitch) equipment).getPort35() != null) {
                    cf.setInt(64, ((NetworkSwitch) equipment).getPort35().getId());
                }
                if (((NetworkSwitch) equipment).getPort36() != null) {
                    cf.setInt(65, ((NetworkSwitch) equipment).getPort36().getId());
                }
                if (((NetworkSwitch) equipment).getPort37() != null) {
                    cf.setInt(66, ((NetworkSwitch) equipment).getPort37().getId());
                }
                if (((NetworkSwitch) equipment).getPort38() != null) {
                    cf.setInt(67, ((NetworkSwitch) equipment).getPort38().getId());
                }
                if (((NetworkSwitch) equipment).getPort39() != null) {
                    cf.setInt(68, ((NetworkSwitch) equipment).getPort39().getId());
                }
                if (((NetworkSwitch) equipment).getPort40() != null) {
                    cf.setInt(69, ((NetworkSwitch) equipment).getPort40().getId());
                }
                if (((NetworkSwitch) equipment).getPort41() != null) {
                    cf.setInt(70, ((NetworkSwitch) equipment).getPort41().getId());
                }
                if (((NetworkSwitch) equipment).getPort42() != null) {
                    cf.setInt(71, ((NetworkSwitch) equipment).getPort42().getId());
                }
                if (((NetworkSwitch) equipment).getPort43() != null) {
                    cf.setInt(72, ((NetworkSwitch) equipment).getPort43().getId());
                }
                if (((NetworkSwitch) equipment).getPort44() != null) {
                    cf.setInt(73, ((NetworkSwitch) equipment).getPort44().getId());
                }
                if (((NetworkSwitch) equipment).getPort45() != null) {
                    cf.setInt(74, ((NetworkSwitch) equipment).getPort45().getId());
                }
                if (((NetworkSwitch) equipment).getPort46() != null) {
                    cf.setInt(75, ((NetworkSwitch) equipment).getPort46().getId());
                }
                if (((NetworkSwitch) equipment).getPort47() != null) {
                    cf.setInt(76, ((NetworkSwitch) equipment).getPort47().getId());
                }
                if (((NetworkSwitch) equipment).getPort48() != null) {
                    cf.setInt(77, ((NetworkSwitch) equipment).getPort48().getId());
                }

            }

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return null;
    }

    @Override
    public List<String> getListRoomForCompany(String nameCompany) {

        List<String> listRoom = new ArrayList<>();

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (equipment.getRoom() != null && listRoom.stream().noneMatch(s -> s.equals(equipment.getRoom()))) { // изменено
                listRoom.add(equipment.getRoom());
            }
        }

        return listRoom;
    }

    @Override
    public List<Equipment> getListEquipmentForRoom(String nameCompany, String nameRoom) {

        List<Equipment> equipmentList = new ArrayList<>(getListEquipmentForCompany(nameCompany));
        List<Equipment> retList = new ArrayList<>();
        for (Equipment equipment : equipmentList) {
            if (nameRoom.equals(equipment.getRoom())) {
                retList.add(equipment);
            }
        }

        return retList;
    }

    @Override
    public Equipment change(int idEquipment, String column, Object value) {
        createEquipmentTable();
        String sql = "UPDATE equipment SET " + column + " = ? WHERE id = ?";

        try {
            PreparedStatement cf = connection.prepareStatement(sql);

            if (value instanceof String) {
                cf.setString(1, (String) value);
            }
            if (value instanceof Integer) {
                cf.setInt(1, (Integer) value);
            }
            cf.setInt(2, idEquipment);

            cf.executeUpdate();

            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return null;
    }

    public List<Equipment> getListEquipmentWithIpAddress(String nameCompany) {
        List<Equipment> equipmentList = new ArrayList<>();

        List<Equipment> allEquipmentList = new ArrayList<>(getListEquipmentForCompany(nameCompany));

        for (Equipment equipment : allEquipmentList) {
            if (hashMethod("getIpAddress", equipment)) {
                equipmentList.add(equipment);
            }
        }

        return equipmentList;
    }

    public List<Equipment> getListEquipmentWithIpAddressDante(String nameCompany) {
        List<Equipment> equipmentList = new ArrayList<>();

        List<Equipment> allEquipmentList = new ArrayList<>(getListEquipmentForCompany(nameCompany));

        for (Equipment equipment : allEquipmentList) {
            if (hashMethod("danteIpAddress", equipment)) {
                equipmentList.add(equipment);
            }
        }

        return equipmentList;
    }

    @Override
    public List<Equipment> getListEquipmentForCompanyCondition(String nameCompany, String condition) {

        List<Equipment> list = new ArrayList<>();

        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (equipment.getCondition().equals(condition)) {
                list.add(equipment);
            }
        }

        return list;
    }

    @Override
    public Equipment getEquipmentById(int id, String nameCompany) {
        for (Equipment equipment : getListEquipmentForCompany(nameCompany)) {
            if (id == equipment.getId()) {
                return equipment;
            }
        }
        return null;
    }

    private boolean hashMethod(String s, Equipment equipment) {

        Method[] method = equipment.getClass().getMethods();

        for (Method m : method) {
            if (m.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> getListEquipmentName(String lang) {
        List<String> list = new ArrayList<>();
        list.add(language.PROJECTOR(lang));
        list.add(language.MICROPHONE(lang));
        list.add(language.CONTROL_PROCESSOR(lang));
        list.add(language.AUDIO_PROCESSOR(lang));
        list.add(language.AUDIO_AMPLIFIER(lang));
        list.add(language.ACOUSTIC_SPEAKER(lang));
        list.add(language.AUDIO_INTERFACE(lang));
        list.add(language.TV_PANEL(lang));
        list.add(language.TV_TUNER(lang));
        list.add(language.MEDIA_PLAYER(lang));
        list.add(language.LAPTOP(lang));
        list.add(language.VIDEO_TRANSMITTER(lang));
        list.add(language.VIDEO_RECEIVER(lang));
        list.add(language.MATRIX_SWITCHER(lang));
        list.add(language.NETWORK_SWITCH(lang));
        list.add(language.TOUCH_CONTROL_PANEL(lang));

        return list;
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
