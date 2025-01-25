package com.ufc.pix.model;

import com.ufc.pix.dto.ViewAccountOnTransactionDto;
import com.ufc.pix.dto.ViewTransactionDto;
import com.ufc.pix.enumeration.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account sender;
    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiver;
    private Double transferValue;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime finishedAt;
    private LocalDate sendDate;

    public Transaction(Account sender, Account receiver, Double value, LocalDate sendDate, LocalDateTime createdAt){
        setSender(sender);
        setReceiver(receiver);
        setTransferValue(value);
        setSendDate(sendDate);
        setCreatedAt(createdAt);
    }

    public ViewTransactionDto toView(){
        return new ViewTransactionDto(
                getId(),
                getTransferValue(),
                getStatus(),
                getSendDate(),
                getFinishedAt(),
                getSender().toViewInTransaction(),
                getReceiver().toViewInTransaction()
        );
    }
}
