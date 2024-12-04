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

    @JoinColumn(name = "user_id", nullable = false)
    @Column(name = "idusers", nullable = false)
    private UUID idUser;
    @Column(nullable = false)
    private String institution;
    @Column(name = "accountAgency", nullable = false)
    private Integer accountAgency;
    @Column(name = "accountNumber", nullable = false)
    private Integer accountNumber;
    @Column(name = "accountType", nullable = false)
    private String accountType;
    private Float balance;
    @Column(name= "fourDigitPassword",nullable = false)
    private Integer fourDigitPassword;
    @Column(name = "sixDigitPassword",nullable = false)
    private Integer sixDigitPassword;

    public Account(UUID User, String institution, Integer accountAgency, Integer accountNumber, String accountType, Integer fourDigitPassword, Integer sixDigitPassword) {
        setIdUser(User);
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