package com.nkzly.accountapi.model;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {
    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    @ManyToOne
    @JoinColumn(
            name = "account_id",
            nullable = false
    )
    @NotNull
    private Account account;

    public Transaction(Account account, @Nullable BigDecimal amount, @Nullable LocalDateTime transactionDate) {
        this.account=account;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Transaction() {
    }

    public String getId() {
        return id;
    }

    @Nullable
    public BigDecimal getAmount() {
        return amount;
    }

    @Nullable
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Account getAccount() {
        return account;
    }
}
