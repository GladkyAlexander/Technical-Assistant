package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.User;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.DefectRepository;
import ru.greatlarder.technicalassistant.services.database.sqlite.repository.impl.DefectRepositoryImpl;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkStartEngineerController;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.DefectList;
import ru.greatlarder.technicalassistant.services.user_listener.DataUser;
import ru.greatlarder.technicalassistant.services.user_listener.ObserverUser;

public class FragmentDefectController implements ObserverLang, ObserverCompany, ObserverUser {
    @FXML public SplitPane splitPaneDefect;
    @FXML public TabPane tabPaneDefect;
    private String lang;
    private Company company;
    HandlerLang handlerLang = GlobalLinkMainController.getMainController().handlerLang;
    HandlerCompanyListener handlerCompanyListener = GlobalLinkStartEngineerController.getStartEngineerController().handlerCompanyListener;
    DefectList defectList = new DefectList();
    DefectRepository defectRepository = new DefectRepositoryImpl();
    Language language = new LanguageImpl();
    private User user;

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        loadFragment();
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
    }
    public void loadFragment(){
        handlerLang.registerObserverLang(defectList);
        handlerCompanyListener.registerObserverCompany(defectList);

        defectList.updateLang(new DataLang(lang));
        defectList.updateUser(new DataUser(user));
        defectList.updateCompany(new DataCompany(company));

        tabPaneDefect.getTabs().clear();
        Tab tabActive = new Tab(language.ACTIVE_DEFECTS(lang), defectList.upBox(defectRepository
                .getListAllActiveDefectToEquipmentByCompany(company.getNameCompany())));
        Tab tabAll = new Tab(language.ALL_DEFECTS(lang), defectList.upBox(defectRepository
                .getListAllDefectByCompany(company.getNameCompany())));
        tabPaneDefect.getTabs().addAll(tabActive, tabAll);
    }

    @Override
    public void updateUser(DataUser dataUser) {
        this.user = dataUser.getUser();
    }
}
