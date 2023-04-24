package ru.great_larder.technical_assistant.database.mysql.equipment;

import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.GetEquipment;
import ru.great_larder.technical_assistant.database.general.GetEquipmentService;
import ru.great_larder.technical_assistant.database.general.GetEquipmentServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.EquipmentTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.EquipmentTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Equipment;
import ru.great_larder.technical_assistant.domain.user.User;

public class EquipmentByNameCompanyMySQL implements GetEquipment {
    @Override
    public Equipment getEquipment(User user, String nameCompany, String value) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createEquipmentTableMySQL();

        EquipmentTableMySQL equipmentTableMySQL = new EquipmentTableMySQLImpl();
        GetEquipmentService getEquipment = new GetEquipmentServiceImpl();
        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(equipmentTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("company").equals(nameCompany)){
                    return getEquipment.getEquipment(connection.resultSetMySQL);
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return null;
    }
}
