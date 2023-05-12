package ru.greatlarder.technicalassistant.services.database.sqlite.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingArrangementsImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingsArrangementsService;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteSeatingArrangements;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetSeatingArrangementsByNameSQLite implements GetSeatingArrangements {
    @Override
    public SeatingArrangements getSeatingArrangements(User user, String nameCompany, String value) {
        createRoomTable();
        
        try {
            resultSet = statement.executeQuery(SQLiteSeatingArrangements.READ_SEATING_ARRANGEMENTS);
            
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)
                    && resultSet.getString("nameSeatingArrangements").equals(value)){
                    GetSeatingsArrangementsService getSeatingsArrangementsService = new GetSeatingArrangementsImpl();
                    return getSeatingsArrangementsService.getSeating(resultSet);
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
