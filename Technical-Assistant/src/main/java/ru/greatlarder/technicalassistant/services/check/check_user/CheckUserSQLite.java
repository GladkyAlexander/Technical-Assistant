package ru.greatlarder.technicalassistant.services.check.check_user;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetUserService;
import ru.greatlarder.technicalassistant.services.database.general.GetUserServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteUser;

import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class CheckUserSQLite implements CheckUser{
    @Override
    public boolean checkUser(User user) {
        createUserTable();
        GetUserService getUser = new GetUserServiceImpl();

        User userIn;

        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_USER);
            while (resultSet.next()) {
                userIn = getUser.getUser(resultSet);
                if(userIn.getLastName().equals(user.getLastName())
                && userIn.getFirstName().equals(user.getFirstName())
                && userIn.getLogin().equals(user.getLogin())
                && userIn.getPassword().equals(user.getPassword())){
                    return true;
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return false;
    }

}
