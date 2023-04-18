package ru.greatlarder.technicalassistant.services;

import ru.greatlarder.technicalassistant.domain.Defect;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListDefect;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.GetListActivDefectByCompanySQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.util.List;

public class ProblemMonitor {

    User user = GlobalLinkMainController.getMainController().getUser();
    public boolean searchProblemMonitor(String nameCompany){
        GetListDefect getListDefect = new GetListActivDefectByCompanySQLite();

        List<Defect> defectList = getListDefect.getListDefect(user, nameCompany, null);
        if(defectList.size() > 0) {
            return true;
        } else return false;
    }
}
