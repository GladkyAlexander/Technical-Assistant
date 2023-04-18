package ru.greatlarder.technicalassistant.services.database.sqlite.company;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyService;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCompany;

import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class CompanyByNameSQLite implements GetCompany {
    @Override
    public Company getCompany(User user, String companyName) {
        GetCompanyService getCompany = new GetCompanyServiceImpl();
        createCompanyTable();
        try {
            resultSet = statement.executeQuery(SQLiteCompany.READE_COMPANY);
            while (resultSet.next()) {
                if(resultSet.getString("nameCompany").equals(companyName)){
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
