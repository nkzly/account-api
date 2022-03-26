package com.nkzly.accountapi.controller;

import com.nkzly.accountapi.model.swaggerModel.AccountRequest;
import com.nkzly.accountapi.model.swaggerModel.AccountResponse;
import com.nkzly.accountapi.model.swaggerModel.AccountTransactionDetailResponse;
import com.nkzly.accountapi.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountRequest request){
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<AccountTransactionDetailResponse> getCustomerByCustomerNumber(@PathVariable Integer customerID){
        return ResponseEntity.ok(accountService.getCustomerAccountInfo(customerID));
    }
}
