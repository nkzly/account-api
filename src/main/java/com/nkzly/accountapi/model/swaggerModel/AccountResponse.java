package com.nkzly.accountapi.model.swaggerModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AccountResponse {
    private Integer accountId;
    private BigDecimal balance;
    private List<TransactionResponse> transactions;
    public AccountResponse(Integer accountId, BigDecimal balance,List<TransactionResponse> transactions ) {
        this.accountId = accountId;
        this.balance = balance;
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

    public List<TransactionResponse> getTransactions() {
        if (transactions == null) {
            transactions= new ArrayList<>();
        }
        return transactions;
    }
}
