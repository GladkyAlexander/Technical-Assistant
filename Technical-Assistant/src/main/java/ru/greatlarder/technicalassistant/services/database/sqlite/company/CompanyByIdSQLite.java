package ru.greatlarder.technicalassistant.services.database.sqlite.company;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyService;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCompany;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class CompanyByIdSQLite implements GetCompany {
    @Override
    public Company getCompany(User user, String idCompany) {
        createCompanyTable();
        GetCompanyService getCompany = new GetCompanyServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteCompany.READE_COMPANY);
            while (resultSet.next()) {
                if(Objects.equals(resultSet.getInt("id"), Integer.valueOf(idCompany))) {
                    return getCompany.getCompany(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
