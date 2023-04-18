package ru.greatlarder.technicalassistant.services.database.sqlite.company;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyService;
import ru.greatlarder.technicalassistant.services.database.general.GetCompanyServiceImpl;
import ru.greatlarder.technicalassistant.services.database.general.IdCompanyByUser;
import ru.greatlarder.technicalassistant.services.database.GetListCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.IdCompanyByUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCompany;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class ListAllCompanySQLite implements GetListCompany {
    @Override
    public List<Company> getAllCompany(User user, String idUser) {
        IdCompanyByUser idCompanyByUser = new IdCompanyByUserSQLite();
        List<Integer> listIdCompany = idCompanyByUser.getListIdCompanyByIdUser(String.valueOf(user.getId()), Integer.valueOf(idUser));
        List<Company> companies = new ArrayList<>();
        createCompanyTable();
        GetCompanyService getCompany = new GetCompanyServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteCompany.READE_COMPANY);
            while (resultSet.next()) {
                for (Integer i : listIdCompany) {
                    if(Objects.equals(resultSet.getInt("id"), i)){
                        companies.add(getCompany.getCompany(resultSet));
                    }
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return companies;
    }
}
