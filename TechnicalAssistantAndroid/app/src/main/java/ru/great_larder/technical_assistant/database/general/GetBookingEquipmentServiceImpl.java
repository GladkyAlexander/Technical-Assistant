package ru.great_larder.technical_assistant.database.general;

import android.os.Build;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import ru.great_larder.technical_assistant.domain.BookingEquipment;

public class GetBookingEquipmentServiceImpl implements GetBookingEquipmentService{
    @Override
    public BookingEquipment getBookingEquipment(ResultSet resultSet) throws SQLException {

        BookingEquipment bookingEquipment = new BookingEquipment();

        bookingEquipment.setId(resultSet.getInt("id"));
        bookingEquipment.setIdEquipment(resultSet.getInt("equipmentId"));
        bookingEquipment.setConditionBooking(resultSet.getInt("conditionBooking"));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bookingEquipment.setDate(resultSet.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        }

        return bookingEquipment;
    }
}
