package com.ufc.pix.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewGenerateReportsDto {
    private Integer totalTransactions;
    private List<ViewTransactionDto> transactions;
}
