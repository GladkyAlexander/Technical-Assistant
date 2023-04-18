package ru.greatlarder.technicalassistant.services.database.sqlite.tool;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetTools;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTools;

import java.sql.*;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetToolSQLite implements SetTools {
    @Override
    public Integer setTool(User user, String nameCompany, Tool tool) {
        Integer idTool = null;
        createToolsTable();

        try (PreparedStatement cf = connection.prepareStatement(SQLiteTools.INSERT_TOOLS, Statement.RETURN_GENERATED_KEYS)) {
            cf.setString(1, tool.getToolName());
            cf.setString(2, tool.getToolBrand());
            cf.setString(3, tool.getToolSerialNumber());
            cf.setString(4, tool.getNameCompanyToolLocation());
            cf.setDate(5, Date.valueOf(tool.getStartOfOperation()));
            cf.setString(6, tool.getToolCondition());
            cf.executeUpdate();

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idTool = rs.getInt(1);
                }
            }

            closeDB();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return idTool;
    }
}
