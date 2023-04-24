package ru.great_larder.technical_assistant.database.general;

import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Defect;

public interface GetDefectService {
    Defect getDefect(ResultSet resultSet) throws SQLException;
}
