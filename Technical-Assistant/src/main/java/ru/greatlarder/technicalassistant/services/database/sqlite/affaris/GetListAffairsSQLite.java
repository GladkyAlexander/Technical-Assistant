package ru.greatlarder.technicalassistant.services.database.sqlite.affaris;

import ru.greatlarder.technicalassistant.domain.Affairs;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListAffairs;
import ru.greatlarder.technicalassistant.services.database.general.GetAffairsService;
import ru.greatlarder.technicalassistant.services.database.general.GetAffairsServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteAffairs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;

public class GetListAffairsSQLite implements GetListAffairs {
    @Override
    public List<Affairs> getListAffairs(User user, String nameCompany, String value) {
        List<Affairs> listTask = new ArrayList<>();
        createTaskTable();
        try {
            resultSet = statement.executeQuery(SQLiteAffairs.READ_AFFAIRS);

            while (resultSet.next()) {
                if (resultSet.getString("nameCompany").equals(nameCompany)) {
                    GetAffairsService getAffairsService = new GetAffairsServiceImpl();
                    listTask.add(getAffairsService.getAffairs(resultSet));
                }
            }
            closeDB();
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return listTask;
    }
}
