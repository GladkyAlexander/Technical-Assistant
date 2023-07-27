package ru.great_larder.technical_assistant_android.services.language;

import java.util.ArrayList;
import java.util.List;

public class LanguageImpl implements Language{
    String en = "English";
    String ru = "Русский";
    @Override
    public List<String> LIST_POST(String language) {
        List<String> listPost = new ArrayList<>();
        
        if (language.equals(en)) {
            listPost.add("Engineer");
            // listPost.add("Manager");
            // listPost.add("Head of Department");
            listPost.add("Reception Secretary");
            // listPost.add("Assistant Manager");
            //listPost.add("Installer");
            // listPost.add("IT Specialist");
            //listPost.add("Customer's representative");
            //listPost.add("Director");
            //listPost.add("Administrator");
        }
        if (language.equals(ru)) {
            listPost.add("Инженер");
            //listPost.add("Менеджер");
            //listPost.add("Начальник отдела");
            listPost.add("Секретарь приемной");
            //listPost.add("Помощник руководителя");
            //listPost.add("Монтажник");
            //listPost.add("Специалист IT");
            //listPost.add("Представитель заказчика");
            //listPost.add("Директор");
            //listPost.add("Администратор");
        }
        
        return listPost;
    }
    
    @Override
    public CharSequence Select_a_country(String language) {
        if (language.equals(en)) {
            return "Select a country";
        }
        if (language.equals(ru)) {
            return "Выберите страну";
        }
        return null;
    }
}
