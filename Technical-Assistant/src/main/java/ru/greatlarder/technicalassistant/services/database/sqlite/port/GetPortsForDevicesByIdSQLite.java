package ru.greatlarder.technicalassistant.services.database.sqlite.port;

import ru.greatlarder.technicalassistant.domain.PortsForDevices;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.GetPortsForDevices;
import ru.greatlarder.technicalassistant.services.database.general.GetPortsForDevicesService;
import ru.greatlarder.technicalassistant.services.database.general.GetPortsForDevicesServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.equipment.EquipmentByIdSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLitePort;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class GetPortsForDevicesByIdSQLite implements GetPortsForDevices {

    User user ;
    String nameCompany;
    @Override
    public PortsForDevices getPortsForDevices(User user, String nameCompany, Integer idNetworkSwitch) {

        this.user = user;
        this.nameCompany = nameCompany;

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        createPortTable();
        try {
            resultSet = statement.executeQuery(SQLitePort.READ_TABLE_PORT);

            while (resultSet.next()) {
                if (Objects.equals(resultSet.getInt("idNetworkSwitch"), idNetworkSwitch)){
                    GetPortsForDevicesService getPortsForDevicesService = new GetPortsForDevicesServiceImpl();
                    hashMap = getPortsForDevicesService.getHashMapPorts(resultSet);
                }

            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return get(hashMap);
    }

    private PortsForDevices get(HashMap<Integer, Integer> hashMap){
        PortsForDevices port = new PortsForDevices();

        GetEquipment getEquipment = new EquipmentByIdSQLite();

        if(hashMap.get(1) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(1))));}
        if(hashMap.get(2) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(2))));}
        if(hashMap.get(3) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(3))));}
        if(hashMap.get(4) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(4))));}
        if(hashMap.get(5) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(5))));}
        if(hashMap.get(6) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(6))));}
        if(hashMap.get(7) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(7))));}
        if(hashMap.get(8) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(8))));}
        if(hashMap.get(9) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(9))));}
        if(hashMap.get(10) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(10))));}
        if(hashMap.get(11) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(11))));}
        if(hashMap.get(12) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(12))));}
        if(hashMap.get(13) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(13))));}
        if(hashMap.get(14) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(14))));}
        if(hashMap.get(15) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(15))));}
        if(hashMap.get(16) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(16))));}
        if(hashMap.get(17) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(17))));}
        if(hashMap.get(18) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(18))));}
        if(hashMap.get(19) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(19))));}
        if(hashMap.get(20) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(20))));}
        if(hashMap.get(21) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(21))));}
        if(hashMap.get(22) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(22))));}
        if(hashMap.get(23) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(23))));}
        if(hashMap.get(24) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(24))));}
        if(hashMap.get(25) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(25))));}
        if(hashMap.get(26) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(26))));}
        if(hashMap.get(27) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(27))));}
        if(hashMap.get(28) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(28))));}
        if(hashMap.get(29) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(29))));}
        if(hashMap.get(30) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(30))));}
        if(hashMap.get(31) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(31))));}
        if(hashMap.get(32) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(32))));}
        if(hashMap.get(33) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(33))));}
        if(hashMap.get(34) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(34))));}
        if(hashMap.get(35) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(35))));}
        if(hashMap.get(36) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(36))));}
        if(hashMap.get(37) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(37))));}
        if(hashMap.get(38) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(38))));}
        if(hashMap.get(39) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(39))));}
        if(hashMap.get(40) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(40))));}
        if(hashMap.get(41) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(41))));}
        if(hashMap.get(42) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(42))));}
        if(hashMap.get(43) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(43))));}
        if(hashMap.get(44) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(44))));}
        if(hashMap.get(45) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(45))));}
        if(hashMap.get(46) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(46))));}
        if(hashMap.get(47) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(47))));}
        if(hashMap.get(48) != null) {port.setPort1(getEquipment.getEquipment(user, nameCompany, String.valueOf(hashMap.get(48))));}

        if(hashMap.get(49) != null) {port.setId(hashMap.get(49));}
        if(hashMap.get(50) != null) {port.setIdNetworkSwitch(hashMap.get(50));}

        return port;
    }

}
