package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse getCustomerByCustomerNumber(Integer customerNumber);

    List<CustomerResponse> getCustomers();
}
