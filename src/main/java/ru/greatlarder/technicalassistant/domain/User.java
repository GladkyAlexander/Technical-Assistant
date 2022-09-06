package ru.greatlarder.technicalassistant.domain;

import java.util.List;

public class User {
    private int id;
    private String lastName;
    private String firstName;
    private String post;
    private String phone;
    private String mailAddress;
    private String language;
    private String login;
    private String password;
    private List<Company> companyList;
    private List<MailSettings> mailSettings;

    public User() {
    }

    public User(String lastName, String firstName, String post, String phone, String mailAddress, String language, String login, String password, List<Company> companyList, List<MailSettings> mailSettings) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.post = post;
        this.phone = phone;
        this.mailAddress = mailAddress;
        this.language = language;
        this.login = login;
        this.password = password;
        this.companyList = companyList;
        this.mailSettings = mailSettings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

    public List<MailSettings> getMailSettings() {
        return mailSettings;
    }

    public void setMailSettings(List<MailSettings> mailSettings) {
        this.mailSettings = mailSettings;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", post='" + post + '\'' +
                ", phone='" + phone + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                ", language='" + language + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", companyList=" + companyList +
                ", mailSettings=" + mailSettings +
                '}';
    }
}
