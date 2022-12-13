package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl;

import ru.greatlarder.technicalassistant.domain.Tool;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.ToolsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteTools;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;


public class ToolsRepositoryImpl implements ToolsRepository {
    private Tool getTools(ResultSet resultSet){
        Tool tool = new Tool();
        try {
                tool.setId(resultSet.getInt("id"));
                tool.setToolName(resultSet.getString("toolName"));
                tool.setToolBrand(resultSet.getString("toolBrand"));
                tool.setToolSerialNumber(resultSet.getString("toolSerialNumber"));
                tool.setNameCompanyToolLocation(resultSet.getString("nameCompanyToolLocation"));
                tool.setStartOfOperation(resultSet.getDate("startOfOperation").toLocalDate());
                tool.setToolCondition(resultSet.getString("toolCondition"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tool;
    }

    @Override
    public String getTool(String toolSerialNumber, String nameCompany) {
        
        return null;
    }

    @Override
    public List<Tool> getListTool() {
        List<Tool> toolList = new ArrayList<>();
        createToolsTable();
        try {
            resultSet = statement.executeQuery(SQLiteTools.READ_TOOLS);

            while (resultSet.next()){
               toolList.add(getTools(resultSet));
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return toolList;
    }

    @Override
    public List<Tool> getToolByCompany(String nameCompany) {
       List<Tool> toolList = new ArrayList<>();
       for (Tool tool : getListTool()){
           if(tool.getNameCompanyToolLocation().equals(nameCompany)){
               toolList.add(tool);
           }
       }
        return toolList;
    }

    @Override
    public List<Tool> getToolByToolBrand(String toolBrand, String nameCompany) {
        return null;
    }

    @Override
    public List<Tool> getToolByToolName(String toolName, String nameCompany) {
        return null;
    }

    @Override
    public Tool setTool(Tool tool) {

        createToolsTable();

        try (PreparedStatement cf = connection.prepareStatement(SQLiteTools.INSERT_TOOLS)) {
            cf.setString(1, tool.getToolName());
            cf.setString(2, tool.getToolBrand());
            cf.setString(3, tool.getToolSerialNumber());
            cf.setString(4, tool.getNameCompanyToolLocation());
            cf.setDate(5, Date.valueOf(tool.getStartOfOperation()));
            cf.setString(6, tool.getToolCondition());
            cf.executeUpdate();
            closeDB();
            System.out.println("The tool" + tool.getToolSerialNumber() + " table is filled in !");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return null;
    }

    @Override
    public Tool getToolBySerialNumber(String toolSerialNumber) {
        for (Tool tool1 : getListTool()){
            if(tool1.getToolSerialNumber().equals(toolSerialNumber)){
                return tool1;
            }
        }
        return null;
    }

    @Override
    public Tool changeTool(int toolId, String column, String values) {
        createToolsTable();
        String sql = "UPDATE tools SET " + column + " = ? WHERE id = ?";

        try {
            PreparedStatement cf = connection.prepareStatement(sql);

            cf.setString(1, values);
            cf.setInt(2, toolId);

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return null;
    }
}
