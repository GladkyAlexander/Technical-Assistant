package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetBookingEquipmentService {
    BookingEquipment getBookingEquipment(ResultSet resultSet) throws SQLException;
}
