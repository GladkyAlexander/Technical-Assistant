package ru.greatlarder.technicalassistant.services.database.sqlite.defect;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetDefect;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteDefect;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetDefectSQLite implements SetDefect {
    @Override
    public Integer setDefect(User user, String nameCompany, Defect defect) {
        Integer idDefect = null;
        createDefectTable();
        try {

            PreparedStatement ps = connection.prepareStatement(SQLiteDefect.INSERT_DEFECT);

            ps.setString(1, defect.getDefect());
            ps.setString(2, defect.getRoom());
            ps.setDate(3, Date.valueOf(defect.getDate_defect()));
            ps.setString(4, defect.getTime_defect().toString());
            ps.setString(5, defect.getInitiatorName());
            ps.setString(6, defect.getCondition());
            if(defect.getDateOfCompletion() != null){
                ps.setDate(7, Date.valueOf(defect.getDateOfCompletion()));
            }
            if(defect.getTimeOfCompletion() != null){
                ps.setString(8, defect.getTimeOfCompletion().toString());
            }
            ps.setString(9, defect.getExecutorName());
            ps.setString(10, defect.getSerial_number_equipment());
            ps.setInt(11, defect.getIdEquipment());
            ps.setString(12, defect.getCauseOfTheMalfunction());
            ps.setString(13, defect.getHowFixed());
            ps.setString(14, defect.getNoteExecutor());
            ps.setString(15, defect.getName_Company());

            if(ps.executeUpdate() > 0){
                ResultSet rs = ps.getGeneratedKeys();
                if(rs.next()){
                    idDefect = rs.getInt(1);
                }
            }
            closeDB();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeDB();
        }

        return idDefect;
    }
}
