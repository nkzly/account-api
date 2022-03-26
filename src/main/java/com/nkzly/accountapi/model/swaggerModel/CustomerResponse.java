package com.nkzly.accountapi.model.swaggerModel;

import java.util.Set;

public class CustomerResponse {
    private Integer customerNumber;
    private String name;
    private String surname;
    private Set<AccountResponse> accounts;

    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Set<AccountResponse> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<AccountResponse> accounts) {
        this.accounts = accounts;
    }
}
