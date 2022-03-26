package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.model.CustomerResponse;
import com.nkzly.accountapi.model.CustomerResponseConverter;
import com.nkzly.accountapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements  CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerResponseConverter converter;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerResponseConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    @Override
    public CustomerResponse getCustomerById(String customerId) {
        return converter.convert(findCustomerById(customerId));
    }
    protected Customer findCustomerById(String id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Customer could not find by id: " + id));

    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }
}
