package com.nkzly.accountapi.model;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity

public final class Account {
    @Id
    @GeneratedValue(
            generator = "UUID"
    )
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private final String id;
    @Nullable
    private final BigDecimal balance;

    private final LocalDateTime creationDate;
    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL}
    )
    @JoinColumn(
            name = "customer_id",
            nullable = false
    )
    private final Customer customer;
    @OneToMany(
            mappedBy = "account",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL}
    )
    private final Set transaction;

    public Account(String id, @Nullable BigDecimal balance, LocalDateTime creationDate, Customer customer, Set transaction) {
        this.id = id;
        this.balance = balance;
        this.creationDate = creationDate;
        this.customer = customer;
        this.transaction = transaction;
    }
}