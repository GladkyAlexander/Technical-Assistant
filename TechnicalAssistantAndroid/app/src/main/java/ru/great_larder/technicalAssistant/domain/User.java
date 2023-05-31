package ru.great_larder.technicalAssistant.domain;

import java.util.List;

public class User {
    private int id;
    private String lastName;
    private String firstName;
    private String post;
    private String companyAffiliation;
    private String phone;
    private String mailAddress;
    private String language;
    private String login;
    private String password;
    private String server;
    private String port;
    private String nameDB;
    private String userDB;
    private String passwordDB;
    private String serverFTP;
    private int portFTP;
    private String userFTP;
    private String passwordFTP;
    private List<MailSettings> mailSettings;
    public User() {
    }
    
    public User(int id, String lastName, String firstName, String post, String companyAffiliation, String phone, String mailAddress, String language, String login, String password, String server, String port, String nameDB, String userDB, String passwordDB, String serverFTP, int portFTP, String userFTP, String passwordFTP, List<MailSettings> mailSettings) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.post = post;
        this.companyAffiliation = companyAffiliation;
        this.phone = phone;
        this.mailAddress = mailAddress;
        this.language = language;
        this.login = login;
        this.password = password;
        this.server = server;
        this.port = port;
        this.nameDB = nameDB;
        this.userDB = userDB;
        this.passwordDB = passwordDB;
        this.serverFTP = serverFTP;
        this.portFTP = portFTP;
        this.userFTP = userFTP;
        this.passwordFTP = passwordFTP;
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
    
    public String getCompanyAffiliation() {
        return companyAffiliation;
    }
    
    public void setCompanyAffiliation(String companyAffiliation) {
        this.companyAffiliation = companyAffiliation;
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
    
    public String getServer() {
        return server;
    }
    
    public void setServer(String server) {
        this.server = server;
    }
    
    public String getPort() {
        return port;
    }
    
    public void setPort(String port) {
        this.port = port;
    }
    
    public String getNameDB() {
        return nameDB;
    }
    
    public void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }
    
    public String getUserDB() {
        return userDB;
    }
    
    public void setUserDB(String userDB) {
        this.userDB = userDB;
    }
    
    public String getPasswordDB() {
        return passwordDB;
    }
    
    public void setPasswordDB(String passwordDB) {
        this.passwordDB = passwordDB;
    }
    
    public List<MailSettings> getMailSettings() {
        return mailSettings;
    }
    
    public void setMailSettings(List<MailSettings> mailSettings) {
        this.mailSettings = mailSettings;
    }
    
    public String getServerFTP() {
        return serverFTP;
    }
    
    public void setServerFTP(String serverFTP) {
        this.serverFTP = serverFTP;
    }
    
    public int getPortFTP() {
        return portFTP;
    }
    
    public void setPortFTP(int portFTP) {
        this.portFTP = portFTP;
    }
    
    public String getUserFTP() {
        return userFTP;
    }
    
    public void setUserFTP(String userFTP) {
        this.userFTP = userFTP;
    }
    
    public String getPasswordFTP() {
        return passwordFTP;
    }
    
    public void setPasswordFTP(String passwordFTP) {
        this.passwordFTP = passwordFTP;
    }
    
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", post='" + post + '\'' +
            ", companyAffiliation='" + companyAffiliation + '\'' +
            ", phone='" + phone + '\'' +
            ", mailAddress='" + mailAddress + '\'' +
            ", language='" + language + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", server='" + server + '\'' +
            ", port='" + port + '\'' +
            ", nameDB='" + nameDB + '\'' +
            ", userDB='" + userDB + '\'' +
            ", passwordDB='" + passwordDB + '\'' +
            ", serverFTP='" + serverFTP + '\'' +
            ", portFTP=" + portFTP +
            ", userFTP='" + userFTP + '\'' +
            ", passwordFTP='" + passwordFTP + '\'' +
            ", mailSettings=" + mailSettings +
            '}';
    }
}
