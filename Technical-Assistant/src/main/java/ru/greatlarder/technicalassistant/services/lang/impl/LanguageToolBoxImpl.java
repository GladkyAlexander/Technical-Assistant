package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.LanguageToolBox;

public class LanguageToolBoxImpl implements LanguageToolBox {
    String en = "English";
    String ru = "Русский";
    
    @Override
    public String Equipment_list(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Список оборудования";
        } else if (lang.equals(en)) {
            added = "Equipment list";
        }
        return added;
    }
    
    @Override
    public String List_of_tools(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Список инструментов";
        } else if (lang.equals(en)) {
            added = "List of tools";
        }
        return added;
    }
    
    @Override
    public String IP_ADDRESS_POOL(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Пул ip адресов";
        } else if (lang.equals(en)) {
            added = "IP address pool";
        }
        return added;
    }
    
    @Override
    public String LIST_OF_DEFECT(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Список дефектов";
        } else if (lang.equals(en)) {
            added = "List of defects";
        }
        return added;
    }
    
    @Override
    public String List_of_rooms(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Список комнат";
        } else if (lang.equals(en)) {
            added = "List of rooms";
        }
        return added;
    }
    
    @Override
    public String List_of_events(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Список мероприятий";
        } else if (lang.equals(en)) {
            added = "List of events";
        }
        return added;
    }
    
    @Override
    public String Notes(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Заметки";
        } else if (lang.equals(en)) {
            added = "Notes";
        }
        return added;
    }
    
    @Override
    public String Phonebook(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Телефонная книга";
        } else if (lang.equals(en)) {
            added = "Phonebook";
        }
        return added;
    }
    
    @Override
    public String add_equipment(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Добавить оборудование";
        } else if (lang.equals(en)) {
            added = "Add a equipment";
        }
        return added;
    }
    
    @Override
    public String add_tools(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Добавить инструмент";
        } else if (lang.equals(en)) {
            added = "Add a tool";
        }
        return added;
    }
    
    @Override
    public String add_defects(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Добавить дефект";
        } else if (lang.equals(en)) {
            added = "Add a defect";
        }
        return added;
    }
    
    @Override
    public String Change_the_operating_time_of_the_projector_lamps(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Изменить время наработки ламп проектора";
        } else if (lang.equals(en)) {
            added = "Change the operating time of the projector lamps";
        }
        return added;
    }
    
    @Override
    public String Add_a_room(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Добавить комнату";
        } else if (lang.equals(en)) {
            added = "Add a room";
        }
        return added;
    }
    
    @Override
    public String Add_a_events(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Добавить мероприятие";
        } else if (lang.equals(en)) {
            added = "Add an event";
        }
        return added;
    }
    
    @Override
    public String Add_seating_arrangement(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Добавить расположение сидячих мест";
        } else if (lang.equals(en)) {
            added = "Add seating arrangement";
        }
        return added;
    }
    
    @Override
    public String ipScanner(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Ip-сканер";
        } else if (lang.equals(en)) {
            added = "Ip scanner";
        }
        return added;
    }
    
    @Override
    public String seating_arrangement(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Расположение сидячих мест";
        } else if (lang.equals(en)) {
            added = "Seating arrangement";
        }
        return added;
    }
}
