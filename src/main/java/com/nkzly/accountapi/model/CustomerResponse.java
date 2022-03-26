package com.nkzly.accountapi.model;

import java.util.Set;

public class CustomerResponse {
    private String id;
    private String name;
    private String surname;
    private Set<AccountResponse> accounts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
