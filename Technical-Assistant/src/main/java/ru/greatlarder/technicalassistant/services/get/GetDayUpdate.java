package ru.greatlarder.technicalassistant.services.get;

import ru.greatlarder.technicalassistant.domain.Day;
import ru.greatlarder.technicalassistant.domain.EventFormat;

import java.util.ArrayList;
import java.util.List;

public class GetDayUpdate {
    public Day getUpdateDay(EventFormat events, Day current, String time){
        Day nDey = current;
        List<String> gapSheet = new ArrayList<>();
        GetListTimeToString getListTimeToString = new GetListTimeToString();
        GetLocDatTime getLocDatTime = new GetLocDatTime();
        for (String s : getListTimeToString.getListTimeToString(current, getLocDatTime.getLocDatTime(time))){
            if(s.equals(events.getEndTimeOfTheEvent())){
                gapSheet.add(s);
                break;
            }
            gapSheet.add(s);
        }
        for (String s : gapSheet){
            getDayUpdate(nDey, s, events.getId());
        }
        return nDey;
    }
    private void getDayUpdate(Day d, String s, int idEvent){

        if(s.equals("8:00")){d.setA800(idEvent);}
        if(s.equals("8:15")){d.setA815(idEvent);}
        if(s.equals("8:30")){d.setA830(idEvent);}
        if(s.equals("8:45")){d.setA845(idEvent);}
        if(s.equals("9:00")){d.setA900(idEvent);}
        if(s.equals("9:15")){d.setA915(idEvent);}
        if(s.equals("9:30")){d.setA930(idEvent);}
        if(s.equals("9:45")){d.setA945(idEvent);}
        if(s.equals("10:00")){d.setA1000(idEvent);}
        if(s.equals("10:15")){d.setA1015(idEvent);}
        if(s.equals("10:30")){d.setA1030(idEvent);}
        if(s.equals("10:45")){d.setA1045(idEvent);}
        if(s.equals("11:00")){d.setA1100(idEvent);}
        if(s.equals("11:15")){d.setA1115(idEvent);}
        if(s.equals("11:30")){d.setA1130(idEvent);}
        if(s.equals("11:45")){d.setA1145(idEvent);}
        if(s.equals("12:00")){d.setA1200(idEvent);}
        if(s.equals("12:15")){d.setA1215(idEvent);}
        if(s.equals("12:30")){d.setA1230(idEvent);}
        if(s.equals("12:45")){d.setA1245(idEvent);}
        if(s.equals("13:00")){d.setA1300(idEvent);}
        if(s.equals("13:15")){d.setA1315(idEvent);}
        if(s.equals("13:30")){d.setA1330(idEvent);}
        if(s.equals("13:45")){d.setA1345(idEvent);}
        if(s.equals("14:00")){d.setA1400(idEvent);}
        if(s.equals("14:15")){d.setA1415(idEvent);}
        if(s.equals("14:30")){d.setA1430(idEvent);}
        if(s.equals("14:45")){d.setA1445(idEvent);}
        if(s.equals("15:00")){d.setA1500(idEvent);}
        if(s.equals("15:15")){d.setA1515(idEvent);}
        if(s.equals("15:30")){d.setA1530(idEvent);}
        if(s.equals("15:45")){d.setA1545(idEvent);}
        if(s.equals("16:00")){d.setA1600(idEvent);}
        if(s.equals("16:15")){d.setA1615(idEvent);}
        if(s.equals("16:30")){d.setA1630(idEvent);}
        if(s.equals("16:45")){d.setA1645(idEvent);}
        if(s.equals("17:00")){d.setA1700(idEvent);}
        if(s.equals("17:15")){d.setA1715(idEvent);}
        if(s.equals("17:30")){d.setA1730(idEvent);}
        if(s.equals("17:45")){d.setA1745(idEvent);}
        if(s.equals("18:00")){d.setA1800(idEvent);}
        if(s.equals("18:15")){d.setA1815(idEvent);}
        if(s.equals("18:30")){d.setA1830(idEvent);}
        if(s.equals("18:45")){d.setA1845(idEvent);}
        if(s.equals("19:00")){d.setA1900(idEvent);}
        if(s.equals("19:15")){d.setA1915(idEvent);}
        if(s.equals("19:30")){d.setA1930(idEvent);}
        if(s.equals("19:45")){d.setA1945(idEvent);}
        if(s.equals("20:00")){d.setA2000(idEvent);}
    }
}
