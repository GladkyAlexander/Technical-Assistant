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

public class ListEquipmentByNameCompanyMySQL implements GetListEquipment {
    @Override
    public List<Equipment> getListEquipment(User user, String companyName, String value) {

        List<Equipment> equipmentList = new ArrayList<>();

        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEquipmentTableMySQL();

        EquipmentTableMySQL equipmentTableMySQL = new EquipmentTableMySQLImpl();
        GetEquipmentService getEquipment = new GetEquipmentServiceImpl();
        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(equipmentTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("company").equals(companyName)){
                    equipmentList.add(getEquipment.getEquipment(connection.resultSetMySQL));
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
