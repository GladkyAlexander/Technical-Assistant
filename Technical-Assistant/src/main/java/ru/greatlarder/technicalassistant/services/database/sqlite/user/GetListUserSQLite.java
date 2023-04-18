package ru.greatlarder.technicalassistant.services.database.sqlite.user;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.general.GetUserService;
import ru.greatlarder.technicalassistant.services.database.general.GetUserServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetListUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteUser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class GetListUserSQLite implements GetListUser {
    @Override
    public List<User> getListUser(String value1, String value2) {
        List<User> users = new ArrayList<>();
        createUserTable();
        GetUserService getUser = new GetUserServiceImpl();
        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_USER);
            while (resultSet.next()) {
                users.add(getUser.getUser(resultSet));
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return users;
    }
}
