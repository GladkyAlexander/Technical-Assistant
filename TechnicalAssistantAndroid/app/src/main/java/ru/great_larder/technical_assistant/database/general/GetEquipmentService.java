package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Equipment;

public interface GetEquipmentService {
    Equipment getEquipment(ResultSet resultSet) throws SQLException;
}
