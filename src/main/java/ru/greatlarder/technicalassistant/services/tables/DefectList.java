package ru.greatlarder.technicalassistant.services.tables;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import ru.greatlarder.technicalassistant.controller.fragment_item.ItemDefectController;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Defect;
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
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DefectList implements ObserverLang, ObserverCompany {
    DefectRepository defectRepository = new DefectRepositoryImpl();
    HandlerLang handlerLang = new HandlerLang();
    HandlerCompanyListener handlerCompanyListener = new HandlerCompanyListener();
    Company company;
    String lang;
    Language language = new LanguageImpl();
    public ListView<Defect> upBox(List<Defect> defectList){
        ObservableList<Defect> observableList = FXCollections.observableArrayList();
        observableList.addAll(defectList);
        ListView<Defect> list = new ListView<>(observableList);

        list.setCellFactory(param -> new ListCell<>(){
            final FXMLLoader loader = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_defect.fxml"));
            Node node;
            ItemDefectController itemDefectController;
            {
                try {
                    node = loader.load();

                    handlerLang.registerObserverLang(loader.getController());
                    handlerCompanyListener.registerObserverCompany(loader.getController());

                    handlerLang.onNewDataLang(new DataLang(lang));
                    handlerCompanyListener.onNewDataCompany(new DataCompany(company));

                    itemDefectController = loader.getController();
                    setPrefWidth(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void updateItem(Defect item, boolean empty) {
                super.updateItem(item, empty);
                if(empty){
                    setGraphic(null);
                } else {
                    Defect defect = new Defect();
                    defect = item;
                    itemDefectController.setDefect(defect);
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy" + " г.");
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                    if(item.getCondition().equals(language.SATISFACTORY(lang))){
                        itemDefectController.hBoxItemDefect.setStyle(StyleSRC.STYLE_EXCELLENT);
                    }
                    itemDefectController.setImgLogoEquipment("/ru/greatlarder/technicalassistant/images/warning_min.png");

                    itemDefectController.setLabelDateOfCreation(item.getDate_defect().format(dateFormat));
                    itemDefectController.setLabelTimeOfCreation(item.getTime_defect().format(dateTimeFormatter));
                    itemDefectController.setLabelSerialNumber(item.getSerial_number_equipment());
                    itemDefectController.setTextAreaCauseMalfunction(item.getCauseOfTheMalfunction());
                    itemDefectController.setTextAreaHowFixed(item.getHowFixed());
                    itemDefectController.setTextAreaNote(item.getNoteExecutor());
                    itemDefectController.setCheckBoxStatus(item.getCondition());
                    itemDefectController.setIdEquipment(item.getIdEquipment());

                    itemDefectController.textAreaCauseMalfunction.setWrapText(true);
                    itemDefectController.textAreaHowFixed.setWrapText(true);
                    itemDefectController.textAreaNote.setWrapText(true);
                    setGraphic(node);
                }
            }
        });
        //list.setPrefHeight(1000);
        return list;
    }

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
}
