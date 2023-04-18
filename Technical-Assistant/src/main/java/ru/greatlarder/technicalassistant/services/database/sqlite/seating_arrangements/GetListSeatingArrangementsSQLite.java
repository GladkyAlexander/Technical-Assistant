package ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingArrangementsImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingsArrangementsService;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteSeatingArrangements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetListSeatingArrangementsSQLite implements GetListSeatingArrangements {
    @Override
    public List<SeatingArrangements> getListSeatingArrangements(User user, String nameCompany, String value) {
        List<SeatingArrangements> listSeatingArrangements = new ArrayList<SeatingArrangements>();
        createSettingArrangementTable();
        try {
            resultSet = statement.executeQuery(SQLiteSeatingArrangements.READ_SEATING_ARRANGEMENTS);
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)){
                    GetSeatingsArrangementsService getSeatingArrangements = new GetSeatingArrangementsImpl();
                    listSeatingArrangements.add(getSeatingArrangements.getSeating(resultSet));
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return listSeatingArrangements;
    }
}
