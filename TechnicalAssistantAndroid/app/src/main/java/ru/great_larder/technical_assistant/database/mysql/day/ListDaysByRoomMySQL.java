package ru.great_larder.technical_assistant.database.mysql.day;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.great_larder.technical_assistant.database.GetListDays;
import ru.great_larder.technical_assistant.database.general.GetDayService;
import ru.great_larder.technical_assistant.database.general.GetDayServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.DaysTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.DaysTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Company;
import ru.great_larder.technical_assistant.domain.Day;
import ru.great_larder.technical_assistant.domain.user.User;

public class ListDaysByRoomMySQL implements GetListDays {

    @Override
    public List<Day> getListDays(User user, Company company, String nameRoom) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        GetDayService getDays = new GetDayServiceImpl();
        List<Day> daysFromBD = new ArrayList<>();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(nameRoom)){
                    daysFromBD.add(getDays.getDay(connectMySQL.resultSetMySQL));
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return daysFromBD;
    }
}
