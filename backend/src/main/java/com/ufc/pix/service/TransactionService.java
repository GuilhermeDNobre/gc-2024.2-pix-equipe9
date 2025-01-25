package com.ufc.pix.service;

import com.ufc.pix.dto.CreateTransactionByIdDto;
import com.ufc.pix.dto.CreateTransactionByPixDto;
import com.ufc.pix.dto.SearchTrasactionDto;
import com.ufc.pix.dto.ViewTransactionDto;
import java.util.List;

public interface TransactionService {

    void create(CreateTransactionByIdDto userDto);

    List<ViewTransactionDto> list(SearchTrasactionDto dto);

    void createByPix(CreateTransactionByPixDto dto);

}
