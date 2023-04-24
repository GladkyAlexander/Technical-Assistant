package ru.great_larder.technical_assistant.domain;

public class MailSettings {
    private int id;
    private String mailMonitoring;
    private String passwordMailMonitoring;
    private String hostMailMonitoring;
    private String subjectOfTheLetter;
    private int idUser;

    public MailSettings(int idUser, String mailMonitoring, String passwordMailMonitoring, String hostMailMonitoring
            , String subjectOfTheLetter) {
        this.idUser = idUser;
        this.mailMonitoring = mailMonitoring;
        this.passwordMailMonitoring = passwordMailMonitoring;
        this.hostMailMonitoring = hostMailMonitoring;
        this.subjectOfTheLetter = subjectOfTheLetter;

    }

    public MailSettings() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMailMonitoring() {
        return mailMonitoring;
    }

    public void setMailMonitoring(String mailMonitoring) {
        this.mailMonitoring = mailMonitoring;
    }

    public String getPasswordMailMonitoring() {
        return passwordMailMonitoring;
    }

    public void setPasswordMailMonitoring(String passwordMailMonitoring) {
        this.passwordMailMonitoring = passwordMailMonitoring;
    }

    public String getHostMailMonitoring() {
        return hostMailMonitoring;
    }

    public void setHostMailMonitoring(String hostMailMonitoring) {
        this.hostMailMonitoring = hostMailMonitoring;
    }

    public String getSubjectOfTheLetter() {
        return subjectOfTheLetter;
    }

    public void setSubjectOfTheLetter(String subjectOfTheLetter) {
        this.subjectOfTheLetter = subjectOfTheLetter;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int userName) {
        this.idUser = userName;
    }

    @Override
    public String toString() {
        return "MailSettings{" +
                "id=" + id +
                ", mailMonitoring='" + mailMonitoring + '\'' +
                ", passwordMailMonitoring='" + passwordMailMonitoring + '\'' +
                ", hostMailMonitoring='" + hostMailMonitoring + '\'' +
                ", subjectOfTheLetter='" + subjectOfTheLetter + '\'' +
                ", userName='" + idUser + '\'' +
                '}';
    }
}
