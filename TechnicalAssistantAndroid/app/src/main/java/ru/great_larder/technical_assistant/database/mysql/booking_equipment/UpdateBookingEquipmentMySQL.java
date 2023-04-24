package ru.great_larder.technical_assistant.database.mysql.booking_equipment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.UpdateBookingEquipment;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

public class UpdateBookingEquipmentMySQL implements UpdateBookingEquipment {

    @Override
    public void updateBookingEquipment(User user, BookingEquipment bookingEquipment) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createBookingEquipmentTableMySQL();
        BookingEquipmentTableMySQL bookingEquipmentTableMySQL = new BookingEquipmentTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(bookingEquipmentTableMySQL.UPDATE(user.getNameDB()));

            ps.setInt(1, bookingEquipment.getId());
            ps.setInt(2, bookingEquipment.getIdEquipment());
            ps.setInt(3, bookingEquipment.getConditionBooking());
            ps.setDate(4, Date.valueOf(String.valueOf(bookingEquipment.getDate())));

            ps.setInt(5, bookingEquipment.getId());

            ps.executeUpdate();

            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
}
