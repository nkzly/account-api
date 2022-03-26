package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.model.swaggerModel.CustomerResponse;
import com.nkzly.accountapi.converter.CustomerResponseConverter;
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
    public CustomerResponse getCustomerByCustomerNumber(Integer customerNumber) {
        return converter.convert(findCustomerById(customerNumber));
    }
    protected Customer findCustomerById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalStateException("Customer could not find by id: " + id));

    }

    @Override
    public List<CustomerResponse> getCustomers() {
        return customerRepository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }
}
