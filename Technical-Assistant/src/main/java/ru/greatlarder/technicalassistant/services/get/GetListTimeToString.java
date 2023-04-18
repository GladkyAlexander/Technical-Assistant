package ru.greatlarder.technicalassistant.services.get;

import ru.greatlarder.technicalassistant.domain.Day;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GetListTimeToString {
    public List<String> getListTimeToString(Day dayIn, LocalDateTime timeStart){
        TreeMap<LocalDateTime, Integer> treeMap = new TreeMap<>();

        treeMap.put(LocalDateTime.now().withHour(8).withMinute(0), dayIn.getA800());
        treeMap.put(LocalDateTime.now().withHour(8).withMinute(15), dayIn.getA815());
        treeMap.put(LocalDateTime.now().withHour(8).withMinute(30), dayIn.getA830());
        treeMap.put(LocalDateTime.now().withHour(8).withMinute(45), dayIn.getA845());
        treeMap.put(LocalDateTime.now().withHour(9).withMinute(0), dayIn.getA900());
        treeMap.put(LocalDateTime.now().withHour(9).withMinute(15), dayIn.getA915());
        treeMap.put(LocalDateTime.now().withHour(9).withMinute(30), dayIn.getA930());
        treeMap.put(LocalDateTime.now().withHour(9).withMinute(45), dayIn.getA945());
        treeMap.put(LocalDateTime.now().withHour(10).withMinute(0), dayIn.getA1000());
        treeMap.put(LocalDateTime.now().withHour(10).withMinute(15), dayIn.getA1015());
        treeMap.put(LocalDateTime.now().withHour(10).withMinute(30), dayIn.getA1030());
        treeMap.put(LocalDateTime.now().withHour(10).withMinute(45), dayIn.getA1045());
        treeMap.put(LocalDateTime.now().withHour(11).withMinute(0), dayIn.getA1100());
        treeMap.put(LocalDateTime.now().withHour(11).withMinute(15), dayIn.getA1115());
        treeMap.put(LocalDateTime.now().withHour(11).withMinute(30), dayIn.getA1130());
        treeMap.put(LocalDateTime.now().withHour(11).withMinute(45), dayIn.getA1145());
        treeMap.put(LocalDateTime.now().withHour(12).withMinute(0), dayIn.getA1200());
        treeMap.put(LocalDateTime.now().withHour(12).withMinute(15), dayIn.getA1215());
        treeMap.put(LocalDateTime.now().withHour(12).withMinute(30), dayIn.getA1230());
        treeMap.put(LocalDateTime.now().withHour(12).withMinute(45), dayIn.getA1245());
        treeMap.put(LocalDateTime.now().withHour(13).withMinute(0), dayIn.getA1300());
        treeMap.put(LocalDateTime.now().withHour(13).withMinute(15), dayIn.getA1315());
        treeMap.put(LocalDateTime.now().withHour(13).withMinute(30), dayIn.getA1330());
        treeMap.put(LocalDateTime.now().withHour(13).withMinute(45), dayIn.getA1345());
        treeMap.put(LocalDateTime.now().withHour(14).withMinute(0), dayIn.getA1400());
        treeMap.put(LocalDateTime.now().withHour(14).withMinute(15), dayIn.getA1415());
        treeMap.put(LocalDateTime.now().withHour(14).withMinute(30), dayIn.getA1430());
        treeMap.put(LocalDateTime.now().withHour(14).withMinute(45), dayIn.getA1445());
        treeMap.put(LocalDateTime.now().withHour(15).withMinute(0), dayIn.getA1500());
        treeMap.put(LocalDateTime.now().withHour(15).withMinute(15), dayIn.getA1515());
        treeMap.put(LocalDateTime.now().withHour(15).withMinute(30), dayIn.getA1530());
        treeMap.put(LocalDateTime.now().withHour(15).withMinute(45), dayIn.getA1545());
        treeMap.put(LocalDateTime.now().withHour(16).withMinute(0), dayIn.getA1600());
        treeMap.put(LocalDateTime.now().withHour(16).withMinute(15), dayIn.getA1615());
        treeMap.put(LocalDateTime.now().withHour(16).withMinute(30), dayIn.getA1630());
        treeMap.put(LocalDateTime.now().withHour(16).withMinute(45), dayIn.getA1645());
        treeMap.put(LocalDateTime.now().withHour(17).withMinute(0), dayIn.getA1700());
        treeMap.put(LocalDateTime.now().withHour(17).withMinute(15), dayIn.getA1715());
        treeMap.put(LocalDateTime.now().withHour(17).withMinute(30), dayIn.getA1730());
        treeMap.put(LocalDateTime.now().withHour(17).withMinute(45), dayIn.getA1745());
        treeMap.put(LocalDateTime.now().withHour(18).withMinute(0), dayIn.getA1800());
        treeMap.put(LocalDateTime.now().withHour(18).withMinute(15), dayIn.getA1815());
        treeMap.put(LocalDateTime.now().withHour(18).withMinute(30), dayIn.getA1830());
        treeMap.put(LocalDateTime.now().withHour(18).withMinute(45), dayIn.getA1845());
        treeMap.put(LocalDateTime.now().withHour(19).withMinute(0), dayIn.getA1900());
        treeMap.put(LocalDateTime.now().withHour(19).withMinute(15), dayIn.getA1915());
        treeMap.put(LocalDateTime.now().withHour(19).withMinute(30), dayIn.getA1930());
        treeMap.put(LocalDateTime.now().withHour(19).withMinute(45), dayIn.getA1945());
        treeMap.put(LocalDateTime.now().withHour(20).withMinute(0), dayIn.getA2000());

        List<String> retList = new ArrayList<>();

        for (Map.Entry<LocalDateTime, Integer> entry : treeMap.entrySet()){

            if (entry.getKey().equals(timeStart) || entry.getKey().isAfter(timeStart)){
                if(entry.getValue() == 0){
                    if(entry.getKey().getMinute() == 0){
                        retList.add(entry.getKey().getHour() + ":00");
                    } else {
                        retList.add(entry.getKey().getHour() + ":" + entry.getKey().getMinute());
                    }
                } else break;
            }
        }
        return retList;
    }
}
