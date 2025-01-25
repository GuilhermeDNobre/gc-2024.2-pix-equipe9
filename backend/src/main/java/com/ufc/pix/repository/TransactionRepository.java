package com.ufc.pix.repository;

import com.ufc.pix.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Transaction findStatementById(UUID id);

    @Query("""
            select t from Transaction t
            where (:id is null or t.id = :id) 
            and ((:valueStart is null or :valueEnd is null) or t.transferValue between :valueStart and :valueEnd) 
            and ((:transactionDateStart is null or :transactionDateEnd is null) or t.transactionDate between :transactionDateStart and :transactionDateEnd) 
            and (:senderId is null or t.sender.id = :senderId) 
            and (:receiverId is null or t.receiver.id = :receiverId) 
            order by t.transactionDate
            """)
    List<Transaction> list(
            @Param("id") UUID id,
            @Param("valueStart") Double valueStart,
            @Param("valueEnd") Double valueEnd,
            @Param("transactionDateStart") LocalDateTime transactionDateStart,
            @Param("transactionDateEnd") LocalDateTime transactionDateEnd,
            @Param("senderId") UUID senderId,
            @Param("receiverId") UUID receiverId
    );


}
