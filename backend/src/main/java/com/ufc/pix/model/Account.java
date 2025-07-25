package com.ufc.pix.model;

import com.ufc.pix.dto.ViewAccountDto;
import com.ufc.pix.dto.ViewAccountOnTransactionDto;
import com.ufc.pix.enumeration.AccountStatus;
import com.ufc.pix.enumeration.AccountType;
import jakarta.persistence.*;

import java.util.*;

import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer agency;
    private Integer number;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    private Double balance;
    private String password;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PixKey> pixKeys;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    private Double dailyValueLimit = 1000.00;

    public ViewAccountDto toView(){
        return new ViewAccountDto(
                getId(),
                getAgency(),
                getNumber(),
                getType(),
                getBalance(),
                getStatus(),
                getUser().toView(),
                getPixKeys() == null ? null : getPixKeys().stream().map(PixKey::toView).toList()
        );
    }

    public ViewAccountOnTransactionDto toViewInTransaction(){
        return new ViewAccountOnTransactionDto(
                getId(),
                getAgency(),
                getNumber(),
                getType(),
                getStatus(),
                getUser().getId(),
                getPixKeys() == null ? null : getPixKeys().stream().map(PixKey::toView).toList()
        );
    }
}