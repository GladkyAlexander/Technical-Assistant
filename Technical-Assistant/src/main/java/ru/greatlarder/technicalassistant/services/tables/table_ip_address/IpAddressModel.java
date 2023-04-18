package ru.greatlarder.technicalassistant.services.tables.table_ip_address;

import javafx.beans.property.SimpleStringProperty;
import ru.greatlarder.technicalassistant.domain.Equipment;

public class IpAddressModel {
    private SimpleStringProperty name;
    private SimpleStringProperty model;
    private SimpleStringProperty manufacturer;
    private SimpleStringProperty serialNumber;
    private SimpleStringProperty macAddress;
    private SimpleStringProperty login;
    private SimpleStringProperty password;
    private SimpleStringProperty image;
    private SimpleStringProperty room;
    private SimpleStringProperty location;
    private SimpleStringProperty dateWork;
    private SimpleStringProperty condition;
    private SimpleStringProperty company;
    private SimpleStringProperty manual;
    private SimpleStringProperty ipAddress;
    private SimpleStringProperty masc;
    private SimpleStringProperty gateway;
    private SimpleStringProperty danteIpAddress;
    private SimpleStringProperty danteMasc;
    private SimpleStringProperty danteGateway;


    public IpAddressModel(Equipment equipment, String ip, String grid) {
        if(grid.equals("network")){
            network(equipment, ip);
        }
        if(grid.equals("dante")){
            dante(equipment, ip);
        }

    }

    private void dante(Equipment equipment, String ip){
            if (equipment.getDanteIpAddress() != null) {
                this.name = new SimpleStringProperty(equipment.getName());
                this.model = new SimpleStringProperty(equipment.getModel());
                this.manufacturer = new SimpleStringProperty(equipment.getManufacturer());
                this.serialNumber = new SimpleStringProperty(equipment.getSerialNumber());
                this.macAddress = new SimpleStringProperty(equipment.getMacAddress());
                this.login = new SimpleStringProperty(equipment.getLogin());
                this.password = new SimpleStringProperty(equipment.getPassword());
                this.image = new SimpleStringProperty(equipment.getImage());
                this.room = new SimpleStringProperty(equipment.getRoom());
                this.location = new SimpleStringProperty(equipment.getLocation());
                this.dateWork = new SimpleStringProperty(equipment.getDateWork().toString());
                this.condition = new SimpleStringProperty(equipment.getCondition());
                this.company = new SimpleStringProperty(equipment.getCompany());
                this.manual = new SimpleStringProperty(equipment.getManual());
                this.ipAddress = new SimpleStringProperty(equipment.getIpAddress());
                this.masc = new SimpleStringProperty(equipment.getMasc());
                this.gateway = new SimpleStringProperty(equipment.getGateway());
                this.danteIpAddress = new SimpleStringProperty(equipment.getDanteIpAddress());
                this.danteMasc = new SimpleStringProperty(equipment.getDanteMasc());
                this.danteGateway = new SimpleStringProperty(equipment.getDanteGateway());
            } else {
                this.name = new SimpleStringProperty(null);
                this.model = new SimpleStringProperty(null);
                this.manufacturer = new SimpleStringProperty(null);
                this.serialNumber = new SimpleStringProperty(null);
                this.macAddress = new SimpleStringProperty(null);
                this.login = new SimpleStringProperty(null);
                this.password = new SimpleStringProperty(null);
                this.image = new SimpleStringProperty(null);
                this.room = new SimpleStringProperty(null);
                this.location = new SimpleStringProperty(null);
                this.dateWork = new SimpleStringProperty(null);
                this.condition = new SimpleStringProperty(null);
                this.company = new SimpleStringProperty(null);
                this.manual = new SimpleStringProperty(null);
                this.ipAddress = new SimpleStringProperty(null);
                this.masc = new SimpleStringProperty(null);
                this.gateway = new SimpleStringProperty(null);
                this.danteIpAddress = new SimpleStringProperty(ip);
                this.danteMasc = new SimpleStringProperty(null);
                this.danteGateway = new SimpleStringProperty(null);
            }
    }
    private void network(Equipment equipment, String ip){
        if (equipment.getIpAddress() != null) {
            this.name = new SimpleStringProperty(equipment.getName());
            this.model = new SimpleStringProperty(equipment.getModel());
            this.manufacturer = new SimpleStringProperty(equipment.getManufacturer());
            this.serialNumber = new SimpleStringProperty(equipment.getSerialNumber());
            this.macAddress = new SimpleStringProperty(equipment.getMacAddress());
            this.login = new SimpleStringProperty(equipment.getLogin());
            this.password = new SimpleStringProperty(equipment.getPassword());
            this.image = new SimpleStringProperty(equipment.getImage());
            this.room = new SimpleStringProperty(equipment.getRoom());
            this.location = new SimpleStringProperty(equipment.getLocation());
            this.dateWork = new SimpleStringProperty(equipment.getDateWork().toString());
            this.condition = new SimpleStringProperty(equipment.getCondition());
            this.company = new SimpleStringProperty(equipment.getCompany());
            this.manual = new SimpleStringProperty(equipment.getManual());
            this.ipAddress = new SimpleStringProperty(equipment.getIpAddress());
            this.masc = new SimpleStringProperty(equipment.getMasc());
            this.gateway = new SimpleStringProperty(equipment.getGateway());
            this.danteIpAddress = new SimpleStringProperty(equipment.getDanteIpAddress());
            this.danteMasc = new SimpleStringProperty(equipment.getDanteMasc());
            this.danteGateway = new SimpleStringProperty(equipment.getDanteGateway());
        } else {
            this.name = new SimpleStringProperty(null);
            this.model = new SimpleStringProperty(null);
            this.manufacturer = new SimpleStringProperty(null);
            this.serialNumber = new SimpleStringProperty(null);
            this.macAddress = new SimpleStringProperty(null);
            this.login = new SimpleStringProperty(null);
            this.password = new SimpleStringProperty(null);
            this.image = new SimpleStringProperty(null);
            this.room = new SimpleStringProperty(null);
            this.location = new SimpleStringProperty(null);
            this.dateWork = new SimpleStringProperty(null);
            this.condition = new SimpleStringProperty(null);
            this.company = new SimpleStringProperty(null);
            this.manual = new SimpleStringProperty(null);
            this.ipAddress = new SimpleStringProperty(ip);
            this.masc = new SimpleStringProperty(null);
            this.gateway = new SimpleStringProperty(null);
            this.danteIpAddress = new SimpleStringProperty(null);
            this.danteMasc = new SimpleStringProperty(null);
            this.danteGateway = new SimpleStringProperty(null);
        }
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }

