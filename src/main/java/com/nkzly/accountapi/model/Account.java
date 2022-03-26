package com.nkzly.accountapi.model;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table
public final class Account {
    @Id
    @Column
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private final String id;
    @Column
    private final BigDecimal balance;
    @Column
    private final LocalDateTime creationDate;
    @Column
    private final boolean defaultAccount;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    public Account(String id, @Nullable BigDecimal balance, LocalDateTime creationDate, boolean defaultAccount, Customer customer) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.defaultAccount = defaultAccount;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public boolean isDefaultAccount() {
        return defaultAccount;
    }
}