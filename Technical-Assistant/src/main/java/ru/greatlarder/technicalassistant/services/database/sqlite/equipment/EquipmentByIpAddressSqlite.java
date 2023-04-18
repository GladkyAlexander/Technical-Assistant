package ru.greatlarder.technicalassistant.services.database.sqlite.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentService;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEquipment;

import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class EquipmentByIpAddressSqlite implements GetEquipment {

    @Override
    public Equipment getEquipment(User user, String nameCompany, String ipAddress) {
        GetEquipmentService getEquipment = new GetEquipmentServiceImpl();
        createEquipmentTable();

        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()){
                if(resultSet.getString("company").equals(nameCompany)
                        && resultSet.getString("ipAddress").equals(ipAddress)){
                    return getEquipment.getEquipment(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return null;
    }
}
