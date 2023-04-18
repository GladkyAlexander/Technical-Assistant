package ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.general.GetBookingEquipmentService;
import ru.greatlarder.technicalassistant.services.database.general.GetBookingEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class BookingEquipmentByIdMySQL implements GetBookingEquipment {
    @Override
    public BookingEquipment getBookingEquipment(User user, LocalDate lastDate, String idBookingEquipment) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createBookingEquipmentTableMySQL();
        GetBookingEquipmentService getBookingEquipmentService = new GetBookingEquipmentServiceImpl();
        BookingEquipmentTableMySQL bookingEquipmentTableMySQL = new BookingEquipmentTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(bookingEquipmentTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(Objects.equals(connectMySQL.resultSetMySQL.getInt("id"), Integer.valueOf(idBookingEquipment))){
                    return getBookingEquipmentService.getBookingEquipment(connectMySQL.resultSetMySQL);
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
