package ru.great_larder.technical_assistant.database;

import java.util.List;

import ru.great_larder.technical_assistant.domain.Tool;
import ru.great_larder.technical_assistant.domain.user.User;

public interface GetListTool {
    List<Tool> getListTool(User user, String nameCompany, String value);
}
