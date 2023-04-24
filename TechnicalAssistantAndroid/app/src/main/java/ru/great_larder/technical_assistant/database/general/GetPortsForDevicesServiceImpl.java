package ru.great_larder.technical_assistant.database.general;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GetPortsForDevicesServiceImpl implements GetPortsForDevicesService {
    @Override
    public HashMap<Integer, Integer> getHashMapPorts(ResultSet resultSet) throws SQLException {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(1, resultSet.getInt("port1"));
        hashMap.put(2, resultSet.getInt("port2"));
        hashMap.put(3, resultSet.getInt("port3"));
        hashMap.put(4, resultSet.getInt("port4"));
        hashMap.put(5, resultSet.getInt("port5"));
        hashMap.put(6, resultSet.getInt("port6"));
        hashMap.put(7, resultSet.getInt("port7"));
        hashMap.put(8, resultSet.getInt("port8"));
        hashMap.put(9, resultSet.getInt("port9"));
        hashMap.put(10, resultSet.getInt("port10"));
        hashMap.put(11, resultSet.getInt("port11"));
        hashMap.put(12, resultSet.getInt("port12"));
        hashMap.put(13, resultSet.getInt("port13"));
        hashMap.put(14, resultSet.getInt("port14"));
        hashMap.put(15, resultSet.getInt("port15"));
        hashMap.put(16, resultSet.getInt("port16"));
        hashMap.put(17, resultSet.getInt("port17"));
        hashMap.put(18, resultSet.getInt("port18"));
        hashMap.put(19, resultSet.getInt("port19"));
        hashMap.put(20, resultSet.getInt("port20"));
        hashMap.put(21, resultSet.getInt("port21"));
        hashMap.put(22, resultSet.getInt("port22"));
        hashMap.put(23, resultSet.getInt("port23"));
        hashMap.put(24, resultSet.getInt("port24"));
        hashMap.put(25, resultSet.getInt("port25"));
        hashMap.put(26, resultSet.getInt("port26"));
        hashMap.put(27, resultSet.getInt("port27"));
        hashMap.put(28, resultSet.getInt("port28"));
        hashMap.put(29, resultSet.getInt("port29"));
        hashMap.put(30, resultSet.getInt("port30"));
        hashMap.put(31, resultSet.getInt("port31"));
        hashMap.put(32, resultSet.getInt("port32"));
        hashMap.put(33, resultSet.getInt("port33"));
        hashMap.put(34, resultSet.getInt("port34"));
        hashMap.put(35, resultSet.getInt("port35"));
        hashMap.put(36, resultSet.getInt("port36"));
        hashMap.put(37, resultSet.getInt("port37"));
        hashMap.put(38, resultSet.getInt("port38"));
        hashMap.put(39, resultSet.getInt("port39"));
        hashMap.put(40, resultSet.getInt("port40"));
        hashMap.put(41, resultSet.getInt("port41"));
        hashMap.put(42, resultSet.getInt("port42"));
        hashMap.put(43, resultSet.getInt("port43"));
        hashMap.put(44, resultSet.getInt("port44"));
        hashMap.put(45, resultSet.getInt("port45"));
        hashMap.put(46, resultSet.getInt("port46"));
        hashMap.put(47, resultSet.getInt("port47"));
        hashMap.put(48, resultSet.getInt("port48"));

        hashMap.put(49, resultSet.getInt("id"));
        hashMap.put(50, resultSet.getInt("idNetworkSwitch"));

        return hashMap;
    }
}
