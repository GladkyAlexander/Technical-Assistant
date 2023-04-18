package ru.greatlarder.technicalassistant.services.database.sqlite.defect;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateDefect;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteDefect;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class UpdateDefectSQLite implements UpdateDefect {
    @Override
    public void updateDefect(User user, String nameCompany, Defect defect, Integer idDefect) {
        createDefectTable();

        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteDefect.UPDATE_DEFECT);

            cf.setString(1, defect.getCondition());
            if (defect.getDateOfCompletion() != null) {
                cf.setDate(2, Date.valueOf(defect.getDateOfCompletion()));
            }
            if (defect.getTimeOfCompletion() != null) {
                cf.setString(3, defect.getTimeOfCompletion().toString());
            }
            cf.setString(4, defect.getExecutorName());
            cf.setString(5, defect.getCauseOfTheMalfunction());
            cf.setString(6, defect.getHowFixed());
            cf.setString(7, defect.getNoteExecutor());

            cf.setInt(8, defect.getId());

            cf.executeUpdate();

            closeDB();
            System.out.println("The defect table has been changed !");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }

    }
}
