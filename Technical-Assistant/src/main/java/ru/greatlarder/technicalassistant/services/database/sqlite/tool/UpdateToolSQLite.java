package ru.greatlarder.technicalassistant.services.database.sqlite.tool;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateTools;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTools;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdateToolSQLite implements UpdateTools {
    @Override
    public void updateTool(User user, String nameCompany, Tool tool, Integer id) {
        createToolsTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteTools.UPDATE_TOOLS);

            cf.setString(1, tool.getToolName());
            cf.setString(2, tool.getToolBrand());
            cf.setString(3, tool.getToolSerialNumber());
            cf.setString(4, tool.getNameCompanyToolLocation());
            cf.setString(5, tool.getStartOfOperation().toString());
            cf.setString(6, tool.getToolCondition());

            cf.setInt(7, id);

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
