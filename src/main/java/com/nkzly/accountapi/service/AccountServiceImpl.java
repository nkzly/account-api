package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.*;
import com.nkzly.accountapi.repository.AccountRepository;
import com.nkzly.accountapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public String createAccount(AccountRequest request) {
            Customer customer = customerRepository.getById(request.getCustomerID());
            LocalDateTime createTime = LocalDateTime.now();
            Account account = new Account(
                    customer,
                    request.getInitialCredit(),
                    createTime);

            if (request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
                Transaction transaction = new Transaction(
                        account,
                        request.getInitialCredit(),
                        createTime);

                account.getTransactions().add(transaction);
            }
            return "success";
    }

    @Override
    public AccountResponse getCustomerAccountInfo(Integer customerNumber) {
        return null;
    }
}
