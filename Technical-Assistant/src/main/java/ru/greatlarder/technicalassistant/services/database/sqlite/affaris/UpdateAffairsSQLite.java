package ru.greatlarder.technicalassistant.services.database.sqlite.affaris;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateAffairs;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteAffairs;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class UpdateAffairsSQLite implements UpdateAffairs {
    @Override
    public void updateAffairs(User user, String nameCompany, Affairs affairs, Integer id) {
        createTaskTable();

        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteAffairs.UPDATE_AFFAIRS);

            cf.setDate(1, Date.valueOf(affairs.getDateOfCreation()));
            cf.setTime(2, Time.valueOf(affairs.getTimeOfCreation()));
            if(affairs.getClosingDate() != null) {
                cf.setDate(3, Date.valueOf(affairs.getClosingDate()));
            }
            if(affairs.getClosingTime() != null) {
                cf.setTime(4, Time.valueOf(affairs.getClosingTime()));
            }
            cf.setString(5, affairs.getCreator());
            cf.setString(6, affairs.getRoom());
            cf.setString(7, affairs.getExecutor());
            cf.setString(8, affairs.getTextTask());
            cf.setInt(9, affairs.getStatus());
            cf.setString(10, affairs.getNameCompany());

            cf.setInt(11, id);

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

    }
}
