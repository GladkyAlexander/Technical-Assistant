package ru.greatlarder.technicalassistant.services.lang.impl;

import ru.greatlarder.technicalassistant.services.lang.LanguageDataName;

import java.time.LocalDate;
import java.util.HashMap;

public class LanguageDataNameImpl implements LanguageDataName {
    String en = "English";
    String ru = "Русский";
    @Override
    public String getDayWeek(LocalDate date, String lang) {

        HashMap<String, String> hashMapEn = new HashMap<>();
        hashMapEn.put("MONDAY", "Monday");
        hashMapEn.put("TUESDAY", "Tuesday");
        hashMapEn.put("WEDNESDAY", "Wednesday");
        hashMapEn.put("THURSDAY", "Thursday");
        hashMapEn.put("FRIDAY", "Friday");
        hashMapEn.put("SATURDAY", "Saturday");
        hashMapEn.put("SUNDAY", "Sunday");
        HashMap<String, String> hashMapRu = new HashMap<>();
        hashMapRu.put("MONDAY", "Понедельник");
        hashMapRu.put("TUESDAY", "Вторник");
        hashMapRu.put("WEDNESDAY", "Среда");
        hashMapRu.put("THURSDAY", "Четверг");
        hashMapRu.put("FRIDAY", "Пятница");
        hashMapRu.put("SATURDAY", "Суббота");
        hashMapRu.put("SUNDAY", "Воскресенье");

        if(lang.equals(ru)) return hashMapRu.get(String.valueOf(date.getDayOfWeek()));
        if(lang.equals(en)) return hashMapEn.get(String.valueOf(date.getDayOfWeek()));

        return null;

    }

    @Override
    public String getMonthWeek(LocalDate date, String lang) {

        HashMap<String, String> hashMapEn = new HashMap<>();
        hashMapEn.put("JANUARY", "January");
        hashMapEn.put("FEBRUARY", "February");
        hashMapEn.put("MARCH", "March");
        hashMapEn.put("APRIL", "April");
        hashMapEn.put("MAY", "May");
        hashMapEn.put("JUNE", "June");
        hashMapEn.put("JULY", "July");
        hashMapEn.put("AUGUST", "August");
        hashMapEn.put("SEPTEMBER", "September");
        hashMapEn.put("OCTOBER", "October");
        hashMapEn.put("NOVEMBER", "November");
        hashMapEn.put("DECEMBER", "December");

        HashMap<String, String> hashMapRu = new HashMap<>();
        hashMapRu.put("JANUARY", "Январь");
        hashMapRu.put("FEBRUARY", "Февраль");
        hashMapRu.put("MARCH", "Март");
        hashMapRu.put("APRIL", "Апрель");
        hashMapRu.put("MAY", "Май");
        hashMapRu.put("JUNE", "Июнь");
        hashMapRu.put("JULY", "Июль");
        hashMapRu.put("AUGUST", "Август");
        hashMapRu.put("SEPTEMBER", "Сентябрь");
        hashMapRu.put("OCTOBER", "Октябрь");
        hashMapRu.put("NOVEMBER", "Ноябрь");
        hashMapRu.put("DECEMBER", "Декабрь");

        if(lang.equals(ru)) return hashMapRu.get(String.valueOf(String.valueOf(date.getMonth())));
        if(lang.equals(en)) return hashMapEn.get(String.valueOf(String.valueOf(date.getMonth())));

        return null;
    }
}
