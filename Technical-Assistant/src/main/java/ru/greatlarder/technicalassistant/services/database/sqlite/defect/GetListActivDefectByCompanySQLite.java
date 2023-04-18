package ru.greatlarder.technicalassistant.services.database.sqlite.defect;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListDefect;
import ru.greatlarder.technicalassistant.services.database.general.GetDefectService;
import ru.greatlarder.technicalassistant.services.database.general.GetDefectServiceImpl;
import ru.greatlarder.technicalassistant.services.database.sqlite.sintax_sqlite.SQLiteDefect;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.*;
import static ru.greatlarder.technicalassistant.services.database.sqlite.DBconnect.closeDB;

public class GetListActivDefectByCompanySQLite implements GetListDefect {
    Language language = new LanguageImpl();
    String lang = GlobalLinkMainController.getMainController().getLang();
    @Override
    public List<Defect> getListDefect(User user, String nameCompany, String value) {
        createDefectTable();
        List<Defect> defects = new ArrayList<>();
        try {
            resultSet = statement.executeQuery(SQLiteDefect.READ_DEFECT);
            while (resultSet.next()){
                if(resultSet.getString("nameCompany").equals(nameCompany)
                        && resultSet.getString("condition").equals(language.FAULTY(lang))){
                    GetDefectService defectService = new GetDefectServiceImpl();
                    defects.add(defectService.getDefect(resultSet));
                }
            }
            closeDB();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        return defects;
    }
}
