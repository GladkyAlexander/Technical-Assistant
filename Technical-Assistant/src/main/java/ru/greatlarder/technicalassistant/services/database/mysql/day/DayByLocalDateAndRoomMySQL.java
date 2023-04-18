package ru.greatlarder.technicalassistant.services.database.mysql.day;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.DaysTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.DaysTableMySQLImpl;
import ru.greatlarder.technicalassistant.services.database.general.GetDayService;
import ru.greatlarder.technicalassistant.services.database.general.GetDayServiceImpl;
import ru.greatlarder.technicalassistant.services.database.GetDay;

import java.sql.SQLException;
import java.time.LocalDate;

public class DayByLocalDateAndRoomMySQL implements GetDay {

    @Override
    public Day getDay(User user, LocalDate localDate, String nameRoom) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createDaysTableMySQL();
        GetDayService getDay = new GetDayServiceImpl();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(nameRoom)
                        && connectMySQL.resultSetMySQL.getDate("date").toLocalDate().equals(localDate)){
                    return getDay.getDay(connectMySQL.resultSetMySQL);
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
