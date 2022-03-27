package com.nkzly.accountapi.repository;

import com.nkzly.accountapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
