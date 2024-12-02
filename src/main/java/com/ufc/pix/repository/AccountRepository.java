package com.ufc.pix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufc.pix.model.Account;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findByAccountAgencyAndAccountNumber(Integer accountAgency, Integer accountNumber);
}