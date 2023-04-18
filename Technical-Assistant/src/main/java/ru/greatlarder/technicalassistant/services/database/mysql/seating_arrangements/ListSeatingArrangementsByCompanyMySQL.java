package ru.greatlarder.technicalassistant.services.database.mysql.seating_arrangements;

import ru.greatlarder.technicalassistant.domain.SeatingArrangements;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.SeatingArrangementsTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.SeatingArrangementsTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingArrangementsImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetSeatingsArrangementsService;
import ru.greatlarder.technicalassistant.services.database.GetListSeatingArrangements;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
