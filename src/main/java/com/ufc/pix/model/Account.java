package com.ufc.pix.model;

import jakarta.persistence.*;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idaccounts", nullable = false)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "idusers", nullable = false)
    private User User;

    private String institution;
    private Integer accountAgency;
    private Integer accountNumber;
    private String accountType;
    private Float balance;
    private Integer fourDigitPassword;
    private Integer sixDigitPassword;

    public Account(User User, String institution, Integer accountAgency, Integer accountNumber, String accountType, Integer fourDigitPassword, Integer sixDigitPassword) {
        setUser(User);
        setInstitution(institution);
        setAccountAgency(accountAgency);
        setAccountNumber(accountNumber);
        setAccountType(accountType);
        setBalance(0.0f);
        setFourDigitPassword(fourDigitPassword);
        setSixDigitPassword(sixDigitPassword);
    }


    public String toString(){
        return "Account{" +
                ", institution='" + institution + '\'' +
                ", accountAgency=" + accountAgency +
                ", accountNumber=" + accountNumber +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}