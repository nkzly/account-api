package com.nkzly.accountapi.converter;

import com.nkzly.accountapi.model.Transaction;
import com.nkzly.accountapi.model.swaggerModel.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionResponseConverter {

    public TransactionResponse convert(Transaction trx) {
        TransactionResponse resp = new TransactionResponse();
        resp.setTransactionId(trx.getId());
        resp.setAmount(trx.getAmount());
        resp.setTransferTime(trx.getTransactionDate());
        return resp;
    }
}
