package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.AccountRequest;
import com.nkzly.accountapi.model.AccountResponse;

public interface AccountService {
    String createAccount(AccountRequest request);

    AccountResponse getCustomerAccountInfo(Integer customerNumber);
}
