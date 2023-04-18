package ru.greatlarder.technicalassistant.services.database.general;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public interface GetPortsForDevicesService {
    HashMap<Integer, Integer> getHashMapPorts(ResultSet resultSet) throws SQLException;
}
