package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.LanguageArrangements;

import java.util.ArrayList;
import java.util.List;

public class LanguageArrangementsImpl implements LanguageArrangements {
    
    String en = "English";
    String ru = "Русский";
    @Override
    public List<String> getListNameArrangements(String lang) {
        List<String> list = new ArrayList<>();
        List<String> england = new ArrayList<>();
        england.add("Circle");
        england.add("Chevron");
        england.add("Banquet");
        england.add("Seminar");
        england.add("Classroom");
        england.add("Boardroom");
        england.add("Oval boardroom");
        england.add("Crescent rounds(cabaret)");
        england.add("E Shape");
        england.add("U Shape");
        england.add("V Shape");
        england.add("T Shape");
        england.add("Hollow rectangle");
        england.add("Octagon meeting");
        england.add("Theatre / Auditorium");
        england.add("Horseshoe");
        england.add("Columns");
        
        List<String> russian = new ArrayList<>();
        russian.add("Круг");
        russian.add("Шеврон");
        russian.add("Банкет");
        russian.add("Семинар");
        russian.add("Класс");
        russian.add("Зал заседаний");
        russian.add("Овальный зал заседаний");
        russian.add("Раунды полумесяца(Кабаре)");
        russian.add("E-образная форма");
        russian.add("U-образная форма");
        russian.add("V-образная форма");
        russian.add("T-образная форма");
        russian.add("Полый прямоугольник");
        russian.add("Восьмиугольник");
        russian.add("Театр / Зрительный зал");
        russian.add("Подкова");
        russian.add("Столбцы");
        
        if(lang.equals(ru)) return russian;
        if(lang.equals(en)) return england;
        else return list;
    }
    
    @Override
    public String getCircle(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Круг";
        } else if (lang.equals(en)) {
            added = "Circle";
        }
        return added;
    }
    
    @Override
    public String getChevron(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Шеврон";
        } else if (lang.equals(en)) {
            added = "Chevron";
        }
        return added;
    }
    
    @Override
    public String getBanquet(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Банкет";
        } else if (lang.equals(en)) {
            added = "Banquet";
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
    public String getClassRoom(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Класс";
        } else if (lang.equals(en)) {
            added = "ClassRoom";
        }
        return added;
    }
    
    @Override
    public String getBoardRoom(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Зал заседаний";
        } else if (lang.equals(en)) {
            added = "BoardRoom";
        }
        return added;
    }
    
    @Override
    public String getOvalBoardRoom(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Овальный зал заседаний";
        } else if (lang.equals(en)) {
            added = "Oval BoardRoom";
        }
        return added;
    }
    
    @Override
    public String getCrescentRoundsCabaret(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Раунды полумесяца(Кабаре)";
        } else if (lang.equals(en)) {
            added = "Crescent rounds(cabaret)";
        }
        return added;
    }
    
    @Override
    public String getEShape(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "E-образная форма";
        } else if (lang.equals(en)) {
            added = "E Shape";
        }
        return added;
    }
    
    @Override
    public String getUShape(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "U-образная форма";
        } else if (lang.equals(en)) {
            added = "U Shape";
        }
        return added;
    }
    
    @Override
    public String getVShape(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "V-образная форма";
        } else if (lang.equals(en)) {
            added = "V Shape";
        }
        return added;
    }
    
    @Override
    public String getTShape(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "T-образная форма";
        } else if (lang.equals(en)) {
            added = "T Shape";
        }
        return added;
    }
    
    @Override
    public String getHollowRectangle(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Полый прямоугольник";
        } else if (lang.equals(en)) {
            added = "Hollow rectangle";
        }
        return added;
    }
    
    @Override
    public String getOctagonMeeting(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Восьмиугольник";
        } else if (lang.equals(en)) {
            added = "Octagon meeting";
        }
        return added;
    }
    
    @Override
    public String getTheatreAuditorium(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Театр / Зрительный зал";
        } else if (lang.equals(en)) {
            added = "Theatre / Auditorium";
        }
        return added;
    }
    
    @Override
    public String getHorseshoe(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Подкова";
        } else if (lang.equals(en)) {
            added = "Horseshoe";
        }
        return added;
    }
    
    @Override
    public String getColumns(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Столбцы";
        } else if (lang.equals(en)) {
            added = "Columns";
        }
        return added;
    }
}
