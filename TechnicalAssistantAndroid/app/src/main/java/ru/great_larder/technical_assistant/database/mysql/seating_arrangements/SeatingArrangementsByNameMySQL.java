package ru.great_larder.technical_assistant.database.mysql.seating_arrangements;


import java.sql.SQLException;

import ru.great_larder.technical_assistant.database.GetSeatingArrangements;
import ru.great_larder.technical_assistant.database.general.GetSeatingArrangementsImpl;
import ru.great_larder.technical_assistant.database.general.GetSeatingsArrangementsService;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

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
