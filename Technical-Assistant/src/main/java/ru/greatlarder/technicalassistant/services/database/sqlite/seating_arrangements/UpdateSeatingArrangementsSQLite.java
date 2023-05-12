package ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.UpdateSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteSeatingArrangements;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class UpdateSeatingArrangementsSQLite implements UpdateSeatingArrangements {
    @Override
    public void updateSeatingArrangements(User user, String nameCompany, SeatingArrangements seatingArrangements, Integer id) {
        createSettingArrangementTable();
        try {
            PreparedStatement cf = connection.prepareStatement(SQLiteSeatingArrangements.UPDATE_SEATING_ARRANGEMENTS);

            cf.setString(1, seatingArrangements.getNameSeatingArrangements());
            cf.setString(2, seatingArrangements.getNameCompany());
            cf.setString(3, seatingArrangements.getUrlImageSeatingArrangements());

            cf.setInt(4, id);

            cf.executeUpdate();
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
    }
}
