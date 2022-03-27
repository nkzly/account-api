package com.nkzly.accountapi.controller;

import com.nkzly.accountapi.model.swaggerModel.AccountRequest;
import com.nkzly.accountapi.model.swaggerModel.AccountTransactionDetailResponse;
import com.nkzly.accountapi.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;
    private final Log logger = LogFactory.getLog(getClass());
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@Valid @RequestBody AccountRequest request){
        logger.info(request.toString());
        return ResponseEntity.ok(accountService.createAccount(request));
    }

    @GetMapping("/{customerID}")
    public ResponseEntity<AccountTransactionDetailResponse> getCustomerByCustomerNumber(@PathVariable Integer customerID){
        logger.info(customerID);
        return ResponseEntity.ok(accountService.getCustomerAccountInfo(customerID));
    }
}
