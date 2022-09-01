package ru.greatlarder.technicalassistant.services;


import ru.greatlarder.technicalassistant.domain.Equipment;

import java.util.ArrayList;
import java.util.List;

public class ListOfIpAddressForTheCompany {

    private Integer network1;
    private Integer network2;
    private Integer subnet;
    private Integer device;
    List<Equipment> equipmentList;

    public ListOfIpAddressForTheCompany(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<String> receiveListAddress(){
        List<String> stringList = new ArrayList<>();
        for (Equipment equipment : equipmentList){
                if (equipment.getIpAddress() != null &&
                        !stringList.stream().anyMatch(h-> tr(h).equals(tr(equipment.getIpAddress())))){
                    stringList.add(equipment.getIpAddress());
                }
        }

        List<String> strings = new ArrayList<>();
        for (String b : stringList){
            strings.addAll(readyListAddress(b));
        }

       return strings;
    }
    public List<String> receiveListAddressDante(){
        List<String> stringList = new ArrayList<>();
        for (Equipment equipment : equipmentList){
            if (equipment.getDanteIpAddress() != null &&
                    !stringList.stream().anyMatch(h-> tr(h).equals(tr(equipment.getDanteIpAddress())))){
                stringList.add(equipment.getDanteIpAddress());
            }
        }

        List<String> strings = new ArrayList<>();
        for (String b : stringList){
            strings.addAll(readyListAddress(b));
        }

        return strings;
    }
    private String tr(String s){
        String[] net = s.split("\\.");
        List<Integer> list = new ArrayList<>();
        for (String d : net){
            list.add(Integer.valueOf(d));
        }
        String network1 = list.get(0).toString();
        String network2 = list.get(1).toString();
        String subnet = list.get(2).toString();
        String device = list.get(3).toString();

        String r = network1+"."+network2+"."+subnet;
        return r;
    }

    private List<String> readyListAddress(String s){
        ipAddress(s);
        List<String> listIpAddress = new ArrayList<>();
        int size = 257;
        int [] line = new int[size];
        for(int i = 0; i < size; i++){
            listIpAddress.add(network1 + "." + network2 + "." + subnet + "." + (line[i] = i));
        }

        return listIpAddress;
    }
    private void ipAddress(String ip){
        String[] net = ip.split("\\.");
        List<Integer> list = new ArrayList<>();
        for (String s : net){
            list.add(Integer.valueOf(s));
        }
        network1 = list.get(0);
        network2 = list.get(1);
        subnet = list.get(2);
        device = list.get(3);

    }
}
