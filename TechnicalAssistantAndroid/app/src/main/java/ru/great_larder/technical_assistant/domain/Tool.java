package ru.great_larder.technical_assistant.domain;

import java.time.LocalDate;

public class Tool {
    protected Integer id;
    protected String toolName;
    protected String toolBrand;
    protected String toolSerialNumber;
    protected String nameCompanyToolLocation;
    protected LocalDate startOfOperation;
    protected String toolCondition;

    public Tool(String toolName, String toolBrand, String toolSerialNumber,
                String nameCompanyToolLocation, LocalDate startOfOperation, String toolCondition) {
        this.toolName = toolName;
        this.toolBrand = toolBrand;
        this.toolSerialNumber = toolSerialNumber;
        this.nameCompanyToolLocation = nameCompanyToolLocation;
        this.startOfOperation = startOfOperation;
        this.toolCondition = toolCondition;
    }

    public Tool() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

    public String getToolSerialNumber() {
        return toolSerialNumber;
    }

    public void setToolSerialNumber(String toolSerialNumber) {
        this.toolSerialNumber = toolSerialNumber;
    }

    public String getNameCompanyToolLocation() {
        return nameCompanyToolLocation;
    }

    public void setNameCompanyToolLocation(String nameCompanyToolLocation) {
        this.nameCompanyToolLocation = nameCompanyToolLocation;
    }

    public LocalDate getStartOfOperation() {
        return startOfOperation;
    }

    public void setStartOfOperation(LocalDate startOfOperation) {
        this.startOfOperation = startOfOperation;
    }

    public String getToolCondition() {
        return toolCondition;
    }

    public void setToolCondition(String toolCondition) {
        this.toolCondition = toolCondition;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolName='" + toolName + '\'' +
                ", toolBrand='" + toolBrand + '\'' +
                ", toolSerialNumber='" + toolSerialNumber + '\'' +
                ", nameCompanyToolLocation='" + nameCompanyToolLocation + '\'' +
                ", startOfOperation=" + startOfOperation +
                ", toolCondition='" + toolCondition + '\'' +
                '}';
    }
}
