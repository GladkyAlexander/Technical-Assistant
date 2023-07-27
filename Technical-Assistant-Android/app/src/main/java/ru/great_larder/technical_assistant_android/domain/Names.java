package ru.great_larder.technical_assistant_android.domain;

import java.io.File;

public class Names {
     int id;
     String names;
     String nameCompany;
     /*String url;*/
     File url;
     String domain;

    public Names(String names, String nameCompany, File url, String domain) {
        this.names = names;
        this.nameCompany = nameCompany;
        this.url = url;
        this.domain = domain;
    }

    public Names() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public File getUrl() {
        return url;
    }

    public void setUrl(File url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    
    @Override
    public String toString() {
        return "Names{" +
            "id=" + id +
            ", names='" + names + '\'' +
            ", nameCompany='" + nameCompany + '\'' +
            ", url=" + url +
            ", domain='" + domain + '\'' +
            '}';
    }
}
