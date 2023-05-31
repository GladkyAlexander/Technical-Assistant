package ru.great_larder.technicalAssistant.services.lang;

import java.util.List;

public interface LanguageEvents {
    List<String> getListEvents(String lang);
    String getThe_Conference(String lang);
    String getSeminar(String lang);
    String getTraining(String lang);
    String getExhibition(String lang);
    String getCongress(String lang);
    String getPresentation(String lang);
    String getReception(String lang);
    String getRoundTable(String lang);
    String getMeeting(String lang);
    String getLunch(String lang);
    String getBriefing(String lang);
    
}
