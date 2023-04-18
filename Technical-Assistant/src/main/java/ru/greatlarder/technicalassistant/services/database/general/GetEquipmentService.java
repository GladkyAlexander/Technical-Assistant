package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Equipment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetEquipmentService {
    Equipment getEquipment(ResultSet resultSet) throws SQLException;
}
