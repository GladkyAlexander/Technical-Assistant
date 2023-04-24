package ru.great_larder.technical_assistant.database.mysql.equipment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.great_larder.technical_assistant.database.GetListEquipment;
import ru.great_larder.technical_assistant.database.general.GetEquipmentService;
import ru.great_larder.technical_assistant.database.general.GetEquipmentServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.EquipmentTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.EquipmentTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.user.User;

public class ListEquipmentByTypeMySQL implements GetListEquipment {

    @Override
    public List<Equipment> getListEquipment(User user, String companyName, String type) {
        List<Equipment> equipmentList = new ArrayList<>();
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEquipmentTableMySQL();
        EquipmentTableMySQL equipmentTableMySQL = new EquipmentTableMySQLImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(equipmentTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("company").equals(companyName)
                        && connection.resultSetMySQL.getString("type").equals(type)){
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
