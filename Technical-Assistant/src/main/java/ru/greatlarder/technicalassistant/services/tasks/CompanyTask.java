package ru.greatlarder.technicalassistant.services.tasks;

import javafx.concurrent.Task;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListCompany;
import ru.greatlarder.technicalassistant.services.database.mysql.GetListCompanyByUserMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.util.List;

public class CompanyTask extends Task<List<Company>> {

    User user = GlobalLinkMainController.getMainController().getUser();
    @Override
    protected List<Company> call() throws Exception {

        GetListCompany getListCompany = new GetListCompanyByUserMySQL();

        return getListCompany.getAllCompany(user, null);
    }
}
