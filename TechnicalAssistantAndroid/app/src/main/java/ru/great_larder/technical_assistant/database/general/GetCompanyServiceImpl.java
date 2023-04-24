package ru.great_larder.technical_assistant.database.general;


import java.sql.ResultSet;
import java.sql.SQLException;

import ru.great_larder.technical_assistant.domain.Company;

public class GetCompanyServiceImpl implements GetCompanyService {

    @Override
    public Company getCompany(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setNameCompany(resultSet.getString("nameCompany"));
        company.setAddress(resultSet.getString("address"));
        company.setCuratorLastName(resultSet.getString("curatorLastName"));
        company.setCuratorFirstName(resultSet.getString("curatorFirstName"));
        company.setPhoneCurator(resultSet.getString("phoneCurator"));
        company.setMailCurator(resultSet.getString("mailCurator"));
        company.setWebsiteCompany(resultSet.getString("websiteCompany"));
        company.setLogoCompany(resultSet.getString("logoCompany"));
        company.setManagerLastName(resultSet.getString("managerLastName"));
        company.setManagerFirstName(resultSet.getString("managerFirstName"));
        company.setPhoneManager(resultSet.getString("phoneManager"));
        company.setMailManager(resultSet.getString("mailManager"));
        company.setEngineerLastName(resultSet.getString("engineerLastName"));
        company.setEngineerFirstName(resultSet.getString("engineerFirstName"));
        company.setPhoneEngineer(resultSet.getString("phoneEngineer"));
        company.setMailEngineer(resultSet.getString("mailEngineer"));

        return company;
    }
}
