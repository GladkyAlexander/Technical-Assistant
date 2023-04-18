package ru.greatlarder.technicalassistant.services.database.sqlite.company;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.IdCompanyByUser;
import ru.greatlarder.technicalassistant.services.database.SetCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.IdCompanyByUserSQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCompany;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class SetCompanySQLite implements SetCompany {

    @Override
    public Integer setCompany(User user, Company company) {
        Integer idCompany = null;
        createCompanyTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteCompany.INSERT_COMPANY, Statement.RETURN_GENERATED_KEYS);

            cf.setString(1, company.getNameCompany());
            cf.setString(2, company.getAddress());
            cf.setString(3, company.getCuratorLastName());
            cf.setString(4, company.getCuratorFirstName());
            cf.setString(5, company.getPhoneCurator());
            cf.setString(6, company.getMailCurator());
            cf.setString(7, company.getWebsiteCompany());
            cf.setString(8, company.getLogoCompany());
            cf.setString(9, company.getManagerLastName());
            cf.setString(10, company.getManagerFirstName());
            cf.setString(11, company.getPhoneManager());
            cf.setString(12, company.getMailManager());
            cf.setString(13, company.getEngineerLastName());
            cf.setString(14, company.getEngineerFirstName());
            cf.setString(15, company.getPhoneEngineer());
            cf.setString(16, company.getMailEngineer());

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idCompany = rs.getInt(1);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        IdCompanyByUser idCompanyByUser = new IdCompanyByUserSQLite();
        if(idCompany != null) {
            idCompanyByUser.setIdCompanyAffiliation(String.valueOf(user.getId()), user.getId(), idCompany);
        }

        return idCompany;
    }
}
