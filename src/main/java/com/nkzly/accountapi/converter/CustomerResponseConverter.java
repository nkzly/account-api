package com.nkzly.accountapi.model;

import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerResponseConverter {

    AccountResponseConverter accountResponseConverter = new AccountResponseConverter();
    public CustomerResponse convert(Customer customer) {
        CustomerResponse resp = new CustomerResponse();
        resp.setCustomerNumber(customer.getId());
        resp.setName(customer.getName());
        resp.setSurname(customer.getSurname());
        resp.setAccounts(customer.getAccounts().stream().map(accountResponseConverter::convert).collect(Collectors.toSet()));
        return resp;
    }
}
