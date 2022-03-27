package com.nkzly.accountapi.model.swaggerModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountRequest {
    @NotNull
    private final Integer customerID;
    @Min(
            value = 0L,
            message = "initialCredit can not be negative value"
    )
    @NotNull
    private final BigDecimal initialCredit;

    public AccountRequest(Integer customerID, BigDecimal initialCredit) {
        this.customerID = customerID;
        this.initialCredit = initialCredit;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public BigDecimal getInitialCredit() {
        return initialCredit;
    }
}
