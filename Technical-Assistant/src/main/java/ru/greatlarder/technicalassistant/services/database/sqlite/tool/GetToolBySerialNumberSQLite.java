package ru.greatlarder.technicalassistant.services.database.sqlite.tool;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetTools;
import ru.greatlarder.technicalassistant.services.database.general.GetToolService;
import ru.greatlarder.technicalassistant.services.database.general.GetToolServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTools;

import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetToolBySerialNumberSQLite implements GetTools {
    @Override
    public Tool getTool(User user, String nameCompany, String serialNumber) {
        createToolsTable();
        GetToolService getTools = new GetToolServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteTools.READ_TOOLS);

            while (resultSet.next()){
                if(resultSet.getString("toolSerialNumber").equals(serialNumber)){
                    return getTools.getTool(resultSet);
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
