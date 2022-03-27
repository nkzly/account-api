package com.nkzly.accountapi.repository;

import com.nkzly.accountapi.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTransactionRepository extends JpaRepository<Transaction, String> {
}
