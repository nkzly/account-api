package com.nkzly.accountapi.service;

import com.nkzly.accountapi.converter.AccountResponseConverter;
import com.nkzly.accountapi.converter.CustomerResponseConverter;
import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.model.swaggerModel.CustomerResponse;
import com.nkzly.accountapi.repository.AccountRepository;
import com.nkzly.accountapi.repository.AccountTransactionRepository;
import com.nkzly.accountapi.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    private CustomerRepository customerRepository;
    private CustomerResponseConverter converter;

    private CustomerService service;

    private final Customer customer = new Customer("Nihat","Kzly");
    @BeforeEach
    public void setup() {
        customerRepository = mock(CustomerRepository.class);
        converter = new CustomerResponseConverter();
        service = new CustomerServiceImpl(customerRepository, converter);
    }
    @Test
    void getCustomerByCustomerNumber() {
        when(customerRepository.findById(2)).thenReturn(Optional.of(customer));
        CustomerResponse result = service.getCustomerByCustomerNumber(2);
        assertEquals(result.getName(), "Nihat");
    }

    @Test
    void getCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer));
        List<CustomerResponse> results = service.getCustomers();
        assertEquals(results.isEmpty(), false);
        assertEquals(results.get(0).getName(), "Nihat");
    }
}