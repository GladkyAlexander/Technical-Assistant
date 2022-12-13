package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite;
import ru.greatlarder.technicalassistant.domain.Tool;

import java.util.List;

public interface ToolsRepository {

    String getTool(String toolSerialNumber, String nameCompany);

    List<Tool> getListTool();

    List<Tool> getToolByCompany(String nameCompany);

    List<Tool> getToolByToolBrand(String toolBrand, String nameCompany);

    List<Tool> getToolByToolName(String toolName, String nameCompany);

    Tool setTool (Tool tool);

    Tool getToolBySerialNumber(String toolSerialNumber);
    Tool changeTool(int toolId,String columnName, String values);

}
