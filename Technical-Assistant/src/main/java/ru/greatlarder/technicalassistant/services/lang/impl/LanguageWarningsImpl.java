package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.LanguageWarnings;

public class LanguageWarningsImpl implements LanguageWarnings {
    String en = "English";
    String ru = "Русский";
    @Override
    public String THE_PICTURE_SHOULD_BE_NO_MORE_THAN_483_KB(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Картинка должна быть не больше 483 KB!";
        } else if (lang.equals(en)) {
            added = "The picture should be no more than 483 KB!";
        }
        return added;
    }
    
    @Override
    public String INCORRECT_IMAGE(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Неверное изображение";
        } else if (lang.equals(en)) {
            added = "Incorrect image";
        }
        return added;
    }
    
    @Override
    public String CHOOSE_A_COMPANY(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Выберите компанию";
        } else if (lang.equals(en)) {
            added = "Choose a company";
        }
        return added;
    }
    
    @Override
    public String CONNECTION_ERROR(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Ошибка соединения";
        } else if (lang.equals(en)) {
            added = "Connection error";
        }
        return added;
    }
    
    @Override
    public String Error_when_adding_to_the_database(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Ошибка при добавлении в базу данных";
        } else if (lang.equals(en)) {
            added = "Error when adding to the database";
        }
        return added;
    }
    
    @Override
    public String Error_when_adding_to_the_database_SQLite(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Ошибка при добавлении в базу данных SQLite";
        } else if (lang.equals(en)) {
            added = "Error when adding to the database SQLite";
        }
        return added;
    }
    
    @Override
    public String Error_when_adding_to_the_database_MySQL(String lang) {
        String added = "";
        if (lang.equals(ru)) {
            added = "Ошибка при добавлении в базу данных MySQL";
        } else if (lang.equals(en)) {
            added = "Error when adding to the database MySQL";
        }
        return added;
    }
    
    @Override
    public String there_is_no_data_about_the_external_database(String language) {
        String added = "";
        if (language.equals(ru)) {
            added = "Нет данных о внешней базе данных";
        } else if (language.equals(en)) {
            added = "There is no data about the external database";
        }
        return added;
    }
}
