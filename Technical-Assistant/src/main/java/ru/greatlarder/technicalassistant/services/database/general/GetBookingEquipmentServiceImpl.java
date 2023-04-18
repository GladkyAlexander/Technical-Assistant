package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetBookingEquipmentServiceImpl implements GetBookingEquipmentService{
    @Override
    public BookingEquipment getBookingEquipment(ResultSet resultSet) throws SQLException {

        BookingEquipment bookingEquipment = new BookingEquipment();

        bookingEquipment.setId(resultSet.getInt("id"));
        bookingEquipment.setIdEquipment(resultSet.getInt("equipmentId"));
        bookingEquipment.setConditionBooking(resultSet.getInt("conditionBooking"));
        bookingEquipment.setDate(resultSet.getDate("date").toLocalDate());

        return bookingEquipment;
    }
}
