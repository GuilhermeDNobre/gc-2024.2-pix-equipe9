package com.ufc.pix.repository;

import com.ufc.pix.enumeration.TransactionStatus;
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
            and ((:transferValueStart is null or :transferValueEnd is null) or t.transferValue between :transferValueStart and :transferValueEnd) 
            and (:status is null or t.status = :status) 
            and ((:createdAtStart is null or :createdAtEnd is null) or t.createdAt between :createdAtStart and :createdAtEnd) 
            and ((:finishedAtStart is null or :finishedAtEnd is null) or t.finishedAt between :finishedAtStart and :finishedAtEnd) 
            and ((:sendDateStart is null or :sendDateEnd is null) or t.sendDate between :sendDateStart and :sendDateEnd)
            and (:senderId is null or t.sender.id = :senderId) 
            and (:receiverId is null or t.receiver.id = :receiverId)
            order by t.createdAt""")
    List<Transaction> list(
            @Param("id")            UUID id,
            @Param("transferValueStart") Double transferValueStart,
            @Param("transferValueEnd") Double transferValueEnd,
            @Param("status")        TransactionStatus status,
            @Param("createdAtStart") LocalDateTime createdAtStart,
            @Param("createdAtEnd")  LocalDateTime createdAtEnd,
            @Param("finishedAtStart") LocalDateTime finishedAtStart,
            @Param("finishedAtEnd") LocalDateTime finishedAtEnd,
            @Param("sendDateStart") LocalDateTime sendDateStart,
            @Param("sendDateEnd")   LocalDateTime sendDateEnd,
            @Param("senderId")      UUID senderId,
            @Param("receiverId")    UUID receiverId
    );


    List<Transaction> findByStatus(TransactionStatus status);
}
