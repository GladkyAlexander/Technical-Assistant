package ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            ps.setDate(4, Date.valueOf(bookingEquipment.getDate()));

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
