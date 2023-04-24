package ru.great_larder.technical_assistant.database.mysql.booking_equipment;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

import ru.great_larder.technical_assistant.database.GetBookingEquipment;
import ru.great_larder.technical_assistant.database.general.GetBookingEquipmentService;
import ru.great_larder.technical_assistant.database.general.GetBookingEquipmentServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

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
