package com.nkzly.accountapi.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String surname;
    @OneToMany(mappedBy="customer")
    private Set<Account> accounts;

    public Set<Account> getAccounts() {
        if(accounts == null) {
            accounts= new HashSet<>();
        }
        return accounts;
    }

    public Customer() {
    }

    public Customer(String name, String surname) {
        this.name = name;
        this.surname = surname;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(surname, customer.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
