package ru.great_larder.technical_assistant.database.mysql;

import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.GetCompany;
import ru.great_larder.technical_assistant.database.general.GetCompanyService;
import ru.great_larder.technical_assistant.database.general.GetCompanyServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.sintax.CompanyTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.CompanyTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.user.User;

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
