package com.ufc.pix.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "statements")
@Getter
@Setter
@NoArgsConstructor
public class Statement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @JoinColumn(name = "idaccounts", nullable = false)
    @Column(name = "sender_id", nullable = false)
    private UUID idAccountSender;

    @JoinColumn(name = "idaccounts", nullable = false)
    @Column(name = "receiver_id", nullable = false)
    private UUID idAccountReceiver;

    @Column(name = "value", nullable = false)
    private float value;

    @Column(name = "transactionDate", nullable = false)
    private Date transactionDate;

    public Statement(UUID id, UUID idAccountSender, UUID idAccountReceiver, float value, Date transactionDate) {
        setId(id);
        setIdAccountSender(idAccountSender);
        setIdAccountReceiver(idAccountReceiver);
        setValue(value);
        setTransactionDate(transactionDate);
    }

    @Override
    public String toString() {
        return "Statement{" +
                "id=" + id +
                ", idAccountSender=" + idAccountSender +
                ", idAccountReceiver=" + idAccountReceiver +
                ", value=" + value +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
