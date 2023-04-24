package ru.great_larder.technical_assistant.database.mysql.seating_arrangements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.great_larder.technical_assistant.database.GetListSeatingArrangements;
import ru.great_larder.technical_assistant.database.general.GetSeatingArrangementsImpl;
import ru.great_larder.technical_assistant.database.general.GetSeatingsArrangementsService;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.SeatingArrangements;
import ru.great_larder.technical_assistant.domain.user.User;

public class ListSeatingArrangementsByCompanyMySQL implements GetListSeatingArrangements {
    @Override
    public List<SeatingArrangements> getListSeatingArrangements(User user, String nameCompany, String value) {

        List<SeatingArrangements> list = new ArrayList<>();
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createSeatingArrangementsTableMySQL();
        SeatingArrangementsTableMySQL seatingArrangements = new SeatingArrangementsTableMySQLImpl();

        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(seatingArrangements.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("nameCompany").equals(nameCompany)){
                    GetSeatingsArrangementsService getSeatingsArrangementsService = new GetSeatingArrangementsImpl();
                    list.add(getSeatingsArrangementsService.getSeating(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return list;
    }
}
