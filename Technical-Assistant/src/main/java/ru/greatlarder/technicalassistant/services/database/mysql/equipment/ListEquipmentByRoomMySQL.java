package ru.greatlarder.technicalassistant.services.database.mysql.equipment;

import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.EquipmentTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.EquipmentTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentService;
import ru.greatlarder.technicalassistant.services.database.general.GetEquipmentServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListEquipmentByRoomMySQL implements GetListEquipment {
    @Override
    public List<Equipment> getListEquipment(User user, String companyName, String room) {
        List<Equipment> equipmentList = new ArrayList<>();
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEquipmentTableMySQL();
        EquipmentTableMySQL equipmentTableMySQL = new EquipmentTableMySQLImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(equipmentTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(room.equals(connection.resultSetMySQL.getString("room")) &&
                        connection.resultSetMySQL.getString("company").equals(companyName)){
                    GetEquipmentService getEquipmentService = new GetEquipmentServiceImpl();
                    equipmentList.add(getEquipmentService.getEquipment(connection.resultSetMySQL));
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return equipmentList;
    }
}
