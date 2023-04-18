package ru.greatlarder.technicalassistant.services.check.check_user;

import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteUser;

import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class CheckingUserLoginSQLite implements CheckUserLogin{
    @Override
    public boolean checkUserLogin(String login) {

        createUserTable();
        try {
            resultSet = statement.executeQuery(SQLiteUser.READ_TABLE_USER);
            while (resultSet.next()) {
                if(resultSet.getString("login").equals(login)){
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
