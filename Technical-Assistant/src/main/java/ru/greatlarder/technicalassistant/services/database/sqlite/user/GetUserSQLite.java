package ru.greatlarder.technicalassistant.services.database.sqlite.user;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetUserService;
import ru.greatlarder.technicalassistant.services.database.general.GetUserServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteUser;

import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class GetUserSQLite implements GetUser {
    @Override
    public User getUser(String login, String password) {
        createUserTable();
        GetUserService getUser = new GetUserServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_USER);
            while (resultSet.next()) {
                if(resultSet.getString("login").equals(login)
                        && resultSet.getString("password").equals(password)){
                    return getUser.getUser(resultSet);
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
}
