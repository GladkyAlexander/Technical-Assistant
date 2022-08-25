package ru.greatlarder.technicalassistant.domain;

import java.util.List;

public class Company {
    private Integer id;
    private String nameCompany;
    private String address;
    private String curator;
    private String phoneCurator;
    private String mailCurator;
    private String websiteCompany;
    private String logoCompany;
    private String manager;
    private String phoneManager;
    private String mailManager;
    private String engineer;
    private String phoneEngineer;
    private String mailEngineer;
    private List<Equipment> equipmentList;
    private List<Tool> toolList;
    private List<Task> taskList;

    public Company(String nameCompany, String address, String curator, String phoneCurator
            , String mailCurator, String websiteCompany, String logoCompany, String manager, String phoneManager
            , String mailManager, String engineer, String phoneEngineer, String mailEngineer
            , List<Equipment> equipmentList, List<Tool> toolList, List<Task> taskList) {
        this.nameCompany = nameCompany;
        this.address = address;
        this.curator = curator;
        this.phoneCurator = phoneCurator;
        this.mailCurator = mailCurator;
        this.websiteCompany = websiteCompany;
        this.logoCompany = logoCompany;
        this.manager = manager;
        this.phoneManager = phoneManager;
        this.mailManager = mailManager;
        this.engineer = engineer;
        this.phoneEngineer = phoneEngineer;
        this.mailEngineer = mailEngineer;
        this.equipmentList = equipmentList;
        this.toolList = toolList;
        this.taskList = taskList;
    }

    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCurator() {
        return curator;
    }

    public void setCurator(String curator) {
        this.curator = curator;
    }

    public String getPhoneCurator() {
        return phoneCurator;
    }

    public void setPhoneCurator(String phoneCurator) {
        this.phoneCurator = phoneCurator;
    }

    public String getMailCurator() {
        return mailCurator;
    }

    public void setMailCurator(String mailCurator) {
        this.mailCurator = mailCurator;
    }

    public String getWebsiteCompany() {
        return websiteCompany;
    }

    public void setWebsiteCompany(String websiteCompany) {
        this.websiteCompany = websiteCompany;
    }

    public String getLogoCompany() {
        return logoCompany;
    }

    public void setLogoCompany(String logoCompany) {
        this.logoCompany = logoCompany;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getPhoneManager() {
        return phoneManager;
    }

    public void setPhoneManager(String phoneManager) {
        this.phoneManager = phoneManager;
    }

    public String getMailManager() {
        return mailManager;
    }

    public void setMailManager(String mailManager) {
        this.mailManager = mailManager;
    }

    public String getEngineer() {
        return engineer;
    }

    public void setEngineer(String engineer) {
        this.engineer = engineer;
    }

    public String getPhoneEngineer() {
        return phoneEngineer;
    }

    public void setPhoneEngineer(String phoneEngineer) {
        this.phoneEngineer = phoneEngineer;
    }

    public String getMailEngineer() {
        return mailEngineer;
    }

    public void setMailEngineer(String mailEngineer) {
        this.mailEngineer = mailEngineer;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<Equipment> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public List<Tool> getToolList() {
        return toolList;
    }

    public void setToolList(List<Tool> toolList) {
        this.toolList = toolList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", nameCompany='" + nameCompany + '\'' +
                ", address='" + address + '\'' +
                ", curator='" + curator + '\'' +
                ", phoneCurator='" + phoneCurator + '\'' +
                ", mailCurator='" + mailCurator + '\'' +
                ", websiteCompany='" + websiteCompany + '\'' +
                ", logoCompany='" + logoCompany + '\'' +
                ", manager='" + manager + '\'' +
                ", phoneManager='" + phoneManager + '\'' +
                ", mailManager='" + mailManager + '\'' +
                ", engineer='" + engineer + '\'' +
                ", phoneEngineer='" + phoneEngineer + '\'' +
                ", mailEngineer='" + mailEngineer + '\'' +
                ", equipmentList=" + equipmentList +
                ", toolList=" + toolList +
                ", taskList=" + taskList +
                '}';
    }
}
