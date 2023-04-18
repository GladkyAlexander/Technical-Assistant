package ru.greatlarder.technicalassistant.services.database.sqlite.affaris;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetAffairs;
import ru.greatlarder.technicalassistant.services.database.general.GetAffairsService;
import ru.greatlarder.technicalassistant.services.database.general.GetAffairsServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteAffairs;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetAffairsByIdSQLite implements GetAffairs {
    @Override
    public Affairs getAffairs(User user, String nameCompany, String id) {
        createTaskTable();
        try {
            resultSet = statement.executeQuery(SQLiteAffairs.READ_AFFAIRS);

            while (resultSet.next()) {
                if (Objects.equals(resultSet.getString("id"), String.valueOf(id))) {
                    GetAffairsService getAffairsService = new GetAffairsServiceImpl();
                    return getAffairsService.getAffairs(resultSet);
                }
            }
            closeDB();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
