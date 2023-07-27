package ru.great_larder.technical_assistant_android.services.language;

import java.util.List;

public interface Language {
    List<String> LIST_POST(String language);
    
    CharSequence Select_a_country(String language);
}
