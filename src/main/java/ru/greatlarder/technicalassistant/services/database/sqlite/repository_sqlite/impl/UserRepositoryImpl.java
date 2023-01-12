package ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.impl;

import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.CompanyRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.MailSettingsRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository_sqlite.UserRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UserRepositoryImpl implements UserRepository {
    CompanyRepository companyRepository = new CompanyRepositoryImpl();
    MailSettingsRepository mailSettingsRepository = new MailSettingsRepositoryImpl();

    private User getUser(ResultSet resultSet) {
        User user = new User();
        try {
                    user.setId(resultSet.getInt("id"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setFirstName(resultSet.getString("firstName"));
                    user.setMailAddress(resultSet.getString("mailAddress"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setPost(resultSet.getString("post"));
                    user.setCompanyAffiliation(resultSet.getString("companyAffiliation"));
                    user.setLanguage(resultSet.getString("language"));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setServer(resultSet.getString("server"));
                    user.setPort(resultSet.getString("port"));
                    user.setNameDB(resultSet.getString("nameDB"));
                    user.setUserDB(resultSet.getString("userDB"));
                    user.setPasswordDB(resultSet.getString("passwordDB"));
        } catch (SQLException e) {
            e.printStackTrace();
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
            cf.setString(6, user.getCompanyAffiliation());
            cf.setString(7,user.getLanguage());
            cf.setString(8, user.getLogin());
            cf.setString(9, user.getPassword());
            cf.setString(10, user.getServer());
            cf.setString(11, user.getPort());
            cf.setString(12, user.getNameDB());
            cf.setString(13, user.getUserDB());
            cf.setString(14, user.getPasswordDB());

            cf.executeUpdate();
            closeDB();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }

    @Override
    public List<User> getListUser() {
        List<User> users = new ArrayList<>();
        createUserTable();
        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_USER);
            while (resultSet.next()) {
                users.add(getUser(resultSet));
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        
        return users;
    }

    @Override
    public User getUserLoginPassword(String login, String password) {
        createUserTable();
        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_USER);
            while (resultSet.next()) {
                if(resultSet.getString("login").equals(login)
                        && resultSet.getString("password").equals(password)){
                    return getUser(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }

    @Override
    public User change(User user, String valueWhatToChange, String valueOnWhat) {
        createUserTable();
        String sql = "UPDATE user SET " + valueWhatToChange + " = ? WHERE id = ?";

        try {
            PreparedStatement cf = connection.prepareStatement(sql);

            cf.setString(1,valueOnWhat);
            cf.setInt(2, user.getId());
            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeDB();
        }

        return null;
    }

}
