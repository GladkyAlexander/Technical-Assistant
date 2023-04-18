package ru.greatlarder.technicalassistant.services.lang;

import java.time.LocalDate;

public interface GetLanguageDataName {
    String getDayWeek(LocalDate date, String lang);
    String getMonthWeek(LocalDate date, String lang);

}
