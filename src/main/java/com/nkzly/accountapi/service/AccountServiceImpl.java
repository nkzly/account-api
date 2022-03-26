package com.nkzly.accountapi.service;

import com.nkzly.accountapi.converter.AccountResponseConverter;
import com.nkzly.accountapi.model.*;
import com.nkzly.accountapi.model.swaggerModel.AccountRequest;
import com.nkzly.accountapi.model.swaggerModel.AccountResponse;
import com.nkzly.accountapi.model.swaggerModel.AccountTransactionDetailResponse;
import com.nkzly.accountapi.repository.AccountRepository;
import com.nkzly.accountapi.repository.AccountTransactionRepository;
import com.nkzly.accountapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTransactionRepository transactionRepository;
    private final AccountResponseConverter accountResponseConverter;
    public AccountServiceImpl(AccountRepository accountRepository, CustomerService customerService, CustomerRepository customerRepository, AccountTransactionRepository transactionRepository, AccountResponseConverter accountResponseConverter) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.accountResponseConverter = accountResponseConverter;
    }

    @Override
    public String createAccount(AccountRequest request) {
        Customer customer = customerRepository.getById(request.getCustomerID());
        LocalDateTime createTime = LocalDateTime.now();
        Transaction transaction = null;
        changeCurrentAccount(customer);
        Account account = new Account(
                customer,
                request.getInitialCredit(),
                createTime);

        if (request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            transaction = new Transaction(
                    account,
                    request.getInitialCredit(),
                    createTime);

            account.getTransactions().add(transaction);
        }
        accountRepository.save(account);
        if (transaction != null) {
            transactionRepository.save(transaction);
        }
        return "success";
    }

    private void changeCurrentAccount(Customer customer) {
        if (!customer.getAccounts().isEmpty())
        customer.getAccounts().stream().forEach(account ->
                updateAccount(accountRepository, account)
        );
    }

    private void updateAccount(AccountRepository accountRepository, Account account) {
        account.setDefaultAccount(false);
        accountRepository.save(account);
    }

    @Override
    public AccountTransactionDetailResponse getCustomerAccountInfo(Integer customerNumber) {
        Customer customer = customerRepository.findById(customerNumber).get();

        return accountResponseConverter.convert(customer);
    }
}
