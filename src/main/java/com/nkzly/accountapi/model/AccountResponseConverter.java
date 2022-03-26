package com.nkzly.accountapi.model;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AccountResponseConverter {

    public AccountResponse convert(Account from) {
        return new AccountResponse(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                from.getCustomer().getId());
    }
}
