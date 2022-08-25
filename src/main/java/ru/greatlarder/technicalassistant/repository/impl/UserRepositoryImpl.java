package ru.greatlarder.technicalassistant.repository.impl;

import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.repository.CompanyRepository;
import ru.greatlarder.technicalassistant.repository.MailSettingsRepository;
import ru.greatlarder.technicalassistant.repository.UserRepository;
import ru.greatlarder.technicalassistant.services.db.SQLiteUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.db.DBconnect.*;

public class UserRepositoryImpl implements UserRepository {
    CompanyRepository companyRepository = new CompanyRepositoryImpl();
    MailSettingsRepository mailSettingsRepository = new MailSettingsRepositoryImpl();
    @Override
    public User getUser() {
        User user = new User();
        createUserTable();
        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_user);
            while (resultSet.next()){
                    user.setId(resultSet.getInt("id"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setMailAddress(resultSet.getString("mailAddress"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setPost(resultSet.getString("post"));
                    user.setLanguage(resultSet.getString("language"));
            }
            user.setCompanyList(companyRepository.getAllCompany());
            user.setMailSettings(mailSettingsRepository.getListMailSettingsByUser(user.getId()));
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return user;
    }

    @Override
    public void setUser(User user) {
        createUserTable();

        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteUser.INSERT_TABLE_USER);
            cf.setString(1, user.getLastName());
            cf.setString(2, user.getFirstName());
            cf.setString(3, user.getMailAddress());
            cf.setString(4, user.getPhone());
            cf.setString(5, user.getPost());
            cf.setString(6,user.getLanguage());

            cf.executeUpdate();
            closeDB();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
