package com.ufc.pix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewGenerateReportsDto {
    private String userName;
    private String userCpf;
    private String userAgency;
    private String userAccount;
    private Date startDate;
    private Date endDate;
    private Float totalCredits;
    private Float totalDebits;
    private Integer totalTransactions;
    private List<ViewTransactionDto> transactions;
}