package ru.great_larder.technical_assistant.database.mysql.booking_equipment;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.great_larder.technical_assistant.database.SetBookingEquipment;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

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
            ps.setDate(3, Date.valueOf(String.valueOf(bookingEquipment.getDate())));

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
