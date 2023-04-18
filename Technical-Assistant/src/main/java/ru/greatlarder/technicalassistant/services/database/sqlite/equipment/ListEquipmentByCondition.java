package ru.greatlarder.technicalassistant.services.database.sqlite.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentService;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteEquipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class ListEquipmentByCondition implements GetListEquipment {
    @Override
    public List<Equipment> getListEquipment(User user, String companyName, String condition) {
        GetEquipmentService getEquipments = new GetEquipmentServiceImpl();
        createEquipmentTable();
        List<Equipment> equipments = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()){
                if(resultSet.getString("company").equals(companyName)
                        && resultSet.getString("condition_e").equals(condition)){
                    equipments.add(getEquipments.getEquipment(resultSet));

                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return equipments;
    }
}
