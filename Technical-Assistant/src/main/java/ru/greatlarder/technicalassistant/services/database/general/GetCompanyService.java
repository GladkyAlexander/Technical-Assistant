package ru.greatlarder.technicalassistant.services.database.general;

import ru.greatlarder.technicalassistant.domain.Company;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface GetCompanyService {
    Company getCompany(ResultSet resultSet) throws SQLException;
}
