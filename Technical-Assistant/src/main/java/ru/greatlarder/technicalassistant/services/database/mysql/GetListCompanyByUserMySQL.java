package ru.greatlarder.technicalassistant.services.database.mysql;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListCompany;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyService;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyServiceImpl;
import ru.greatlarder.technicalassistant.services.database.general.IdCompanyByUser;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.CompanyTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.CompanyTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.IdCompanyByUserSQLite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.resultSet;

public class GetListCompanyByUserMySQL implements GetListCompany {
    @Override
    public List<Company> getAllCompany(User user, String value) {
        IdCompanyByUser idCompanyByUser = new IdCompanyByUserSQLite();
        List<Integer> listIdCompany = idCompanyByUser.getListIdCompanyByIdUser(String.valueOf(user.getId()), user.getId());


        List<Company> companyList = new ArrayList<>();
        ConnectMySQL connection = new ConnectMySQL(user);
        connection.createCompanyTableMySQL();

        CompanyTableMySQL companyTableMySQL = new CompanyTableMySQLImpl();
        GetCompanyService getCompany = new GetCompanyServiceImpl();

        try {
            connection.resultSetMySQL = connection.statementMySQL.executeQuery(companyTableMySQL.SELECT(user.getNameDB()));
            while (connection.resultSetMySQL.next()){
                    if(getIdCompany(listIdCompany, resultSet.getInt("id"))){
                        companyList.add(getCompany.getCompany(connection.resultSetMySQL));
                    }
            }
            connection.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeMySQLDatabase();
        }
        return companyList;
    }
    private boolean getIdCompany(List<Integer> list, Integer in){
        for (Integer i : list){
            if(Objects.equals(i,in)) return true;
        }
        return false;
    }
}
