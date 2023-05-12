package ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.SetSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteSeatingArrangements;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class SetSeatingArrangementsSQLite implements SetSeatingArrangements {
    @Override
    public Integer setSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements) {
        Integer idSeatingArrangements = null;
        createSettingArrangementTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteSeatingArrangements.INSERT_SEATING_ARRANGEMENTS, Statement.RETURN_GENERATED_KEYS);

            cf.setString(1, seatingArrangements.getNameSeatingArrangements());
            cf.setString(2, seatingArrangements.getNameCompany());
            cf.setString(3, seatingArrangements.getUrlImageSeatingArrangements());

            if(cf.executeUpdate() > 0){
                ResultSet rs = cf.getGeneratedKeys();
                if(rs.next()){
                    idSeatingArrangements = rs.getInt(1);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return idSeatingArrangements;
    }
}
