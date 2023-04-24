package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.BookingEquipment;

public interface GetBookingEquipmentService {
    BookingEquipment getBookingEquipment(ResultSet resultSet) throws SQLException;
}
