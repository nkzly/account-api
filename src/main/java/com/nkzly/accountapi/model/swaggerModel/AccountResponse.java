package com.nkzly.accountapi.model.swaggerModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AccountResponse {
    private Integer accountId;
    private BigDecimal balance;
    private LocalDateTime creationDate;
    private List<TransactionResponse> transactions;
    public AccountResponse(Integer accountId, BigDecimal balance,LocalDateTime creationDate, List<TransactionResponse> transactions ) {
        this.accountId = accountId;
        this.balance = balance;
        this.creationDate=creationDate;
        this.transactions = transactions;
    }

    public AccountResponse() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<TransactionResponse> getTransactions() {
        if (transactions == null) {
            transactions= new ArrayList<>();
        }
        return transactions;
    }
}
