package ru.greatlarder.technicalassistant.services.database.sqlite.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetEquipment;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentService;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEquipment;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class EquipmentByIdSQLite implements GetEquipment {
    @Override
    public Equipment getEquipment(User user, String nameCompany, String idEquipment) {
        GetEquipmentService getEquipment = new GetEquipmentServiceImpl();
        createEquipmentTable();

        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()){
                if(resultSet.getString("company").equals(nameCompany)
                        && Objects.equals(resultSet.getInt("id"), Integer.valueOf(idEquipment))){
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
