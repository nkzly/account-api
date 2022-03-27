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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {
    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private AccountTransactionRepository transactionRepository;
    private AccountResponseConverter accountResponseConverter;

    private AccountService service;

    private final Customer customer = new Customer("Nihat","Kzly");
    @BeforeEach
    public void setup() {
        accountRepository = mock(AccountRepository.class);
        customerRepository = mock(CustomerRepository.class);
        transactionRepository = mock(AccountTransactionRepository.class);
        accountResponseConverter = new AccountResponseConverter();

        service = new AccountServiceImpl(accountRepository, customerRepository, transactionRepository, accountResponseConverter);

    }
    @Test
    void createAccount() {
        AccountRequest request = new AccountRequest(2, BigDecimal.TEN);
        LocalDateTime createTime = LocalDateTime.now();
        Transaction transaction = null;
        Account account = new Account(
                customer,
                request.getInitialCredit(),
                createTime);

            transaction = new Transaction(
                    account,
                    request.getInitialCredit(),
                    createTime);

            account.getTransactions().add(transaction);

        when(customerRepository.getById(2)).thenReturn(customer);
        when(accountRepository.save(account)).thenReturn(account);
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        String result = service.createAccount(request);
        assertEquals(result, "success");
    }

    @Test
    void getCustomerAccountInfo() {
        AccountRequest request = new AccountRequest(2, BigDecimal.TEN);
        LocalDateTime createTime = LocalDateTime.now();
        Transaction transaction = null;
        Account account = new Account(
                customer,
                request.getInitialCredit(),
                createTime);

        transaction = new Transaction(
                account,
                request.getInitialCredit(),
                createTime);

        account.getTransactions().add(transaction);
        customer.getAccounts().add(account);

        account.getTransactions().add(transaction);
        when(customerRepository.getById(2)).thenReturn(customer);
        AccountTransactionDetailResponse result = service.getCustomerAccountInfo(2);
        assertEquals(result.getName(), "Nihat");
        assertEquals(result.getSurname(), "Kzly");
        assertEquals(result.getAccounts().get(0).getBalance(), BigDecimal.TEN);
        assertEquals(result.getAccounts().get(0).getTransactions().size(), 1);
    }

}