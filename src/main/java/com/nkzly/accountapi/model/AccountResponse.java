package com.nkzly.accountapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private String id;
    private String name;
    private String surname;
    private BigDecimal balance;
    private TransactionResponse transactions;
    private Integer customerId;
    private String result;

    public AccountResponse(String id, BigDecimal balance, Integer customerId) {
        this.id = id;
        this.balance = balance;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
