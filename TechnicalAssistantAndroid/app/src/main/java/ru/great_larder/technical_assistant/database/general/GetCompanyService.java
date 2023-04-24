package ru.great_larder.technical_assistant.database.general;

import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Company;

public interface GetCompanyService {
    Company getCompany(ResultSet resultSet) throws SQLException;
}
