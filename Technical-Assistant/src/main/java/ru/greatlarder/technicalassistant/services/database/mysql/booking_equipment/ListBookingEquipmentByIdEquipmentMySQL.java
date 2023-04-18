package ru.greatlarder.technicalassistant.services.database.mysql.booking_equipment;

import ru.greatlarder.technicalassistant.domain.BookingEquipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListBookingEquipment;
import ru.greatlarder.technicalassistant.services.database.general.GetBookingEquipmentService;
import ru.greatlarder.technicalassistant.services.database.general.GetBookingEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.BookingEquipmentTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.BookingEquipmentTableMySQLImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
