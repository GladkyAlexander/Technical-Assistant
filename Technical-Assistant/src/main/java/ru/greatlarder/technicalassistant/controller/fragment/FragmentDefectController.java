package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListDefect;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.GetListConditionDefectForCompanySQLite;
import ru.greatlarder.technicalassistant.services.database.sqlite.defect.GetListDefectSQLite;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.TableViewDefect;

import java.net.URL;
import java.util.ResourceBundle;

public class FragmentDefectController implements Initializable {
    @FXML public SplitPane splitPaneDefect;
    @FXML public TabPane tabPaneDefect;
    String lang;
    Company company;
    TableViewDefect defectList = new TableViewDefect();
    Language language = new LanguageImpl();
    User user;

    public void loadFragment(){
        tabPaneDefect.getTabs().clear();
        GetListDefect getListDefectActive = new GetListConditionDefectForCompanySQLite();
        GetListDefect getAllDefect = new GetListDefectSQLite();
        Tab tabActive = new Tab(language.ACTIVE_DEFECTS(lang), defectList.upBox(getListDefectActive
                .getListDefect(user, company.getNameCompany(), language.ACTIVE_DEFECTS(lang))));
        Tab tabAll = new Tab(language.ALL_DEFECTS(lang), defectList.upBox(getAllDefect.getListDefect(user
        , company.getNameCompany(), null)));
        tabPaneDefect.getTabs().addAll(tabActive, tabAll);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
        loadFragment();
    }
}
