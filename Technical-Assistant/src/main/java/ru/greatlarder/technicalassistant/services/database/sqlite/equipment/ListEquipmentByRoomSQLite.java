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

public class ListEquipmentByRoomSQLite implements GetListEquipment {
    @Override
    public List<Equipment> getListEquipment(User user, String companyName, String room) {
        List<Equipment> equipmentList = new ArrayList<>();
        createEquipmentTable();

        GetEquipmentService getEquipment = new GetEquipmentServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteEquipment.READ_TABLE_EQUIPMENT);
            while (resultSet.next()){
                if(resultSet.getString("company") != null) {
                   if (resultSet.getString("company").equals(companyName)){
                       if (resultSet.getString("room") != null){
                           if(resultSet.getString("room").equals(room)){
                               equipmentList.add(getEquipment.getEquipment(resultSet));
                           }
                       }
                   }
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return equipmentList;
    }
}
