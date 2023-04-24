package ru.great_larder.technical_assistant.database.mysql.booking_equipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import ru.great_larder.technical_assistant.database.GetListBookingEquipment;
import ru.great_larder.technical_assistant.database.general.GetBookingEquipmentService;
import ru.great_larder.technical_assistant.database.general.GetBookingEquipmentServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.BookingEquipment;
import ru.great_larder.technical_assistant.domain.user.User;

public class ListBookingEquipmentByIdEquipmentMySQL implements GetListBookingEquipment {
    @Override
    public List<BookingEquipment> getListBookingEquipments(User user, String nameCompany, String idEquipment) {

        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createBookingEquipmentTableMySQL();

        BookingEquipmentTableMySQL bookingEquipmentTableMySQL = new BookingEquipmentTableMySQLImpl();
        GetBookingEquipmentService getBookingEquipmentService = new GetBookingEquipmentServiceImpl();

        List<BookingEquipment> list = new ArrayList<>();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(bookingEquipmentTableMySQL.SELECT(user.getNameDB()));

            while (connectMySQL.resultSetMySQL.next()){
                if(Objects.equals(connectMySQL.resultSetMySQL.getInt("equipmentId"), Integer.valueOf(idEquipment))){
                    list.add(getBookingEquipmentService.getBookingEquipment(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            connectMySQL.closeMySQLDatabase();
        }

        return list;
    }
}
