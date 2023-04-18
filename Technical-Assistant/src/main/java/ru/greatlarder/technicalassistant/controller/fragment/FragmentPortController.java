package ru.greatlarder.technicalassistant.controller.fragment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import ru.greatlarder.technicalassistant.domain.Company;
import ru.greatlarder.technicalassistant.domain.Equipment;
import ru.greatlarder.technicalassistant.domain.equipment.NetworkSwitch;
import ru.greatlarder.technicalassistant.domain.user.User;
import ru.greatlarder.technicalassistant.services.database.GetListEquipment;
import ru.greatlarder.technicalassistant.services.database.mysql.equipment.ListEquipmentByNameCompanyMySQL;
import ru.greatlarder.technicalassistant.services.global_link.GlobalLinkMainController;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FragmentPortController implements Initializable {

    @FXML public ComboBox kb1;
    @FXML public ComboBox kb2;
    @FXML public ComboBox kb3;
    @FXML public ComboBox kb4;
    @FXML public ComboBox kb5;
    @FXML public ComboBox kb6;
    @FXML public ComboBox kb7;
    @FXML public ComboBox kb8;
    @FXML public ComboBox kb9;
    @FXML public ComboBox kb10;
    @FXML public ComboBox kb11;
    @FXML public ComboBox kb12;
    @FXML public ComboBox kb13;
    @FXML public ComboBox kb14;
    @FXML public ComboBox kb15;
    @FXML public ComboBox kb16;
    @FXML public ComboBox kb17;
    @FXML public ComboBox kb18;
    @FXML public ComboBox kb19;
    @FXML public ComboBox kb20;
    @FXML public ComboBox kb21;
    @FXML public ComboBox kb23;
    @FXML public ComboBox kb22;
    @FXML public ComboBox kb25;
    @FXML public ComboBox kb26;
    @FXML public ComboBox kb27;
    @FXML public ComboBox kb28;
    @FXML public ComboBox kb29;
    @FXML public ComboBox kb30;
    @FXML public ComboBox kb31;
    @FXML public ComboBox kb32;
    @FXML public ComboBox kb33;
    @FXML public ComboBox kb34;
    @FXML public ComboBox kb36;
    @FXML public ComboBox kb35;
    @FXML public ComboBox kb37;
    @FXML public ComboBox kb38;
    @FXML public ComboBox kb39;
    @FXML public ComboBox kb40;
    @FXML public ComboBox kb41;
    @FXML public ComboBox kb42;
    @FXML public ComboBox kb43;
    @FXML public ComboBox kb44;
    @FXML public ComboBox kb45;
    @FXML public ComboBox kb46;
    @FXML public ComboBox kb47;
    @FXML public ComboBox kb24;
    @FXML public ComboBox kb48;
    @FXML public Button btnSave;
    @FXML public Button btnChange;
    NetworkSwitch networkSwitch;
    User user;
    Company company;
    String lang;

    public void setPort(NetworkSwitch networkSwitch){
        disable(true);
        this.networkSwitch = networkSwitch;
        loadPort();
    }
    private void loadPort(){
        if(networkSwitch.getPort1() != null){kb1.setPromptText(networkSwitch.getPort1().getSerialNumber());}
        if(networkSwitch.getPort2() != null){kb2.setPromptText(networkSwitch.getPort2().getSerialNumber());}
        if(networkSwitch.getPort3() != null){kb3.setPromptText(networkSwitch.getPort3().getSerialNumber());}
        if(networkSwitch.getPort4() != null){kb4.setPromptText(networkSwitch.getPort4().getSerialNumber());}
        if(networkSwitch.getPort5() != null){kb5.setPromptText(networkSwitch.getPort5().getSerialNumber());}
        if(networkSwitch.getPort6() != null){kb6.setPromptText(networkSwitch.getPort6().getSerialNumber());}
        if(networkSwitch.getPort7() != null){kb7.setPromptText(networkSwitch.getPort7().getSerialNumber());}
        if(networkSwitch.getPort8() != null){kb8.setPromptText(networkSwitch.getPort8().getSerialNumber());}
        if(networkSwitch.getPort9() != null){kb9.setPromptText(networkSwitch.getPort9().getSerialNumber());}
        if(networkSwitch.getPort10() != null){kb10.setPromptText(networkSwitch.getPort10().getSerialNumber());}
        if(networkSwitch.getPort11() != null){kb11.setPromptText(networkSwitch.getPort11().getSerialNumber());}
        if(networkSwitch.getPort12() != null){kb12.setPromptText(networkSwitch.getPort12().getSerialNumber());}
        if(networkSwitch.getPort13() != null){kb13.setPromptText(networkSwitch.getPort13().getSerialNumber());}
        if(networkSwitch.getPort14() != null){kb14.setPromptText(networkSwitch.getPort14().getSerialNumber());}
        if(networkSwitch.getPort15() != null){kb15.setPromptText(networkSwitch.getPort15().getSerialNumber());}
        if(networkSwitch.getPort16() != null){kb16.setPromptText(networkSwitch.getPort16().getSerialNumber());}
        if(networkSwitch.getPort17() != null){kb17.setPromptText(networkSwitch.getPort17().getSerialNumber());}
        if(networkSwitch.getPort18() != null){kb18.setPromptText(networkSwitch.getPort18().getSerialNumber());}
        if(networkSwitch.getPort19() != null){kb19.setPromptText(networkSwitch.getPort19().getSerialNumber());}
        if(networkSwitch.getPort20() != null){kb20.setPromptText(networkSwitch.getPort20().getSerialNumber());}
        if(networkSwitch.getPort21() != null){kb21.setPromptText(networkSwitch.getPort21().getSerialNumber());}
        if(networkSwitch.getPort22() != null){kb22.setPromptText(networkSwitch.getPort22().getSerialNumber());}
        if(networkSwitch.getPort23() != null){kb23.setPromptText(networkSwitch.getPort23().getSerialNumber());}
        if(networkSwitch.getPort24() != null){kb24.setPromptText(networkSwitch.getPort24().getSerialNumber());}
        if(networkSwitch.getPort25() != null){kb25.setPromptText(networkSwitch.getPort25().getSerialNumber());}
        if(networkSwitch.getPort26() != null){kb26.setPromptText(networkSwitch.getPort26().getSerialNumber());}
        if(networkSwitch.getPort27() != null){kb27.setPromptText(networkSwitch.getPort27().getSerialNumber());}
        if(networkSwitch.getPort28() != null){kb28.setPromptText(networkSwitch.getPort28().getSerialNumber());}
        if(networkSwitch.getPort29() != null){kb29.setPromptText(networkSwitch.getPort29().getSerialNumber());}
        if(networkSwitch.getPort30() != null){kb30.setPromptText(networkSwitch.getPort30().getSerialNumber());}
        if(networkSwitch.getPort31() != null){kb31.setPromptText(networkSwitch.getPort31().getSerialNumber());}
        if(networkSwitch.getPort32() != null){kb32.setPromptText(networkSwitch.getPort32().getSerialNumber());}
        if(networkSwitch.getPort33() != null){kb33.setPromptText(networkSwitch.getPort33().getSerialNumber());}
        if(networkSwitch.getPort34() != null){kb34.setPromptText(networkSwitch.getPort34().getSerialNumber());}
        if(networkSwitch.getPort35() != null){kb35.setPromptText(networkSwitch.getPort35().getSerialNumber());}
        if(networkSwitch.getPort36() != null){kb36.setPromptText(networkSwitch.getPort36().getSerialNumber());}
        if(networkSwitch.getPort37() != null){kb37.setPromptText(networkSwitch.getPort37().getSerialNumber());}
        if(networkSwitch.getPort38() != null){kb38.setPromptText(networkSwitch.getPort38().getSerialNumber());}
        if(networkSwitch.getPort39() != null){kb39.setPromptText(networkSwitch.getPort39().getSerialNumber());}
        if(networkSwitch.getPort40() != null){kb40.setPromptText(networkSwitch.getPort40().getSerialNumber());}
        if(networkSwitch.getPort41() != null){kb41.setPromptText(networkSwitch.getPort41().getSerialNumber());}
        if(networkSwitch.getPort42() != null){kb42.setPromptText(networkSwitch.getPort42().getSerialNumber());}
        if(networkSwitch.getPort43() != null){kb43.setPromptText(networkSwitch.getPort43().getSerialNumber());}
        if(networkSwitch.getPort44() != null){kb44.setPromptText(networkSwitch.getPort44().getSerialNumber());}
        if(networkSwitch.getPort45() != null){kb45.setPromptText(networkSwitch.getPort45().getSerialNumber());}
        if(networkSwitch.getPort46() != null){kb46.setPromptText(networkSwitch.getPort46().getSerialNumber());}
        if(networkSwitch.getPort47() != null){kb47.setPromptText(networkSwitch.getPort47().getSerialNumber());}
        if(networkSwitch.getPort48() != null){kb48.setPromptText(networkSwitch.getPort48().getSerialNumber());}
    }

    private void disable(Boolean tr){
        for (ComboBox comboBox : getListComboBox()){
            comboBox.setDisable(tr);
        }
    }

    private List <ComboBox> getListComboBox(){
        List <ComboBox> comboBoxList = new ArrayList<>();
        comboBoxList.add(kb1);comboBoxList.add(kb2);comboBoxList.add(kb3);comboBoxList.add(kb4);comboBoxList.add(kb5);
        comboBoxList.add(kb6);comboBoxList.add(kb7);comboBoxList.add(kb8);comboBoxList.add(kb9);comboBoxList.add(kb10);
        comboBoxList.add(kb11);comboBoxList.add(kb12);comboBoxList.add(kb13);comboBoxList.add(kb14);comboBoxList.add(kb15);
        comboBoxList.add(kb16);comboBoxList.add(kb17);comboBoxList.add(kb18);comboBoxList.add(kb19);comboBoxList.add(kb20);
        comboBoxList.add(kb21);comboBoxList.add(kb22);comboBoxList.add(kb23);comboBoxList.add(kb24);comboBoxList.add(kb25);
        comboBoxList.add(kb26);comboBoxList.add(kb27);comboBoxList.add(kb28);comboBoxList.add(kb29);comboBoxList.add(kb30);
        comboBoxList.add(kb31);comboBoxList.add(kb32);comboBoxList.add(kb33);comboBoxList.add(kb34);comboBoxList.add(kb35);
        comboBoxList.add(kb36);comboBoxList.add(kb37);comboBoxList.add(kb38);comboBoxList.add(kb39);comboBoxList.add(kb40);
        comboBoxList.add(kb41);comboBoxList.add(kb42);comboBoxList.add(kb43);comboBoxList.add(kb44);comboBoxList.add(kb45);
        comboBoxList.add(kb46);comboBoxList.add(kb47);comboBoxList.add(kb48);
        return comboBoxList;
    }

    public void change(ActionEvent actionEvent) {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        disable(false);
        GetListEquipment getListEquipment = new ListEquipmentByNameCompanyMySQL();
        for (Equipment equipment : getListEquipment.getListEquipment(user, company.getNameCompany(), null)){
            observableList.add(equipment.getSerialNumber());
        }

        for (ComboBox s : getListComboBox()){
            s.setItems(observableList);
        }
    }

    public void save(ActionEvent actionEvent) {
    }

    public void actionKB48(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port48", kb48.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb48.getValue().toString()).getId()
                , "portNumberInTheSwitch", 48);*/
    }

    public void actionKB24(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port24", kb24.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb24.getValue().toString()).getId()
                , "portNumberInTheSwitch", 24);*/
    }

    public void actionKB47(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port47", kb47.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb47.getValue().toString()).getId()
                , "portNumberInTheSwitch", 47);*/
    }

    public void actionKB46(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port46", kb46.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb46.getValue().toString()).getId()
                , "portNumberInTheSwitch", 46);*/
    }

    public void actionKB45(ActionEvent actionEvent) {
        /*equipmentRepository.change(networkSwitch.getId(), "Port45", kb45.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb45.getValue().toString()).getId()
                , "portNumberInTheSwitch", 45);*/
    }

    public void actionKB44(ActionEvent actionEvent) {
        /*equipmentRepository.change(networkSwitch.getId(), "Port44", kb44.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb44.getValue().toString()).getId()
                , "portNumberInTheSwitch", 44);*/
    }

    public void actionKB43(ActionEvent actionEvent) {
        /*equipmentRepository.change(networkSwitch.getId(), "Port43", kb43.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb43.getValue().toString()).getId()
                , "portNumberInTheSwitch", 43);*/
    }

    public void actionKB42(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port42", kb42.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb42.getValue().toString()).getId()
                , "portNumberInTheSwitch", 42);*/
    }

    public void actionKB41(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port41", kb41.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb41.getValue().toString()).getId()
                , "portNumberInTheSwitch", 41);*/
    }

    public void actionKB40(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port40", kb40.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb40.getValue().toString()).getId()
                , "portNumberInTheSwitch", 40);*/
    }

    public void actionKB39(ActionEvent actionEvent) {
        /*equipmentRepository.change(networkSwitch.getId(), "Port39", kb39.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb39.getValue().toString()).getId()
                , "portNumberInTheSwitch", 39);*/
    }

    public void actionKB38(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port38", kb38.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb38.getValue().toString()).getId()
                , "portNumberInTheSwitch", 38);*/
    }

    public void actionKB37(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port37", kb37.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb37.getValue().toString()).getId()
                , "portNumberInTheSwitch", 37);*/
    }

    public void actionKB36(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port36", kb36.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb36.getValue().toString()).getId()
                , "portNumberInTheSwitch", 36);*/
    }

    public void actionKB35(ActionEvent actionEvent) {
   /*     equipmentRepository.change(networkSwitch.getId(), "Port35", kb35.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb35.getValue().toString()).getId()
                , "portNumberInTheSwitch", 35);*/
    }

    public void actionKB34(ActionEvent actionEvent) {
 /*       equipmentRepository.change(networkSwitch.getId(), "Port34", kb34.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb34.getValue().toString()).getId()
                , "portNumberInTheSwitch", 34);*/
    }

    public void actionKB33(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port33", kb33.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb33.getValue().toString()).getId()
                , "portNumberInTheSwitch", 33);*/
    }

    public void actionKB32(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port32", kb32.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb32.getValue().toString()).getId()
                , "portNumberInTheSwitch", 32);*/
    }

    public void actionKB31(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port31", kb31.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb31.getValue().toString()).getId()
                , "portNumberInTheSwitch", 31);*/
    }

    public void actionKB30(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port30", kb30.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb30.getValue().toString()).getId()
                , "portNumberInTheSwitch", 30);*/
    }

    public void actionKB29(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port29", kb29.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb29.getValue().toString()).getId()
                , "portNumberInTheSwitch", 29);*/
    }

    public void actionKB28(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port28", kb28.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb28.getValue().toString()).getId()
                , "portNumberInTheSwitch", 28);*/
    }

    public void actionKB27(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port27", kb27.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb27.getValue().toString()).getId()
                , "portNumberInTheSwitch", 27);*/
    }

    public void actionKB26(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port26", kb26.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb26.getValue().toString()).getId()
                , "portNumberInTheSwitch", 26);*/
    }

    public void actionKB25(ActionEvent actionEvent) {
        /*equipmentRepository.change(networkSwitch.getId(), "Port25", kb25.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb25.getValue().toString()).getId()
                , "portNumberInTheSwitch", 25);*/
    }

    public void actionKB23(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port23", kb23.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb23.getValue().toString()).getId()
                , "portNumberInTheSwitch", 23);*/
    }

    public void actionKB22(ActionEvent actionEvent) {
    /*    equipmentRepository.change(networkSwitch.getId(), "Port22", kb22.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb22.getValue().toString()).getId()
                , "portNumberInTheSwitch", 22);*/
    }

    public void actionKB21(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port21", kb21.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb21.getValue().toString()).getId()
                , "portNumberInTheSwitch", 21);*/
    }

    public void actionKB20(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port20", kb20.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb20.getValue().toString()).getId()
                , "portNumberInTheSwitch", 20);*/
    }

    public void actionKB19(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port19", kb19.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb19.getValue().toString()).getId()
                , "portNumberInTheSwitch", 19);*/
    }

    public void actionKB18(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port18", kb18.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb18.getValue().toString()).getId()
                , "portNumberInTheSwitch", 18);*/
    }

    public void actionKB17(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port17", kb17.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb17.getValue().toString()).getId()
                , "portNumberInTheSwitch", 17);*/
    }

    public void actionKB16(ActionEvent actionEvent) {
 /*       equipmentRepository.change(networkSwitch.getId(), "Port16", kb16.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb16.getValue().toString()).getId()
                , "portNumberInTheSwitch", 16);*/
    }

    public void actionKB15(ActionEvent actionEvent) {
    /*    equipmentRepository.change(networkSwitch.getId(), "Port15", kb15.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb15.getValue().toString()).getId()
                , "portNumberInTheSwitch", 15);*/
    }

    public void actionKB14(ActionEvent actionEvent) {
  /*      equipmentRepository.change(networkSwitch.getId(), "Port14", kb14.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb14.getValue().toString()).getId()
                , "portNumberInTheSwitch", 14);*/
    }

    public void actionKB13(ActionEvent actionEvent) {
/*        equipmentRepository.change(networkSwitch.getId(), "Port13", kb13.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb13.getValue().toString()).getId()
                , "portNumberInTheSwitch", 13);*/
    }

    public void actionKB12(ActionEvent actionEvent) {
/*        equipmentRepository.change(networkSwitch.getId(), "Port12", kb12.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb12.getValue().toString()).getId()
                , "portNumberInTheSwitch", 12);*/
    }

    public void actionKB11(ActionEvent actionEvent) {
   /*     equipmentRepository.change(networkSwitch.getId(), "Port11", kb11.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb11.getValue().toString()).getId()
                , "portNumberInTheSwitch", 11);*/
    }

    public void actionKB10(ActionEvent actionEvent) {
  /*      equipmentRepository.change(networkSwitch.getId(), "Port10", kb10.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb10.getValue().toString()).getId()
                , "portNumberInTheSwitch", 10);*/
    }

    public void actionKB9(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port9", kb9.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb9.getValue().toString()).getId()
                , "portNumberInTheSwitch", 9);*/
    }

    public void actionKB8(ActionEvent actionEvent) {
    /*    equipmentRepository.change(networkSwitch.getId(), "Port8", kb8.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb8.getValue().toString()).getId()
                , "portNumberInTheSwitch", 8);*/
    }

    public void actionKB7(ActionEvent actionEvent) {
      /*  equipmentRepository.change(networkSwitch.getId(), "Port7", kb7.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb7.getValue().toString()).getId()
                , "portNumberInTheSwitch", 7);*/
    }

    public void actionKB6(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port6", kb6.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb6.getValue().toString()).getId()
                , "portNumberInTheSwitch", 6);*/
    }

    public void actionKB3(ActionEvent actionEvent) {
    /*    equipmentRepository.change(networkSwitch.getId(), "Port3", kb3.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb3.getValue().toString()).getId()
                , "portNumberInTheSwitch", 3);*/
    }

    public void actionKB4(ActionEvent actionEvent) {
    /*    equipmentRepository.change(networkSwitch.getId(), "Port4", kb4.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb4.getValue().toString()).getId()
                , "portNumberInTheSwitch", 4);*/
    }

    public void actionKB2(ActionEvent actionEvent) {
  /*      equipmentRepository.change(networkSwitch.getId(), "Port2", kb2.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb2.getValue().toString()).getId()
                , "portNumberInTheSwitch", 2);*/
    }

    public void actionKB1(ActionEvent actionEvent) {
     /*   equipmentRepository.change(networkSwitch.getId(), "Port1", kb1.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb1.getValue().toString()).getId()
                , "portNumberInTheSwitch", 1);*/
    }

    public void actionKB5(ActionEvent actionEvent) {
       /* equipmentRepository.change(networkSwitch.getId(), "Port5", kb5.getValue().toString());
        equipmentRepository.change(equipmentRepository.getEquipmentBySerialNumber(networkSwitch.getCompany(), kb5.getValue().toString()).getId()
                , "portNumberInTheSwitch", 5);*/
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.user = GlobalLinkMainController.getMainController().getUser();
        this.company = GlobalLinkMainController.getMainController().getCompany();
        this.lang = GlobalLinkMainController.getMainController().getLang();
    }
}
