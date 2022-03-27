package com.nkzly.accountapi.service;

import com.nkzly.accountapi.converter.AccountResponseConverter;
import com.nkzly.accountapi.model.Account;
import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.model.Transaction;
import com.nkzly.accountapi.model.swaggerModel.AccountRequest;
import com.nkzly.accountapi.model.swaggerModel.AccountTransactionDetailResponse;
import com.nkzly.accountapi.repository.AccountRepository;
import com.nkzly.accountapi.repository.AccountTransactionRepository;
import com.nkzly.accountapi.repository.CustomerRepository;
import com.nkzly.accountapi.utility.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AccountTransactionRepository transactionRepository;
    private final AccountResponseConverter accountResponseConverter;
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, AccountTransactionRepository transactionRepository, AccountResponseConverter accountResponseConverter) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.accountResponseConverter = accountResponseConverter;
    }

    @Override
    public String createAccount(AccountRequest request) {
        Customer customer = customerRepository.findById(request.getCustomerID()).orElseThrow(
                () -> new NotFoundException("Customer could not find by id: " + request.getCustomerID()));

        LocalDateTime createTime = LocalDateTime.now();
        Transaction transaction = null;
        changeCurrentAccount(customer);
        Account account = createAccount(request, customer, createTime);
        if (request.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
            transaction = addTransaction(request, createTime, account);
        }
        accountRepository.save(account);

        if (transaction != null) {
            transactionRepository.save(transaction);
        }

        return "success";
    }

    private Transaction addTransaction(AccountRequest request, LocalDateTime createTime, Account account) {
        Transaction transaction;
        transaction = new Transaction(
                account,
                request.getInitialCredit(),
                createTime);

        account.getTransactions().add(transaction);
        return transaction;
    }

    private Account createAccount(AccountRequest request, Customer customer, LocalDateTime createTime) {
        Account account = new Account(
                customer,
                request.getInitialCredit(),
                createTime);
        return account;
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
        Customer customer = customerRepository.findById(customerNumber).orElseThrow(
                () -> new NotFoundException("Customer could not find by id: " + customerNumber));
        return accountResponseConverter.convert(customer);
    }
}
