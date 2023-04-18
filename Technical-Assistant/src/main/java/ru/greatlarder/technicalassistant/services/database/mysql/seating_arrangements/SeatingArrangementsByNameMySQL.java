package ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetSeatingArrangements;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingArrangementsImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingsArrangementsService;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;

import java.sql.SQLException;

public class SeatingArrangementsByNameMySQL implements GetSeatingArrangements {
    @Override
    public SeatingArrangements getSeatingArrangements(User user, String nameCompany, String nameSeatingArrangement) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createSeatingArrangementsTableMySQL();
        SeatingArrangementsTableMySQL seatingArrangements = new SeatingArrangementsTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(seatingArrangements.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("nameCompany").equals(nameCompany)
                && connectMySQL.resultSetMySQL.getString("nameSeatingArrangements").equals(nameSeatingArrangement)){
                    GetSeatingsArrangementsService getSeatingsArrangementsService = new GetSeatingArrangementsImpl();
                    return getSeatingsArrangementsService.getSeating(connectMySQL.resultSetMySQL);
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return null;
    }
}
