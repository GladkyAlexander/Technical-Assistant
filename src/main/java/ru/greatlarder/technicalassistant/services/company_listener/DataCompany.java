package ru.greatlarder.technicalassistant.services.company_listener;

import ru.greatlarder.technicalassistant.domain.Company;

public class DataCompany {
    private final Company company;

    public DataCompany(Company company) {
        this.company = company;
    }

    public Company getCompany(){
        return company;
    }

}
