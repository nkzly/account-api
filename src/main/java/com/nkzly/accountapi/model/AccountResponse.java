package com.nkzly.accountapi.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AccountResponse {

    private String id;
    private BigDecimal balance;
    private LocalDateTime creationDate;
    private String customerId;

    public AccountResponse(String id, BigDecimal balance, LocalDateTime creationDate, String customerId) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
