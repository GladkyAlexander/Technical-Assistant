package ru.great_larder.technical_assistant_android.domain;

public class Lang {
    Integer idResource;
    String language;
    
    public Lang(Integer idResource, String language) {
        this.idResource = idResource;
        this.language = language;
    }
    
    public Integer getIdResource() {
        return idResource;
    }
    
    public void setIdResource(Integer idResource) {
        this.idResource = idResource;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    @Override
    public String toString() {
        return "Lang{" +
            "idResource=" + idResource +
            ", language='" + language + '\'' +
            '}';
    }
}