    public SimpleStringProperty manufacturerProperty() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.set(manufacturer);
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public String getMacAddress() {
        return macAddress.get();
    }

    public SimpleStringProperty macAddressProperty() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress.set(macAddress);
    }

    public String getLogin() {
        return login.get();
    }

    public SimpleStringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public String getRoom() {
        return room.get();
    }

    public SimpleStringProperty roomProperty() {
        return room;
    }

    public void setRoom(String room) {
        this.room.set(room);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getDateWork() {
        return dateWork.get();
    }

    public SimpleStringProperty dateWorkProperty() {
        return dateWork;
    }

    public void setDateWork(String dateWork) {
        this.dateWork.set(dateWork);
    }

    public String getCondition() {
        return condition.get();
    }

    public SimpleStringProperty conditionProperty() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition.set(condition);
    }

    public String getCompany() {
        return company.get();
    }

    public SimpleStringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getManual() {
        return manual.get();
    }

    public SimpleStringProperty manualProperty() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual.set(manual);
    }

    public String getIpAddress() {
        return ipAddress.get();
    }

    public SimpleStringProperty ipAddressProperty() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress.set(ipAddress);
    }

    public String getMasc() {
        return masc.get();
    }

    public SimpleStringProperty mascProperty() {
        return masc;
    }

    public void setMasc(String masc) {
        this.masc.set(masc);
    }

    public String getGateway() {
        return gateway.get();
    }

    public SimpleStringProperty gatewayProperty() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway.set(gateway);
    }

    public String getDanteIpAddress() {
        return danteIpAddress.get();
    }

    public SimpleStringProperty danteIpAddressProperty() {
        return danteIpAddress;
    }

    public void setDanteIpAddress(String danteIpAddress) {
        this.danteIpAddress.set(danteIpAddress);
    }

    public String getDanteMasc() {
        return danteMasc.get();
    }

    public SimpleStringProperty danteMascProperty() {
        return danteMasc;
    }

    public void setDanteMasc(String danteMasc) {
        this.danteMasc.set(danteMasc);
    }

    public String getDanteGateway() {
        return danteGateway.get();
    }

    public SimpleStringProperty danteGatewayProperty() {
        return danteGateway;
    }

    public void setDanteGateway(String danteGateway) {
        this.danteGateway.set(danteGateway);
    }
}
