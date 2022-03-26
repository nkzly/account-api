package com.nkzly.accountapi.converter;

import com.nkzly.accountapi.model.Account;
import com.nkzly.accountapi.model.Customer;
import com.nkzly.accountapi.model.Transaction;
import com.nkzly.accountapi.model.swaggerModel.AccountResponse;
import com.nkzly.accountapi.model.swaggerModel.AccountTransactionDetailResponse;
import com.nkzly.accountapi.model.swaggerModel.TransactionResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class AccountResponseConverter {

    public AccountResponse convert(Account from) {

        AccountResponse response = new AccountResponse(
                from.getId(),
                from.getBalance(),
                convertTrx(from.getTransactions()));

        return response;
    }
    public AccountTransactionDetailResponse convert(Customer from) {
        AccountTransactionDetailResponse response = new AccountTransactionDetailResponse(
                from.getId(),
                from.getName(),
                from.getSurname(),
                convert(from.getAccounts()));
        return response;
    }

    private List<AccountResponse> convert(Set<Account> accounts) {
        List<AccountResponse> result = new ArrayList<>();
        accounts.forEach(account -> {
            AccountResponse response = new AccountResponse();
            response.setAccountId(account.getId());
            response.setBalance(account.getBalance());
            response.getTransactions().addAll(convertTrx(account.getTransactions()));
            result.add(response);
        });
        return result;
    }

    private List<TransactionResponse> convertTrx(Set<Transaction> transactions) {
        List<TransactionResponse> result = new ArrayList<>();
        transactions.forEach(transaction -> {
                TransactionResponse resp = new TransactionResponse();
            resp.setTransferTime(transaction.getTransactionDate());
            resp.setAmount(transaction.getAmount());
            resp.setTransactionId(transaction.getId());
            result.add(resp);
        });
        return result;
    }
}
