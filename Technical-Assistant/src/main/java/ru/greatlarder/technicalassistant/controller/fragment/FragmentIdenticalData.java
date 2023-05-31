package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.Room;
import ru.greatlarder.technicalassistant.domain.equipment.*;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;
import ru.greatlarder.technicalassistant.services.lang.Language;
import ru.greatlarder.technicalassistant.services.lang.impl.LanguageImpl;
import ru.greatlarder.technicalassistant.services.style.StyleSRC;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class FragmentIdenticalData implements Initializable {
    @FXML
    public TabPane tabPaneFID;
    String lang;
    Language language = new LanguageImpl();


    public void loadFragment(HashMap<Object, Object> hashMap) {
        tabPaneFID.setStyle(StyleSRC.STYLE_ORDINARY);
        for (Map.Entry<Object, Object> e : hashMap.entrySet()) {
            if (getEquip(e.getValue()) != null) {
                Equipment equipmentKey = getEquip(e.getKey());
                Equipment equipmentValue = getEquip(e.getValue());
                tabPaneFID.getTabs().add(new Tab(equipmentKey.getName() + " : " +
                        equipmentKey.getSerialNumber(), updatePaneFrEquipment(equipmentKey, equipmentValue)));
            }
            if (e.getValue() instanceof Room roomValue) {
                Room roomKey = (Room) e.getKey();
                tabPaneFID.getTabs().add(new Tab(roomKey.getNameRoom()
                        , updatePaneFrRoom(roomKey, roomValue)));
            }
        }
    }

    private SplitPane updatePaneFrEquipment(Equipment equipment, Equipment equipment_external) {
        SplitPane pane = new SplitPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment/fragmentEquipmentOne.fxml"));

        try {
            pane.getItems().add(new ScrollPane(loader1.load()));
            FragmentEquipmentOneController fragmentEquipmentOneController1 = loader1.getController();
            fragmentEquipmentOneController1.setEquip(equipment);
            fragmentEquipmentOneController1.setBtnChangeDataStorage(language.SAVE_THIS_DATA_TO(lang) + " " + language.EXTERNAL_STORAGE(lang), pane);
            fragmentEquipmentOneController1.setLabelStorage(language.LOCAL_STORAGE(lang));
            disableButton(fragmentEquipmentOneController1);

            pane.getItems().add(new ScrollPane(loader2.load()));
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

    private SplitPane updatePaneFrRoom(Room room, Room room_external) {
        SplitPane pane = new SplitPane();
        /*FXMLLoader loader1 = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_room.fxml"));
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/ru/greatlarder/technicalassistant/layout/fragment_item/item_room.fxml"));

        try {
            pane.getItems().add(new ScrollPane(loader1.load()));
            ItemRoom itemRoom1 = loader1.getController();
            itemRoom1.setRoom(room);
            itemRoom1.labelNameRoom.setText(room.getNameRoom());

            pane.getItems().add(new ScrollPane(loader2.load()));
            ItemRoom itemRoom2 = loader2.getController();
            itemRoom2.setRoom(room_external);
            itemRoom2.labelNameRoom.setText(room_external.getNameRoom());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return pane;
    }

    private void disableButton(FragmentEquipmentOneController controller) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }

    private Equipment getEquip(Object obj) {

        if(obj instanceof AcousticSpeaker) {return (Equipment) obj;}
        else if(obj instanceof AudioAmplifier){ return (Equipment) obj;}
        else if(obj instanceof AudioInterface){ return (Equipment) obj;}
        else if(obj instanceof AudioProcessor){ return (Equipment) obj;}
        else if(obj instanceof Controller){ return (Equipment) obj;}
        else if(obj instanceof ControlProcessor){ return (Equipment) obj;}
        else if(obj instanceof Laptop){ return (Equipment) obj;}
        else if(obj instanceof MatrixSwitcher){ return (Equipment) obj;}
        else if(obj instanceof MediaPlayer){ return (Equipment) obj;}
        else if(obj instanceof Microphone){ return (Equipment) obj;}
        else if(obj instanceof NetworkSwitch){ return (Equipment) obj;}
        else if(obj instanceof Projector){ return (Equipment) obj;}
        else if(obj instanceof TouchControlPanel){ return (Equipment) obj;}
        else if(obj instanceof TvPanel){ return (Equipment) obj;}
        else if(obj instanceof TvTuner){ return (Equipment) obj;}
        else if(obj instanceof VideoReceiver){ return (Equipment) obj;}
        else if(obj instanceof VideoTransmitter){ return (Equipment) obj;}

       else return null;
    }
}
