package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse getCustomerById(String customerId);

    List<CustomerResponse> getCustomers();
}
