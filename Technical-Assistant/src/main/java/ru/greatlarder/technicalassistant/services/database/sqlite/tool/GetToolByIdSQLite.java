package ru.greatlarder.technicalassistant.services.database.sqlite.tool;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetTools;
import ru.greatlarder.technicalassistant.services.database.general.GetToolService;
import ru.greatlarder.technicalassistant.services.database.general.GetToolServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTools;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetToolByIdSQLite implements GetTools {
    @Override
    public Tool getTool(User user, String nameCompany, String id) {
        createToolsTable();
        GetToolService getTools = new GetToolServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteTools.READ_TOOLS);

            while (resultSet.next()){
                if(Objects.equals(resultSet.getInt("id"), Integer.valueOf(id))){
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
