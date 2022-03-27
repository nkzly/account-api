package com.nkzly.accountapi.service;

import com.nkzly.accountapi.model.swaggerModel.AccountRequest;
import com.nkzly.accountapi.model.swaggerModel.AccountTransactionDetailResponse;

public interface AccountService {
    String createAccount(AccountRequest request);

    AccountTransactionDetailResponse getCustomerAccountInfo(Integer customerNumber);
}
