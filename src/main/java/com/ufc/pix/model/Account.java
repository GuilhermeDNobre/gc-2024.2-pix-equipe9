package com.ufc.pix.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "idaccounts", nullable = false)
    private UUID id;
    private UUID idUser;
    private String institution;
    private Integer accountAgency;
    private Integer accountNumber;
    private String accountType;
    private Float balance;
    private Integer fourDigitPassword;
    private Integer sixDigitPassword;

    public Account(UUID idUser, String institution, Integer accountAgency, Integer accountNumber, String accountType, Float balance, Integer fourDigitPassword, Integer sixDigitPassword) {
        this.idUser = idUser;
        this.institution = institution;
        this.accountAgency = accountAgency;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.fourDigitPassword = fourDigitPassword;
        this.sixDigitPassword = sixDigitPassword;
    }

    // Construtor vazio, pois estava causando erro.
    protected Account() {
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public Integer getAccountAgency() {
        return accountAgency;
    }

    public void setAccountAgency(Integer accountAgency) {
        this.accountAgency = accountAgency;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getFourDigitPassword() {
        return fourDigitPassword;
    }

    public void setFourDigitPassword(Integer fourDigitPassword) {
        this.fourDigitPassword = fourDigitPassword;
    }

    public Integer getSixDigitPassword() {
        return sixDigitPassword;
    }

    public void setSixDigitPassword(Integer sixDigitPassword) {
        this.sixDigitPassword = sixDigitPassword;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
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