package ru.greatlarder.technicalassistant.domain;

public class SeatingArrangements {
    Integer id;
    String nameSeatingArrangements;
    String nameCompany;
    String urlImageSeatingArrangements;
    Integer numberOfParticipants;

    public SeatingArrangements() {
    }

    public SeatingArrangements(String nameSeatingArrangements, String nameCompany, String urlImageSeatingArrangements, Integer numberOfParticipants) {
        this.nameSeatingArrangements = nameSeatingArrangements;
        this.nameCompany = nameCompany;
        this.urlImageSeatingArrangements = urlImageSeatingArrangements;
        this.numberOfParticipants = numberOfParticipants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameSeatingArrangements() {
        return nameSeatingArrangements;
    }

    public void setNameSeatingArrangements(String nameSeatingArrangements) {
        this.nameSeatingArrangements = nameSeatingArrangements;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getUrlImageSeatingArrangements() {
        return urlImageSeatingArrangements;
    }

    public void setUrlImageSeatingArrangements(String urlImageSeatingArrangements) {
        this.urlImageSeatingArrangements = urlImageSeatingArrangements;
    }

    public Integer getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(Integer numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    @Override
    public String toString() {
        return "SeatingArrangements{" +
                "id=" + id +
                ", nameSeatingArrangements='" + nameSeatingArrangements + '\'' +
                ", nameCompany='" + nameCompany + '\'' +
                ", urlImageSeatingArrangements='" + urlImageSeatingArrangements + '\'' +
                ", numberOfParticipants=" + numberOfParticipants +
                '}';
    }
}
