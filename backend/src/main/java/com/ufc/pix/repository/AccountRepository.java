package com.ufc.pix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ufc.pix.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findAccountsById(UUID id);

    @Query("select a from Account a where a.user.id = :id")
    Optional<Account> findByUserId(@Param("id") UUID id);

    Optional<Account> findByAgencyAndNumber(Integer agency, Integer number);


}