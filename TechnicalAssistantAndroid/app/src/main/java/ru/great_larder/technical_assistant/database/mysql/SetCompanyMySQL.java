package ru.great_larder.technical_assistant.database.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.great_larder.technical_assistant.database.SetCompany;
import ru.great_larder.technical_assistant.database.mysql.sintax.CompanyTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.CompanyTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.user.User;

public class SetCompanyMySQL implements SetCompany {
    @Override
    public Integer setCompany(User user, Company company) {
        Integer idCompany = null;
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createCompanyTableMySQL();

        CompanyTableMySQL companyTableMySQL = new CompanyTableMySQLImpl();
        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(companyTableMySQL.INSERT(user.getNameDB()), Statement.RETURN_GENERATED_KEYS);
            cf.setInt(1, company.getId());
            cf.setString(2, company.getNameCompany());
            cf.setString(3, company.getAddress());
            cf.setString(4, company.getCuratorLastName());
            cf.setString(5, company.getCuratorFirstName());
            cf.setString(6, company.getPhoneCurator());
            cf.setString(7, company.getMailCurator());
            cf.setString(8, company.getWebsiteCompany());
            cf.setString(9, company.getLogoCompany());
            cf.setString(10, company.getManagerLastName());
            cf.setString(11, company.getManagerFirstName());
            cf.setString(12, company.getPhoneManager());
            cf.setString(13, company.getMailManager());
            cf.setString(14, company.getEngineerLastName());
            cf.setString(15, company.getEngineerFirstName());
            cf.setString(16, company.getPhoneEngineer());
            cf.setString(17, company.getMailEngineer());

            if (cf.executeUpdate() > 0) {
                ResultSet rs = cf.getGeneratedKeys();
                if (rs.next()) {
                    idCompany = rs.getInt(1);
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }

        return idCompany;
    }
}
