package com.nkzly.accountapi.model;


import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public final class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column
    private Integer id;
    @Column
    private BigDecimal balance;
    @Column
    private LocalDateTime creationDate;

    @Column
    private boolean defaultAccount;
    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(mappedBy="account")
    @EqualsExclude
    @HashCodeExclude
    @ToStringExclude
    private Set<Transaction> transactions;

    public Account() {
    }

    public Account(Customer customer, BigDecimal initialCredit, LocalDateTime now) {
        this.balance = initialCredit;
        this.creationDate = now;
        this.defaultAccount = true;
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Set<Transaction> getTransactions() {
        if(transactions == null) {
            transactions= new HashSet<>();
        }
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    public boolean isDefaultAccount() {
        return defaultAccount;
    }
    public void setDefaultAccount(boolean defaultAccount) {
        this.defaultAccount = defaultAccount;
    }
}