package com.ufc.pix.model;

import com.ufc.pix.dto.ViewTransactionDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    @CreationTimestamp
    private LocalDateTime transactionDate;

    public Transaction(Account sender, Account receiver, Double value){
        setSender(sender);
        setReceiver(receiver);
        setTransferValue(value);
        setTransactionDate(LocalDateTime.now());
    }

    public ViewTransactionDto toView(){
        return new ViewTransactionDto(
                getId(),
                getTransferValue(),
                getTransactionDate(),
                getSender().toView(),
                getReceiver().toView()
        );
    }
}
