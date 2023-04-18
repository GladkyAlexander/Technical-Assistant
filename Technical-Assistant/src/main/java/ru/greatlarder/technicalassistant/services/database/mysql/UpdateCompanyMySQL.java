package ru.greatlarder.technicalassistant.services.database.mysql;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateCompany;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.CompanyTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.CompanyTableMySQLImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCompanyMySQL implements UpdateCompany {

    @Override
    public void updateCompany(User user, Company company) {

        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createCompanyTableMySQL();
        CompanyTableMySQL companyTableMySQL = new CompanyTableMySQLImpl();
        try {
            PreparedStatement cf = connectMySQL.connectionMySQL.prepareStatement(companyTableMySQL.UPDATE(user.getNameDB()));

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

            cf.setInt(18, company.getId());

            cf.executeUpdate();
           connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
    }
}
