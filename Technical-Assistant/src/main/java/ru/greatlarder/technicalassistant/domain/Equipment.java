package ru.greatlarder.technicalassistant.domain;

import java.time.LocalDate;
import java.util.List;

public class Equipment {
    protected int id;
    protected String name;
    protected String model;
    protected String manufacturer;
    protected String serialNumber;
    protected String macAddress;
    protected String macAddress1;
    protected String macAddress2;
    protected String macAddress3;
    protected String login;
    protected String password;
    protected String image;
    protected String room;
    protected String location;
    protected LocalDate dateWork;
    protected String condition;
    protected String company;
    protected String manual;
    protected String ipAddress;
    protected String masc;
    protected String gateway;
    protected String danteIpAddress;
    protected String danteMasc;
    protected String danteGateway;
    protected String outletNumber;
    protected Integer portNumberInTheSwitch;
    protected Integer idNetworkSwitcher;
    protected String type;
    protected boolean userVisibility;
    private List<Defect> defectList;
    Class<? extends Equipment> cl = this.getClass();

    public Equipment() {
    }

    public Equipment(int id, String name, String model, String manufacturer, String serialNumber, String macAddress, String macAddress1, String macAddress2, String macAddress3, String login, String password, String image, String room, String location, LocalDate dateWork, String condition, String company, String manual, String ipAddress, String masc, String gateway, String danteIpAddress, String danteMasc, String danteGateway, String outletNumber, Integer portNumberInTheSwitch, Integer idNetworkSwitcher, String type, boolean visible, List<Defect> defectList, Class<? extends Equipment> cl) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.serialNumber = serialNumber;
        this.macAddress = macAddress;
        this.macAddress1 = macAddress1;
        this.macAddress2 = macAddress2;
        this.macAddress3 = macAddress3;
        this.login = login;
        this.password = password;
        this.image = image;
        this.room = room;
        this.location = location;
        this.dateWork = dateWork;
        this.condition = condition;
        this.company = company;
        this.manual = manual;
        this.ipAddress = ipAddress;
        this.masc = masc;
        this.gateway = gateway;
        this.danteIpAddress = danteIpAddress;
        this.danteMasc = danteMasc;
        this.danteGateway = danteGateway;
        this.outletNumber = outletNumber;
        this.portNumberInTheSwitch = portNumberInTheSwitch;
        this.idNetworkSwitcher = idNetworkSwitcher;
        this.type = type;
        this.userVisibility = visible;
        this.defectList = defectList;
        this.cl = cl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMasc() {
        return masc;
    }

    public void setMasc(String masc) {
        this.masc = masc;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getDanteIpAddress() {
        return danteIpAddress;
    }

    public void setDanteIpAddress(String danteIpAddress) {
        this.danteIpAddress = danteIpAddress;
    }

    public String getDanteMasc() {
        return danteMasc;
    }

    public void setDanteMasc(String danteMasc) {
        this.danteMasc = danteMasc;
    }

    public String getDanteGateway() {
        return danteGateway;
    }

    public void setDanteGateway(String danteGateway) {
        this.danteGateway = danteGateway;
    }

    public String getOutletNumber() {
        return outletNumber;
    }

    public void setOutletNumber(String outletNumber) {
        this.outletNumber = outletNumber;
    }

    public Integer getPortNumberInTheSwitch() {
        return portNumberInTheSwitch;
    }

    public void setPortNumberInTheSwitch(Integer portNumberInTheSwitch) {
        this.portNumberInTheSwitch = portNumberInTheSwitch;
    }

    public Integer getIdNetworkSwitcher() {
        return idNetworkSwitcher;
    }

    public void setIdNetworkSwitcher(Integer idNetworkSwitcher) {
        this.idNetworkSwitcher = idNetworkSwitcher;
    }

    public List<Defect> getDefectList() {
        return defectList;
    }

    public void setDefectList(List<Defect> defectList) {
        this.defectList = defectList;
    }

    public Class<? extends Equipment> getCl() {
        return cl;
    }

    public void setCl(Class<? extends Equipment> cl) {
        this.cl = cl;
    }

    public String getMacAddress1() {
        return macAddress1;
    }

    public void setMacAddress1(String macAddress1) {
        this.macAddress1 = macAddress1;
    }

    public String getMacAddress2() {
        return macAddress2;
    }

    public void setMacAddress2(String macAddress2) {
        this.macAddress2 = macAddress2;
    }

    public String getMacAddress3() {
        return macAddress3;
    }

    public void setMacAddress3(String macAddress3) {
        this.macAddress3 = macAddress3;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUserVisibility() {
        return userVisibility;
    }

    public void setUserVisibility(boolean userVisibility) {
        this.userVisibility = userVisibility;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", macAddress1='" + macAddress1 + '\'' +
                ", macAddress2='" + macAddress2 + '\'' +
                ", macAddress3='" + macAddress3 + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", image='" + image + '\'' +
                ", room='" + room + '\'' +
                ", location='" + location + '\'' +
                ", dateWork=" + dateWork +
                ", condition='" + condition + '\'' +
                ", company='" + company + '\'' +
                ", manual='" + manual + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", masc='" + masc + '\'' +
                ", gateway='" + gateway + '\'' +
                ", danteIpAddress='" + danteIpAddress + '\'' +
                ", danteMasc='" + danteMasc + '\'' +
                ", danteGateway='" + danteGateway + '\'' +
                ", outletNumber='" + outletNumber + '\'' +
                ", portNumberInTheSwitch=" + portNumberInTheSwitch +
                ", idNetworkSwitcher=" + idNetworkSwitcher +
                ", type='" + type + '\'' +
                ", visible=" + userVisibility +
                ", defectList=" + defectList +
                ", cl=" + cl +
                '}';
    }
}
