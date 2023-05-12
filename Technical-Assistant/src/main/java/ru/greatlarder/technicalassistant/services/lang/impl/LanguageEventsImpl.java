package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.LanguageEvents;

import java.util.ArrayList;
import java.util.List;

public class LanguageEventsImpl implements LanguageEvents {
    String en = "English";
    String ru = "Русский";
    @Override
    public List<String> getListEvents(String lang) {
        List<String> list = new ArrayList<>();
        List<String> england = new ArrayList<>();
        england.add("The conference");
        england.add("Seminar");
        england.add("Training");
        england.add("Exhibition");
        england.add("Congress");
        england.add("Presentation");
        england.add("Reception");
        england.add("Round table");
        england.add("Meeting");
        england.add("Lunch");
        england.add("Briefing");
        
        List<String> russian = new ArrayList<>();
        russian.add("Конференция");
        russian.add("Семинар");
        russian.add("Тренинг");
        russian.add("Выставка");
        russian.add("Съезд");
        russian.add("Презентация");
        russian.add("Прием");
        russian.add("Круглый стол");
        russian.add("Встреча");
        russian.add("Ланч");
        russian.add("Брифинг");
        
        if(lang.equals(ru)) return russian;
        if(lang.equals(en)) return england;
        else return list;
    }
    
    @Override
    public String getThe_Conference(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Конференция";
        } else if (lang.equals(en)) {
            added = "The conference";
        }
        return added;
    }
    
    @Override
    public String getSeminar(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Семинар";
        } else if (lang.equals(en)) {
            added = "Seminar";
        }
        return added;
    }
    
    @Override
    public String getTraining(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Тренинг";
        } else if (lang.equals(en)) {
            added = "Training";
        }
        return added;
    }
    
    @Override
    public String getExhibition(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Выставка";
        } else if (lang.equals(en)) {
            added = "Exhibition";
        }
        return added;
    }
    
    @Override
    public String getCongress(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Съезд";
        } else if (lang.equals(en)) {
            added = "Congress";
        }
        return added;
    }
    
    @Override
    public String getPresentation(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Презентация";
        } else if (lang.equals(en)) {
            added = "Presentation";
        }
        return added;
    }
    
    @Override
    public String getReception(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Прием";
        } else if (lang.equals(en)) {
            added = "Reception";
        }
        return added;
    }
    
    @Override
    public String getRoundTable(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Круглый стол";
        } else if (lang.equals(en)) {
            added = "Round table";
        }
        return added;
    }
    
    @Override
    public String getMeeting(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Встреча";
        } else if (lang.equals(en)) {
            added = "Meeting";
        }
        return added;
    }
    
    @Override
    public String getLunch(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Ланч";
        } else if (lang.equals(en)) {
            added = "Lunch";
        }
        return added;
    }
    
    @Override
    public String getBriefing(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Брифинг";
        } else if (lang.equals(en)) {
            added = "Briefing";
        }
        return added;
    }
}
