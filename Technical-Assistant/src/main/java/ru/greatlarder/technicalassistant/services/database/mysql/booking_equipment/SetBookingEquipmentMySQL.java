package ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;

import java.sql.*;

public class SetBookingEquipmentMySQL implements SetBookingEquipment {
    @Override
    public Integer setBookingEquipment(User user, String nameCompany, BookingEquipment bookingEquipment) {

        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createBookingEquipmentTableMySQL();
        BookingEquipmentTableMySQL bookingEquipmentTableMySQL = new BookingEquipmentTableMySQLImpl();

        try {
            PreparedStatement ps = connectMySQL.connectionMySQL.prepareStatement(bookingEquipmentTableMySQL
                    .INSERT(user.getNameDB()), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, bookingEquipment.getIdEquipment());
            ps.setInt(2, bookingEquipment.getConditionBooking());
            ps.setDate(3, Date.valueOf(bookingEquipment.getDate()));

            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    return rs.getInt(1);
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return null;
    }
}
