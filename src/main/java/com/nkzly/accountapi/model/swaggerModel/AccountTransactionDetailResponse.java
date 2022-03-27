package com.nkzly.accountapi.model.swaggerModel;

import java.util.ArrayList;
import java.util.List;

public class AccountTransactionDetailResponse {

    private Integer customerId;
    private String name;
    private String surname;
    private List<AccountResponse> account;

    public AccountTransactionDetailResponse(Integer customerId, String name, String surname, List<AccountResponse> account) {
        this.customerId = customerId;
        this.name = name;
        this.surname = surname;
        this.account = account;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public List<AccountResponse> getAccounts() {
        if(account== null) {
            account = new ArrayList<>();
        }
        return account;
    }
}
