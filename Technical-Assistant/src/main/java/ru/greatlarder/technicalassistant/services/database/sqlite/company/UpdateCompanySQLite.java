package ru.greatlarder.technicalassistant.services.database.sqlite.company;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteCompany;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class UpdateCompanySQLite implements UpdateCompany {
    @Override
    public void updateCompany(User user, Company company) {
        createCompanyTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteCompany.UPDATE_COMPANY);

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

            cf.setInt(17, company.getId());

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
