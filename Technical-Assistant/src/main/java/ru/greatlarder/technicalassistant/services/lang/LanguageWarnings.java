package ru.greatlarder.technicalassistant.services.lang;

public interface LanguageWarnings {
    String THE_PICTURE_SHOULD_BE_NO_MORE_THAN_483_KB(String lang);
    String INCORRECT_IMAGE(String lang);
    String CHOOSE_A_COMPANY(String lang);
    
    String CONNECTION_ERROR(String lang);
    
    String Error_when_adding_to_the_database(String lang);
    
    String Error_when_adding_to_the_database_SQLite(String lang);
    
    String Error_when_adding_to_the_database_MySQL(String lang);
}
