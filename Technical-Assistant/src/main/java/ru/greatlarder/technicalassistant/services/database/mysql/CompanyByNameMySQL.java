package ru.greatlarder.technicalassistant.services.database.mysql;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.CompanyTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.CompanyTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyService;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetCompany;

import java.sql.SQLException;

public class CompanyByNameMySQL implements GetCompany {
    @Override
    public Company getCompany(User user, String companyName) {
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createCompanyTableMySQL();

        CompanyTableMySQL companyTableMySQL = new CompanyTableMySQLImpl();
        GetCompanyService getCompany = new GetCompanyServiceImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(companyTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                if(connection.resultSetMySQL.getString("nameCompany").equals(companyName)){
                    return getCompany.getCompany(connection.resultSetMySQL);
                }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return null;
    }
}
