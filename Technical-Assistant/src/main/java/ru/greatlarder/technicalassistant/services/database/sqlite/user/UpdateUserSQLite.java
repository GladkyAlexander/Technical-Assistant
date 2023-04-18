package ru.greatlarder.technicalassistant.services.database.sqlite.user;

import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateUser;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteUser;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdateUserSQLite implements UpdateUser {

    @Override
    public User changeUser(User user) {
        createUserTable();

        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteUser.UPDATE_USER);

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
            cf.setString(15, user.getServerFTP());
            cf.setInt(16, user.getPortFTP());
            cf.setString(17, user.getUserFTP());
            cf.setString(18, user.getPasswordFTP());

            cf.setInt(19, user.getId());

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
