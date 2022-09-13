package ru.greatlarder.technicalassistant.domain;

import java.util.List;

public class Company {
    private Integer id;
    private String nameCompany;
    private String address;
    private String curatorLastName;
    private String curatorFirstName;
    private String phoneCurator;
    private String mailCurator;
    private String websiteCompany;
    private String logoCompany;
    private String managerLastName;
    private String managerFirstName;
    private String phoneManager;
    private String mailManager;
    private String engineerLastName;
    private String engineerFirstName;
    private String phoneEngineer;
    private String mailEngineer;
    private List<Equipment> equipmentList;
    private List<Tool> toolList;
    private List<Task> taskList;

    public Company() {
    }

    public Company(Integer id, String nameCompany, String address, String curatorLastName, String curatorFirstName, String phoneCurator, String mailCurator, String websiteCompany, String logoCompany, String managerLastName, String managerFirstName, String phoneManager, String mailManager, String engineerLastName, String engineerFirstName, String phoneEngineer, String mailEngineer, List<Equipment> equipmentList, List<Tool> toolList, List<Task> taskList) {
        this.id = id;
        this.nameCompany = nameCompany;
        this.address = address;
        this.curatorLastName = curatorLastName;
        this.curatorFirstName = curatorFirstName;
        this.phoneCurator = phoneCurator;
        this.mailCurator = mailCurator;
        this.websiteCompany = websiteCompany;
        this.logoCompany = logoCompany;
        this.managerLastName = managerLastName;
        this.managerFirstName = managerFirstName;
        this.phoneManager = phoneManager;
        this.mailManager = mailManager;
        this.engineerLastName = engineerLastName;
        this.engineerFirstName = engineerFirstName;
        this.phoneEngineer = phoneEngineer;
        this.mailEngineer = mailEngineer;
        this.equipmentList = equipmentList;
        this.toolList = toolList;
        this.taskList = taskList;
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

    public String getCuratorLastName() {
        return curatorLastName;
    }

    public void setCuratorLastName(String curatorLastName) {
        this.curatorLastName = curatorLastName;
    }

    public String getCuratorFirstName() {
        return curatorFirstName;
    }

    public void setCuratorFirstName(String curatorFirstName) {
        this.curatorFirstName = curatorFirstName;
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

    public String getManagerLastName() {
        return managerLastName;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
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

    public String getEngineerLastName() {
        return engineerLastName;
    }

    public void setEngineerLastName(String engineerLastName) {
        this.engineerLastName = engineerLastName;
    }

    public String getEngineerFirstName() {
        return engineerFirstName;
    }

    public void setEngineerFirstName(String engineerFirstName) {
        this.engineerFirstName = engineerFirstName;
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
                ", curatorLastName='" + curatorLastName + '\'' +
                ", curatorFirstName='" + curatorFirstName + '\'' +
                ", phoneCurator='" + phoneCurator + '\'' +
                ", mailCurator='" + mailCurator + '\'' +
                ", websiteCompany='" + websiteCompany + '\'' +
                ", logoCompany='" + logoCompany + '\'' +
                ", managerLastName='" + managerLastName + '\'' +
                ", managerFirstName='" + managerFirstName + '\'' +
                ", phoneManager='" + phoneManager + '\'' +
                ", mailManager='" + mailManager + '\'' +
                ", engineerLastName='" + engineerLastName + '\'' +
                ", engineerFirstName='" + engineerFirstName + '\'' +
                ", phoneEngineer='" + phoneEngineer + '\'' +
                ", mailEngineer='" + mailEngineer + '\'' +
                ", equipmentList=" + equipmentList +
                ", toolList=" + toolList +
                ", taskList=" + taskList +
                '}';
    }
}
