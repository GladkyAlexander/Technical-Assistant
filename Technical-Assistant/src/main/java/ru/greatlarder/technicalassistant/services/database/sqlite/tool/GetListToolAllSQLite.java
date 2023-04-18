package ru.greatlarder.technicalassistant.services.database.sqlite.tool;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListTool;
import ru.greatlarder.technicalassistant.services.database.general.GetToolService;
import ru.greatlarder.technicalassistant.services.database.general.GetToolServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class GetListToolAllSQLite implements GetListTool {
    @Override
    public List<Tool> getListTool(User user, String nameCompany, String value) {
        List<Tool> toolList = new ArrayList<>();
        createToolsTable();
        GetToolService getTools = new GetToolServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteTools.READ_TOOLS);

            while (resultSet.next()){
                toolList.add(getTools.getTool(resultSet));
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return toolList;
    }
}
