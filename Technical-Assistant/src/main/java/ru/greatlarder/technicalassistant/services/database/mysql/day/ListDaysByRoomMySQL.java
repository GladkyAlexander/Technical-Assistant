package ru.greatlarder.technicalassistant.services.database.mysql.day;

import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.DaysTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.DaysTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetDayService;
import ru.greatlarder.technicalassistant.services.database.general.GetDayServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetListDays;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
