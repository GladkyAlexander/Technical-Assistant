package ru.greatlarder.technicalassistant.services.check.check_day;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.mysql.ConnectMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.DaysTableMySQL;
import ru.greatlarder.technicalassistant.services.database.mysql.sintax.impl.DaysTableMySQLImpl;

import java.sql.SQLException;

public class CheckinkDayByNameMySQL implements CheckDay{
    @Override
    public boolean checkDay(User user, Day day) {
        ConnectMySQL connectMySQL = new ConnectMySQL(user);
        connectMySQL.createDaysTableMySQL();
        DaysTableMySQL daysTableMySQL = new DaysTableMySQLImpl();
        try {
            connectMySQL.resultSetMySQL = connectMySQL.statementMySQL.executeQuery(daysTableMySQL.SELECT(user.getNameDB()));
            while (connectMySQL.resultSetMySQL.next()){
                if(connectMySQL.resultSetMySQL.getString("room").equals(day.getRoom())
                        && connectMySQL.resultSetMySQL.getString("date").equals(day.getDate().toString())){
                    return true;
                }
            }
            connectMySQL.closeMySQLDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectMySQL.closeMySQLDatabase();
        }
        return false;
    }
}
