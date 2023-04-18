package ru.greatlarder.technicalassistant.services.database.sqlite.defect;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetDefect;
import ru.greatlarder.technicalassistant.services.database.general.GetDefectService;
import ru.greatlarder.technicalassistant.services.database.general.GetDefectServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteDefect;

import java.sql.SQLException;
import java.util.Objects;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetDefectByIdSQLite implements GetDefect {
    @Override
    public Defect getDefect(User user, String nameCompany, String idDefect) {
        createDefectTable();
        try {
            resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);
            while (resultSet.next()){
                if (Objects.equals(resultSet.getInt("id"), Integer.valueOf(idDefect))){
                    GetDefectService getDefectService = new GetDefectServiceImpl();
                    return getDefectService.getDefect(resultSet);
                }
            } closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return null;
    }
}
