package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.repository.DefectRepository;
import ru.greatlarder.technicalassistant.repository.impl.DefectRepositoryImpl;
import ru.greatlarder.technicalassistant.services.company_listener.DataCompany;
import ru.greatlarder.technicalassistant.services.company_listener.HandlerCompanyListener;
import ru.greatlarder.technicalassistant.services.company_listener.ObserverCompany;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.tables.DefectList;

public class FragmentDefectController implements ObserverLang, ObserverCompany {
    @FXML public SplitPane splitPaneDefect;
    @FXML public TabPane tabPaneDefect;
    private String lang;
    private Company company;
    HandlerLang handlerLang = new HandlerLang();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    DefectList defectList = new DefectList();
    DefectRepository defectRepository = new DefectRepositoryImpl();
    Language language = new LanguageImpl();

    @Override
    public void updateCompany(DataCompany dataCompany) {
        this.company = dataCompany.getCompany();
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        handlerLang.onNewDataLang(new DataLang(lang));
    }
    public void loadFragment(){
        handlerLang.registerObserverLang(defectList);
        handlerCompanyListener.registerObserverCompany(defectList);

        handlerLang.onNewDataLang(new DataLang(lang));
        handlerCompanyListener.onNewDataCompany(new DataCompany(company));

        tabPaneDefect.getTabs().clear();
        Tab tabActive = new Tab(language.ACTIVE_DEFECTS(lang), defectList.upBox(defectRepository
                .getListAllActiveDefectToEquipmentByCompany(company.getNameCompany())));
        Tab tabAll = new Tab(language.ALL_DEFECTS(lang), defectList.upBox(defectRepository
                .getListAllDefectByCompany(company.getNameCompany())));
        tabPaneDefect.getTabs().addAll(tabActive, tabAll);
    }
}
