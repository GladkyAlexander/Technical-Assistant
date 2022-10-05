package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.services.lang.DataLang;
import ru.greatlarder.technicalassistant.services.lang.HandlerLang;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.ObserverLang;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.util.HashMap;

public class FragmentIdenticalData implements ObserverLang {
    @FXML public TabPane tabPaneFID;
    String lang;
    HandlerLang handlerLang = new HandlerLang();
    Language language = new LanguageImpl();


    public void loadFragment(HashMap<Equipment, Equipment> hashMap){
        tabPaneFID.setStyle(StyleSRC.STYLE_ORDINARY);
        hashMap.forEach((key, value)-> tabPaneFID.getTabs().add(new Tab(key.getName() + " : " + key.getSerialNumber(), updatePaneFr(key, value))));
    }

    private SplitPane updatePaneFr(Equipment equipment, Equipment equipment_external){
        SplitPane pane = new SplitPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));

        try {
            pane.getItems().add(new ScrollPane(loader1.load()));
            handlerLang.registerObserverLang(loader1.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentEquipmentOneController fragmentEquipmentOneController1 = loader1.getController();
            fragmentEquipmentOneController1.setEquip(equipment);
            fragmentEquipmentOneController1.setBtnChangeDataStorage(language.SAVE_THIS_DATA_TO(lang) + " " + language.EXTERNAL_STORAGE(lang), pane);
            fragmentEquipmentOneController1.setLabelStorage(language.LOCAL_STORAGE(lang));
            disableButton(fragmentEquipmentOneController1);

            pane.getItems().add(new ScrollPane(loader2.load()));
            handlerLang.registerObserverLang(loader2.getController());
            handlerLang.onNewDataLang(new DataLang(lang));
            FragmentEquipmentOneController fragmentEquipmentOneController2 = loader2.getController();
            fragmentEquipmentOneController2.setEquip(equipment_external);
            fragmentEquipmentOneController2.setBtnChangeDataStorage(language.SAVE_THIS_DATA_TO(lang) + " " + language.LOCAL_STORAGE(lang), pane);
            fragmentEquipmentOneController2.setLabelStorage(language.EXTERNAL_STORAGE(lang));
            disableButton(fragmentEquipmentOneController2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    @Override
    public void updateLang(DataLang dataLang) {
        this.lang = dataLang.getLanguage();
        handlerLang.onNewDataLang(new DataLang(lang));
    }
    private void disableButton(FragmentEquipmentOneController controller){
        controller.imgChangeIp.setVisible(false);
        controller.imgChangeIp.setManaged(false);
        controller.imgChangeLogin.setVisible(false);
        controller.imgChangeLogin.setManaged(false);
        controller.imgChangePassword.setVisible(false);
        controller.imgChangePassword.setManaged(false);
        controller.imgChangeRoom.setVisible(false);
        controller.imgChangeRoom.setManaged(false);
        controller.imgChangeLocationToRoom.setVisible(false);
        controller.imgChangeLocationToRoom.setManaged(false);
        controller.imgConnectWebInterface.setVisible(false);
        controller.imgConnectWebInterface.setManaged(false);
        controller.imgChangeCondition.setVisible(false);
        controller.imgChangeCondition.setManaged(false);
        controller.imgChangeMasc.setVisible(false);
        controller.imgChangeMasc.setManaged(false);
        controller.imgChangeGateway.setVisible(false);
        controller.imgChangeGateway.setManaged(false);
        controller.imgChangeIpDante.setVisible(false);
        controller.imgChangeIpDante.setManaged(false);
        controller.imgChangeMascDante.setVisible(false);
        controller.imgChangeMascDante.setManaged(false);
        controller.imgChangeGatewayDante.setVisible(false);
        controller.imgChangeGatewayDante.setManaged(false);
        controller.imgFrequencyChange.setVisible(false);
        controller.imgFrequencyChange.setManaged(false);
        controller.imgChangeLampTimeWork.setVisible(false);
        controller.imgChangeLampTimeWork.setManaged(false);
        controller.imgChangePortNumber.setVisible(false);
        controller.imgChangePortNumber.setManaged(false);
        controller.imgOpenHBoxChangeOutletNumber.setVisible(false);
        controller.imgOpenHBoxChangeOutletNumber.setManaged(false);
        controller.imgChangeMac1.setVisible(false);
        controller.imgChangeMac1.setManaged(false);
        controller.imgChangeMac2.setVisible(false);
        controller.imgChangeMac2.setManaged(false);
        controller.imgChangeMac3.setVisible(false);
        controller.imgChangeMac3.setManaged(false);
        controller.imgChangeMac.setVisible(false);
        controller.imgChangeMac.setManaged(false);
    }


}
