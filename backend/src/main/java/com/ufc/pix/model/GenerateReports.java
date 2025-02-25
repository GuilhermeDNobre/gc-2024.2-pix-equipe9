package com.ufc.pix.model;

import com.ufc.pix.dto.ViewGenerateReportsDto;
import com.ufc.pix.dto.ViewTransactionDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GenerateReports {
    public ViewGenerateReportsDto toView(){
        return new ViewGenerateReportsDto(
        );
    }
}
