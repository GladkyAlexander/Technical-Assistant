package ru.greatlarder.technicalassistant.services.database.sqlite.defect;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListDefect;
import ru.greatlarder.technicalassistant.services.database.general.GetDefectService;
import ru.greatlarder.technicalassistant.services.database.general.GetDefectServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteDefect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetListDefectSQLite implements GetListDefect {
    @Override
    public List<Defect> getListDefect(User user, String nameCompany, String value) {
        createDefectTable();
        try {
            resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);

            List<Defect> defects = new ArrayList<>();

            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)){
                    GetDefectService defectService = new GetDefectServiceImpl();
                    defects.add(defectService.getDefect(resultSet));
                }
            }
            closeDB();
            return defects;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
