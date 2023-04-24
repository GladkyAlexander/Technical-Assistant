package ru.great_larder.technical_assistant.database.mysql.day;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import ru.great_larder.technical_assistant.database.GetDay;
import ru.great_larder.technical_assistant.database.general.GetDayService;
import ru.great_larder.technical_assistant.database.general.GetDayServiceImpl;
import ru.great_larder.technical_assistant.database.mysql.ConnectMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.DaysTableMySQL;
import ru.great_larder.technical_assistant.database.mysql.sintax.impl.DaysTableMySQLImpl;
import ru.great_larder.technical_assistant.domain.Day;
import ru.great_larder.technical_assistant.domain.user.User;

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
                        && connectMySQL.resultSetMySQL.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                        .equals(localDate)){
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
