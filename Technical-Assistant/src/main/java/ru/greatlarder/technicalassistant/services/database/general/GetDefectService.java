package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Defect;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetDefectService {
    Defect getDefect(ResultSet resultSet) throws SQLException;
}
